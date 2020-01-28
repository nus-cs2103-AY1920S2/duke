package duke;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    public void run() {
        ui.greetUser();
        processInput();
    }

    public void processInput() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        Parser curr = new Parser(next);
        while (!curr.getTaskType().equals("bye")) {
            if (curr.getTaskType().equals("list")) {
                ui.printList(tasks);
            } else if (curr.getTaskType().equals("find")) {
                String finding = curr.getSecond();
                TaskList filtered = tasks.filter(finding);
                ui.printFindings(filtered);
            } else if (curr.getTaskType().equals("done")) {
                try {
                    Integer taskNumber = Integer.valueOf(curr.getSecond());
                    Task updatedTask = tasks.get(taskNumber - 1).setDone();
                    tasks.set(taskNumber - 1, updatedTask);
                    storage.updateDrive(updatedTask);
                    ui.printDone(updatedTask);
                } catch (IndexOutOfBoundsException e) {
                    ui.printException(new DukeException(" ☹ OOPS!!! The description of a done cannot be empty."));
                }
            } else if (curr.getTaskType().equals("delete")) {
                try {
                    Integer taskNumber = Integer.valueOf(curr.getSecond());
                    Task removedTask = tasks.get(taskNumber - 1);
                    tasks.remove(removedTask);
                    storage.deleteDrive(taskNumber);
                    ui.printRemove(removedTask, tasks.size());
                } catch (IndexOutOfBoundsException e) {
                    ui.printException(new DukeException(" ☹ OOPS!!! The description of a delete cannot be empty."));
                }
            } else {
                Task newTask;
                String taskName;
                if (curr.getTaskType().equals("todo")) {
                    try {
                        taskName = curr.getThird();
                        newTask = new ToDo(taskName);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printException(new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty."));
                    }
                } else if (curr.getTaskType().equals("deadline")) {
                    try {
                        taskName = curr.getThird();
                        LocalDateTime time = curr.getDate();
                        newTask = new Deadline(taskName, time);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printException(new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty."));
                    }
                } else if (next.trim().split(" ")[0].equals("event")) {
                    try {
                        taskName = curr.getThird();
                        LocalDateTime time = curr.getDate();
                        newTask = new Event(taskName, time);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printException(new DukeException(" ☹ OOPS!!! The description of an event cannot be empty."));
                    }
                } else {
                    ui.printException(new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
                }
            }
            next = sc.nextLine();
            curr = new Parser(next);
        }
        ui.printBye();
    }

    void addTask(Task newTask) {
        tasks.add(newTask);
        storage.updateDrive(newTask);
        ui.printTask(newTask, tasks.size());
    }

}
