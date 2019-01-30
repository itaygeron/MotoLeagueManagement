package com.motoleague.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motoleague.entity.Rider;
import com.motoleague.exceptions.IdDoesNotExistException;
import com.motoleague.repository.RiderRepository;
import com.motoleague.service.RiderService;

@Service
public class RiderServiceImpl implements RiderService {
	
	@Autowired
	private RiderRepository riderRepository;

	@Override
	public Rider saveOrUpdateRider(Rider rider) {

		return riderRepository.save(rider);
	}

	@Override
	public Rider findRiderById(Long id) {
		
		Rider rider = riderRepository.findRiderByRiderId(id);
		
		if(rider == null)
			throw new IdDoesNotExistException("Rider with ID " + id + " does not exists");
		return rider;
	}

	@Override
	public Iterable<Rider> findAllRiders() {
		
		return riderRepository.findAll();
	}

	@Override
	public void deleteRiderById(Long id) {
		
		Rider rider = riderRepository.findRiderByRiderId(id);
		
		if (rider == null)
			throw new IdDoesNotExistException("Rider with ID " + id + " does not exist.");
		
		riderRepository.deleteById(id);
	}

}
