package com.churninsight.one.services.implementations;

import java.math.BigDecimal;
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

        System.out.println("===== JSON ENVIADO A IA =====");
        System.out.println(usuarioDto);
        System.out.println("============================");

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

        // Traducciones rápidas para no romper el contrato con la IA
        String generoIA = "Male".equalsIgnoreCase(datosPersonalizados.getGenero()) || "Masculino".equalsIgnoreCase(datosPersonalizados.getGenero()) ? "Male" : "Female";

        String internetIA;
        if (datosPersonalizados.getServicioInternet() == null) internetIA = "No";
        else if (datosPersonalizados.getServicioInternet().contains("Coaxial") || datosPersonalizados.getServicioInternet().contains("Fiber")) internetIA = "Fiber optic";
        else internetIA = "DSL";

        String pagoIA = "Electronic check"; // E-wallet debe mapearse a esto
        if (datosPersonalizados.getMetodoPago() != null) {
            String p = datosPersonalizados.getMetodoPago().toLowerCase();
            if (p.contains("card") || p.contains("tarjeta")) pagoIA = "Credit card (automatic)";
            else if (p.contains("bank") || p.contains("transfer")) pagoIA = "Bank transfer (automatic)";
            else if (p.contains("mailed")) pagoIA = "Mailed check";
        }

        String contratoIA = "Month-to-month"; // Valor por defecto por seguridad
        if (datosPersonalizados.getTipoContrato() != null) {
            String c = datosPersonalizados.getTipoContrato().trim();
            if (c.equalsIgnoreCase("One year") || c.equalsIgnoreCase("Un año")) {
                contratoIA = "One year";
            } else if (c.equalsIgnoreCase("Two year") || c.equalsIgnoreCase("Dos años")) {
                contratoIA = "Two year";
            } else {
                contratoIA = "Month-to-month"; // Para cualquier otro caso (Mensual, null, etc.)
            }
        }

        // Transformar DTO al formato que espera el servicio de Data Science
        outputModelViewDto usuarioDto = new outputModelViewDto(
                datosPersonalizados.getIdCliente() != null ? datosPersonalizados.getIdCliente() : "USR-TEMP",
                generoIA,
                datosPersonalizados.getAdultoMayor()!= null ? datosPersonalizados.getAdultoMayor() : 0,
                datosPersonalizados.getTienePareja() != null ? datosPersonalizados.getTienePareja() : "No",
                datosPersonalizados.getTieneDependientes()!= null ? datosPersonalizados.getTieneDependientes() : "No" ,
                datosPersonalizados.getAntiguedadMeses() != null ? BigDecimal.valueOf(datosPersonalizados.getAntiguedadMeses()) : BigDecimal.ZERO,
                datosPersonalizados.getServicioTelefono()!= null ? datosPersonalizados.getServicioTelefono() : "No" ,
                datosPersonalizados.getLineasMultiples() != null ? datosPersonalizados.getLineasMultiples() : "No",
                internetIA,
                datosPersonalizados.getSeguridadEnLinea() != null ? datosPersonalizados.getSeguridadEnLinea() : "No",
                datosPersonalizados.getRespaldoEnLinea() != null ? datosPersonalizados.getRespaldoEnLinea() : "No",
                datosPersonalizados.getProteccionDispositivo() != null ? datosPersonalizados.getProteccionDispositivo() : "No",
                datosPersonalizados.getSoporteTecnico()!= null ? datosPersonalizados.getSoporteTecnico() : "No" ,
                datosPersonalizados.getStreamingTv() != null ? datosPersonalizados.getStreamingTv() : "No",
                datosPersonalizados.getStreamingPeliculas() != null ? datosPersonalizados.getStreamingPeliculas() : "No" ,
                contratoIA,
                datosPersonalizados.getFacturacionElectronica() != null ? datosPersonalizados.getFacturacionElectronica() : "No",
                pagoIA,
                datosPersonalizados.getCargoMensual() != null ? datosPersonalizados.getCargoMensual() : BigDecimal.ZERO,
                datosPersonalizados.getCargosTotales() != null ? datosPersonalizados.getCargosTotales() : BigDecimal.ZERO
        );


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(usuarioDto, headers);

        PrediccionDSResponse response = restTemplate.postForObject(dataScienceUrl, entity, PrediccionDSResponse.class);

        if (response == null) {
            throw new RuntimeException("Respuesta nula del servicio de predicción con datos personalizados");
        }
        // 1. Validamos el ID que viene del DTO
        String idUsuario = datosPersonalizados.getIdCliente();

        // 2. Verificamos que no esté vacío para no romper la llave foránea
        if (idUsuario == null || idUsuario.isEmpty()) {
            System.out.println("⚠️ El ID llegó nulo. Contenido del DTO: " + datosPersonalizados.toString());
            throw new RuntimeException("No se pudo encontrar un ID de cliente válido en la petición.");
        }

        // Buscar o crear predicción para el usuario
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
    private outputModelViewDto toDto(outputModeloView u) {
        return new outputModelViewDto(
                u.getIdCliente(),
                u.getGenero(),
                u.getAdultoMayor(),
                boolToYesNo(u.getTienePareja()),
                boolToYesNo(u.getTieneDependientes()),
                u.getAntiguedadMeses(),
                boolToYesNo(u.getServicioTelefono()),
                boolToYesNo(u.getLineasMultiples()),
                u.getServicioInternet(),
                boolToYesNo(u.getSeguridadEnLinea()),
                boolToYesNo(u.getRespaldoEnLinea()),
                boolToYesNo(u.getProteccionDispositivo()),
                boolToYesNo(u.getSoporteTecnico()),
                boolToYesNo(u.getStreamingTv()),
                boolToYesNo(u.getStreamingPeliculas()),
                u.getTipoContrato(),
                boolToYesNo(u.getFacturacionElectronica()),
                u.getMetodoPago(),
                u.getCargoMensual(),
                u.getCargosTotales()
        );
    }

    private String boolToYesNo(Boolean value) {
        if (value == null) {
            return "No";
        }
        return value ? "Yes" : "No";
    }

}