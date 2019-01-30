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

import com.motoleague.entity.LeagueScore;
import com.motoleague.service.LeagueScoreService;
import com.motoleague.service.MapValidationErrorService;

@RestController
@RequestMapping("/api/league-scores")
@CrossOrigin
public class LeagueScoreController {

	@Autowired
	private LeagueScoreService leagueScoreService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/{roundId}")
	public ResponseEntity<?> createNewLeagueScores(
			@PathVariable Long roundId) {
		
		leagueScoreService.saveOrUpdateLeagueScore(roundId);
		
		return new ResponseEntity<String>("League scores are updated", HttpStatus.CREATED);
	}
	
	@PostMapping("")
	public ResponseEntity<?> updateLeagueScore(
			@Valid @RequestBody LeagueScore leagueScore, 
			BindingResult result) {
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		
		if (errorMap != null) {
			return errorMap;
		}
		
		leagueScoreService.updateLeagueScore(leagueScore);
		
		return new ResponseEntity<LeagueScore>(leagueScore, HttpStatus.CREATED);
	}
	
	@GetMapping("/{leagueId}")
	public ResponseEntity<?> getLeagueScoresByLeagueId(@PathVariable Long leagueId) {
		
		List<LeagueScore> leagueScore = leagueScoreService.findLeagueScoresByLeagueId(leagueId);
		
		return new ResponseEntity<List<LeagueScore>>(leagueScore, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLeagueScoreById(@PathVariable Long id) {
		
		leagueScoreService.deleteLeagueScore(id);
		
		return new ResponseEntity<String>("LeagueScore with id " + id + " has been deleted.", HttpStatus.OK);
	}
}
