package com.motoleague.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoleague.entity.RoundScore;
import com.motoleague.service.MapValidationErrorService;
import com.motoleague.service.RoundScoreService;

@RestController
@RequestMapping("/api/round-scores")
@CrossOrigin
public class RoundScoreController {

	@Autowired
	private RoundScoreService roundScoreService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewRoundScores(
			@Valid 
			@RequestBody List<RoundScore> scores, 
			BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		
		if (errorMap != null)
			return errorMap;
		
		roundScoreService.saveOrUpdateRoundScore(scores);
		
		return new ResponseEntity<List<RoundScore>>(scores, HttpStatus.CREATED);
	}
	
	@GetMapping("/{roundId}/{categoryId}")
	public List<RoundScore> getRoundScoreByRoundIdAndCategoryId(
			@PathVariable Long roundId,
			@PathVariable Long categoryId) {
		
		return roundScoreService.findRoundScoreByRoundIdAndCategoryId(roundId, categoryId);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoundScoreById(@PathVariable Long id) {
		
		roundScoreService.deleteRoundScoreById(id);
		
		return new ResponseEntity<String>(
				"The score has been deleted", HttpStatus.OK);
	}
	
}
