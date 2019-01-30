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

import com.motoleague.entity.League;
import com.motoleague.service.LeagueService;
import com.motoleague.service.MapValidationErrorService;

@RestController
@RequestMapping("/api/league")
@CrossOrigin
public class LeagueController {
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewLeague(@RequestBody @Valid League league, BindingResult result) {
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		
		if (errorMap != null) {
			return errorMap;
		}
		
		leagueService.saveOrUpdateLeague(league);
		
		return new ResponseEntity<League>(league, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getLeagueById(@PathVariable Long id) {
		
		League league = leagueService.findLeagueById(id);
		
		return new ResponseEntity<League>(league, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<League> getAllLeagues() {
		
		return leagueService.findAllLeagues();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLeagueById(@PathVariable Long id) {
		
		League league = leagueService.findLeagueById(id);
		leagueService.deleteLeagueById(id);
		
		return new ResponseEntity<String>(
				"The league '" + league.getName() + "' has been deleted.",
				HttpStatus.OK);
	}
	
}
