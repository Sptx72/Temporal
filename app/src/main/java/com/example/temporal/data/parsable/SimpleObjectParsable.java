package com.example.temporal.data.parsable;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SimpleObjectParsable {

    public static List<Map<String, Object>> parseFromDocuments(List<DocumentSnapshot> list) {
        List<Map<String, Object>> objects = new ArrayList<>();

        for (DocumentSnapshot doc : list) {
            objects.add(doc.getData());
        }

        return objects;
    }
}
