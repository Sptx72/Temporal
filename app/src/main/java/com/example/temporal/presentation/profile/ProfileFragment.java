package com.example.temporal.presentation.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.temporal.R;
import com.example.temporal.databinding.FragmentProfileBinding;
import com.example.temporal.di.TemporalApplication;

public class ProfileFragment extends Fragment {

    private ProfileDataFragment profileDataFragment;
    private ProfileFormFragment profileFormFragment;

    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((TemporalApplication) requireActivity().getApplication()).getAppComponent().inject(this);
        displayProfileDataFragment();
    }

    private void displayProfileDataFragment() {
        profileDataFragment = new ProfileDataFragment();

        displayFragment(profileDataFragment);
    }

    private void displayProfileFormFragment() {
        if (profileFormFragment == null) {
            profileFormFragment = new ProfileFormFragment();
        }

        displayFragment(profileFormFragment);
    }

    private void displayFragment(Fragment fragment) {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.profile_data_container, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }
}
