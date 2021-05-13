package com.harpy.harpyserver.repository;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.harpy.harpyserver.model.ScriptRequirement;
import com.harpy.harpyserver.model.FirebaseCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ScriptRequirementRepository {

    private final Firestore firestore;

    private CollectionReference scriptRequirementReference;

    @PostConstruct
    public void setup() {
        this.scriptRequirementReference = firestore.collection(FirebaseCollection.SCRIPT_REQUIREMENTS);
    }

    public List<ScriptRequirement> findAll() {
        List<ScriptRequirement> scriptRequirements = new ArrayList<>();
        try {
            List<QueryDocumentSnapshot> documents;
            documents = this.scriptRequirementReference.get().get().getDocuments();

            for (DocumentSnapshot document : documents) {
                ScriptRequirement scriptRequirement = document.toObject(ScriptRequirement.class);
                scriptRequirements.add(scriptRequirement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scriptRequirements;
    }

    public Optional<ScriptRequirement> findByName(String name) {
        System.out.println("Script name: " + name);
        try {
            Map<String, Object> dataRaw = this.scriptRequirementReference.document(name).get().get().getData();
            Gson gson = new Gson();
            JsonElement jsonElement = gson.toJsonTree(Objects.requireNonNull(dataRaw));
            return Optional.of(gson.fromJson(jsonElement, ScriptRequirement.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
