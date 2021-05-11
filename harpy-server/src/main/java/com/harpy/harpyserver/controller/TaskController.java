package com.harpy.harpyserver.controller;

import com.harpy.harpyserver.model.TaskConfiguration;
import com.harpy.harpyserver.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskConfiguration> getAllTasks() {
        return null;
    }

    @GetMapping("/new")
    public TaskConfiguration getNewTask() {
        return null;
    }

    @GetMapping("/{taskId}")
    public TaskConfiguration getTask(@PathVariable String taskId) {
        TaskConfiguration taskConfiguration = null;

        try {
            return this.taskService.getTask(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskConfiguration;
    }

}
