package com.motoleague.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoleague.entity.Course;
import com.motoleague.service.CourseService;
import com.motoleague.service.MapValidationErrorService;

@RestController
@RequestMapping("/api/course")
@CrossOrigin
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewCourse(
			@Valid @RequestBody Course course, 
			BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		
		if (errorMap != null)
			return errorMap;
		
		courseService.saveOrUpdateCourse(course);
		
		return new ResponseEntity<Course>(course, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public Iterable<Course> getAllCourses() {
		
		return courseService.findAllCourses();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCourseById(@PathVariable Long id) {
		
		Course course = courseService.findCourseById(id);
		
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourseById(@PathVariable Long id) {
		
		Course course = courseService.findCourseById(id);	
		courseService.deleteCourseById(id);
		
		return new ResponseEntity<String>(
				"The course '" + course.getCourseName() + "' has been deleted.",
				HttpStatus.OK);
	}

}
