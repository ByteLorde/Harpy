package com.harpy.harpyserver.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ScriptRequirement {
    public List<String> quests;
    public Map<String, Integer> stats;
}
