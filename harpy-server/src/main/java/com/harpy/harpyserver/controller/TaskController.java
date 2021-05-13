package com.harpy.harpyserver.controller;

import com.harpy.harpyserver.entity.AccountDetails;
import com.harpy.harpyserver.model.DreambotConfiguration;
import com.harpy.harpyserver.model.ScriptRequirement;
import com.harpy.harpyserver.model.TaskConfiguration;
import com.harpy.harpyserver.repository.TaskRepository;
import com.harpy.harpyserver.service.AccountFulfillmentService;
import com.harpy.harpyserver.service.DreambotCommandMapper;
import com.harpy.harpyserver.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final AccountFulfillmentService accountFulfillmentService;
    private final DreambotCommandMapper dreambotCommandMapper;

    @GetMapping
    public List<TaskConfiguration> getAllTasks() {
        return this.taskRepository.findAll();
    }

    @GetMapping("/queue")
    public List<TaskConfiguration> getNewTasks(@RequestParam Integer capacity) {
        List<TaskConfiguration> tasks = this.taskService.getTasks(capacity);
        tasks.forEach(task -> {
            List<ScriptRequirement> requirements = taskService.getScriptRequirements(task.botNames);
            List<AccountDetails> accounts = this.accountFulfillmentService.getAccounts(requirements);
            System.out.println("Accounts chosen: " + accounts);
            task.accountNames = accounts.stream().map(AccountDetails::getUsername).collect(Collectors.toList());
            task.dreambotConfigurations = accounts.stream().map(this.dreambotCommandMapper::map).collect(Collectors.toList());
        });
        return tasks;
    }

    @GetMapping("/{taskId}")
    public TaskConfiguration getTask(@PathVariable String taskId) throws ExecutionException, InterruptedException {
        return this.taskRepository.findTask(taskId);
    }

    @PostMapping("")
    public String createTask(@RequestBody TaskConfiguration taskConfiguration) {
        return this.taskRepository.createTask(taskConfiguration);
    }

}
