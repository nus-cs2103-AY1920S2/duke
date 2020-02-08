package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList tasklist) throws DukeException {
        String description = obtainItemToBeFound("find ", this.userInput);
        TaskList foundTasks = new TaskList();

        for (Task k : tasklist.getList()) {
            if (k.getDescription().contains(description)) {
                foundTasks.addToList(k);
            }
        }

        if (foundTasks.getList().isEmpty()) {
            throw new DukeException("There are no tasks with this keyword :'( ");
        } else {
            assert (!foundTasks.getList().isEmpty()) : "The foundTasks list should be empty";
            return ui.printList(foundTasks);
        }

    }

    private String obtainItemToBeFound(String regrexWanted, String userInput) {
        String[] splittedString = userInput.split(regrexWanted);
        return Arrays.stream(splittedString)
                .filter(x -> x.equals(splittedString[1]))
                .collect(Collectors.joining());
    }

}
