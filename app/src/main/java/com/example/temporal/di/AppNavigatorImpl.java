package com.example.temporal.di;

import android.app.Activity;
import android.content.Intent;

import com.example.temporal.presentation.auth.AuthActivity;
import com.example.temporal.presentation.home.HomeActivity;

public class AppNavigatorImpl implements AppNavigator {

    public AppNavigatorImpl() {

    }


    @Override
    public void navigateToHomeActivity(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void navigateToAuthActivity(Activity activity) {
        Intent intent = new Intent(activity, AuthActivity.class);
        activity.startActivity(intent);
    }
}
