package com.example.temporal.presentation.menu_options;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.temporal.databinding.FragmentMenuBinding;
import com.example.temporal.di.AppNavigator;
import com.example.temporal.di.TemporalApplication;

import javax.inject.Inject;

public class MenuOptionsFragment extends Fragment implements MenuOptionsPresenter.MenuOptionsView {

    @Inject
    MenuOptionsPresenter presenter;
    @Inject
    AppNavigator appNavigator;

    private FragmentMenuBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(getLayoutInflater());
        attachListeners();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TemporalApplication) requireActivity().getApplication()).getAppComponent().inject(this);
        callPresenter();
    }

    private void attachListeners() {
        attachClickListener(binding.signOutButton, this::userPressExit);
    }

    private void userPressExit() {
        presenter.signOut();
    }

    private void attachClickListener(@NonNull View view, Runnable runnable) {
        view.setOnClickListener(v -> {
            runnable.run();
        });
    }

    private void callPresenter() {
        presenter.onViewCreated(this);
    }

    @Override
    public void signOut() {
        appNavigator.navigateToAuthActivity(requireActivity());
    }
}
