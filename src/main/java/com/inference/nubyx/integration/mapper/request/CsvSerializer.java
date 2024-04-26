package com.inference.nubyx.integration.mapper.request;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class CsvSerializer extends JsonSerializer<CsvData> {

    @Override
    public void serialize(CsvData record, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {

        jsonGenerator.writeStartArray();
        for(String[] row : record.getRows()){
            jsonGenerator.writeString(String.join(";", row) + System.lineSeparator());
        }
        jsonGenerator.writeEndArray();
    }
}
