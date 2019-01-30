package com.motoleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.motoleague.entity.PointsForPosition;

@RepositoryRestResource
public interface PointsForPositionRepository extends JpaRepository<PointsForPosition, Long> {

}
