package com.company.kun_uz.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

    private String massage;
    private Boolean type;

    public ResponseDTO(String massage, Boolean type) {
        this.massage = massage;
        this.type = type;
    }
}
