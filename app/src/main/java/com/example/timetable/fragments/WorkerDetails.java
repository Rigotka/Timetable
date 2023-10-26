package com.example.timetable.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.timetable.R;
import com.example.timetable.listeners.WorkerDetailsListener;

import java.util.ArrayList;

public class WorkerDetails extends DialogFragment {
    LinearLayout checkboxContainer;
    private String[] days = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
    private String[] workDays;
    private WorkerDetailsListener listener;
    private ArrayList<CheckBox> checkBoxes= new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Dialog_Alert);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_worker_details, container, false);
        TextView nameTextView = view.findViewById(R.id.name);
        if(getArguments() != null) {
            String name = getArguments().getString("name");
            workDays = getArguments().getStringArray("workDays");
            nameTextView.setText(name);
        }
        checkboxContainer = view.findViewById(R.id.checkboxContainer);
        initCheckBox();

        Button ready = (Button) view.findViewById(R.id.buttonOk);
        Button delete = (Button) view.findViewById(R.id.deleteButton);
        ready.setOnClickListener(v -> {
            CollectionData();
            listener.SaveData(workDays);
        });

        delete.setOnClickListener(view1 -> listener.DeleteWorker());


        return view;
    }
    public void initCheckBox(){
        for (int i = 0; i < days.length; i++) {
            CheckBox checkBox = new CheckBox(this.getContext());
            checkBoxes.add(checkBox);
            checkBox.setText(days[i]);
            if(workDays[i] == days[i])
                checkBox.setChecked(true);
            checkboxContainer.addView(checkBox);
        }
    }
    public void SetListener(WorkerDetailsListener saveDataListener){
        listener = saveDataListener;
    }

    public void CollectionData (){
        for(int i = 0; i < checkBoxes.size(); i++){
            CheckBox checkBox = checkBoxes.get(i);
            workDays[i] = null;
            if(checkBox.isChecked()){
                workDays[i] = checkBox.getText().toString();
            }
        }
    }
}