package duke;

import java.io.IOException;

public class EditNameCommand implements Command {

    @Override
    public String execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        String[] split = task.split(" ", 4);

        if(split.length < 4) {
            throw new DukeException("Edit name command needs a task number and a new name");
        }

        int taskNumber = Integer.parseInt(split[2]);
        String newName = split[3];

        Task taskToEdit = taskList.getTask(taskNumber);
        String originalName = taskToEdit.getItem();
        taskToEdit.setItem(newName);
        storage.newSave(taskList);

        return "The task " + originalName + " has been changed to " + newName + "\n" + taskToEdit.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
