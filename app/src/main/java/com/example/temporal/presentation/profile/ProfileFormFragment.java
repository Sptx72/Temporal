package com.example.temporal.presentation.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.temporal.databinding.FragmentProfileFormBinding;
import com.example.temporal.di.TemporalApplication;

public class ProfileFormFragment extends Fragment {

    private FragmentProfileFormBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileFormBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TemporalApplication) requireActivity().getApplication()).getAppComponent().inject(this);
    }
}
