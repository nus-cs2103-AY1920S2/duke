package duke;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is the main driving chatbot
 **/
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor which takes in a file path
     * to obtain information from the file
     * @param filePath The file path to the file to be modified
     **/
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

    /**
     * The main method to run the chatbot
     **/
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    /**
     * This run method
     **/
    public void run() {
        ui.greetUser();
        processInput();
    }

    /**
     * Method to process the input from user
     **/
    public void processInput() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        Parser curr = new Parser(next);
        while (!curr.getTaskType().equals("bye")) {
            if (curr.getTaskType().equals("list")) {
                ui.printList(tasks);
            } else if (curr.getTaskType().equals("done")) {
                try {
                    Integer taskNumber = Integer.valueOf(curr.getSecond());
                    Task updatedTask = tasks.get(taskNumber - 1).setDone();
                    tasks.set(taskNumber - 1, updatedTask);
                    storage.updateDrive(updatedTask);
                    ui.printDone(updatedTask);
                } catch (IndexOutOfBoundsException e) {
                    ui.printException(new DukeException(
                            " ☹ OOPS!!! The description of a done cannot be empty."));
                }
            } else if (curr.getTaskType().equals("delete")) {
                try {
                    Integer taskNumber = Integer.valueOf(curr.getSecond());
                    Task removedTask = tasks.get(taskNumber - 1);
                    tasks.remove(removedTask);
                    storage.deleteDrive(taskNumber);
                    ui.printRemove(removedTask, tasks.size());
                } catch (IndexOutOfBoundsException e) {
                    ui.printException(new DukeException(
                            " ☹ OOPS!!! The description of a delete cannot be empty."));
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
                        ui.printException(new DukeException(
                                " ☹ OOPS!!! The description of a todo cannot be empty."));
                    }
                } else if (curr.getTaskType().equals("deadline")) {
                    try {
                        taskName = curr.getThird();
                        LocalDateTime time = curr.getDate();
                        newTask = new Deadline(taskName, time);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printException(new DukeException(
                                " ☹ OOPS!!! The description of a deadline cannot be empty."));
                    }
                } else if (next.trim().split(" ")[0].equals("event")) {
                    try {
                        taskName = curr.getThird();
                        LocalDateTime time = curr.getDate();
                        newTask = new Event(taskName, time);
                        addTask(newTask);
                    } catch (IndexOutOfBoundsException e) {
                        ui.printException(new DukeException(
                                " ☹ OOPS!!! The description of an event cannot be empty."));
                    }
                } else {
                    ui.printException(new DukeException(
                            " ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
                }
            }
            next = sc.nextLine();
            curr = new Parser(next);
        }
        ui.printBye();
    }

    /**
     * Method to add a task to the list
     **/
    void addTask(Task newTask) {
        tasks.add(newTask);
        storage.updateDrive(newTask);
        ui.printTask(newTask, tasks.size());
    }

}
