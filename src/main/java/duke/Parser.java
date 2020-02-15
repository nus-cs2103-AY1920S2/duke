package duke;

import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.command.TodoCommand;

/**
 * Interpret and process user inputs.
 */
public class Parser {
    protected TaskList tasks;
    protected Storage storage;

    /**
     * Saves tasks, storage and ui class.
     *
     * @param tasks The TaskList class.
     * @param storage The Storage class.
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Processes the user input.
     *
     * @param userInput The commands issued to duke by the user.
     * @return Returns the response generated from the corresponding commands.
     */
    public String parse(String userInput) throws DukeException {
        String msg;
        String input = userInput.trim().split(" ", 2)[0];
        switch (input.toUpperCase()) {
        case "LIST":
            msg = new ListCommand().execute(storage, tasks);
            break;
        case "DONE":
            msg = new DoneCommand(userInput, tasks.size()).execute(storage, tasks);
            break;
        case "DELETE":
            msg = new DeleteCommand(userInput, tasks.size()).execute(storage, tasks);
            break;
        case "TODO":
            msg = new TodoCommand(userInput).execute(storage, tasks);
            break;
        case "DEADLINE":
            msg = new DeadlineCommand(userInput).execute(storage, tasks);
            break;
        case "EVENT":
            msg = new EventCommand(userInput).execute(storage, tasks);
            break;
        case "FIND":
            msg = new FindCommand(userInput).execute(storage, tasks);
            break;
        case "SORT":
            msg = new SortCommand().execute(storage, tasks);
            break;
        case "BYE":
            msg = "Bye. Hope to see you again soon!";
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return msg;
    }
}