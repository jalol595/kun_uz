package com.company.kun_uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachDTO {

    private String id;

    private  String orginalName;

    private String extensional;

    private  Long size;

    private String path;

    private LocalDateTime createdDate;

    private String url;

}
