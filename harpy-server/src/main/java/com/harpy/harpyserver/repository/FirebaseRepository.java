package com.harpy.harpyserver.repository;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.harpy.harpyserver.HarpyServerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class FirebaseRepository {

    Firestore db;

    public FirebaseRepository() {
        try {
            InputStream serviceAccount = new FileInputStream("C:\\Users\\bytel\\Workspace\\Harpy\\harpy-server\\src\\main\\resources\\json\\serviceAccount.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

            this.db = FirestoreClient.getFirestore();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public CollectionReference getCollection(String collectionName) {
        return this.db.collection(collectionName);
    }
}
