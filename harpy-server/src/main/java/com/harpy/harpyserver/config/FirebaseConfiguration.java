package com.harpy.harpyserver.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.InputStream;

@Configuration
@RequiredArgsConstructor
public class FirebaseConfiguration {

    private final FirebaseProperties firebaseProperties;

    @Bean
    public Firestore firestore() {
        try {
            InputStream serviceAccount = new FileInputStream(ResourceUtils.getFile(this.firebaseProperties.getServiceAccountUrl()));
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

            return FirestoreClient.getFirestore();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

