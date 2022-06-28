package com.company.kun_uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter

public class CommentProfilePaginationDTO {

    private Integer id;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String content;
    private Integer replaseId;

    private ProfileByCommentDTO dto;




}
