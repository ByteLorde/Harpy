package com.harpy.harpyserver.model;

import lombok.Data;

import java.util.List;

@Data
public class TaskConfiguration {
    public List<DreambotConfiguration> dreambotConfigurations;
    public List<String> accountNames;
    public String endTime;
    public String hostClient;
    public String startTime;
    public TaskStatus status;
    public List<String> tags;
    public String taskId;
    public String taskName;
    public String createdBy;

    public List<String> botNames;
    public Integer priority;

}
