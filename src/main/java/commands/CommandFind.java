package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Ui;

public class CommandFind implements Command {

    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        String searchString = argsArray[1];

        Ui.print(String.format("We've found the following tasks related to your search (\"%s\"):", searchString));
        processor.getTaskList().printTasksContaining(searchString);
    }
}
