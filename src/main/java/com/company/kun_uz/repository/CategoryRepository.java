package com.company.kun_uz.repository;

import com.company.kun_uz.entity.CategoryEntity;
import com.company.kun_uz.entity.RegionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

    Optional<CategoryEntity> findByKey(String key);

    Optional<CategoryEntity> findByIdAndVisibleTrue(Integer id);

    List<CategoryEntity> findAllByVisible(Boolean b);


}
