package com.company.kun_uz.dto;

import com.company.kun_uz.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtDTO {

    private Integer id;
    private ProfileRole role;

}
