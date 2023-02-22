package com.example.temporal.presentation.home;

import javax.inject.Inject;

public class HomeActivityPresenter {

    private HomeView homeView;

    @Inject
    public HomeActivityPresenter() {

    }

    public void onConfigurationChanged() {

    }

    public void setView(HomeView homeView){
        this.homeView = homeView;
    }

    public interface HomeView {
        void displayData();
    }
}
