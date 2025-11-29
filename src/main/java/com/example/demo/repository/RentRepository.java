package com.example.demo.repository;

import com.example.demo.entity.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<RentEntity, Long> {

    boolean existsByBookId(Long bookId);

    boolean existsByClientId(Long rentId);
}
