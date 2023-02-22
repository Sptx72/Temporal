package com.example.temporal.data.parsable;

import com.example.temporal.domain.Job;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public abstract class JobParsable {

    public static List<Job> parseResult(List<DocumentSnapshot> list) {
        List<Job> jobs = new ArrayList<>();

        for (DocumentSnapshot doc : list) {
            jobs.add(parseObject(doc));
        }

        return jobs;
    }

    public static Job parseObject(DocumentSnapshot documentSnapshot) {
        Job job = new Job();

        job.setCompany(documentSnapshot.getString(Job.COMPANY));
        job.setDescription(documentSnapshot.getString(Job.DESCRIPTION));

        if (documentSnapshot.contains(Job.DURATION)) {
            job.setDuration(documentSnapshot.get(Job.DURATION, Integer.class));
        }

        job.setName(documentSnapshot.getString(Job.NAME));

        return job;
    }

}
