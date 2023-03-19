package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class TodoServer {
    private int port;
    private Todos todos;

    private static LinkedList<Task> allOperation = new LinkedList<>();

    private static final int MAX_TASK = 7;


    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException, RuntimeException {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);) {

                System.out.println("Starting server at " + port + "...");

                    try (
                            Socket socket = serverSocket.accept();
                            BufferedReader in = new BufferedReader
                                    (new InputStreamReader(socket.getInputStream()));
                            PrintWriter out = new PrintWriter(socket.getOutputStream());
                    ) {
                        System.out.println("Новое подключение принято");
                        String clientMess = in.readLine();
                        Gson gson = new Gson();
                        Task task = gson.fromJson(clientMess, Task.class);

                        switch (task.getType()) {
                            case ("ADD"):
                                if (Todos.taskList.size() < MAX_TASK) {
                                    todos.addTask(task.getTask());
                                    allOperation.add(task);
                                }
                                break;
                            case ("REMOVE"):
                                if (!Todos.taskList.isEmpty()) {
                                    todos.removeTask(task.getTask());
                                    allOperation.add(task);
                                }
                                break;
                            case ("RESTORE"):
                                if (allOperation.isEmpty()) break;
                                Task lastTask = allOperation.getLast();
                                if (lastTask.getType().equals("ADD")) todos.removeTask(lastTask.getTask());
                                if (lastTask.getType().equals("REMOVE")) todos.addTask(lastTask.getTask());
                                allOperation.removeLast();
                                break;
                        }

                        String answer = todos.getAllTasks();
                        if (answer.equals("")) {
                            out.println("Список задач пуст!");
                        } else {
                            out.println(answer);
                        }
                    }

            } catch (IOException e) {
                System.out.println("Не могу стартовать сервер");
                e.printStackTrace();
            }
        }
    }
}


