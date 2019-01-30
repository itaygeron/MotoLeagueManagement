package com.motoleague.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="league_score")
public class LeagueScore {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull(message="Rider is required")
	@ManyToOne(fetch=FetchType.EAGER, cascade= {CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="rider_id", nullable=false)
	private Rider rider;
	@NotNull(message="League is required")
	@ManyToOne(fetch=FetchType.EAGER, cascade= {CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="league_id", nullable=false)
	private League league;
	private Short score;
	
	public LeagueScore() {
		
	}

	public LeagueScore(@NotNull(message = "Rider is required") Rider rider,
			@NotNull(message = "League is required") League league, Short score) {
		this.rider = rider;
		this.league = league;
		this.score = score;
	}



	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Rider getRider() {
		return rider;
	}
	
	public void setRider(Rider rider) {
		this.rider = rider;
	}
	
	public League getLeague() {
		return league;
	}
	
	public void setLeague(League league) {
		this.league = league;
	}
	
	public Short getScore() {
		return score;
	}
	
	public void setScore(Short score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "LeagueScore [id=" + id + ", rider=" + rider + ", league=" + league + ", score=" + score + "]";
	}
	
}
