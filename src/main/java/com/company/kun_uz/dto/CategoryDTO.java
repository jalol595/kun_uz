package com.company.kun_uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    private Integer id;
    private String key;
    private  String nameUz;
    private  String nameRu;
    private  String nameEn;
    private  String name;
    private Boolean visible;

    public CategoryDTO() {
    }

    public CategoryDTO(String key, String name) {
        this.key = key;
        this.name = name;
    }
}
