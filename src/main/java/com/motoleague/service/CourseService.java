package com.motoleague.service;

import com.motoleague.entity.Course;

public interface CourseService {
	
	Course saveOrUpdateCourse(Course course);
	
	Course findCourseById(Long id);
	
	Iterable<Course> findAllCourses();
	
	void deleteCourseById(Long id);
}
