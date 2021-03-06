package com.company.kun_uz.dto;

import com.company.kun_uz.dto.article.ArticleDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavedArticleDTO {

    private Integer id;
    private String articleId;
    private Integer profileId;

    private ArticleDTO article;

}
