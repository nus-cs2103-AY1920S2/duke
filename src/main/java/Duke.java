import main.java.*;

import java.io.IOException;
import java.lang.StringBuilder;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main class that drives the code to run the Duke bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke class constructor that creates a new instance of a Duke bot.
     * @param filePath Path of file for instance of Storage class to be created.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readFile());
    }

    /**
     * Main method that drives the Duke bot.
     */
    public void run() {
        ui.printWelcome();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc);

        String command = parser.readCommand();

        try {
            while (!parser.getCommandType(command).equals("bye")) {

                String commandType = parser.getCommandType(command);

                if (commandType.equals("list")) {
                    tasks.printList();
                    command = parser.readCommand();
                } else if (commandType.equals("find")) {
                    String findString = parser.getFind(command);
                    ArrayList<Task> matchedTask = tasks.find(findString);
                    ui.printFind(matchedTask);
                    command = parser.readCommand();
                } else if (commandType.equals("done")) {
                    int taskNo = parser.getTaskNo(command);
                    try {
                        tasks.setDone(taskNo);
                        ui.printDone(tasks.getTask(taskNo));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("There is no task " + (taskNo + 1) + " in the list.");
                    }
                    command = parser.readCommand();
                } else if (commandType.equals("delete")) {
                    int taskNo = parser.getTaskNo(command);
                    Task task = tasks.getTask(taskNo);
                    ui.printDelete(task, tasks.getSize() - 1);
                    tasks.deleteTask(taskNo);
                    storage.saveFile(tasks.getTaskList());
                    command = parser.readCommand();
                } else if (commandType.equals("deadline")) {
                    try {
                        Deadline deadline = parser.getDeadline(command);
                        tasks.addTask(deadline);
                        storage.saveFile(tasks.getTaskList());
                        ui.printAdd(deadline, tasks.getSize());
                    } catch (DukeException | ParseException e) {
                        System.out.println(e.getMessage());
                    }
                    command = parser.readCommand();
                } else if (commandType.equals("event")) {
                    try {
                        Event event = parser.getEvent(command);
                        tasks.addTask(event);
                        storage.saveFile(tasks.getTaskList());
                        ui.printAdd(event, tasks.getSize());
                    } catch (DukeException | ParseException e) {
                        System.out.println(e.getMessage());
                    }
                    command = parser.readCommand();
                } else if (commandType.equals("todo")) {
                    try {
                        ToDo toDo = parser.getToDo(command);
                        tasks.addTask(toDo);
                        storage.saveFile(tasks.getTaskList());
                        ui.printAdd(toDo, tasks.getSize());
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    command = parser.readCommand();
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            command = parser.readCommand();
        }

        ui.printBye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }


}
