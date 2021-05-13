package com.harpy.harpyserver.service;

import com.harpy.harpyserver.entity.AccountDetails;
import com.harpy.harpyserver.model.DreambotConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DreambotCommandMapper {

    public String map(DreambotConfiguration config) {

        return "java -jar DreamBot " +
                mapAccountUsername(config) +
                mapAccountPassword(config) +
                mapProxy(config) +
                mapProxyHost(config) +
                mapProxyPort(config) +
                mapProxyUser(config) +
                mapProxyPass(config) +
                mapScript(config);
    }

    public DreambotConfiguration map(AccountDetails accountDetails) {
        DreambotConfiguration config = new DreambotConfiguration();
        config.setAccountUsername(this.mapUsername(accountDetails));
        config.setAccountPassword(this.mapPassword(accountDetails));
        config.setProxyHost(this.mapProxyHost(accountDetails));
        config.setProxyPort(this.mapProxyPort(accountDetails));
        config.setProxyUser(this.mapProxyUser(accountDetails));
        config.setProxyPass(this.mapProxyPass(accountDetails));
        config.setWorld(this.mapWorld(accountDetails));
        return config;
    }

    public String mapProxyPass(AccountDetails accountDetails) {
        return accountDetails.getProxyPass();
    }

    public String mapProxyUser(AccountDetails accountDetails) {
        return accountDetails.getProxyUser();
    }

    public String mapProxyPort(AccountDetails accountDetails) {
        return accountDetails.getProxyPort();
    }

    public String mapProxyHost(AccountDetails accountDetails) {
        return accountDetails.getProxyHost();
    }

    public String mapWorld(AccountDetails accountDetails) {
        return accountDetails.getHomeWorld();
    }

    public String mapUsername(AccountDetails accountDetails) {
        return accountDetails.getUsername();
    }

    public String mapPassword(AccountDetails accountDetails) {
        return accountDetails.getPassword();
    }

    public int mapPin(AccountDetails accountDetails) {
        return accountDetails.getPin();
    }

    private String mapAccountUsername(DreambotConfiguration config) {
        return config.getAccountUsername();
    }

    private String mapAccountPassword(DreambotConfiguration config) {
        return config.getAccountPassword();
    }

    private String mapProxy(DreambotConfiguration config) {
        return config.getProxy();
    }

    private String mapProxyHost(DreambotConfiguration config) {
        return config.getProxyHost();
    }

    private String mapProxyPort(DreambotConfiguration config) {
        return config.getProxyPort();
    }

    private String mapProxyUser(DreambotConfiguration config) {
        return config.getProxyUser();
    }

    private String mapProxyPass(DreambotConfiguration config) {
        return config.getProxyPass();
    }

    private String mapScript(DreambotConfiguration config) {
        return config.getScript();
    }
}
