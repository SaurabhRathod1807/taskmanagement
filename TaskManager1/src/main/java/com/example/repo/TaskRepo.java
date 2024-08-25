package com.example.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Task;

public interface TaskRepo extends JpaRepository<Task, Long>{
	List<Task> findByStatus(String status);
    List<Task> findByPriority(String priority);
    List<Task> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Task> findByTitleContainingOrDescriptionContaining(String title, String description);
}
