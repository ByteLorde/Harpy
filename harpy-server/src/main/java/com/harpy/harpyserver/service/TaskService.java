package com.harpy.harpyserver.service;

import com.harpy.harpyserver.model.ScriptRequirement;
import com.harpy.harpyserver.model.TaskConfiguration;
import com.harpy.harpyserver.repository.ScriptRequirementRepository;
import com.harpy.harpyserver.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ScriptRequirementRepository scriptRequirementRepository;

    public List<TaskConfiguration> getTasks(Integer botCapacity) {

        List<TaskConfiguration> selectedTasks = new ArrayList<>();

        List<TaskConfiguration> taskConfigurations = this.taskRepository.findAll();
        taskConfigurations.sort(Comparator.comparingInt(t -> t.priority));

        while (botCapacity > 0 && !taskConfigurations.isEmpty()) {
            TaskConfiguration selectedTask = taskConfigurations.remove(0);
            selectedTasks.add(selectedTask);
            botCapacity -= selectedTask.botNames.size();
        }
        return selectedTasks;
    }

    private List<String> getScripts(List<TaskConfiguration> taskConfigurations) {
        return taskConfigurations.stream().flatMap(config -> config.botNames.stream()).collect(Collectors.toList());
    }

    public List<ScriptRequirement> getScriptRequirements(List<String> botNames) {
        return botNames.stream()
                .map(this.scriptRequirementRepository::findByName)
                .flatMap(optional -> optional.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());
    }

    public List<ScriptRequirement> getScriptRequirements(Integer capacity) {
        List<TaskConfiguration> tasks = this.getTasks(capacity);
        List<String> scripts = this.getScripts(tasks);
        return this.getScriptRequirements(scripts);
    }

}
