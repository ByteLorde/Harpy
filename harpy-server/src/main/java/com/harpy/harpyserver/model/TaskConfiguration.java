package com.harpy.harpyserver.model;

import lombok.Data;

import java.util.List;

@Data
public class TaskConfiguration {
    public String taskId;
    public List<DreambotConfiguration> dreambotConfigurations;
    public String hostClient;
    public String startTime;
    public String endTime;
    public List<String> tags;
    public TaskStatus status;
}
