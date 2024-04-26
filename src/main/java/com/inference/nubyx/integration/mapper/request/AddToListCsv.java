package com.inference.nubyx.integration.mapper.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddToListCsv {

    @JacksonXmlProperty(localName = "listName")
    private String listName;

    @JacksonXmlProperty(localName = "listUpdateSimpleSettings")
    private ListUpdateSettings listUpdateSimpleSettings;

    @JacksonXmlProperty(localName = "record")
    private Record record;


}
