package com.example.temporal.data.usecases;

import com.example.temporal.data.parsable.SimpleObjectParsable;
import com.example.temporal.data.parsable.UserParsable;
import com.example.temporal.domain.User;
import com.example.temporal.domain.specifics_domains.BasicUserDTO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class FetchSearchInfoUseCase {

    private Task<QuerySnapshot> task;
    private FirebaseFirestore _db;

    @Inject
    public FetchSearchInfoUseCase() {
        _db = FirebaseFirestore.getInstance();
    }

    public void execute(String text, Callback callback) {
        task = _db.collection(User.COLLECTION).
                whereArrayContains(User.NAME, text).get();

        task.addOnCompleteListener(task -> {
           if (task.isSuccessful()) {
               if (task.getResult().getDocuments().size() > 0) {
                   callback.onSuccess(SimpleObjectParsable.parseFromDocuments(task.getResult().getDocuments()));
               } else {
                   callback.noDataFounded();
               }
           } else {
               callback.onFailure(task.getException());
           }
        });
    }

    public interface Callback extends OnFailureListener {
        void noDataFounded();
        void onSuccess(List<Map<String, Object>> list);
    }
}
