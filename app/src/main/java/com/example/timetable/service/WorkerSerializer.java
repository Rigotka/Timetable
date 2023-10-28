package com.example.timetable.service;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.widget.Toast;

import com.example.timetable.model.Worker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WorkerSerializer {
    private final String _fileName = "workers.json";

    public void saveToFile(List<Worker> workers, Context context) {
        FileOutputStream fos = null;

        try {
            String str = new Gson().toJson(workers);
            fos = context.openFileOutput(_fileName, MODE_PRIVATE);
            fos.write(str.getBytes());
        } catch (IOException ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public List<Worker> LoadFromFile(Context context) {
        FileInputStream fin = null;
        try {
            fin = context.openFileInput(_fileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            Type listType = new TypeToken<ArrayList<Worker>>() {}.getType();
            return new Gson().fromJson(text, listType);
        } catch (IOException ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ex) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return new ArrayList<Worker>();
    }
}
