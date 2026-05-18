package com.taskflow.api.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskflow.api.model.Task;
import com.taskflow.api.service.TaskService;



@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

	@Autowired
	private TaskService	taskService;

	@PostMapping
	private ResponseEntity<?>	createTask(@RequestBody Map<String, String> pyload) {
		try {
			String	title = pyload.get("title");
			UUID	userId = UUID.fromString(pyload.get("userId"));

			Task	newTask = taskService.createTask(title, userId);
			return ResponseEntity.ok(newTask);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Task>>	getTaskByUserId(@PathVariable UUID userId) {
		List<Task>	tasks = taskService.getTasksByUserId(userId);
		return ResponseEntity.ok(tasks);
	}
}
