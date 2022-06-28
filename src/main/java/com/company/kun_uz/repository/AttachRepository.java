package com.company.kun_uz.repository;


import com.company.kun_uz.entity.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachRepository extends JpaRepository<AttachEntity, String> {

    boolean existsById(String id);

}
