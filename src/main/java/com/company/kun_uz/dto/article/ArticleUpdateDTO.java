package com.company.kun_uz.dto.article;

import com.company.kun_uz.dto.CategoryDTO;
import com.company.kun_uz.dto.RegionDTO;
import com.company.kun_uz.dto.TagDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleUpdateDTO {

    private String title;
    private String content;
    private String description;

    private String imageId;



}
