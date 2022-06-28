package com.company.kun_uz.repository;

import com.company.kun_uz.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagRepository extends CrudRepository<TagEntity, Integer> {

    Optional<TagEntity> findByName(String name);

    boolean existsByName(String name);
}
