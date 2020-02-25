package duke.main;

import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.SortCommand;

public class Parser {


    /**
     * Method that parses user input into instructions to be followed.
     *
     * @param input the user input
     * @param ui    current instance of ui so that ui can interact with user
     * @param tasks the tasks available in current duke.main.Duke program
     */
    public String parse(String input, Ui ui, TaskList tasks) {
        String command = "";

        try {
            command = getCommand(input);
        } catch (DukeException d) {
            return d.toString();
        }

        switch (command) {
        case "list": {
            return ListCommand.run(tasks);
        }
        case "todo": {
            String task = input.substring(input.indexOf(' ') + 1);
            return TodoCommand.run(tasks, task);
        }
        case "deadline": {
            String byDeadline = input.substring(input.indexOf('/') + 4);
            return DeadlineCommand.run(tasks, getTaskString(input), byDeadline);
        }
        case "event": {
            String atEvent = input.substring(input.indexOf('/') + 4);
            return EventCommand.run(tasks, getTaskString(input), atEvent);
        }
        case "delete": {
            return DeleteCommand.run(tasks, getTaskIndex(input));
        }
        case "done": {
            return DoneCommand.run(tasks, getTaskIndex(input));
        }
        case "find": {
            String keyword = input.substring(input.indexOf(' ') + 1);
            return FindCommand.run(tasks, keyword);
        }
        case "sort": {
            return SortCommand.run(tasks);
        }
        default:
            assert false;
            return "Invalid Command";
        }
    }

    public String getTaskString(String input) {
        return input.substring(input.indexOf(' ') + 1, input.indexOf('/') - 1);
    }

    public int getTaskIndex(String input) {
        return Integer.parseInt(input.substring(input.indexOf(' ') + 1, input.length())) - 1;
    }


    /**
     * Gets the command from input string for further processing.
     *
     * @param input is the user input
     * @return a String that is only the command
     * @throws DukeException is thrown when there is an invalid command by user
     */
    public String getCommand(String input) throws DukeException {
        if (!input.contains(" ")) {
            // check if the command is correct
            if (!input.equals("todo")
                    && !input.equals("deadline")
                    && !input.equals("event")
                    && !input.equals("delete")
                    && !input.equals("done")
                    && !input.equals("find")
                    && !input.equals("list")
                    && !input.equals("sort")) {
                throw new DukeException("Mister Excuse You, Command I Dont Understand!");
            } else {
                // command is either not valid, or is list
                if (!input.equals("list") && !input.equals("sort")) {
                    throw new DukeException("What is this! " + input + " cannot be empty okay?!");
                } else {
                    return input;
                }

            }
        } else {
            if (!input.substring(0, input.indexOf(' ')).equals("todo")
                    && !input.substring(0, input.indexOf(' ')).equals("deadline")
                    && !input.substring(0, input.indexOf(' ')).equals("event")
                    && !input.substring(0, input.indexOf(' ')).equals("delete")
                    && !input.substring(0, input.indexOf(' ')).equals("done")
                    && !input.substring(0, input.indexOf(' ')).equals("find")) {
                throw new DukeException("Mister Excuse You, Command I Dont Understand!");
            } else {
                return input.substring(0, input.indexOf(' '));
            }
        }
    }

}
