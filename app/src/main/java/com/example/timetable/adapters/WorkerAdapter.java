package com.example.timetable.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetable.model.Worker;
import com.example.timetable.R;

import java.util.ArrayList;
import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> implements View.OnClickListener{

    private List<Worker> _workersList = new ArrayList<>();

    private final int[] _avatars = {
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3
    };

    @NonNull
    @Override
    public WorkerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.worker_item, parent, false);
        return new ViewHolder(view, new EditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerAdapter.ViewHolder holder, int position) {
        Worker worker = _workersList.get(position);

        holder.itemView.setTag(worker);
        holder.itemView.setOnClickListener(this);
        holder.editTextListener.SetViewHolder(holder);

        ImageView avatarImageView = holder.avatarImageView;
        EditText nameEditText = holder.nameEditText;
        TextView daysWorkTextView = holder.workDaysTextView;

        avatarImageView.setImageResource(worker.getImage());
        nameEditText.setText(worker.getName());
        daysWorkTextView.setText(worker.getworkDaysAsString());

    }

    @Override
    public int getItemCount() {
        return _workersList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.nameEditText != null) {
            holder.nameEditText.requestFocus();
        }
    }

    @Override
    public void onClick(View view) {

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatarImageView;
        public EditText nameEditText;
        public TextView workDaysTextView;
        public EditTextListener editTextListener;
        public ViewHolder(@NonNull View itemView, EditTextListener editTextListener) {
            super(itemView);
            this.editTextListener = editTextListener;

            this.avatarImageView = itemView.findViewById(R.id.avatarImageView);
            this.nameEditText = itemView.findViewById(R.id.nameEditText);
            this.workDaysTextView = itemView.findViewById(R.id.workDaysTextView);

            this.nameEditText.addTextChangedListener(editTextListener);
        }
    }

    private class EditTextListener implements TextWatcher {
        private WorkerAdapter.ViewHolder viewHolder;

        public void SetViewHolder(WorkerAdapter.ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            int position = viewHolder.getAdapterPosition();
            _workersList.get(position).setName(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }

    public void addWorker() {
        _workersList.add(new Worker(_avatars[(int) (Math.random() * 3)], ""));
    }

}