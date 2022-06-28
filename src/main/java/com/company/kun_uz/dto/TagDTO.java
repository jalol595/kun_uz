package com.company.kun_uz.dto;

import com.company.kun_uz.enums.TagStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDTO {

    private Integer id;
    private String name;
    private TagStatus status;
    private LocalDateTime createdDate;

}
