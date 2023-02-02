package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    static Set<String> taskList = new TreeSet<>();


    public void addTask(String task) {
        taskList.add(task);
    }

    public void removeTask(String task) {
        taskList.remove(task);
    }

    public String getAllTasks() {
        StringBuilder str = new StringBuilder();
        for (String t : taskList) {
            str.append(t);
            str.append(" ");
        }
        return str.toString();
    }

}
