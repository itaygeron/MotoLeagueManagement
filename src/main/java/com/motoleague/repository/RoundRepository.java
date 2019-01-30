package com.motoleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.motoleague.entity.League;
import com.motoleague.entity.Round;

@RepositoryRestResource
public interface RoundRepository extends JpaRepository<Round, Long> {

	Round findRoundById(Long id);
	
	@Query("SELECT league FROM Round r WHERE r.id = :id")
	League findLeagueById(@Param("id") Long id);
}
