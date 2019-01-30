package com.motoleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.motoleague.entity.Rider;

@RepositoryRestResource
public interface RiderRepository extends JpaRepository<Rider, Long> {
	
	Rider findRiderByRiderId(Long id);

}
