package com.company.kun_uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDto {

    private String name;
    private String surName;
    private String email;
    private String phone;

    private String jwt;

}
