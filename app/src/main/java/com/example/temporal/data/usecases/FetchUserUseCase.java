package com.example.temporal.data.usecases;

import com.example.temporal.data.UserCallback;
import com.example.temporal.domain.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;

public class FetchUserUseCase {

    private final FirebaseFirestore _db;
    private FirebaseAuth auth;

    @Inject
    public FetchUserUseCase() {
        auth = FirebaseAuth.getInstance();
        _db = FirebaseFirestore.getInstance();
    }

    public void execute(UserCallback callback) {
        _db.collection(User.COLLECTION)
                .whereEqualTo(User.UID, auth.getUid()).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().getDocuments().size() > 0) {
                            callback.onSuccess(task.getResult().getDocuments().get(0).getData());
                        } else {
                            callback.onFailure(User.EXCEPTION);
                            auth.signOut();
                        }
                    } else {
                        callback.onFailure(task.getException());
                        auth.signOut();
                    }
                });
    }
}
