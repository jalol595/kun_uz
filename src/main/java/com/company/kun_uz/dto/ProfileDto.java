package com.company.kun_uz.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ProfileDto {

    private String name;
    private String surName;
    private String email;
    private String phone;

    private String jwt;

}
