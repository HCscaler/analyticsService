package com.knowit.AnalyticsService.kafkaCunsumer;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowit.AnalyticsService.entities.Task;
import com.knowit.AnalyticsService.service.AnalyticsService;

@Service
public class KafkaCunsumer {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	AnalyticsService analyticsService;

	@KafkaListener(topics = "getAllTask", groupId = "group2")
	public void getAllTask(String str) throws JsonMappingException, JsonProcessingException
	{
		List<Task> tasks = objectMapper.readValue(str, new TypeReference<List<Task>>() {
		});
		analyticsService.getAllTasks(tasks);
		System.out.println(tasks);
		
	}
}

