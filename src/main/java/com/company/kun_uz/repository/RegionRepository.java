package com.company.kun_uz.repository;

import com.company.kun_uz.entity.ProfileEntity;
import com.company.kun_uz.entity.RegionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends PagingAndSortingRepository<RegionEntity, Integer> {

    Optional<RegionEntity> findByKey(String key);

    Optional<RegionEntity> findByIdAndVisibleTrue(Integer id);

    List<RegionEntity> findAllByVisible(Boolean b);


}
