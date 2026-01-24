package com.churninsight.one.infra.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BooleanTo01SerializerTest {
    
    static class TestDTO {
        @JsonSerialize(using = BooleanTo01Serializer.class)
        public Boolean predictor;
    }

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Debería convertir TRUE en 1 (Integer) para la IA")
    void deberiaConvertirTrueEnUno() throws Exception {
        TestDTO dto = new TestDTO();
        dto.predictor = true;
        String json = mapper.writeValueAsString(dto);
        assertThat(json).isEqualTo("{\"predictor\":1}");
    }

    @Test
    @DisplayName("Debería convertir FALSE en 0 (Integer) para la IA")
    void deberiaConvertirFalseEnCero() throws Exception {
        TestDTO dto = new TestDTO();
        dto.predictor = false;

        String json = mapper.writeValueAsString(dto);

        assertThat(json).isEqualTo("{\"predictor\":0}");
    }

    @Test
    @DisplayName("Debería manejar valores nulos correctamente")
    void deberiaManejarNulos() throws Exception {
        TestDTO dto = new TestDTO();
        dto.predictor = null;

        String json = mapper.writeValueAsString(dto);

        assertThat(json).isEqualTo("{\"predictor\":null}");
    }
}