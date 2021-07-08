package duke.command;

import duke.exception.DukeException;
import duke.exception.FindException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList tasklist)
            throws FindException {
        String description = obtainItemToBeFound("find ", this.userInput);
        System.out.println("The description is " + description);
        TaskList foundTasks = new TaskList();
        String[] storageArray = storage.getStoredItems().split(System.lineSeparator());
        ArrayList<String> foundArray = new ArrayList<>();
        for (String s : storageArray) {
            if (s.contains(description)) {
                foundArray.add(s);
            }
        }
        if (foundTasks.getList().isEmpty() && foundArray.isEmpty()) {
            throw new FindException("There are no tasks with this keyword :'( ");
        } else {
            assert (!foundTasks.getList().isEmpty()) : "The foundTasks list should be empty";
            return ui.printFoundList(foundTasks, foundArray);
        }

    }

    private String obtainItemToBeFound(String regrexWanted, String userInput) {
        String[] splittedString = userInput.split(regrexWanted);
        return Arrays.stream(splittedString)
                .filter(x -> x.equals(splittedString[1]))
                .collect(Collectors.joining());
    }

}
