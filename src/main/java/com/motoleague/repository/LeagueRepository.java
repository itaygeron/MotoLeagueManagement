package com.motoleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.motoleague.entity.League;

@RepositoryRestResource
public interface LeagueRepository extends JpaRepository<League, Long> {

	League findLeagueById(Long id);
}
