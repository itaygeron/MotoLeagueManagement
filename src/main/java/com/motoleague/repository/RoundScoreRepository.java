package com.motoleague.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.motoleague.entity.RoundScore;

@RepositoryRestResource
public interface RoundScoreRepository extends JpaRepository<RoundScore, Long> {

	List<RoundScore> findRoundScoreByRoundIdAndCategoryIdOrderByScoreDesc(Long roundId, Long categoryId);
	
	RoundScore findRoundScoreById(Long id);
	
	List<RoundScore> findRoundScoreByRoundIdOrderByRider(Long roundId);
}
