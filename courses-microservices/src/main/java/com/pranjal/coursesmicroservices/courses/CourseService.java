package com.pranjal.coursesmicroservices.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepositiory;
	
	
	public List<Course> getAllCourses(String topicid) {
		List<Course> courses = new ArrayList<>();
		courseRepositiory.findByTopicId(topicid).forEach(courses::add);
		return courses;
	}
	
	public Optional<Course> getCourse(String id){
		//return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		return courseRepositiory.findById(id);
	}

	public void addCourse(Course course) {
		courseRepositiory.save(course);
	}

	public void updateCourse(Course course, String id) {
		/*for(int i=0;i<topics.size();i++){
			Topic t = topics.get(i);
			if(t.getId().equals(id)){
				topics.set(i, topic);
				return;
			}
		}*/
		courseRepositiory.save(course);
	}

	public void deleteCourse(String id) {
		//topics.removeIf(t -> t.getId().equals(id));
		courseRepositiory.deleteById(id);
	}
	
}
