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

import com.motoleague.entity.Rider;
import com.motoleague.service.MapValidationErrorService;
import com.motoleague.service.RiderService;

@RestController
@RequestMapping("/api/rider")
@CrossOrigin
public class RiderController {

	@Autowired
	private RiderService riderService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("")
	public ResponseEntity<?> createNewRider(@Valid @RequestBody Rider rider, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		
		System.out.println(result.toString());

		if (errorMap != null)
			return errorMap;

		riderService.saveOrUpdateRider(rider);

		return new ResponseEntity<Rider>(rider, HttpStatus.CREATED);

	}
	
	@GetMapping("/all")
	public Iterable<Rider> getAllRiders() {
		
		return riderService.findAllRiders();
	}
	
	@GetMapping("/{riderId}")
	public ResponseEntity<?> getRiderById(@PathVariable Long riderId) {
		
		Rider rider = riderService.findRiderById(riderId);
		
		return new ResponseEntity<Rider>(rider, HttpStatus.OK);
	}
	
	@DeleteMapping("/{riderId}")
	public ResponseEntity<?> deleteRiderById(@PathVariable Long riderId) {
		
		Rider rider = riderService.findRiderById(riderId);
		riderService.deleteRiderById(riderId);
		
		return new ResponseEntity<String>(
				"The rider " + rider.getFirstName() + " " + rider.getLastName() + 
				"was deleted.", HttpStatus.OK);
	}

}
