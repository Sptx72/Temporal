package com.example.temporal.presentation.auth;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.temporal.R;
import com.example.temporal.databinding.ActivityAuthBinding;
import com.example.temporal.di.AppNavigator;
import com.example.temporal.di.TemporalApplication;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class AuthActivity extends AppCompatActivity {

    @Inject
    AppNavigator appNavigator;

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private ActivityAuthBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ((TemporalApplication) getApplication()).getAppComponent().inject(this);

        attachListeners();
        initDefaultVariables();
        displayFragment();
    }

    private void attachListeners() {
        attachClickListener(binding.goLogin, this::displayLoginFragment);
        attachClickListener(binding.goRegister, this::displayRegisterFragment);
    }

    private void initDefaultVariables() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void displayFragment() {
        if (firebaseAuth.getCurrentUser() != null) {
            navigateToHomeActivity();
        } else {
            displayLoginFragment();
        }
    }

    private void navigateToHomeActivity() {
        appNavigator.navigateToHomeActivity(this);
    }

    private void displayLoginFragment() {
        if (loginFragment == null) {
            loginFragment = new LoginFragment();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.auth_container, loginFragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();

        binding.goLogin.setVisibility(View.GONE);
        binding.goRegister.setVisibility(View.VISIBLE);
    }

    private void displayRegisterFragment() {
        if (registerFragment == null) {
            registerFragment = new RegisterFragment();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.auth_container, registerFragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
        binding.goLogin.setVisibility(View.VISIBLE);
        binding.goRegister.setVisibility(View.GONE);
    }

    private void attachClickListener(View view, Runnable runnable) {
        view.setOnClickListener(v -> {
            runnable.run();
        });
    }

}