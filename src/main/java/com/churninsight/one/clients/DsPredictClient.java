package com.churninsight.one.clients;

import com.churninsight.one.models.cliente.dto.DatosDsPredict;
import com.churninsight.one.models.cliente.dto.DatosObtenerPrediccionCliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DsPredictClient {

    private final RestClient restClient;

    public DsPredictClient(@Value("${mock.service.url}") String urlServer){
        if (urlServer == null || urlServer.isEmpty()) {
            throw new IllegalStateException("mock.service.url no está definida!");
        }
        this.restClient = RestClient.builder()
                .baseUrl(urlServer)
                .build();
    }

    public DatosDsPredict obtenerPrediccion(DatosObtenerPrediccionCliente datos){;

        return restClient.post()
                .uri("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .body(datos)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new RuntimeException("Error al conectar con el servicio de DS");
                })
                .body(DatosDsPredict.class);
    }
}
