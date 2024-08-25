package com.example.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Task;
import com.example.repo.TaskRepo;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepository;

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task task) {
        if (taskRepository.existsById(id)) {
            task.setId(id);
            task.setUpdatedAt(LocalDateTime.now());
            return taskRepository.save(task);
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> filterTasks(String status, String priority, LocalDateTime startDate, LocalDateTime endDate) {
        if (status != null) return taskRepository.findByStatus(status);
        if (priority != null) return taskRepository.findByPriority(priority);
        if (startDate != null && endDate != null) return taskRepository.findByDueDateBetween(startDate, endDate);
        return taskRepository.findAll();
    }

    public List<Task> searchTasks(String title, String description) {
        return taskRepository.findByTitleContainingOrDescriptionContaining(title, description);
    }
}
