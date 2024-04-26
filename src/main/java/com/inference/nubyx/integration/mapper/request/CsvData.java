package com.inference.nubyx.integration.mapper.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonSerialize(using = CsvSerializer.class)
public class CsvData {

    private List<String[]> rows;

    public CsvData(List<String[]> rows) {
        this.rows = rows;
    }

}
