package com.motoleague.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motoleague.entity.League;
import com.motoleague.entity.LeagueScore;
import com.motoleague.entity.RoundScore;
import com.motoleague.exceptions.UniqueFieldException;
import com.motoleague.repository.LeagueScoreRepository;
import com.motoleague.repository.RoundRepository;
import com.motoleague.repository.RoundScoreRepository;
import com.motoleague.service.LeagueScoreService;

@Service
public class LeagueScoreServiceImpl implements LeagueScoreService {
	
	@Autowired
	private LeagueScoreRepository leagueScoreRepository;
	
	@Autowired
	private RoundScoreRepository roundScoreRepository;
	
	@Autowired
	private RoundRepository roundRepository;

	@Override
	public List<LeagueScore> saveOrUpdateLeagueScore(Long roundId) {

		List<RoundScore> roundScores = roundScoreRepository.findRoundScoreByRoundIdOrderByRider(roundId);
		
		Short max = 0;
		short totalRoundScore = 0;
		RoundScore prevRoundScore = null;
		League league = roundRepository.findLeagueById(roundId);
		
		for (RoundScore rScore : roundScores) {
			System.out.println(rScore.toString());
			if (prevRoundScore == null || 
					rScore.getRider().getRiderId() != prevRoundScore.getRider().getRiderId()) {
				if (prevRoundScore != null) {
					totalRoundScore += max;
					insertLeagueScoreInstance(
							prevRoundScore, 
							totalRoundScore, 
							league);
				}
				max = 0;
				totalRoundScore = 0;
			}

			if (rScore.getScore() > max) {
				totalRoundScore += Math.ceil((int) max / 10.0);
				max = rScore.getScore();
			}
			else {
				totalRoundScore += Math.ceil((int) rScore.getScore() / 10.0);
			}
			
			prevRoundScore = rScore;
		}
		
		if (prevRoundScore != null) {
			totalRoundScore += max;
			insertLeagueScoreInstance(
					prevRoundScore, 
					totalRoundScore, 
					league);
		}
		
		return null;
	}
	
	private void insertLeagueScoreInstance(RoundScore roundScore, short totalRoundScore, League league) {
		LeagueScore lScore = 
				leagueScoreRepository.findLeagueScoreByRiderIdAndLeagueId(
						roundScore.getRider().getRiderId(), league.getId());
		
		short score = totalRoundScore;
		
		if (lScore != null) {
			score += lScore.getScore();
		}
		
		LeagueScore leagueScore = new LeagueScore(roundScore.getRider(), league, score);
		
		if (lScore != null) {
			leagueScore.setId(lScore.getId());
		}
		
		System.out.println(leagueScore.toString());
		
		leagueScoreRepository.save(leagueScore);
	}

	@Override
	public LeagueScore updateLeagueScore(LeagueScore leagueScore) {

		try {
			return leagueScoreRepository.save(leagueScore);
		} catch (Exception e) {
			throw new RuntimeException("Update failed.");
		}
		
	}

	@Override
	public List<LeagueScore> findLeagueScoresByLeagueId(Long leagueId) {

		List<LeagueScore> leagueScores = leagueScoreRepository.findLeagueScoreByLeagueId(leagueId);
		
		if (leagueScores == null || leagueScores.isEmpty()) {
			throw new UniqueFieldException("League with id " + leagueId + " does not exist.");
		}
		
		return leagueScores;
	}

	@Override
	public void deleteLeagueScore(Long id) {

		LeagueScore leagueScore = leagueScoreRepository.findLeagueScoreById(id);
		
		if (leagueScore == null) {
			throw new UniqueFieldException("LeagueScore with id " + id + " does not exist.");
		}
		
		leagueScoreRepository.deleteById(id);
	}

}
