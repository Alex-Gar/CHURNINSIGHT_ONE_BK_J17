package com.churninsight.one.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.churninsight.one.mappers.InputModelViewMapper.toDto;
import com.churninsight.one.models.dto.prediccion.PrediccionDatosPersonalizadosDTO;
import com.churninsight.one.models.dto.prediccion.PrediccionDSResponse;
import com.churninsight.one.models.entities.historialPredicciones.HistorialPrediccion;
import com.churninsight.one.models.entities.prediccion.Prediccion;
import com.churninsight.one.models.entities.vistas.outputModelViewDto;
import com.churninsight.one.models.entities.vistas.outputModeloView;
import com.churninsight.one.models.repositories.PrediccionRepository;
import com.churninsight.one.services.HistorialPrediccionService;
import com.churninsight.one.services.InputModeloViewService;
import com.churninsight.one.services.PrediccionService;

import jakarta.transaction.Transactional;

@Service
public class PrediccionServiceImpl implements PrediccionService {

    @Autowired
    private PrediccionRepository prediccionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InputModeloViewService inputModeloViewService;

    @Autowired
    private HistorialPrediccionService historialPrediccionService;

    // URL del servicio de Data Science
    // @Value("${datascience.mock.url}")
    // private String dataScienceUrl;

    @Value("${datascience.mock.url:http://163.192.138.89:8086/api/churn/predict}")
    private String dataScienceUrl;

    @Override
    public Optional<Prediccion> buscarUsuarioPorId(String idUsuario) {
        return prediccionRepository.findActiveByIdUsuario(idUsuario);
    }

    @Override
    @Transactional
    public Prediccion evaluarPrediccion(String idUsuario) {

        outputModeloView usuario = inputModeloViewService.buscarPorId(idUsuario);
        outputModelViewDto usuarioDto = toDto(usuario);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(usuarioDto, headers);

        PrediccionDSResponse response = restTemplate.postForObject(dataScienceUrl, entity, PrediccionDSResponse.class);

        if (response == null) {
            throw new RuntimeException("Respuesta nula del servicio de predicción");
        }

        Prediccion prediccion = buscarUsuarioPorId(idUsuario)
                .orElseGet(() -> {
                    Prediccion p = new Prediccion();
                    p.setIdUsuario(idUsuario);
                    return p;
                });

        // Guardar historial ANTES de actualizar la predicción
        if (prediccion.getId() != null) {
            HistorialPrediccion historial = new HistorialPrediccion();
            historial.setChurn(prediccion.getChurn());
            historial.setPrevision(prediccion.getPrevision());
            historial.setProbabilidad(prediccion.getProbabilidad());

            prediccion.getHistoriales().add(historial);
        }

        // Actualizar con nueva predicción
        prediccion.setChurn(response.churn());
        prediccion.setPrevision(response.prevision());
        prediccion.setProbabilidad(response.probabilidad());

        return prediccionRepository.save(prediccion);
    }

    @Override
    public List<Prediccion> listarActivas() {
        return prediccionRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Long obtenerTotalEvaluados() {
        return prediccionRepository.totalEvaluados();
    }

@Override
    public Long obtenerTotalChurn() {
        return prediccionRepository.totalChurn();
    }

    @Override
    @Transactional
    public Prediccion evaluarPrediccionConDatos(PrediccionDatosPersonalizadosDTO datosPersonalizados) {
        
        // Transformar DTO al formato que espera el servicio de Data Science
        outputModelViewDto usuarioDto = new outputModelViewDto(
                datosPersonalizados.getIdCliente(),
                datosPersonalizados.getGenero(),
                datosPersonalizados.getAdultoMayor(),
                datosPersonalizados.getTienePareja(),
                datosPersonalizados.getTieneDependientes(),
                datosPersonalizados.getAntiguedadMeses() != null ? 
                    java.math.BigDecimal.valueOf(datosPersonalizados.getAntiguedadMeses()) : null,
                datosPersonalizados.getServicioTelefono(),
                datosPersonalizados.getLineasMultiples(),
                datosPersonalizados.getServicioInternet(),
                datosPersonalizados.getSeguridadEnLinea(),
                datosPersonalizados.getRespaldoEnLinea(),
                datosPersonalizados.getProteccionDispositivo(),
                datosPersonalizados.getSoporteTecnico(),
                datosPersonalizados.getStreamingTv(),
                datosPersonalizados.getStreamingPeliculas(),
                datosPersonalizados.getTipoContrato(),
                datosPersonalizados.getFacturacionElectronica(),
                datosPersonalizados.getMetodoPago(),
                datosPersonalizados.getCargoMensual(),
                datosPersonalizados.getCargosTotales()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(usuarioDto, headers);

        PrediccionDSResponse response = restTemplate.postForObject(dataScienceUrl, entity, PrediccionDSResponse.class);

        if (response == null) {
            throw new RuntimeException("Respuesta nula del servicio de predicción con datos personalizados");
        }

        // Buscar o crear predicción para el usuario
        Prediccion prediccion = buscarUsuarioPorId(datosPersonalizados.getIdCliente())
                .orElseGet(() -> {
                    Prediccion p = new Prediccion();
                    p.setIdUsuario(datosPersonalizados.getIdCliente());
                    return p;
                });

        // Guardar historial ANTES de actualizar la predicción
        if (prediccion.getId() != null) {
            HistorialPrediccion historial = new HistorialPrediccion();
            historial.setChurn(prediccion.getChurn());
            historial.setPrevision(prediccion.getPrevision());
            historial.setProbabilidad(prediccion.getProbabilidad());

            prediccion.getHistoriales().add(historial);
        }

        // Actualizar con nueva predicción
        prediccion.setChurn(response.churn());
        prediccion.setPrevision(response.prevision());
        prediccion.setProbabilidad(response.probabilidad());

        return prediccionRepository.save(prediccion);
    }
}