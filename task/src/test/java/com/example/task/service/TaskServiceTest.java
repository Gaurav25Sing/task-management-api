package com.example.task.service;

import com.example.task.exception.TaskNotFoundException;
import com.example.task.model.Status;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldSaveAndReturnTask() {
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Task created = service.create("T1", "D1");

        assertNotNull(created);
        assertEquals("T1", created.getTitle());
        verify(repository).save(any());
    }

    @Test
    void getAll_shouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(new Task(1L, "A", "B")));

        List<Task> tasks = service.getAll();

        assertFalse(tasks.isEmpty());
        verify(repository).findAll();
    }

    @Test
    void updateStatus_whenNotFound_shouldThrow() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> service.updateStatus(99L, Status.COMPLETED));
    }

    @Test
    void delete_whenNotFound_shouldThrow() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> service.delete(2L));
    }
}
