package ma.local.demo1.controller;

import jakarta.validation.Valid;
import ma.local.demo1.dto.TaskRequest;
import ma.local.demo1.dto.TaskResponse;
import ma.local.demo1.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(
            @Valid @RequestBody TaskRequest request
    ) {
        return taskService.create(request);
    }

    @GetMapping
    public List<TaskResponse> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskResponse findById(
            @PathVariable Long id
    ) {
        return taskService.findById(id);
    }

    @PutMapping("/{id}")
    public TaskResponse update(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request
    ) {
        return taskService.update(id, request);
    }

    @PatchMapping("/{id}/complete")
    public TaskResponse complete(
            @PathVariable Long id
    ) {
        return taskService.complete(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ) {
        taskService.delete(id);
    }
}