package duke;

import java.util.ArrayList;

/**
 * FindCommand class that inherits from Command.
 */
public class FindCommand extends Command {
    protected String description;
    ArrayList<Task> foundList = new ArrayList<>();

    /**
     * FindCommand constructor.
     * @param description description of findCommand
     */
    public FindCommand(String description) {
        this.description = description;
    }

    public void execute(Ui ui, Storage storage, TaskList taskList) {
        for(int i = 0; i < taskList.getTaskListSize(); i ++) {
            if (taskList.getTask(i).description.contains(getDescription())) {
                foundList.add(taskList.getTask(i));
            }
        }
        if (foundList.size() != 0) {
            ui.printFound();
            for (int j = 0; j < foundList.size(); j++) {
                System.out.println((j + 1) + ". " + foundList.get(j).toString());
            }
        } else {
            ui.printNotFound();
        }
    }

    /**
     * Method to return the description.
     * @return returns the description in the class
     */
    public String getDescription() {
        return description;
    }
}
