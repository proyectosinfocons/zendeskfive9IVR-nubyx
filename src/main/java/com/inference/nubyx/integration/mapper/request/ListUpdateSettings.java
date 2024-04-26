package com.inference.nubyx.integration.mapper.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ListUpdateSettings {

    @JacksonXmlProperty(localName = "callAsap")
    private Boolean callAsap;

    @JacksonXmlProperty(localName = "countryCode")
    private String countryCode;

    @JacksonXmlProperty(localName = "fieldsMapping")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<FieldsMapping> fieldsMappings;

    @JacksonXmlProperty(localName = "timeToCall")
    private String timeToCall;

    @JacksonXmlProperty(localName = "updateCRM")
    private Boolean updateCRM;

}
