package duke.parser;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import duke.command.*;

import java.io.IOException;

/** Parser to interpret and execute commands. */
public class Parser {

    /**
     * Interprets user input and creates the appropriate command.
     *
     * @param input    User input during runtime.
     * @param storage  Storage object to save the task list that is updated in this method.
     * @param taskList Task list object to store the tasks that are created from user command in this method.
     * @return An appropriate command.
     * @throws DukeException If there is an error in parsing user input
     * @throws IOException   If there is an error in saving task list.
     */
    public static Command parseUserInput(String input, Storage storage, TaskList taskList) throws DukeException {

        String[] inputArr = input.split(" ");
        String instruction = inputArr[0];

        Command command = null;

        switch (instruction) {

        case "done": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please specify a task number to mark as done!");
            }

            int taskNumber = Integer.parseInt(inputArr[1]);

            if (taskNumber > taskList.getSize()) {
                throw new DukeException("☹ OOPS!!! Please specify a valid task number!");
            }

            command = new DoneCommand(taskNumber);

            break;
        }

        case "delete": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please specify a task number to be deleted!");
            }

            int taskNumber = Integer.parseInt(inputArr[1]);

            if (taskNumber > taskList.getSize()) {
                throw new DukeException("☹ OOPS!!! Please specify a valid task number!");
            }

            command = new DeleteCommand(taskNumber);

            break;
        }

        case "todo": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please enter the descriptions for your to-do list!");
            }

            // Pre-processing
            int idx = input.indexOf(" ");
            String taskInput = input.substring(idx + 1);
            String[] taskInputArr = taskInput.split("/");

            // Create task
            String taskName = taskInputArr[0];
            Task newTask = new ToDo(taskName);

            command = new AddCommand(newTask);

            break;
        }

        case "deadline": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please enter the descriptions for your to-do list!");
            }

            // Pre-processing
            int idx = input.indexOf(" ");
            String taskInput = input.substring(idx + 1);
            String[] taskInputArr = taskInput.split("/");
            String taskName = taskInputArr[0];
            String dateTime = taskInputArr[1];
            idx = dateTime.indexOf(" ");
            dateTime = dateTime.substring(idx + 1);

            // Create task
            Task newTask = new Deadline(taskName, dateTime);

            command = new AddCommand(newTask);

            break;
        }

        case "event": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please enter the descriptions for your to-do list!");
            }

            // Pre-processing
            int idx = input.indexOf(" ");
            String taskInput = input.substring(idx + 1);
            String[] taskInputArr = taskInput.split("/");
            String taskName = taskInputArr[0];
            String dateTime = taskInputArr[1];
            idx = dateTime.indexOf(" ");
            dateTime = dateTime.substring(idx + 1);

            // Create task
            Task newTask = new Event(taskName, dateTime);

            command = new AddCommand(newTask);

            break;
        }

        case "find": {

            if (inputArr.length <= 1) {
                throw new DukeException("☹ OOPS!!! Please enter a keyword so that we can retrieve the appropriate task(s)!");
            }

            // Pre-processing
            int idx = input.indexOf(" ");
            String keyword = input.substring(idx + 1);

            command = new FindCommand(keyword);

            break;
        }

        case "list": {
            command = new ListCommand();
            break;
        }

        case "bye": {
            command = new ByeCommand();
            break;
        }

        default: {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        }

        return command;

    }

}
