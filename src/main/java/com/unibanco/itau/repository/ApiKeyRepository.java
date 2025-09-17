package com.unibanco.itau.repository;

import com.unibanco.itau.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository <ApiKey, Long> {
    Optional<ApiKey> findByKey (String key);
}
