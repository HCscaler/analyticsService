package com.knowit.AnalyticsService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.knowit.AnalyticsService.entities.Task;
import com.knowit.AnalyticsService.entities.TaskStatus;
import com.knowit.AnalyticsService.service.AnalyticsService;

@RestController
@RequestMapping("/analitics")
public class Controller {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AnalyticsService analyticsService;
	
	@GetMapping
	public Map<Integer,Float> taskAnalitics() throws InterruptedException
	{
		String url = "http://localhost:8080/tasks";
            restTemplate.getForObject(url, Void.class);
            Thread.sleep(1000);
            List<Task> task = analyticsService.getTasks();
            Map<Integer, List<Task>> groupByProject = task.stream().collect(Collectors.groupingBy(Task :: getProjectId));
            Map<Integer,Float> progress = new HashMap<Integer, Float>();
            for(Entry<Integer, List<Task>> entry : groupByProject.entrySet())
            {
            	int projectId = entry.getKey();
            	List<Task> tasks = entry.getValue();
            	int total = tasks.size();
            	
            	int completed = (int) tasks.stream().filter(t -> TaskStatus.COMPLETED.equals(t.getStatus())).count();
            	
            	System.out.println(total+"/"+completed);
            	float progress1 = (float)((float)completed/(float)total)*100;
            	progress.put(projectId, progress1);
            }
          
           return progress;           
	}
	@GetMapping("/user")
	public Map<Integer,Float> userAnalitics() throws InterruptedException
	{
		String url = "http://localhost:8080/tasks";
        restTemplate.getForObject(url, Void.class);
        Thread.sleep(1000);
        List<Task> task = analyticsService.getTasks();
        Map<Integer, List<Task>> groupByProject = task.stream().collect(Collectors.groupingBy(Task :: getUserId));
        Map<Integer,Float> progress = new HashMap<Integer, Float>();
        for(Entry<Integer, List<Task>> entry : groupByProject.entrySet())
        {
        	int projectId = entry.getKey();
        	List<Task> tasks = entry.getValue();
        	int total = tasks.size();
        	
        	int completed = (int) tasks.stream().filter(t -> TaskStatus.COMPLETED.equals(t.getStatus())).count();
        	
        	System.out.println(total+"/"+completed);
        	float progress1 = (float)((float)completed/(float)total)*100;
        	progress.put(projectId, progress1);
        }
      
       return progress;
	}

}
