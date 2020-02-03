package com.duke.bot;

import java.lang.String;
import java.time.LocalDate;

/**
 * Represents the Duke bot that manages the tasks of the users.
 */
public class Duke {
    private TaskList tasks;
    private DukeUi ui;
    private Storage storage;

    /**
     * Creates a Duke Bot object.
     */
    public Duke() {
        this.tasks = TaskList.createTaskList();
        storage = Storage.createSrorageFile();
        this.ui = new DukeUi(System.in, System.out);
    }

    /**
     * Takes the user's input and decides the action to be taken by Duke bot.
     *
     * @param ui Tha user interface used by the Duke Bot to interact with the user.
     */
    public Duke(DukeUi ui) {
        this.tasks = TaskList.createTaskList();
        storage = Storage.createSrorageFile();
        this.ui = ui;
    }

    private void echo() {
        String userCommand = ui.getNext();

        try {
            switch (userCommand) {
            case "list":
                ui.print(tasks.printList());
                ui.printEmptyLine();
                ui.setToken("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());
                echo();
                break;

            case "done":
                int targetIdx = ui.getNextInt() - 1;
                this.tasks.getTask(targetIdx).markDone();
                ui.print("Good job! I've marked this task as done:");
                ui.print(String.format("%d. %s\n", targetIdx + 1, tasks.getTask(targetIdx).toString()));
                ui.print("\n");
                ui.setToken("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());
                echo();
                break;

            case "todo":
                ui.setToken("\\n");
                String userInput = ui.getNextLine();
                if (userInput.equals("")) {
                    ui.resetScanner();
                    throw new DukeException("Uh-oh! Description of todo cannot be empty!");
                }
                TodoTask todoTask = TodoTask.createTodoTask(userInput);
                tasks.addTask(todoTask);
                ui.print("Added: " + todoTask.toString());
                ui.print(String.format("Now you have %d task(s) on your list.\n", tasks.getSize()));
                ui.printEmptyLine();
                //echo(new Scanner(System.in);
                ui.setToken("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());
                echo();
                break;

            case "deadline":
                ui.setToken("\\s*/by\\s*|\\n");
                String action = ui.getNext();
                String dateInput = ui.getNext();
                LocalDate byDate = LocalDate.parse(dateInput);
                DeadlineTask deadlineTask = DeadlineTask.createDeadlineTask(action, byDate);
                tasks.addTask(deadlineTask);
                ui.print("Added: " + deadlineTask.toString());
                ui.print(String.format("Now you have %d task(s) on your list.\n", tasks.getSize()));
                ui.printEmptyLine();
                ui.setToken("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());
                echo();
                break;

            case "event":
                ui.setToken("\\s*/at\\s*|\\n");
                action = ui.getNext();
                dateInput = ui.getNext();
                LocalDate atDate = LocalDate.parse(dateInput);
                EventTask eventTask = EventTask.createEventTask(action, atDate);
                tasks.addTask(eventTask);
                ui.print("Added: " + eventTask.toString());
                ui.print(String.format("Now you have %d task(s) on your list.\n", tasks.getSize()));
                ui.printEmptyLine();
                ui.setToken("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());
                echo();
                break;

            case "find":
                ui.resetScanner();
                String keyword = ui.getNextLine();
                for (int i = 0; i < tasks.getSize(); ++i) {
                    Task task = tasks.getTask(i);
                    if (task.getTaskName().contains(keyword)) {
                        ui.print((i + 1) + ". " + task.toString());
                    }
                }
                echo();
                break;

            case "bye":
                ui.printByeMsg();
                break;

            case "delete":
                int delIdx = ui.getNextInt() - 1;
                if (delIdx >= tasks.getSize() || delIdx < 0) {
                    throw new DukeException("Oops! Target object is out of bounds!");
                }
                Task delTask = tasks.getTask(delIdx);
                tasks.deleteTask(delIdx);
                ui.print(String.format("Deleted: %s\n\n", delTask.toString()));
                storage.saveToFile(tasks.printList());
                echo();
                break;

            default:
                throw new DukeException(
                        "Oops! Invalid commmand word, perhaps you would want to try on of the following: "
                                + "1. todo 2.deadline 3.event 4.list 5.done 6.bye");

            }

        } catch (DukeException ex) {
            ui.printError(ex.getMessage());
            ui.printEmptyLine();
            echo();
        }
    }


    /**
     * The main method to simulate and run Duke Bot.
     *
     * @param args The arguments written by the user in the command line.
     */
    public static void main(String[] args) {

        Duke duke  = new Duke();

        duke.ui.greet();

        duke.echo();
    }

}
