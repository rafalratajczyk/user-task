package com.example.usertask.controller;

import com.example.usertask.exception.ResourceNotFoundException;
import com.example.usertask.model.Task;
import com.example.usertask.repo.TaskRepository;
import com.example.usertask.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/tasks")
    public Page<Task> getAllTasksByUserId(@PathVariable(value = "userId") Long userId,
                                          Pageable pageable) {
        return taskRepository.findByUserId(userId, pageable);
    }

    @PostMapping("/users/{userId}/tasks")
    public Task createTask(@PathVariable(value = "userId") Long userId,
                           @Valid @RequestBody Task task) {
        return userRepository.findById(userId).map(user -> {
            task.setUser(user);
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/users/{userId}/tasks/{taskId}")
    public Task updateTask(@PathVariable(value = "userId") Long userId,
                           @PathVariable(value = "taskId") Long taskId,
                           @Valid @RequestBody Task taskRequest) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }

        return taskRepository.findById(taskId).map(task -> {
            task.setName(taskRequest.getName());
            task.setDescription(taskRequest.getDescription());
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("TaskId " + taskId + "not found"));
    }

    @DeleteMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "userId") Long userId,
                                        @PathVariable(value = "taskId") Long taskId) {
        return taskRepository.findByIdAndUserId(taskId, userId).map(task -> {
            taskRepository.delete(task);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId + " and userId " + userId));
    }
}
