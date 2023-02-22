package com.example.temporal.presentation.auth;

import com.example.temporal.domain.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class RegisterFragmentPresenter {

    private RegisterView view;
    private FirebaseAuth auth;
    private FirebaseFirestore _db;

    @Inject
    public RegisterFragmentPresenter() {
        auth = FirebaseAuth.getInstance();
        _db = FirebaseFirestore.getInstance();
    }

    public void setView(RegisterView registerView) {
        this.view = registerView;
    }

    public void userWantToRegister(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        registerInDatabase(email, password);
                    } else {
                        if (task.getException() != null) {
                            view.registerFailed(task.getException().getLocalizedMessage());
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    view.registerFailed(e.getLocalizedMessage());
                });
    }

    private void registerInDatabase(String email, String password) {
        Map<String, Object> user = new HashMap<>();
        user.put(User.EMAIL, email);
        user.put(User.PASSWORD, password);

        _db.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    view.registerComplete();
                })
                .addOnFailureListener(e -> {
                    view.registerFailed(e.getLocalizedMessage());
                });
    }

    public interface RegisterView {
        void registerComplete();
        void registerFailed(String reason);
    }
}
