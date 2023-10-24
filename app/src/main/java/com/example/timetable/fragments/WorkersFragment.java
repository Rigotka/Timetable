package com.example.timetable.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.timetable.MainActivity;
import com.example.timetable.R;
import com.example.timetable.adapters.WorkerAdapter;
import com.example.timetable.listeners.ClickItemListener;
import com.example.timetable.listeners.KeyboardHandler;
import com.example.timetable.listeners.SaveDataListener;
import com.example.timetable.model.Worker;


public class WorkersFragment extends Fragment implements ClickItemListener, SaveDataListener {

    private final WorkerAdapter _adapter = new WorkerAdapter();

    private RecyclerView _recyclerView;

    private KeyboardHandler _keyboardHandler;

    private WorkerDetails workerDetails;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workers, container, false);

        _recyclerView = view.findViewById(R.id.recyclerView);
//        _recyclerView.setItemAnimator(null);
        _recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        _recyclerView.setAdapter(_adapter);

        _adapter.setListener(this);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            _keyboardHandler.showKeyboard(v);
            _adapter.deleteEmptyWorker();
            _adapter.addWorker();
            _recyclerView.smoothScrollToPosition(_adapter.getItemCount() - 1);
            _adapter.notifyItemChanged(_adapter.getItemCount() - 1);

        });

        return view;
    }

    public void setListener(MainActivity mainActivity) {
        _keyboardHandler = mainActivity;
    }

    @Override
    public void onItemClick(Worker worker) {
        if(worker.getName().equals("")){
            Toast.makeText(getContext(), "Заполните поля имя", Toast.LENGTH_SHORT).show();
            return;
        }
        _adapter.deleteEmptyWorker();
        workerDetails = new WorkerDetails();

        Bundle args = new Bundle();
        args.putString("name", worker.getName());
        args.putStringArray("workDays", worker.getWorkDays());
        workerDetails.setArguments(args);
        workerDetails.SetListener(this);
        workerDetails.setCancelable(false);
        workerDetails.show(requireActivity().getSupportFragmentManager(), "");

        View view = requireActivity().getCurrentFocus();
        if(view != null) {
//            view.clearFocus();
            View hz = view.findFocus();
            hz.clearFocus();
        }
    }

    public void test(View view) {
        _recyclerView.requestFocus();
    }

    @Override
    public void SaveData(String[] days) {
        _adapter.updateEmployeeData(days);
        workerDetails.dismiss();
        _recyclerView.requestFocus();
    }
}