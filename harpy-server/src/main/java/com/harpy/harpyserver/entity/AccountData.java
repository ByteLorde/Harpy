package com.harpy.harpyserver.entity;

import lombok.Data;


@Data
public class AccountData {

    public String id;
    public String username;
    public String password;
    public int pin;
    public String status;
    public String taskId;
    public String[] tags;
    public Object config;
}
