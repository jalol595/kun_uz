package com.company.kun_uz.repository;

import com.company.kun_uz.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {


    Optional<ProfileEntity> findByPhone(String phone);
}
