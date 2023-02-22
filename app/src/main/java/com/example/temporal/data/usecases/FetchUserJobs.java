package com.example.temporal.data.usecases;

import com.example.temporal.data.Callback;
import com.example.temporal.data.parsable.JobParsable;
import com.example.temporal.domain.Job;
import com.example.temporal.domain.User;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;

public class FetchUserJobs {

    private FirebaseFirestore _db;

    @Inject
    public FetchUserJobs() {
        _db = FirebaseFirestore.getInstance();
    }

    public void execute(String uid, Callback callback) {
        _db.collection(Job.COLLECTION).whereEqualTo(User.UID, uid).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().getDocuments().size() > 0) {
                            callback.onSuccess(JobParsable.parseResult(task.getResult().getDocuments()));
                        } else {
                            callback.noDataFounded();
                        }
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }
}
