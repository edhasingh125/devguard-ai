package com.devguard.api.repository;

import com.devguard.api.entity.RepositoryEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository extends JpaRepository<RepositoryEntity, Long> {
    Optional<RepositoryEntity> findByUrl(String url);
    boolean existsByUrl(String url);
}
