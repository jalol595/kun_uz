package com.company.kun_uz.repository;

import com.company.kun_uz.entity.SmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SmsRepository extends JpaRepository<SmsEntity, Integer> {


    Optional<SmsEntity> findTopByPhoneOrderByCreatedDateDesc(String phone);

}
