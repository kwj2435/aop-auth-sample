package com.uijin.study.repository;

import com.uijin.study.entity.ApiAuthGroupEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiAuthGroupEntityRepository extends JpaRepository<ApiAuthGroupEntity, Long> {
    Optional<ApiAuthGroupEntity> findByName(String groupName);
}
