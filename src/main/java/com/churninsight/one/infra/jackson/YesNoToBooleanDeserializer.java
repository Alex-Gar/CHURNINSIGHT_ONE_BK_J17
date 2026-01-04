package com.churninsight.one.infra.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Set;


public class YesNoToBooleanDeserializer extends JsonDeserializer<Boolean> {

    private static final Set<String> TRUE_VALUES = Set.of("yes", "si", "true", "1", "y", "s");
    private static final Set<String> FALSE_VALUES = Set.of("no", "false", "0", "n", "f");

    @Override
    public Boolean deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
        String texto = jp.getText();

        if (texto == null || texto.isBlank()) {
            return null;
        }
        String valor = texto.trim().toLowerCase();

        if (TRUE_VALUES.contains(valor)){
            return true;
        }
        if (FALSE_VALUES.contains(valor)){
            return false;
        }
        throw dc.weirdStringException(texto,boolean.class,"Valor no válido para boolean (yes/no/si/true/false/1/0)");
    }


}


