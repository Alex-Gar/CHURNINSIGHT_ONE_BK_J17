package com.churninsight.one.infra.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YesNoToBooleanDeserializerTest {

    static class TestDto {
        @JsonDeserialize(using = YesNoToBooleanDeserializer.class)
        private Boolean tieneServicio;

        private final ObjectMapper mapper = new ObjectMapper();

        @Test
        @DisplayName("Debería retornar TRUE para valores afirmativos variados")
        void deserializeTrue() throws Exception {
            String[] valoresTrue = {"\"yes\"", "\"si\"", "\"true\"", "\"1\"", "\"Y\""};

            for (String valor : valoresTrue) {
                String json = "{\"tieneServicio\":" + valor + "}";
                TestDto dto = mapper.readValue(json, TestDto.class);
                assertThat(dto.tieneServicio).isTrue();
            }
        }

        @Test
        @DisplayName("Debería retornar FALSE para valores negativos variados")
        void deserializeFalse() throws Exception {
            String[] valoresFalse = {"\"no\"", "\"false\"", "\"0\"", "\"n\"", "\"f\""};

            for (String valor : valoresFalse) {
                String json = "{\"tieneServicio\":" + valor + "}";
                TestDto dto = mapper.readValue(json, TestDto.class);
                assertThat(dto.tieneServicio).isFalse();
            }
        }

        @Test
        @DisplayName("Debería lanzar una excepción cuando el valor es basura o no reconocido")
        void deserializeError() {
            String jsonInvalido = "{\"tieneServicio\":\"quizás\"}";
            assertThrows(Exception.class, () -> {
                mapper.readValue(jsonInvalido, TestDto.class);
            });
        }


    }

}