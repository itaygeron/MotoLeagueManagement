package com.motoleague.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motoleague.entity.Course;
import com.motoleague.exceptions.IdDoesNotExistException;
import com.motoleague.exceptions.UniqueFieldException;
import com.motoleague.repository.CourseRepository;
import com.motoleague.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public Course saveOrUpdateCourse(Course course) {
		
		try {
			return courseRepository.save(course);
		} catch (Exception e) {
			throw new UniqueFieldException("Course name '" + course.getCourseName()
			+ "' is already taken.");
		}
	}

	@Override
	public Course findCourseById(Long id) {
		
		Course course = courseRepository.findCourseById(id);
		
		if (course == null)
			throw new IdDoesNotExistException("Course with ID " + id + " does not exists");
		
		return course;
	}

	@Override
	public Iterable<Course> findAllCourses() {

		return courseRepository.findAll();
	}

	@Override
	public void deleteCourseById(Long id) {

		Course course = courseRepository.findCourseById(id);
		
		if (course == null)
			throw new IdDoesNotExistException("Course with ID " + id + " does not exist.");
		
		courseRepository.deleteById(id);
	}

}
