package commands;

import processor.DukeProcessor;

public class CommandList implements Command {

    public void execute(DukeProcessor processor, String args) {
        processor.getTaskList().printTasks();
    }
}
