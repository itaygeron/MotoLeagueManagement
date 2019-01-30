package com.motoleague.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="points_for_position")
public class PointsForPosition {

	@Id
	@NotBlank(message="Position is required")
	private Short position;
	@NotBlank(message="Points are required")
	private Short points;
	
	public PointsForPosition() {
		
	}

	public Short getPosition() {
		return position;
	}
	
	public void setPosition(Short position) {
		this.position = position;
	}
	
	public Short getPoints() {
		return points;
	}
	
	public void setPoints(Short points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "PointsForPosition [position=" + position + ", points=" + points + "]";
	}

}
