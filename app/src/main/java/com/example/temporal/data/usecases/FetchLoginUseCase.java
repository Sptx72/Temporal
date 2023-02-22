package com.example.temporal.data.usecases;

import androidx.annotation.NonNull;

import com.example.temporal.data.Callback;
import com.example.temporal.domain.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import javax.inject.Inject;

public class FetchLoginUseCase {

    private FirebaseAuth auth;
    private FirebaseFirestore _db;

    @Inject
    public FetchLoginUseCase() {
        auth = FirebaseAuth.getInstance();
        _db = FirebaseFirestore.getInstance();
    }

    public void execute(String email, String password, Callback callback) {
        User user = new User(email, password);
        _db.collection(User.COLLECTION)
                .whereEqualTo(User.EMAIL, email)
                .whereEqualTo(User.PASSWORD, password).get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                if(task.getResult().getDocuments().size() > 0) {
                                    secondCall(email, password, callback, user);
                                } else {
                                    callback.noDataFounded();
                                }
                            } else {
                                callback.onFailure(task.getException());
                            }
                        });
    }

    private void secondCall(String email, String password, Callback callback, User user) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callback.onSuccess(user);
            } else {
                callback.onFailure(task.getException());
            }
        });
    }
}
