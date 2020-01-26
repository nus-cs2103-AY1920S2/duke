import main.java.*;

import java.io.IOException;
import java.lang.StringBuilder;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readFile());
    }

    public void run() throws ParseException, DukeException, IOException {
        ui.printWelcome();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc);

        String command = parser.readCommand();

        while (!parser.getCommandType(command).equals("bye")) {
            String commandType = parser.getCommandType(command);
            if (commandType.equals("list")) {
                tasks.printList();
                command = parser.readCommand();
            } else if (commandType.equals("done")) {
                int taskNo = parser.getTaskNo(command);
                tasks.setDone(taskNo);
                ui.printDone(tasks.getTask(taskNo));
                command = parser.readCommand();
            } else if (commandType.equals("delete")) {
                int taskNo = parser.getTaskNo(command);
                Task task = tasks.getTask(taskNo);
                ui.printDelete(task, tasks.getSize() - 1);
                tasks.deleteTask(taskNo);
                storage.saveFile(tasks.getTaskList());
                command = parser.readCommand();
            } else if (commandType.equals("deadline")) {
                Deadline deadline = parser.getDeadline(command);
                tasks.addTask(deadline);
                storage.saveFile(tasks.getTaskList());
                ui.printAdd(deadline, tasks.getSize());
                command = parser.readCommand();
            } else if (commandType.equals("event")) {
                Event event = parser.getEvent(command);
                tasks.addTask(event);
                storage.saveFile(tasks.getTaskList());
                ui.printAdd(event, tasks.getSize());
                command = parser.readCommand();
            } else if (commandType.equals("todo")) {
                ToDo toDo = parser.getToDo(command);
                tasks.addTask(toDo);
                storage.saveFile(tasks.getTaskList());
                ui.printAdd(toDo, tasks.getSize());
                command = parser.readCommand();
            } else {
                System.out.println("Invalid command type; please re-enter a valid command type.");
                command = parser.readCommand();
            }
        }

        ui.printBye();
    }

    public static void main(String[] args) throws ParseException, DukeException, IOException {
        new Duke("data/duke.txt").run();
    }


}
