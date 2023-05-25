package ee.taltech.iti0202.exam.timetable;

import java.sql.Time;
import java.util.*;

public class Timetable {

    int taskNumber = 1;

    List<Task> tasks = new ArrayList<>();

    Map<Integer, Integer> dayLimits = new HashMap<>();



    public Optional<String> addTask(String name, int day, int duration, boolean priority) {
        if (dayLimits.containsKey(day) && dayLimits.get(day) >= 5) {
            return Optional.empty();
        }
        if (day < 1 || duration < 1 || duration > 5) {
            return Optional.empty();
        }
        for (Task task : tasks) {
            if (task.getName().equals(name) && task.getDay() == day) {
                return Optional.empty();
            }
        }
        String tasknumba = String.valueOf(taskNumber);
        Task newTask = new Task(name, day, duration, priority, "T" + tasknumba, false);
        if (dayLimits.containsKey(day)) {
            int value = dayLimits.get(day);
            dayLimits.put(day, value + duration);
        }
        else {
            dayLimits.put(day, duration);
        }
        tasks.add(newTask);
        taskNumber++;

        return Optional.of("T" + tasknumba);

    }

    public boolean markTaskDone(String taskNumber) {
        for (Task task : tasks) {
            if (task.getCode().equals(taskNumber) && !task.isDone()) {
                task.setDone(true);
                tasks.remove(task);
                dayLimits.remove(task.getDay());
                return true;
            }
        }
        return false;

    }

    public List<String> getTasksForDay(int day) {
        List<String> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDay() == day && task.isPriority()) {
                result.add(task.getCode() + " " + task.getName());

            }
        }
        for (Task task : tasks) {
            if (task.getDay() == day && !task.isPriority()) {
                result.add(task.getCode() + " " + task.getName());
            }
        }
        return result;

    }

    public static void main(String[] args) {

        Timetable timetable = new Timetable();

        String task1 = timetable.addTask("wake up1", 1, 1, false).get();
        String task2 = timetable.addTask("wake up2", 1, 1, false).get();
        String task3 = timetable.addTask("wake up3", 1, 1, false).get();
        String task4 = timetable.addTask("wake up4", 1, 1, false).get();
        String task5 = timetable.addTask("wake up5", 1, 1, false).get();
        Optional<String> task6 = timetable.addTask("wake up6", 1, 1, false);
        System.out.println(task6); // Optional.empty(), day already full

        timetable.addTask("swim", 4, 3, true).get();
// cannot have the same task name on the same day (swim), whatever the other parameters are
        System.out.println(timetable.addTask("swim", 4, 1, false)); // Optional.empty
        System.out.println(timetable.getTasksForDay(1));
// [T1 wake up1, T2 wake up2, T3 wake up3, T4 wake up4, T5 wake up5]
        System.out.println(timetable.markTaskDone(task3)); // true
        System.out.println(timetable.markTaskDone(task3)); // false, cannot mark task done twice
        System.out.println(timetable.getTasksForDay(1));
// [T1 wake up1, T2 wake up2, T4 wake up4, T5 wake up5]
// now we can add additional task for day one, priority one!
        String task7 = timetable.addTask("sleep", 1, 1, true).get();
// priority task comes first
        System.out.println(timetable.getTasksForDay(1));
// [T7 sleep, T1 wake up1, T2 wake up2, T4 wake up4, T5 wake up5]

        timetable.addTask("eat", 2, 2, false);
        timetable.addTask("walk", 2, 2, true);
// priority task comes first
        System.out.println(timetable.getTasksForDay(2));
// [T9 walk, T8 eat]

// timetables are independent
        Timetable tt = new Timetable();
// we should not get an error here:
        tt.addTask("wake up1", 1, 1, false).get();

    }


}
