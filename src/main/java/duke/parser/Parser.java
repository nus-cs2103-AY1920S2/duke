package duke.parser;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Parser to interpret commands.
 */
public class Parser {

    /**
     * Parser to interpret user input and execute the appropriate command.
     *
     * @param input User input during runtime.
     * @param storage Storage object to save the task list that is updated in this method.
     * @param taskList Task list object to store the tasks that are created from user command in this method.
     * @return A boolean value to indicate whether to exit the program.
     * @throws DukeException Throws exception if there is an error in parsing user input
     * @throws IOException Throws exception if there is an error in saving task list.
     */
    public static boolean parseUserInput(String input, Storage storage, TaskList taskList) throws DukeException, IOException {

        String[] inputArr = input.split(" ");
        String instruction = inputArr[0];

        switch (instruction) {

        case "done": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please specify a duke.task number to mark as done!");
            }

            int taskNumber = Integer.parseInt(inputArr[1]);

            if (taskNumber > taskList.getSize()) {
                throw new DukeException("☹ OOPS!!! Please specify a valid duke.task number!");
            }

            Task task = taskList.completeTask(taskNumber);

            Ui.printMessage("Nice! I've marked this duke.task as done: \n" + task.toString());

            break;
        }

        case "delete": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please specify a duke.task number to be deleted!");
            }

            int taskNumber = Integer.parseInt(inputArr[1]);

            if (taskNumber > taskList.getSize()) {
                throw new DukeException("☹ OOPS!!! Please specify a valid duke.task number!");
            }

            Task task = taskList.removeTask(taskNumber);

            Ui.printMessage("Noted! I've removed this duke.task: \n"
                    + task.toString() + "\n"
                    + "Now you have " + taskList.getSize()
                    + " tasks in the list.");

            break;
        }

        case "todo": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please enter the descriptions for your to-do list!");
            }

            int idx = input.indexOf(" ");
            String taskInput = input.substring(idx + 1);
            String[] taskInputArr = taskInput.split("/");

            String taskName = taskInputArr[0];
            Task newTask = new ToDo(taskName);
            taskList.addTask(newTask);

            Ui.printMessage("Got it. I've added this duke.task: \n"
                    + newTask.toString() + "\n"
                    + "Now you have " + taskList.getSize()
                    + " tasks in the list.");

            break;
        }

        case "deadline": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please enter the descriptions for your to-do list!");
            }

            int idx = input.indexOf(" ");
            String taskInput = input.substring(idx + 1);
            String[] taskInputArr = taskInput.split("/");
            String taskName = taskInputArr[0];
            String dateTime = taskInputArr[1];
            idx = dateTime.indexOf(" ");
            dateTime = dateTime.substring(idx + 1);

            Task newTask = new Deadline(taskName, dateTime);
            taskList.addTask(newTask);

            Ui.printMessage("Got it. I've added this duke.task: \n"
                    + newTask.toString() + "\n"
                    + "Now you have " + taskList.getSize()
                    + " tasks in the list.");

            break;
        }

        case "event": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please enter the descriptions for your to-do list!");
            }

            int idx = input.indexOf(" ");
            String taskInput = input.substring(idx + 1);
            String[] taskInputArr = taskInput.split("/");
            String taskName = taskInputArr[0];
            String dateTime = taskInputArr[1];
            idx = dateTime.indexOf(" ");
            dateTime = dateTime.substring(idx + 1);

            Task newTask = new Event(taskName, dateTime);
            taskList.addTask(newTask);

            Ui.printMessage("Got it. I've added this duke.task: \n"
                    + newTask.toString() + "\n"
                    + "Now you have " + taskList.getSize()
                    + " tasks in the list.");

            break;
        }

        case "find": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please enter a keyword so that we can retrieve the appropriate task(s)!");
            }

            int idx = input.indexOf(" ");
            String keyword = input.substring(idx + 1);

            UI.printMessage("Here are the matching tasks in your list: \n" + taskList.searchTaskList(keyword));

            break;
        }

        case "list": {
            Ui.printMessage("Here are the tasks in your list: \n" + taskList.getTaskList());
            break;
        }

        case "bye": {
            Ui.printMessage("Bye. Hope to see you again soon!");
            return true;
        }

        default: {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        storage.saveTaskList(taskList);

        return false;

    }


}
