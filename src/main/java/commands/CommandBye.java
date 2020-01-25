package commands;

import processor.DukeProcessor;
import processor.Ui;

public class CommandBye implements Command {

    public void execute(DukeProcessor processor, String args) {
        Ui.print("Ok see you! Hopefully I could help you in some way! :)");
        processor.disable();
    }
}
