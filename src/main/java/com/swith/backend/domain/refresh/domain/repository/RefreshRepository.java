package com.swith.backend.domain.refresh.domain.repository;

import com.swith.backend.domain.refresh.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshRepository extends CrudRepository<RefreshToken, String> {
}
