package com.motoleague.service;

import java.util.List;

import com.motoleague.entity.RoundScore;

public interface RoundScoreService {

	List<RoundScore> saveOrUpdateRoundScore(List<RoundScore> roundScores);
	
	List<RoundScore> findRoundScoreByRoundIdAndCategoryId(Long roundId, Long categoryId);
	
	void deleteRoundScoreById(Long id);
}
