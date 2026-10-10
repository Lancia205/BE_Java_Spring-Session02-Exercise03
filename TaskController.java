
package com.example.session02.controller;

import com.example.session02.model.Task;
import com.example.session02.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(
            @RequestParam(required = false) String search) {

        List<Task> tasks = taskService.findAllTasks();

        if (search != null && !search.isBlank()) {
            tasks = tasks.stream()
                    .filter(t -> t.getTitle()
                            .toLowerCase()
                            .contains(search.toLowerCase()))
                    .toList();
        }

        return ResponseEntity.ok(tasks);
    }
}
