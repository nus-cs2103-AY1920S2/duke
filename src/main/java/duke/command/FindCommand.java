package duke.command;


import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList tasklist) throws DukeException {
        String description = splitDoneString("find ", this.userInput, tasklist, ui);
        TaskList found_tasks = new TaskList();

        for(Task k : tasklist.getList()) {
            if(k.getDescription().contains(description)) {
                found_tasks.addToList(k);
            }
        }

        if(found_tasks.getList().isEmpty()) {
            throw new DukeException("There are no tasks with this keyword :'( ");
        } else {
            ui.printList(found_tasks);
        }

    }

    private String splitDoneString(String regrexWanted, String userInput, TaskList taskList, Ui ui) throws DukeException {
        String[] splittedString = userInput.split(regrexWanted);
        return splittedString[1];
    }

}
