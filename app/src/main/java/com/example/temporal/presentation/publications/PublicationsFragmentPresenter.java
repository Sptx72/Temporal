package com.example.temporal.presentation.publications;

import androidx.annotation.NonNull;

import com.example.temporal.data.usecases.FetchSearchInfoUseCase;
import com.example.temporal.domain.User;
import com.example.temporal.domain.specifics_domains.BasicUserDTO;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class PublicationsFragmentPresenter {

    private final FetchSearchInfoUseCase fetchSearchInfoUseCase;
    private SearchView searchView;

    @Inject
    public PublicationsFragmentPresenter(FetchSearchInfoUseCase fetchSearchInfoUseCase) {
        this.fetchSearchInfoUseCase = fetchSearchInfoUseCase;
    }

    public void searchInfo(String text) {
        fetchSearchInfoUseCase.execute(text, new FetchSearchInfoUseCase.Callback() {
            @Override
            public void noDataFounded() {

            }

            @Override
            public void onSuccess(List<Map<String, Object>> list) {
                searchView.searchFinished(list);
            }

            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }

    public interface SearchView {
        void searchFinished(List<Map<String, Object>> list);
        void dataFinished();
    }
}
