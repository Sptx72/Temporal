package com.example.temporal.presentation.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temporal.R;
import com.example.temporal.domain.Job;

import java.util.ArrayList;
import java.util.List;

public class JobRowAdapter extends RecyclerView.Adapter<JobRowAdapter.JobRowViewHolder> {

    private List<Job> jobList;
    public JobRowAdapter(List<Job> jobs) {
        jobList = jobs;
    }



    @NonNull
    @Override
    public JobRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_view_row, parent, false);
        return new JobRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobRowViewHolder holder, int position) {
        Job job = jobList.get(position);

        //TODO Make the image
        holder.image.setImageResource(R.drawable.ic_account);
        holder.name.setText(job.getName());
        holder.description.setText(job.getDescription());
    }


    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public static class JobRowViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        ImageView image;

        public JobRowViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.job_name_row);
            description = itemView.findViewById(R.id.job_description_row);
            image = itemView.findViewById(R.id.job_row_image);
        }
    }
}
