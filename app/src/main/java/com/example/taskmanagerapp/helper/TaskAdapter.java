package com.example.taskmanagerapp.helper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapp.R;
import com.example.taskmanagerapp.entity.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {


    private Context context;
    private ArrayList<Task> taskList;

    public TaskAdapter(Context context, ArrayList<Task> contactList) {
        this.context = context;
        this.taskList = contactList;
    }


    @NonNull
    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.task_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.MyViewHolder holder, int position) {
        Task t = taskList.get(position);
        holder.txtTaskTitle2.setText(t.gettaskTitle()+"");
        holder.txtDue.setText(t.gettaskTime());
        holder.txtTask2.setText(t.gettaskDescription());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTaskTitle2, txtDue, txtTask2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTaskTitle2 = itemView.findViewById(R.id.txtTaskTitle2);
            txtDue = itemView.findViewById(R.id.txtDue);
            txtTask2 = itemView.findViewById(R.id.txtTask2);
        }
    }
}
