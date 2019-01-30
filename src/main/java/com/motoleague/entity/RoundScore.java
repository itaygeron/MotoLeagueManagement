package com.motoleague.entity;

import java.sql.Time;

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
@Table(name="round_score")
public class RoundScore implements Comparable<RoundScore>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull(message="Rider is required")
	@ManyToOne(fetch=FetchType.EAGER, cascade= {CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="rider_id", nullable=false)
	private Rider rider;
	@NotNull(message="Round is required")
	@ManyToOne(fetch=FetchType.EAGER, cascade= {CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="round_id", nullable=false)
	private Round round;
	@NotNull(message="Category is required")
	@ManyToOne(fetch=FetchType.EAGER, cascade= {CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
	private Time time;
	private Short position;
	private Short score;
	private String comment;
	
	public RoundScore() {
		
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
	
	public Round getRound() {
		return round;
	}
	
	public void setRound(Round round) {
		this.round = round;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Time getTime() {
		return time;
	}
	
	public void setTime(Time time) {
		this.time = time;
	}
	
	public Short getPosition() {
		return position;
	}
	
	public void setPosition(Short position) {
		this.position = position;
	}
	
	public Short getScore() {
		return score;
	}
	
	public void setScore(Short score) {
		this.score = score;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RoundScore [id=" + id + ", rider=" + rider + ", round=" + round + ", category=" + category + ", time="
				+ time + ", position=" + position + ", score=" + score + ", comment=" + comment + "]";
	}

	@Override
	public int compareTo(RoundScore rs) {
		if (this.getRider() == null || rs.getRider() == null) {
		      return 0;
	    }
		
		return this.getRider().getRiderId().compareTo(rs.getRider().getRiderId());
	}
	
}
