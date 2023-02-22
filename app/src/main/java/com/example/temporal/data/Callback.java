package com.example.temporal.data;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public interface Callback  extends OnSuccessListener, OnFailureListener {
    void noDataFounded();
}
