package com.pranjal.coursesmicroservices.courses;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pranjal.coursesmicroservices.topics.Topic;

@RestController
public class CourseController {

	@Autowired
	private CourseService topicService;

	@RequestMapping("/topics/{id}/courses")
	public List<Course> getAllCourses(@PathVariable String id) {
		return topicService.getAllCourses(id);
	}

	@RequestMapping("/topics/{topicid}/courses/{courseid}")
	public Optional<Course> getCourse(@PathVariable String courseid) {
		return topicService.getCourse(courseid);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics/{topicid}/courses")
	public void addCourse(@RequestBody Course course, @PathVariable String topicid) {
		course.setTopic(new Topic(topicid, "", ""));
		topicService.addCourse(course);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}/courses/{courseid}")
	public void updateCourse(@RequestBody Course course, @PathVariable String id, @PathVariable String topicid) {
		course.setTopic(new Topic(topicid, "", ""));
		topicService.updateCourse(course,id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}/courses/{courseid}")
	public void deleteCourse(@PathVariable String id) {
		topicService.deleteCourse(id);
	}

	
}
