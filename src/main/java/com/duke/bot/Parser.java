package com.duke.bot;

import java.time.LocalDate;

public class Parser {

    public Parser() {
    }

    public static String printSuccessMsg(Task task, TaskList tasks) {
        String output = "Added: " + task.toString() + "\n";
        output = output.concat(String.format("Now you have %d task(s) on your list.\n\n", tasks.getSize()));
        return output;
        //echo(new Scanner(System.in);
    }

    public static String parse(String userCommand, DukeUi ui, Storage storage, TaskList tasks) throws DukeException {

        switch(userCommand) {

        case "list":
            storage.saveToFile(tasks.printList());
            return tasks.printList();

        case "done":
            int targetIdx = ui.getNextInt() - 1;
            tasks.getTask(targetIdx).markDone();

            String output = "Good job! I've marked this task as done:\n";
            output = output.concat(String.format("%d. %s\n\n", targetIdx + 1, tasks.getTask(targetIdx).toString()));
            ui.setToken("\\p{javaWhitespace}+");
            storage.saveToFile(tasks.printList());
            return output;

        case "todo":
            ui.setToken("\\n");
            String userInput = ui.getNextLine();
            if (userInput.equals("")) {
                ui.resetScanner();
                throw new DukeException("Uh-oh! Description of todo cannot be empty!");
            }
            TodoTask todoTask = TodoTask.createTodoTask(userInput);
            tasks.addTask(todoTask);

            ui.setToken("\\p{javaWhitespace}+");
            storage.saveToFile(tasks.printList());
            return printSuccessMsg(todoTask, tasks);

        case "deadline":
            ui.setToken("\\s*/by\\s*|\\n");
            String action = ui.getNext();
            String dateInput = ui.getNext();
            LocalDate byDate = LocalDate.parse(dateInput);
            DeadlineTask deadlineTask = DeadlineTask.createDeadlineTask(action, byDate);
            tasks.addTask(deadlineTask);

            ui.setToken("\\p{javaWhitespace}+");
            storage.saveToFile(tasks.printList());
            return printSuccessMsg(deadlineTask, tasks);

        case "event":
            ui.setToken("\\s*/at\\s*|\\n");
            action = ui.getNext();
            dateInput = ui.getNext();
            LocalDate atDate = LocalDate.parse(dateInput);
            EventTask eventTask = EventTask.createEventTask(action, atDate);
            tasks.addTask(eventTask);

            ui.setToken("\\p{javaWhitespace}+");
            storage.saveToFile(tasks.printList());
            return printSuccessMsg(eventTask, tasks);

        case "find":
            ui.resetScanner();
            String keyword = ui.getNextLine();
            output = "";
            for (int i = 0; i < tasks.getSize(); ++i) {
                Task task = tasks.getTask(i);
                if (task.getTaskName().contains(keyword)) {
                    output = output.concat((i + 1) + ". " + task.toString());
                }
            }
            return output;

        case "bye":
            return ui.BYE_MESSAGE;

        case "delete":
            int delIdx = ui.getNextInt() - 1;
            if (delIdx >= tasks.getSize() || delIdx < 0) {
                throw new DukeException("Oops! Target object is out of bounds!");
            }
            Task delTask = tasks.getTask(delIdx);
            tasks.deleteTask(delIdx);
            storage.saveToFile(tasks.printList());
            return String.format("Deleted: %s\n\n", delTask.toString());

        default:
            throw new DukeException(
                    "Oops! Invalid commmand word, perhaps you would want to try on of the following: "
                            + "1. todo 2.deadline 3.event 4.list 5.done 6.bye");

        }
    }
}
