package com.example.temporal.presentation.publications;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.temporal.databinding.FragmentHomeBinding;
import com.example.temporal.databinding.SearchHeaderBinding;
import com.example.temporal.di.TemporalApplication;
import com.example.temporal.presentation.SimpleObjectAdapter;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class PublicationsFragment extends Fragment implements PublicationsFragmentPresenter.SearchView {

    @Inject
    PublicationsFragmentPresenter presenter;

    private FragmentHomeBinding binding;
    private SearchHeaderBinding headerBinding;

    private SimpleObjectAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        attachListeners();
        callPresenter();
        initDefaultVariables();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        headerBinding = SearchHeaderBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        ((TemporalApplication) requireActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void initDefaultVariables() {
        searchAdapter = new SimpleObjectAdapter();
        headerBinding.searchRecycler.setAdapter(searchAdapter);
    }

    private void callPresenter() {
        presenter.setSearchView(this);
    }

    private void attachListeners() {
        headerBinding.searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.searchInfo(s.toString());
            }
        });

    }

    @Override
    public void searchFinished(List<Map<String, Object>> list) {
        System.out.println(list.size());
        if (list.size() > 0) {
            searchAdapter.updateObjects(list);
        }
    }

    @Override
    public void dataFinished() {
        binding.publicationsRecycler.setAdapter(searchAdapter);
    }
}
