package com.example.temporal.presentation.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.temporal.AppTextUtils;
import com.example.temporal.R;
import com.example.temporal.databinding.FragmentRegisterBinding;
import com.example.temporal.di.AppNavigator;
import com.example.temporal.di.TemporalApplication;

import javax.inject.Inject;

public class RegisterFragment extends Fragment implements RegisterFragmentPresenter.RegisterView {

    @Inject
    RegisterFragmentPresenter presenter;
    @Inject
    AppNavigator appNavigator;

    private FragmentRegisterBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((TemporalApplication) requireActivity().getApplication()).getAppComponent().inject(this);
        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
        attachListeners();
        callPresenter();

        return binding.getRoot();
    }

    private void attachListeners() {
        attachClickListener(binding.registerButton, this::registerClick);
    }

    private void registerClick() {
        String email = binding.registerEmail.getText().toString();
        String password = binding.registerPassword.getText().toString();
        String confirmedPassword = binding.registerConfirmPassword.getText().toString();

        if (!AppTextUtils.isAnEmail(email)) {
            binding.registerEmail.setError(getString(R.string.not_an_email));
            return;
        }

        if (!AppTextUtils.compare(password, confirmedPassword)) {
            binding.registerConfirmPassword.setError(getString(R.string.passwords_dont_match));
            return;
        }

        presenter.userWantToRegister(
                binding.registerEmail.getText().toString(), binding.registerPassword.getText().toString()
        );
    }

    private void callPresenter() {
        presenter.setView(this);
    }

    @Override
    public void registerComplete() {
        appNavigator.navigateToHomeActivity(requireActivity());
    }

    @Override
    public void registerFailed(String reason) {
        binding.registerPassword.setError(reason);
    }

    private void attachClickListener(View view, Runnable r) {
        view.setOnClickListener(v -> {
            r.run();;
        });
    }
}
