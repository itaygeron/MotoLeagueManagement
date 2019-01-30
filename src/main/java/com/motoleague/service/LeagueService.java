package com.motoleague.service;

import com.motoleague.entity.League;

public interface LeagueService {

	League saveOrUpdateLeague(League league);
	
	League findLeagueById(Long id);
	
	Iterable<League> findAllLeagues();
	
	void deleteLeagueById(Long id);
}
