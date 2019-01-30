package com.motoleague.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.motoleague.entity.LeagueScore;

@RepositoryRestResource
public interface LeagueScoreRepository extends JpaRepository<LeagueScore, Long> {

	@Query("SELECT ls FROM LeagueScore ls Where rider_id = :riderId AND league_id = :leagueId")
	LeagueScore findLeagueScoreByRiderIdAndLeagueId(
			@Param("riderId") Long riderId, @Param("leagueId") Long leagueId);
	
	List<LeagueScore> findLeagueScoreByLeagueId(Long leagueId);

	LeagueScore findLeagueScoreById(Long id);
}
