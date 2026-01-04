package com.churninsight.one.infra.jackson;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class BooleanToYesNoSerializer extends JsonSerializer<Boolean> {

    @Override
    public void serialize(Boolean value, JsonGenerator jgen, SerializerProvider serializer) throws IOException {

        if (value == null) {
            jgen.writeNull();
            return;
        }
        jgen.writeString(value ? "Yes" : "No");

    }

}
