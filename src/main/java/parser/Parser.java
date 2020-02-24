package parser;

import command.AddCommand;
import command.Command;
import command.DeleteAllCommand;
import command.DeleteCommand;
import command.DeleteSomeCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;

import dukeexception.DukeException;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a Parser class which parses the command and makes sense of the input string into commands.
 */
public class Parser {

    /**
     * Method to return if the string is actually an integer.
     * @param string input string
     * @return boolean to determine if string is numeric
     */
    public static boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        try {
            int value = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Represents a method which takes in user input and parses it, then returning the appropriate command.
     * @param userInput user input.
     * @return returns a command based off input.
     * @throws DukeException throws an exception when input is not aligned with format
     */
    public static Command parseCommand(String userInput) throws DukeException {
        String[] split = userInput.split(" ", 2);
        String first = split[0];
        switch (first) {
        case "bye":
            return new ExitCommand();

        case "done":
            int doneId = Integer.parseInt(split[1]);
            return new DoneCommand(doneId);

        // 3 cases, delete 2, delete all, delete 1 3 5 7
        case "delete":
            if (isNumeric(split[1])) {
                int deleteId = Integer.parseInt(split[1]);
                return new DeleteCommand(deleteId);
            } else if (split[1].equals("all")) {
                return new DeleteAllCommand();
            } else {
                ArrayList<Integer> idOfTaskListToBeDeleted = new ArrayList<>();
                String stringOfNums = split[1];
                String[] stringArrayOfNums = stringOfNums.split(" ");
                System.out.println(stringArrayOfNums.length);
                for (String s : stringArrayOfNums) {
                    int num = Integer.parseInt(s);
                    idOfTaskListToBeDeleted.add(num);
                }
                // reverse order of deletion
                Collections.sort(idOfTaskListToBeDeleted, Collections.reverseOrder());
                return new DeleteSomeCommand(idOfTaskListToBeDeleted);
            }

        case "list":
            return new ListCommand();

        case "event": // event some meeting /at 2020-01-01
            if (split.length < 2) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            } else {
                String[] splitTaskName = split[1].split("/"); // to obtain command when splitting
                String eventDescription = splitTaskName[0];
                String[] splitTaskName2 = splitTaskName[1].split(" ", 2); // to obtain date when splitting
                String eventDate = splitTaskName2[1];
                return new AddCommand(new Event(eventDescription, eventDate));
            }

        case "deadline":
            if (split.length < 2) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String[] splitTaskName3 = split[1].split("/"); // to obtain command when splitting
                String deadlineDescription = splitTaskName3[0];
                String[] splitTaskName4 = splitTaskName3[1].split(" ", 2); // to obtain date when splitting
                String deadlineDate = splitTaskName4[1];
                return new AddCommand(new Deadline(deadlineDescription, deadlineDate));
            }

        case "todo":
            if (split.length < 2) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new AddCommand(new Todo(split[1]));
            }

        case "find":
            String keyword = split[1];
            return new FindCommand(keyword);

        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}