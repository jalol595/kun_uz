package com.company.kun_uz.repository;


import com.company.kun_uz.entity.ArticleEntity;
import com.company.kun_uz.entity.ArticleTagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleTagRepository extends CrudRepository<ArticleTagEntity, Integer> {

    List<ArticleTagEntity> findAllByArticle(ArticleEntity article);
}
