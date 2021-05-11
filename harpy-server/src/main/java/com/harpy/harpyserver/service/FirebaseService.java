package com.harpy.harpyserver.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.harpy.harpyserver.entity.AccountData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class FirebaseService {

    public List<AccountData> getAllAccounts() {
        List<AccountData> accounts = new ArrayList<>();
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future =
                    db.collection("accounts").get();
            List<QueryDocumentSnapshot> documents = null;
            documents = future.get().getDocuments();
            for (DocumentSnapshot document : documents) {
                AccountData accountData = document.toObject(AccountData.class);
                accounts.add(accountData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void addNewAccount(AccountData accountData) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("accounts").document(accountData.getUsername());
        ApiFuture<WriteResult> result = docRef.set(accountData);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
