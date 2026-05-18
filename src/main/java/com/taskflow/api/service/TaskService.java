package com.taskflow.api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskflow.api.model.Task;
import com.taskflow.api.model.User;
import com.taskflow.api.repository.TaskRepository;
import com.taskflow.api.repository.UserRepository;



@Service
public class TaskService {

	@Autowired
	private TaskRepository	taskRepository;

	@Autowired
	private UserRepository	userRepository;


	// Create
	public Task	createTask(String title, UUID userId) {
		User	user = userRepository.findById(userId).orElseThrow(
			() -> new RuntimeException("User not found with id: " + userId));

		Task	task = new Task();
		task.setTitle(title);
		task.setUser(user);

		return taskRepository.save(task);
	}

	//Read
	public List<Task>	getTasksByUserId(UUID userId) {
		return taskRepository.findByUserId(userId);
	}

	//Update	
	public Task	updateTaskStatus(UUID taskId, String newStatus) {
		Task	task = taskRepository.findById(taskId).orElseThrow(
			() -> new RuntimeException("Task not found with id: " + taskId)
		);
		task.setStatus(newStatus);
		return taskRepository.save(task);
	}

	//Delete
	public void	deleteTask(UUID taskId) {
		if (!taskRepository.existsById(taskId))
			throw new RuntimeException("Task not found with id: " + taskId);

		taskRepository.deleteById(taskId);
	}
}
