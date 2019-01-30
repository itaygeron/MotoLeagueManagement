package com.motoleague.web;

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

import com.motoleague.entity.Round;
import com.motoleague.service.MapValidationErrorService;
import com.motoleague.service.RoundService;

@RestController
@RequestMapping("/api/round")
@CrossOrigin
public class RoundController {
	
	@Autowired
	private RoundService roundService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("")
	public ResponseEntity<?> createNewRound(
			@Valid @RequestBody Round round,
			BindingResult result) {
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		
		if (errorMap != null)
			return errorMap;
		
		roundService.saveOrUpdateRound(round);
		
		return new ResponseEntity<Round>(round, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public Iterable<Round> getAllRounds() {
		
		return roundService.findAllRounds();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getRoundById(@PathVariable Long id) {
		
		Round round = roundService.findRoundById(id);
		
		return new ResponseEntity<Round>(round, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoundById(@PathVariable Long id) {
		
		Round round = roundService.findRoundById(id);
		roundService.deleteRoundById(id);
		
		return new ResponseEntity<String>(
				"The round '" + round.getName() + "' has been deleted.",
				HttpStatus.OK);
	}
}
