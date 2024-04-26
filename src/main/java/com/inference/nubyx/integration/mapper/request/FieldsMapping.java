package com.inference.nubyx.integration.mapper.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FieldsMapping {

    @JacksonXmlProperty(localName = "columnNumber")
    private Integer columnNumber;

    @JacksonXmlProperty(localName = "fieldName")
    private String fieldName;

    @JacksonXmlProperty(localName = "key")
    private Boolean key;
}
