package com.motoleague.service;

import java.util.List;

import com.motoleague.entity.LeagueScore;

public interface LeagueScoreService {

	List<LeagueScore> saveOrUpdateLeagueScore(Long roundId);

	LeagueScore updateLeagueScore(LeagueScore leagueScore);
	
	List<LeagueScore> findLeagueScoresByLeagueId(Long leagueId);
	
	void deleteLeagueScore(Long id);
}
