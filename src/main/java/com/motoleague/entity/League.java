package com.motoleague.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="league")
public class League {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="League name is required")
	@Column(unique=true)
	private String name;
	private Short numOfRounds;
	private boolean isActive;
	
	public League() {
		this.numOfRounds = 0;
		this.isActive = true;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Short getNumOfRounds() {
		return numOfRounds;
	}
	
	public void setNumOfRounds(Short numOfRounds) {
		this.numOfRounds = numOfRounds;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "League [id=" + id + ", name=" + name + ", numOfRounds=" + numOfRounds + ", isActive=" + isActive + "]";
	}
	
}
