package com.harpy.harpyserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class FirebaseProperties {

    @Value("${google.service-account.url}")
    private String serviceAccountUrl;
}
