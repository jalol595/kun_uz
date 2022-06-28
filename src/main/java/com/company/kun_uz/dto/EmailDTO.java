package com.company.kun_uz.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmailDTO {

    private Integer id;
    private String email;
    private LocalDateTime dateTime;


}
