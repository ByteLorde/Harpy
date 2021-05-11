package com.harpy.harpyserver.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Getter
@Setter
public class AccountData {

    public String id;
    public String username;
    public String password;
    public int pin;
    public String status;
    public String taskId;
    public List<String> tags;
    public Object config;
}
