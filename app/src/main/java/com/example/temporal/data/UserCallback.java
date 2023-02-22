package com.example.temporal.data;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Map;

public interface UserCallback extends OnSuccessListener<Map<String, Object>> , OnFailureListener {
}
