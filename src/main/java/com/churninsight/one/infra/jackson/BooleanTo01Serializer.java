package com.churninsight.one.infra.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BooleanTo01Serializer extends JsonSerializer<Boolean> {
    @Override
    public void serialize(Boolean data, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
        if (data == null){
            jgen.writeNull();
        }else{
            jgen.writeNumber(data ? 1 : 0);
        }
    }
}
