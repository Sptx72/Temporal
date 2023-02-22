package com.example.temporal.presentation.profile;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.temporal.databinding.FragmentProfileDataBinding;
import com.example.temporal.di.TemporalApplication;
import com.example.temporal.domain.Job;

import java.util.List;

import javax.inject.Inject;

public class ProfileDataFragment extends Fragment implements ProfileDataFragmentPresenter.ProfileDataView {

    @Inject
    ProfileDataFragmentPresenter presenter;

    private FragmentProfileDataBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileDataBinding.inflate(getLayoutInflater());
        callPresenter();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TemporalApplication) requireActivity().getApplication()).getAppComponent().inject(this);
    }

    private void callPresenter() {
        presenter.init(this);
        presenter.onViewCreated();
    }

    @Override
    public void displayData(String name, String lastName, String email, String phone) {
        binding.dataLayout.setVisibility(View.VISIBLE);
        binding.nameText.setText(name.concat(lastName));
        binding.emailText.setText(email);
        binding.phoneText.setText(phone);
    }

    @Override
    public void displayJobs(List<Job> jobs) {
        binding.userJobsRecycler.setVisibility(View.VISIBLE);
        JobRowAdapter adapter = new JobRowAdapter(jobs);
        binding.userJobsRecycler.setAdapter(adapter);
    }

    @Override
    public void noJobsFounded() {
        binding.userJobsRecycler.setVisibility(View.GONE);
        binding.noJobsFounded.setVisibility(View.VISIBLE);
    }
}
