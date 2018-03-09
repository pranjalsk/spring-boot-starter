package com.pranjal.coursesmicroservices.topics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepositiory;
	
	public List<Topic> topics = new ArrayList<Topic>(){{add(new Topic("1", "Spring MVC", "MVC framework by spring"));
	add(new Topic("2", "Spring Security", "Security and validation by spring"));
	add(new Topic("3", "Spring Boot", "Bootstrap your spring application"));}};

	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		topicRepositiory.findAll().forEach(topics::add);
		return topics;
	}
	
	public Optional<Topic> getTopic(String id){
		//return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		return topicRepositiory.findById(id);
	}

	public void addTopic(Topic topic) {
		topicRepositiory.save(topic);
	}

	public void updateTopic(Topic topic, String id) {
		/*for(int i=0;i<topics.size();i++){
			Topic t = topics.get(i);
			if(t.getId().equals(id)){
				topics.set(i, topic);
				return;
			}
		}*/
		topicRepositiory.save(topic);
	}

	public void deleteTopic(String id) {
		//topics.removeIf(t -> t.getId().equals(id));
		topicRepositiory.deleteById(id);
	}
	
}
