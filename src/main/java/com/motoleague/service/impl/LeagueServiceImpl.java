package com.motoleague.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motoleague.entity.League;
import com.motoleague.exceptions.IdDoesNotExistException;
import com.motoleague.exceptions.UniqueFieldException;
import com.motoleague.repository.LeagueRepository;
import com.motoleague.service.LeagueService;

@Service
public class LeagueServiceImpl implements LeagueService {
	
	@Autowired
	private LeagueRepository leagueRepository;

	@Override
	public League saveOrUpdateLeague(League league) {
		
		try {
			return leagueRepository.save(league);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new UniqueFieldException("League name '" + league.getName()
			+ "' is already taken.");
		}
	}

	@Override
	public League findLeagueById(Long id) {

		League league = leagueRepository.findLeagueById(id);
		
		if (league == null) {
			throw new IdDoesNotExistException("League with ID " + id + " does not exists");
		}
		
		return league;
	}

	@Override
	public Iterable<League> findAllLeagues() {

		return leagueRepository.findAll();
	}

	@Override
	public void deleteLeagueById(Long id) {
		
		League league = leagueRepository.findLeagueById(id);
		
		if (league == null) {
			throw new IdDoesNotExistException("League with ID " + id + " does not exists");
		}
		
		leagueRepository.deleteById(id);
	}

}
