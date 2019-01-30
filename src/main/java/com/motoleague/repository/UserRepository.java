package com.motoleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.motoleague.entity.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

}
