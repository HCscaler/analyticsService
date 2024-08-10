package com.knowit.AnalyticsService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.knowit.AnalyticsService.entities.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnalyticsService {
	
	List<Task> tasks = null;

	public void getAllTasks(List<Task> tasks)
	{
		 this.tasks=tasks;
	}
}
