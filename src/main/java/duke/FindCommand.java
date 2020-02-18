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

    /**
     * Execute the command.
     * @param ui Pass in Ui class
     * @param storage Pass in Storage class
     * @param taskList Pass in TaskList class
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        for(int i = 0; i < taskList.getTaskListSize(); i ++) {
            if (taskList.getTask(i).description.contains(getDescription())) {
                foundList.add(taskList.getTask(i));
            }
        }
        if (foundList.size() != 0) {
            String response;
            response =  ui.printFound() + "\n";
            for (int j = 0; j < foundList.size(); j++) {
                response += (j + 1) + ". " + foundList.get(j).toString() + "\n";
            }
            return response;
        } else {
            return ui.printNotFound();
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
