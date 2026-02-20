package com.example.task.service;

import com.example.task.model.Status;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.example.task.exception.TaskNotFoundException;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(String title, String description) {
        Task task = new Task(null, title, description);
        return repository.save(task);
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task updateStatus(Long id, Status status) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.updateStatus(status);
        return repository.save(task);
    }

    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new TaskNotFoundException(id);
        }
        repository.delete(id);
    }
}