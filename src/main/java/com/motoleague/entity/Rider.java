package com.motoleague.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="rider")
public class Rider {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long riderId;
	@NotBlank(message="First name is required")
	private String firstName;
	@NotBlank(message="Last name is required")
	private String lastName;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;
	private String phoneNo;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= CascadeType.REFRESH)
	@JoinTable(
			name="rider_category",
			joinColumns=@JoinColumn(name="rider_id"),
			inverseJoinColumns=@JoinColumn(name="category_id")
			)
	private List<Category> categories = new ArrayList<>();
	
	public Long getId() {
		return riderId;
	}
	
	public Long getRiderId() {
		return riderId;
	}
	
	public void setRiderId(Long riderId) {
		this.riderId = riderId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public List<Category> getCategories() {
		for (Category cat : categories) {
			cat.setRiders(new ArrayList<>());
		}
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addCategory(Category category) {
		categories.add(category);
	}

	@Override
	public String toString() {
		return "Rider [riderId=" + riderId + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", phoneNo=" + phoneNo + ", categories=" + categories + "]";
	}
	
}
