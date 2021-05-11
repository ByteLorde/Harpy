package com.harpy.harpyserver.service;
import java.util.*;

import com.google.cloud.firestore.CollectionReference;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.harpy.harpyserver.entity.AccountData;
import com.harpy.harpyserver.model.DreambotConfiguration;
import com.harpy.harpyserver.model.FirebaseCollection;
import com.harpy.harpyserver.model.TaskConfiguration;
import com.harpy.harpyserver.model.TaskStatus;
import com.harpy.harpyserver.repository.FirebaseRepository;
import com.harpy.harpyserver.util.NumberUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class TaskService {

    public final FirebaseRepository firebaseRepository;
    private CollectionReference tasksRepository;

    public TaskService(FirebaseRepository firebaseRepository) {
        this.firebaseRepository = firebaseRepository;
        this.tasksRepository = this.firebaseRepository.getCollection(FirebaseCollection.TASKS);
    }

    // TODO: Some mock stuff fam
    public void publishRandomTask() {

        String[] accountUsernames = new String[] { "DannyWalters@gmail.com", "OrangeJacket@ruby.org", "Christ@God.me" };
        String[] accountPasswords = new String[] {"forever66", "Ruby21", "21forty21"};

        String taskId = UUID.randomUUID().toString();
        TaskConfiguration task = new TaskConfiguration();

        task.setTaskId(taskId);
        task.status = TaskStatus.NOT_STARTED;
        task.tags = Arrays.asList("Bandos Bot - {AED734}", "PVM");

        List<DreambotConfiguration> dreambotConfigurations = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            DreambotConfiguration dreambotConfiguration = new DreambotConfiguration();
            dreambotConfiguration.setAccountUsername(accountUsernames[i]);
            dreambotConfiguration.setAccountPassword(accountPasswords[i]);
            dreambotConfiguration.setParams(Arrays.asList("Script Param 1", "Script Param 2", "Script Param 3"));
            dreambotConfiguration.setProxy(String.format("%d.%d.%d.%d/%d", NumberUtil.getRandomNumber(111, 255), NumberUtil.getRandomNumber(111, 255), NumberUtil.getRandomNumber(111, 255), NumberUtil.getRandomNumber(111, 255), NumberUtil.getRandomNumber(111, 255)));
            dreambotConfiguration.setScript("BandosBot");
            dreambotConfiguration.setWorld("31");

            dreambotConfigurations.add(dreambotConfiguration);
        }

        task.setDreambotConfigurations(dreambotConfigurations);
        this.tasksRepository.document(taskId).set(task);
    }

    public TaskConfiguration getTask(String taskId) throws ExecutionException, InterruptedException {
        Map<String, Object> dataRaw = this.tasksRepository.document(taskId).get().get().getData();

        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(Objects.requireNonNull(dataRaw));
        return gson.fromJson(jsonElement, TaskConfiguration.class);
    }
}
