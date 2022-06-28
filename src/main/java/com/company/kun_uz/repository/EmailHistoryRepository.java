package com.company.kun_uz.repository;


import com.company.kun_uz.entity.EmailHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EmailHistoryRepository extends PagingAndSortingRepository<EmailHistoryEntity, Integer> {


    Optional<EmailHistoryEntity> findByEmail(String s);

}
