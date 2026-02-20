package com.example.task.controller;

import com.example.task.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createAndGetAll_shouldReturnCreatedAndList() {
        Task req = new Task(null, "IT", "Interview task");

        ResponseEntity<Task> res = restTemplate.postForEntity("/tasks", req, Task.class);

        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertNotNull(res.getBody());

        ResponseEntity<Task[]> all = restTemplate.getForEntity("/tasks", Task[].class);
        assertEquals(HttpStatus.OK, all.getStatusCode());
        assertTrue(all.getBody().length >= 1);
    }

    @Test
    void deleteNonExisting_shouldReturnNotFound() {
        ResponseEntity<Void> res = restTemplate.exchange("/tasks/9999", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }
}
