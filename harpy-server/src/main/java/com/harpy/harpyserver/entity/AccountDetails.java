package com.harpy.harpyserver.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Data
public class AccountDetails {

    public int pin;
    public String username;
    public String password;
    public String status;
    public String taskId;
    public String proxyHost;
    public String proxyPort;
    public String proxyUser;
    public String proxyPass;
    public String created;
    public String lastActive;
    public String homeWorld;
    public List<String> tags;
    public List<String> completedQuests = new ArrayList<>();
    public Map<String, Integer> lastRecordedStats;
    public Map<String, Object> config;

}
