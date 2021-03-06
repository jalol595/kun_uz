package com.company.kun_uz.dto;

import com.company.kun_uz.entity.ArticleEntity;
import com.company.kun_uz.entity.ProfileEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {

    private Integer id;

    @NotNull
    private Integer profile_id;
    @NotNull
    private String article_id;
    @NotBlank
    private String content;


}
