package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Storage;
import processor.Ui;

public class CommandTask implements Command {

    public void execute(DukeProcessor processor, String args) throws DukeException {

        Ui.print("You've now got " + processor.getTaskList().size() + " tasks in your list.");

        try {
            Storage.saveTasks(processor);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
