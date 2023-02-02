package ru.netology.javacore;

public class Task {
    private String type;
    private String task;

    public Task(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Тип: " + this.getType() + " Задача: " + this.getTask();
    }
}
