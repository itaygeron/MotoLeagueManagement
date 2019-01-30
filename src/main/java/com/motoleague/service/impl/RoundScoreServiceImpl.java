package com.motoleague.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motoleague.entity.PointsForPosition;
import com.motoleague.entity.RoundScore;
import com.motoleague.exceptions.IdDoesNotExistException;
import com.motoleague.repository.PointsForPositionRepository;
import com.motoleague.repository.RoundScoreRepository;
import com.motoleague.service.RoundScoreService;

@Service
public class RoundScoreServiceImpl implements RoundScoreService {
	
	@Autowired
	private RoundScoreRepository roundScoreRepository;
	
	@Autowired
	private PointsForPositionRepository pointsForPositionRepository;
	
	Map<Short, Short> pointsMap;
	
	@PostConstruct
	private void fillPoints() {
		List<PointsForPosition> pointsList = pointsForPositionRepository.findAll();
		pointsMap = pointsList.stream().collect(Collectors.toMap(
				PointsForPosition::getPosition, PointsForPosition::getPoints));
		System.out.println(pointsMap.toString());
	}

	@Override
	public List<RoundScore> saveOrUpdateRoundScore(List<RoundScore> roundScores) {

		for (RoundScore score : roundScores) {
			score.setScore(pointsMap.get(score.getPosition()));
			roundScoreRepository.save(score);
		}
		
		return roundScores;
	}
	
//	@Override
//	public RoundScore findRoundScoreById(Long id) {
//
//		RoundScore roundScore = roundScoreRepository.findRoundScoreById(id);
//		
//		if (roundScore == null)
//			throw new IdDoesNotExistException("Score with ID " + id + " does not exists");
//		
//		return roundScore;
//	}

	@Override
	public void deleteRoundScoreById(Long id) {
		
		RoundScore roundScore = roundScoreRepository.findRoundScoreById(id);
		
		if (roundScore == null)
			throw new IdDoesNotExistException("Score with ID " + id + " does not exists");
		
		roundScoreRepository.deleteById(id);
	}

	@Override
	public List<RoundScore> findRoundScoreByRoundIdAndCategoryId(Long roundId, Long categoryId) {
		
		return roundScoreRepository.findRoundScoreByRoundIdAndCategoryIdOrderByScoreDesc(roundId, categoryId);
	}

}
