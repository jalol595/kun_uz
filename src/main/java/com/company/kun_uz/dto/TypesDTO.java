package com.company.kun_uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypesDTO {

    private Integer id;
    private String key;
    private  String nameUz;
    private  String nameRu;
    private  String nameEn;

    private Boolean visible;

    private  String name;
}
