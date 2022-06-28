package com.company.kun_uz.dto.article;

import com.company.kun_uz.dto.AttachDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleCreateDTO {


    @NotNull
    @Size(min = 10, max = 255, message = "About Me must be between 10 and 255 characters")
    private String title;

    @NotNull(message = "mazgi title qani")
    private String content;
    @NotBlank(message = "qani descreption")
    private String description;

    private Integer regionId;
    private Integer categoryId;

    private String imageId;

    private List<Integer> typesList;
    private List<String> tagList;

}
