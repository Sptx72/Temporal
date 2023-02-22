package com.example.temporal.presentation.profile;

import android.app.ActionBar;
import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.temporal.AppTextUtils;
import com.example.temporal.data.Callback;
import com.example.temporal.DialogManager;
import com.example.temporal.data.UserCallback;
import com.example.temporal.data.parsable.UserParsable;
import com.example.temporal.data.usecases.FetchUserJobs;
import com.example.temporal.data.usecases.FetchUserUseCase;
import com.example.temporal.domain.Job;
import com.example.temporal.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class ProfileDataFragmentPresenter {

    private final FetchUserUseCase fetchUserUseCase;
    private final FetchUserJobs fetchUserJobs;

    private DialogManager dialogManager;
    private ProfileDataView view;
    private User user;

    @Inject
    ProfileDataFragmentPresenter(FetchUserUseCase fetchUserUseCase,
                                 FetchUserJobs fetchUserJobs) {
        this.fetchUserUseCase = fetchUserUseCase;
        this.fetchUserJobs = fetchUserJobs;
    }

    public void setView(ProfileDataView view) {
        this.view = view;
    }

    public void init(ProfileDataView profileDataView) {
        this.view = profileDataView;
        dialogManager = new DialogManager(view.requireActivity());
    }

    public void onViewCreated() {
        dialogManager.showLoadingView();
        fetchUserUseCase.execute(new UserCallback() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialogManager.hideLoadingView();
                dialogManager.showErrorView(e.getMessage());
            }

            @Override
            public void onSuccess(Map<String, Object> map) {
                user = UserParsable.parseFromMap(map);
                view.displayData(
                        AppTextUtils.autoFillNull(user.getName()), AppTextUtils.autoFillNull(user.getLastName()),
                        AppTextUtils.autoFillNull(user.getEmail()), AppTextUtils.autoFillNull(user.getPhone()));

                fetchJobData(user.getUid());
            }
        });
    }

    private void fetchJobData(String uid) {
        fetchUserJobs.execute(uid, new Callback() {
            @Override
            public void noDataFounded() {
                view.noJobsFounded();
                dialogManager.hideLoadingView();
            }

            @Override
            public void onFailure(@NonNull Exception e) {
                dialogManager.hideLoadingView();
                dialogManager.showErrorView(e.getMessage());
            }

            @Override
            public void onSuccess(Object o) {
                dialogManager.hideLoadingView();
                view.displayJobs((List<Job>) o);
            }
        });
    }

    public interface ProfileDataView {
        void displayData(String name, String lastName, String email, String phone);
        void displayJobs(List<Job> jobs);
        void noJobsFounded();
        Activity requireActivity();
    }
}
