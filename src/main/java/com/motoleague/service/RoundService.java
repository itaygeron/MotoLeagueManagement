package com.motoleague.service;

import com.motoleague.entity.Round;

public interface RoundService {

	Round saveOrUpdateRound(Round round);
	
	Round findRoundById(Long id);
	
	Iterable<Round> findAllRounds();
	
	void deleteRoundById(Long id);
}
