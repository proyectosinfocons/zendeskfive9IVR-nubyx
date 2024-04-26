package com.inference.nubyx.integration.mapper.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "env:Envelope")
@Builder
public class Envelope {

    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:env")
    private String env;

    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:ser")
    private String ser;

    @JacksonXmlProperty(localName = "env:Header")
    private String header;

    @JacksonXmlProperty(localName = "env:Body")
    private Body body;

    public static Envelope create(List<String> fields) {
        var fieldMapping1 = FieldsMapping.builder().columnNumber(1).fieldName("Lead_id").key(true).build();
        var fieldMapping2 = FieldsMapping.builder().columnNumber(2).fieldName("DNI_RUC").key(false).build();
        var fieldMapping3 = FieldsMapping.builder().columnNumber(3).fieldName("number1").key(false).build();
        var fieldMapping4 = FieldsMapping.builder().columnNumber(4).fieldName("first_name").key(false).build();
        var fieldMapping5 = FieldsMapping.builder().columnNumber(5).fieldName("last_name").key(false).build();

        List<FieldsMapping> fieldsMappingsList = List.of(fieldMapping1, fieldMapping2, fieldMapping3, fieldMapping4, fieldMapping5);

        var listUpdateSetting = ListUpdateSettings.builder()
                .callAsap(true)
                .countryCode("PE")
                .fieldsMappings(fieldsMappingsList)
                .timeToCall("0")
                .updateCRM(true)
                .build();

        var record = Record.builder()
                .fields(fields)
                .build();

        var addToListCsv = AddToListCsv.builder()
                .listName("ListaAPI3")
                .listUpdateSimpleSettings(listUpdateSetting)
                .record(record)
                .build();

        var body = Body.builder()
                .addRecordToListSimple(addToListCsv)
                .build();

        var envelop = Envelope.builder()
                .env("http://schemas.xmlsoap.org/soap/envelope/")
                .ser("http://service.admin.ws.five9.com/")
                .body(body)
                .build();

        return envelop;
    }
}
