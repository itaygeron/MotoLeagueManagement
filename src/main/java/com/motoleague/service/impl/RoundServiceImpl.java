package com.motoleague.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motoleague.entity.Round;
import com.motoleague.exceptions.IdDoesNotExistException;
import com.motoleague.exceptions.UniqueFieldException;
import com.motoleague.repository.RoundRepository;
import com.motoleague.service.RoundService;

@Service
public class RoundServiceImpl implements RoundService {
	
	@Autowired
	private RoundRepository roundRepository;
	
	@Override
	public Round saveOrUpdateRound(Round round) {

		System.out.println(round.toString());
		try {
			return roundRepository.save(round);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new UniqueFieldException("Round name '" + round.getName()
			+ "' is already taken.");
		}
	}

	@Override
	public Round findRoundById(Long id) {

		Round round = roundRepository.findRoundById(id);
		
		if (round == null)
			throw new IdDoesNotExistException("Round with ID " + id + " does not exists");
		
		return round;
	}

	@Override
	public Iterable<Round> findAllRounds() {
		
		return roundRepository.findAll();
	}

	@Override
	public void deleteRoundById(Long id) {

		Round round = roundRepository.findRoundById(id);
		
		if (round == null)
			throw new IdDoesNotExistException("Round with ID " + id + " does not exists");
		
		roundRepository.deleteById(id);
	}

}
