package com.company.kun_uz.repository;

import com.company.kun_uz.entity.ArticleEntity;
import com.company.kun_uz.entity.ProfileEntity;
import com.company.kun_uz.entity.SavedArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SavedArticleRepository extends CrudRepository<SavedArticleEntity, Integer> {

        Optional<SavedArticleEntity> findByIdAndAndProfile(Integer integer, ProfileEntity entity);

        boolean existsByArticleAndProfile(ArticleEntity entity, ProfileEntity profileEntity);

        List<SavedArticleEntity> findAllByProfileId(Integer id);

}
