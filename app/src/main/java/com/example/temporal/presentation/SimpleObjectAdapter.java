package com.example.temporal.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temporal.R;
import com.example.temporal.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleObjectAdapter extends RecyclerView.Adapter<SimpleObjectAdapter.SimpleObjectRow> {

    private List<Map<String, Object>> objects;

    public SimpleObjectAdapter() {
        objects = new ArrayList<>();
    }

    @NonNull
    @Override
    public SimpleObjectRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_row, parent);
        return new SimpleObjectRow(view);
    }

    public void updateObjects(List<Map<String, Object>> list) {
        objects.clear();
        for (Map<String, Object> o : list) {
            objects.add(o);
            notifyItemChanged(objects.indexOf(o));
        }
    }



    @Override
    public void onBindViewHolder(@NonNull SimpleObjectRow holder, int position) {
        holder.textView.setText((CharSequence) objects.get(position).get(User.NAME));
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public static class SimpleObjectRow extends RecyclerView.ViewHolder {

        TextView textView;

        public SimpleObjectRow(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.simple_object_data);
        }
    }
}
