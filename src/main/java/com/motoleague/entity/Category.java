package com.motoleague.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="Category name is required")
	@Column(unique=true)
	private String categoryName;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= CascadeType.REFRESH)
	@JoinTable(
			name="rider_category",
			joinColumns=@JoinColumn(name="category_id"),
			inverseJoinColumns=@JoinColumn(name="rider_id")
			)
	private List<Rider> riders = new ArrayList<>();
	
	public Category() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String name) {
		this.categoryName = name;
	}
	
	public List<Rider> getRiders() {
		for (Rider rider : riders) {
			rider.setCategories(new ArrayList<>());
		}
		return riders;
	}
	
	public void addRider(Rider rider) {
		riders.add(rider);
	}

	public void setRiders(List<Rider> riders) {
		this.riders = riders;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", riders=" + getRiders() + "]";
	}
	
}
