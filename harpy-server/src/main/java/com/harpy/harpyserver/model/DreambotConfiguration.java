package com.harpy.harpyserver.model;

import lombok.Data;

import java.util.List;

@Data
public class DreambotConfiguration {
    boolean covert;   // Enables Covert Mode
    boolean debug;    // Enables debug logging
    boolean destroy;  // Exit the program if Dreambot fails to load
    boolean fresh;    // Enables fresh start for client

    String account;         // Account nickname
    String accountUsername; // Account username
    String accountPassword; // Account Password

    String fps;       // Set Client's FPS
    String proxy;     // Proxy nickname
    String proxyHost; // Proxy IP
    String proxyPort; // Proxy's port
    String proxyUser; // Proxy's username
    String proxyPass; // Proxy's Password
    String script;    // Name of the script to run
    String version;   // If there are multiple versions of a script
    String userHome;  // Path to user home folder of the client
    String username;  // Dreambot username
    String password;  // Dreambot password
    String world;     // World number loaded

    List<String> breaks;  // Breaks to enable (by nickname)
    List<String> params;  // Params to pass the script running
}
