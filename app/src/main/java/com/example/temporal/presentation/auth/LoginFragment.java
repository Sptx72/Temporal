package com.example.temporal.presentation.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.temporal.databinding.FragmentLoginBinding;
import com.example.temporal.di.AppNavigator;
import com.example.temporal.di.TemporalApplication;

import javax.inject.Inject;

public class LoginFragment extends Fragment implements LoginPresenter.LoginView {

    @Inject
    LoginPresenter presenter;
    @Inject
    AppNavigator appNavigator;

    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((TemporalApplication) requireActivity().getApplication()).getAppComponent().inject(this);
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        attachListeners();
        callPresenter();
        return binding.getRoot();
    }

    private void callPresenter() {
        presenter.onViewCreated(this);
    }

    private void attachListeners() {
        attachClickListener(binding.loginButton, this::loginClicked);
    }

    private void loginClicked() {
        presenter.userWantToLogin(
                binding.loginEmail.getText().toString(), binding.loginPassword.getText().toString()
        );
    }

    @Override
    public void loginComplete() {
        appNavigator.navigateToHomeActivity(requireActivity());
    }

    @Override
    public void loginFailed(String reason) {
        binding.loginPassword.setError(reason);
    }

    private void attachClickListener(View view, Runnable runnable) {
        view.setOnClickListener(v -> {
            runnable.run();
        });
    }
}
