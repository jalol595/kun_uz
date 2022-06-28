package com.company.kun_uz.dto.article;

import com.company.kun_uz.dto.CategoryDTO;
import com.company.kun_uz.dto.RegionDTO;
import com.company.kun_uz.dto.TagDTO;
import com.company.kun_uz.dto.TypesDTO;
import com.company.kun_uz.enums.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleCommentDTO {

    private String id;
    private String title;



}
