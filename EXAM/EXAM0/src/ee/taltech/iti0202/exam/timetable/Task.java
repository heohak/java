package ee.taltech.iti0202.exam.timetable;

public class Task {

    private String name;

    private int day;
    private int duration;

    private boolean priority;

    private String code;

    private boolean isDone;

    public Task(String name, int day, int duration, boolean priority, String code, boolean isDone) {
        this.name = name;
        this.day = day;
        this.duration = duration;
        this.priority = priority;
        this.code = code;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
