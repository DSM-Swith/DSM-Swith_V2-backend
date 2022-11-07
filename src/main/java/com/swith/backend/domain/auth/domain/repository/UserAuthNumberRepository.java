package com.swith.backend.domain.auth.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.swith.backend.domain.auth.domain.UserAuthNumber;

public interface UserAuthNumberRepository extends CrudRepository<UserAuthNumber, String> {
}
