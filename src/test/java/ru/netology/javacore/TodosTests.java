package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

    public class TodosTests {

        private static Todos todos = new Todos();
        private static Set<String> actualTaskList = new TreeSet<>();

        @BeforeAll
        public static void beforeAll(){
            actualTaskList.add("Сделать зарядку");
            actualTaskList.add("Убраться в квартире");
            todos.addTask("Убраться в квартире");
            todos.addTask("Сделать зарядку");
        }


        @Test
        public void addTaskTest() {
            Assertions.assertEquals(actualTaskList, Todos.taskList);
        }

        @Test
        public void removeTaskTest() {
            todos.removeTask("Сделать зарядку");
            actualTaskList.remove("Сделать зарядку");
            Assertions.assertEquals(actualTaskList, Todos.taskList);
        }

        @Test
        public void getAllTasksTest(){
            String actual = "Убраться в квартире ";
            Assertions.assertEquals(actual, todos.getAllTasks());
        }
    }

