package ma.local.demo1.service;

import jakarta.transaction.Transactional;
import ma.local.demo1.domain.Task;
import ma.local.demo1.dto.TaskRequest;
import ma.local.demo1.dto.TaskResponse;
import ma.local.demo1.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse create(TaskRequest request) {

        Task task = new Task(
                request.getTitle(),
                request.getDescription()
        );

        Task savedTask = taskRepository.save(task);

        return TaskResponse.from(savedTask);
    }

    @Transactional
    public List<TaskResponse> findAll() {

        return taskRepository.findAll()
                .stream()
                .map(TaskResponse::from)
                .toList();
    }

    @Transactional
    public TaskResponse findById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found with id: " + id)
                );

        return TaskResponse.from(task);
    }

    public TaskResponse update(Long id, TaskRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found with id: " + id)
                );

        task.update(
                request.getTitle(),
                request.getDescription()
        );

        return TaskResponse.from(task);
    }

    public void delete(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }

        taskRepository.deleteById(id);
    }

    public TaskResponse complete(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found with id: " + id)
                );

        task.markAsCompleted();

        return TaskResponse.from(task);
    }
}
