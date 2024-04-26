package com.inference.nubyx.integration.mapper.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Body {

    @JacksonXmlProperty(localName = "ser:addRecordToListSimple")
    private AddToListCsv addRecordToListSimple;
}
