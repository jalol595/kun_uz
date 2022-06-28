package com.company.kun_uz.dto.integration;

import com.company.kun_uz.dto.article.ArticleDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmsRequestDTO {

    private String key;
    private String phone;
    private String massage;






}
