package duke.parser;
import java.time.DateTimeException;
import java.time.LocalDate;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.dukeexception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
/**
 * Makes sense of the command entered by the user.
 */
public class Parser {
    /**
     * Returns the command to be executed.
     * If invalid command entered, a invalid command instruction is returned.
     * @param fullCommand The command entered by the user.
     * @return The command to be executed.
     * @throws DukeException If the user enters an invalid command.
     */
    public static Command parse(String fullCommand) throws DukeException, DateTimeException {
        String[] commandSplit = fullCommand.split(" ");
        String firstWord = commandSplit[0];
        switch(firstWord){
        case "todo":
            if (commandSplit.length < 2){
                throw new DukeException("todo");
            }
            else{
                String description = "";
                for(int i = 1; i < commandSplit.length; i++){
                    description += commandSplit[i] + " ";
                }
                return new AddCommand(new Todo(description));
            }
        case "deadline":
            String deadlineTask = "";
            for(int i = 1; i < commandSplit.length; i++){
                deadlineTask += commandSplit[i] + " ";
            }
            String deadlineDecription = deadlineTask.split("/by ")[0];
            String deadlineDate = deadlineTask.split("/by ")[1];
            return new AddCommand(new Deadline(deadlineDecription,
                    LocalDate.parse(deadlineDate.substring(0, deadlineDate.length() - 1))));
        case "event":
            String eventTask = "";
            for(int i = 1; i < commandSplit.length; i++){
                eventTask += commandSplit[i] + " ";
            }
            String eventDescription = eventTask.split("/at ")[0];
            String eventDate = eventTask.split("/at ")[1];
            return new AddCommand(new Event(eventDescription,
                    LocalDate.parse(eventDate.substring(0, eventDate.length() - 1))));
        case "list":
            return new ListCommand();
        case "delete":
            int deleteTaskNum = Integer.parseInt(commandSplit[1]);
            return new DeleteCommand(deleteTaskNum);
        case "done":
            int doneTaskNum = Integer.parseInt(commandSplit[1]);
            return new DoneCommand(doneTaskNum);
        case "find":
            String keyword = commandSplit[1];
            return new FindCommand(keyword);
        case "bye":
            return new ByeCommand();
        default:
            throw new DukeException("");
        }
    }
}
