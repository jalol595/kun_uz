package com.company.kun_uz.dto;

import com.company.kun_uz.entity.ArticleEntity;
import com.company.kun_uz.entity.ProfileEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {

    private Integer id;

    private Integer profile_id;

    private String article_id;

    private String content;


}
