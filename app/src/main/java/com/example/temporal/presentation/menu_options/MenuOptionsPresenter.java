package com.example.temporal.presentation.menu_options;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class MenuOptionsPresenter {

    private FirebaseAuth auth;

    private MenuOptionsView view;

    @Inject
    public MenuOptionsPresenter() {
        auth = FirebaseAuth.getInstance();
    }

    public void onViewCreated(MenuOptionsView view) {
        this.view = view;
    }

    public void setView(MenuOptionsView view) {
        this.view = view;
    }

    public void signOut() {
        auth.signOut();
        view.signOut();
    }

    public interface MenuOptionsView {
        void signOut();
    }
}
