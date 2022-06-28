package com.company.kun_uz.dto;

import com.company.kun_uz.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private ProfileRole role;
    private String password;

    private String photoId;

    private String jwt;

}
