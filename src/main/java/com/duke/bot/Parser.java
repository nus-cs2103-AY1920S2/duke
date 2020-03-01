package com.duke.bot;

import java.time.LocalDate;

/**
 * Represents the parser that reads and decipher meanings from user commands.
 */
public class Parser {

    /**
     * Creates a Parser object.
     */
    public Parser() {
    }

    /**
     * Adds a task into the task list and prints a success message afterwards.
     *
     * @param task Task being added to the task list.
     * @param tasks The task lists storing all the tasks of the user.
     * @return A string that indicates the success of adding the task into the task list.
     */
    public static String printSuccessMsg(Task task, TaskList tasks) {
        String output = "Added: " + task.toString() + "\n";
        output = output.concat(String.format("Now you have %d task(s) on your list.\n\n", tasks.getSize()));
        return output;
    }

    /**
     * Reads and deciphers the meaning of the user command and react accordingly.
     *
     * @param userCommand The Command issued by the user.
     * @param ui The UI of Duke Bot.
     * @param storage The hard disk storage of the task list.
     * @param tasks The task list of the user.
     * @return A message corresponding to the command being executed.
     * @throws DukeException When the command issued is invalid.
     */
    public static String parse(String userCommand, DukeUi ui, Storage storage, TaskList tasks) throws DukeException {
        switch(userCommand) {

            case "list":
                storage.saveToFile(tasks.printList());
                return tasks.printList();

            case "done":
                int targetIdx = ui.getNextInt() - 1;
                if (targetIdx >= tasks.getSize()) {
                    throw new DukeException("Oops, index out of bounds!");
                }
                tasks.getTask(targetIdx).markDone();

                String output = "Good job! I've marked this task as done:\n";
                output = output.concat(String.format("%d. %s\n\n", targetIdx + 1, tasks.getTask(targetIdx).toString()));
                ui.setToken("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());
                return output;

            case "todo":
                ui.setToken("\\n");
                String userInput;
                try {
                    userInput = ui.getNextLine();
                } catch (Exception e) {
                    throw new DukeException("Uh-oh! Description of todo cannot be empty!");
                }
                TodoTask todoTask = TodoTask.createTodoTask(userInput);
                tasks.addTask(todoTask);

                ui.setToken("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());

                return printSuccessMsg(todoTask, tasks);

            case "deadline":
                ui.setToken("\\s*/by\\s*|\\n");
                String action;
                String dateInput ;
                LocalDate byDate;
                try {
                    action = ui.getNext();
                    dateInput = ui.getNext();
                    byDate = LocalDate.parse(dateInput);
                } catch (Exception e) {
                    throw new DukeException("Oops, invalid format! Try the following format: "
                            + "deadline {task name} /by YYYY-MM-DD");
                }
                DeadlineTask deadlineTask = DeadlineTask.createDeadlineTask(action, byDate);
                tasks.addTask(deadlineTask);

                ui.setToken("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());
                return printSuccessMsg(deadlineTask, tasks);

            case "event":
                ui.setToken("\\s*/at\\s*|\\n");
                String atData;
                LocalDate atDate;
                try {
                    action = ui.getNext();
                    dateInput = ui.getNext();
                    atDate = LocalDate.parse(dateInput);
                } catch (Exception e) {
                    throw new DukeException("Oops, invalid format! Try the following format: "
                            + "event {task name} /at YYYY-MM-DD");
                }
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

                if (output.equals("")) {
                    throw new DukeException("Oops, we can't find the item you're looking for! "
                            + "Perhaps check your spelling?");
                }
                return output;

            case "bye":
                return DukeUi.BYE_MESSAGE;

            case "delete":
                int delIdx = ui.getNextInt() - 1;
                if (delIdx >= tasks.getSize() || delIdx < 0) {
                    throw new DukeException("Oops! Target object is out of bounds!");
                }
                Task delTask = tasks.getTask(delIdx);
                tasks.deleteTask(delIdx);
                storage.saveToFile(tasks.printList());
                return String.format("Deleted: %s\n\n", delTask.toString());

            case "archive":
                storage.archive();
                return "Task list archived to data/archive.txt";

            default:
                throw new DukeException(
                        "Oops! Invalid commmand word, perhaps you would want to try on of the following: "
                                + "1.todo 2.deadline 3.event 4.list 5.done 6.delete 7.archive 8.bye");

        }
    }
}
