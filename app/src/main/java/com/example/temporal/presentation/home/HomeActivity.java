package com.example.temporal.presentation.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.temporal.R;
import com.example.temporal.databinding.ActivityHomeBinding;
import com.example.temporal.di.TemporalApplication;
import com.example.temporal.presentation.menu_options.MenuOptionsFragment;
import com.example.temporal.presentation.profile.ProfileFragment;
import com.example.temporal.presentation.publications.PublicationsFragment;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityRetainedScoped;

@ActivityRetainedScoped
public class HomeActivity extends AppCompatActivity implements HomeActivityPresenter.HomeView {

    @Inject
    HomeActivityPresenter presenter;

    private ActivityHomeBinding binding;
    private boolean viewRotated;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TemporalApplication) getApplication()).getAppComponent().inject(this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        attachListeners();
        callPresenter();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        viewRotated = !viewRotated;
    }

    private void attachListeners() {
        attachClickListener(findViewById(R.id.action_home), this::displayPublicationsFragment);
        attachClickListener(findViewById(R.id.action_menu), this::displayMenuOptionsFragment);
        attachClickListener(findViewById(R.id.action_profile), this::displayProfileFragment);
    }

    private void displayFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.home_container, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    private void displayPublicationsFragment() {
        PublicationsFragment publicationsFragment = new PublicationsFragment();

        binding.bottomNavigation.setSelectedItemId(R.id.action_home);
        displayFragment(publicationsFragment);
    }

    private void displayMenuOptionsFragment() {
        MenuOptionsFragment menuOptionsFragment = new MenuOptionsFragment();

        binding.bottomNavigation.setSelectedItemId(R.id.action_menu);
        displayFragment(menuOptionsFragment);
    }

    private void displayProfileFragment() {
        ProfileFragment profileFragment = new ProfileFragment();

        binding.bottomNavigation.setSelectedItemId(R.id.action_profile);
        displayFragment(profileFragment);
    }

    private void callPresenter() {
        presenter.setView(this);
    }

    @Override
    public void displayData() {
        if (getSupportFragmentManager().getFragments().size() == 0) {
            displayPublicationsFragment();
        }
    }

    private void attachClickListener(View view, Runnable r) {
        view.setOnClickListener(v -> {
            r.run();
        });
    }
}
