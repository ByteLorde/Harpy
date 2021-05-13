package com.harpy.harpyserver.repository;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.harpy.harpyserver.model.FirebaseCollection;
import com.harpy.harpyserver.model.TaskConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Repository
@RequiredArgsConstructor
public class TaskRepository {
    private final Firestore firestore;

    private CollectionReference taskReference;

    @PostConstruct
    public void setup() {
        this.taskReference = firestore.collection(FirebaseCollection.TASKS);
    }

    public List<TaskConfiguration> findAll() {
        try {
            return this.taskReference.get().get().toObjects(TaskConfiguration.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public String createTask(TaskConfiguration taskConfiguration) {

        String generatedTaskId = UUID.randomUUID().toString();
        taskConfiguration.setTaskId(generatedTaskId);
        this.taskReference.document().set(taskConfiguration);
        return generatedTaskId; // TODO: Investigate return of .document() and figure out a way to return the object created from DB. (Not a reference the the one passed in).
    }

    public TaskConfiguration findTask(String taskId) throws ExecutionException, InterruptedException {
        Map<String, Object> dataRaw = this.taskReference.document(taskId).get().get().getData();

        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(Objects.requireNonNull(dataRaw));

        return gson.fromJson(jsonElement, TaskConfiguration.class);
    }

}
