package com.example.timetable.adapters;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetable.listeners.ClickItemListener;
import com.example.timetable.model.Worker;
import com.example.timetable.R;

import java.util.ArrayList;
import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> implements View.OnClickListener{

    private List<Worker> _workersList = new ArrayList<>();

    private int _selectedWorker;


    private ClickItemListener listener;

    private final int[] _avatars = {
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3
    };

    public void setListener(ClickItemListener listener){
        this.listener = listener;
    }

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
        Worker employee = (Worker) view.getTag();
        this._selectedWorker = _workersList.indexOf(employee);
        this.listener.onItemClick(employee);
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
            this.nameEditText.setOnFocusChangeListener(editTextListener);
        }
    }

    private class EditTextListener implements TextWatcher, View.OnFocusChangeListener {
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

        @Override
        public void onFocusChange(View view, boolean b) {
            EditText hz = (EditText) view;
            int position = viewHolder.getAdapterPosition();
            if(!b && hz.getText().toString().equals("")){
                Toast.makeText(view.getContext(), String.valueOf(position) , Toast.LENGTH_SHORT).show();
                if(position > getItemCount() || position == -1)
                    return;
                _workersList.remove(position);
            }
            if(!b && !hz.getText().toString().equals("")) {
//                hz.setEnabled(false);
                hz.setCursorVisible(false);
                hz.setBackgroundColor(Color.TRANSPARENT);
                hz.setKeyListener(null);
                hz.setPadding(0,25,0,0);
            }
            if(b && !hz.getText().toString().equals("")){
                hz.setCursorVisible(false);
                hz.setBackgroundColor(Color.TRANSPARENT);
                hz.setKeyListener(null);
                hz.setPadding(0,25,0,0);
            }

        }
    }

    public void addWorker() {
        _workersList.add(new Worker(_avatars[(int) (Math.random() * 3)], ""));
    }

    public void updateEmployeeData(String[] days) {
        _workersList.get(_selectedWorker).setWorkDays(days);
        notifyItemChanged(_selectedWorker);

    }

}