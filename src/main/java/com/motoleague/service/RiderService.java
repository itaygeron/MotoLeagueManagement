package com.motoleague.service;

import com.motoleague.entity.Rider;

public interface RiderService {

	Rider saveOrUpdateRider(Rider rider);
	
	Rider findRiderById(Long id);
	
	Iterable<Rider> findAllRiders();
	
	void deleteRiderById(Long id);
}
