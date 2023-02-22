package com.example.temporal.presentation.auth;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.temporal.DialogManager;
import com.example.temporal.data.Callback;
import com.example.temporal.data.usecases.FetchLoginUseCase;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class LoginPresenter {

    private FetchLoginUseCase fetchLoginUseCase;

    private LoginView view;
    private DialogManager dialogManager;

    @Inject
    public LoginPresenter(FetchLoginUseCase fetchLoginUseCase) {
        this.fetchLoginUseCase = fetchLoginUseCase;
    }

    public void onViewCreated(LoginView view) {
        this.view = view;
        dialogManager = new DialogManager(view.requireActivity());
    }

    public void setView(LoginView loginView) {
        this.view = loginView;
    }

    public void userWantToLogin(String email, String password) {
        dialogManager.showLoadingView();
        fetchLoginUseCase.execute(email, password, new Callback() {
            @Override
            public void noDataFounded() {
                view.loginFailed("Los datos introducidos no son correctos");
            }

            @Override
            public void onFailure(@NonNull Exception e) {
                dialogManager.hideLoadingView();
                view.loginFailed(e.getMessage());
            }

            @Override
            public void onSuccess(Object o) {
                dialogManager.hideLoadingView();
                view.loginComplete();
            }
        });
    }

    public interface LoginView {
        void loginComplete();
        void loginFailed(String reason);
        Activity requireActivity();
    }
}
