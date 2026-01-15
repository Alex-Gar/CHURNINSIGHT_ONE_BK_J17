package com.churninsight.one.services.implementations;

import com.churninsight.one.models.dto.prediccion.PrediccionDSResponse;
import com.churninsight.one.models.entities.prediccion.Prediccion;
import com.churninsight.one.models.repositories.PrediccionRepository;
import com.churninsight.one.services.PrediccionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrediccionServiceImpl implements PrediccionService {

    private final PrediccionRepository prediccionRepository;
    private final RestTemplate restTemplate;

    //URL del servicio de Data Science
    @Value("${datascience.mock.url}")
    private String dataScienceUrl;


    public PrediccionServiceImpl(PrediccionRepository prediccionRepository, RestTemplate restTemplate) {
        this.prediccionRepository = prediccionRepository;
        this.restTemplate = restTemplate;
    }
    @Override
    public Prediccion evaluarPrediccion(String idUsuario, Object requestDS){

        //1. Consumir API de Data Science (mock)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(requestDS, headers);

        PrediccionDSResponse response = restTemplate.postForObject(
                dataScienceUrl,
                entity,
                PrediccionDSResponse.class
        );
        if(response == null){
            throw new RuntimeException("Respuesta nula del servicio de predicción");
        }

        //Soft delete de predicciones previas del usuario
        eliminarLogicoPorUsuario(idUsuario);

        //3. Crear nueva predicción
        Prediccion prediccion = new Prediccion();
        prediccion.setIdUsuario(idUsuario);
        prediccion.setChurn(response.churn());
        prediccion.setPrevision(response.prevision());
        prediccion.setProbabilidad(response.probabilidad());
        prediccion.setCreatedAt(LocalDateTime.now());
        prediccion.setUpdatedAt(LocalDateTime.now());
        prediccion.setDeletedAt(null);

        // Guardar en BD
        return prediccionRepository.save(prediccion);

    }

    //2. Eliminar lógico (Soft Delete)
    @Override
    public void eliminarLogicoPorUsuario(String idUsuario) {
        List<Prediccion> activas=
                prediccionRepository.findByIdUsuarioAndDeletedAtIsNull(idUsuario);

        for(Prediccion p : activas){
            p.setDeletedAt(LocalDateTime.now());
            p.setUpdatedAt(LocalDateTime.now());
        }
        prediccionRepository.saveAll(activas);
    }
    //3. Listar todas las activas en el sistema
    @Override
    public List<Prediccion> listarActivas() {
        return prediccionRepository.findAllByDeletedAtIsNull();
    }

    //4. Métodos para Estadísticas usando los @Query del Repository
    @Override
    public Long obtenerTotalEvaluados(){
        return prediccionRepository.totalEvaluados();
    }

    @Override
    public Long obtenerTotalChurn(){
        return prediccionRepository.totalChurn();
    }

    @Override
    public List<Prediccion> listarPorUsuario(String idUsuario) {
        return prediccionRepository.findByIdUsuarioAndDeletedAtIsNull(idUsuario);
    }

}

