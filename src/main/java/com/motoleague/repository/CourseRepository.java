package com.motoleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.motoleague.entity.Course;

@RepositoryRestResource
public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findCourseById(Long id);
}
