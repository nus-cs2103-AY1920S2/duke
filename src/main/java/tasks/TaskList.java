package tasks;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Storage;
import processor.Ui;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {

    List<Task> taskList;
    DukeProcessor processor;

    public TaskList(DukeProcessor processor) {
        this.processor = processor;
        taskList = new ArrayList<Task>();

        init();
    }

    private void init() {
        try {
            Storage.init();
            taskList = Storage.loadTasks(processor);
        } catch(Exception e) {
            e.printStackTrace();
            Ui.print("It looks like you have no previously saved tasks! Starting a new list for you...");
        }
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public boolean remove(Task task) {
        return taskList.remove(task);
    }

    public Task removeAt(int index) {
        return taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void printTasks() {
        if(taskList.size() == 0) {
            Ui.print("Looks like you don't have any tasks entered! Try entering one with the " +
                    "commands 'todo', 'deadline' or 'event'!");
        } else {
            Ui.print("Here are the " + taskList.size() + " tasks I've noted down for you:");
            for(int i = 0; i < taskList.size(); i ++) {
                Ui.print(String.format("%d. %s", i + 1, taskList.get(i)));
            }
        }
    }

    public void printTasksOn(LocalDate searchDate) {
        List<Task> outputTaskList = new ArrayList<Task>();
        for(Task task : taskList) {
            if(task instanceof DeadlineTask) {
                if(((DeadlineTask) task).getParsedDeadline().toLocalDate().equals(searchDate)) {
                    outputTaskList.add(task);
                }
            } else if(task instanceof EventTask) {
                if(((EventTask) task).getParsedStartTime().toLocalDate().equals(searchDate)) {
                    outputTaskList.add(task);
                }
            }
        }

        if(outputTaskList.size() == 0) {
            Ui.print("Looks like you don't have any tasks entered on this date! Try entering one with " +
                    "the " +
                    "commands 'todo', 'deadline' or 'event'!");
        } else {
            Ui.print(String.format("Here are the %d tasks I've noted down for you on %s:",
                    outputTaskList.size(), searchDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
            for(int i = 0; i < outputTaskList.size(); i ++) {
                Ui.print(String.format("%d. %s", i + 1, outputTaskList.get(i)));
            }
        }
    }

    public void printTasksContaining(String searchText) {
        List<Task> filteredTaskList = new ArrayList<Task>();

        for(Task task : taskList) {
            if(task.getDescription().contains(searchText)) {
                filteredTaskList.add(task);
            }
        }

        for(Task task : filteredTaskList) {
            Ui.print(task.toString());
        }
    }

}
