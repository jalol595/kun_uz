package com.company.kun_uz.dto;

import com.company.kun_uz.enums.CommentLikeStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommetLikeDTO {

    private CommentLikeStatus commetLikeStatus;
    private Integer commentId;


}
