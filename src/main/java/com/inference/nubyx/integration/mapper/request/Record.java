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
public class Record {

    @JacksonXmlProperty(localName = "fields")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> fields;
}
