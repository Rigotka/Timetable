package com.example.timetable.service;

import com.example.timetable.model.Worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimetableGenerator {

    public static void clearShifts(List<Worker> workers){
        for(Worker worker: workers){
            worker.clearShifts();
        }
    }
    public static Worker getMaxShiftWorker(List<Worker> workers){
        int max = 0;
        Worker maxShiftsWorker = null;
        for(Worker worker: workers){
            if(worker.getShifts() > max){
                max = worker.getShifts();
                maxShiftsWorker = worker;
            }
        }
        return maxShiftsWorker;
    }

    public static String[] generateTimetable(List<Worker> workers){
        clearShifts(workers);
        String[] days = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
        Map<String, List<Worker>> timetable = new HashMap<String, List<Worker>>();
        for (String day: days){
            timetable.put(day, new ArrayList<Worker>());
            for(Worker worker: workers){
                if((boolean) worker.getworkDaysAsString().contains(day)){
                    timetable.get(day).add(worker);
                    worker.addShifts();
                }
            }
        }

        for(String day: days){
            List<Worker> workersDay = timetable.get(day);

            while (workersDay.size() > 2)
            {
                Worker maxShiftsWorker = getMaxShiftWorker(workers);
                maxShiftsWorker.removeShifts();
                workersDay.remove(maxShiftsWorker);
            }
            timetable.put(day, workersDay);
        }
        String[] timetableString = new String[7];
        for(int i = 0; i < days.length; i++){
            timetableString[i] = days[i] + ": ";
            for(Worker worker: timetable.get(days[i])){
                timetableString[i] += worker.getName() + " ";
            }
        }

        return timetableString;
    }

}
