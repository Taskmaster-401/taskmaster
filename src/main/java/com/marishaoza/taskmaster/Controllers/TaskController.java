package com.marishaoza.taskmaster.Controllers;


import com.marishaoza.taskmaster.Models.HistoryObj;
import com.marishaoza.taskmaster.Models.Task;
import com.marishaoza.taskmaster.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return (List) taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task addNewUser (@RequestBody Task task) {
        Task newTask = new Task(task.getId(), task.getTitle(), task.getDescription(), "Available", null);
        newTask.addHistory(new HistoryObj("Task created"));
        taskRepository.save(newTask);
        return newTask;
    }

    @PutMapping("/tasks/{id}/state")
    public Task updateTaskStatus (@PathVariable String id) {
        Task taskToUpdate = taskRepository.findById(id).get();
        if (taskToUpdate.getStatus().equals("Available")) {
            taskToUpdate.setStatus("Assigned");
        } else if (taskToUpdate.getStatus().equals("Assigned")) {
            taskToUpdate.setStatus("Accepted");
        } else if (taskToUpdate.getStatus().equals("Accepted")) {
            taskToUpdate.setStatus("Finished");
        } else if (taskToUpdate.getStatus().equals("Finished")) {
            return taskToUpdate;
        }
        taskToUpdate.addHistory(new HistoryObj("--> " + taskToUpdate.getStatus()));
        taskRepository.save(taskToUpdate);
        return taskToUpdate;
    }

    @GetMapping("/users/{name}/tasks")
    public List<Task> getTasksByAssignee(@PathVariable String name) {
        return (List) taskRepository.findAllByAssignee(name).get();
    }

    @PutMapping("/tasks/{id}/assign/{assignee}")
    public Task updateTaskAssignee(@PathVariable String id, @PathVariable String assignee) {
        Task taskToUpdate = taskRepository.findById(id).get();
        taskToUpdate.setAssignee(assignee);
        taskToUpdate.setStatus("Assigned");
        taskToUpdate.addHistory(new HistoryObj("--> Assigned to " + assignee));
        taskRepository.save(taskToUpdate);
        return taskToUpdate;
    }
}
