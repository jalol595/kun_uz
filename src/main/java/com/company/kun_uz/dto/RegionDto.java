package com.company.kun_uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDto {

    private String key;
    private  String nameUz;
    private  String nameRu;
    private  String nameEn;

}
