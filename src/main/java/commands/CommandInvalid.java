package commands;

import processor.DukeProcessor;
import processor.Ui;

public class CommandInvalid implements Command {
    public void execute(DukeProcessor processor, String args) {
        Ui.print("Sorry, but I don't think you've entered a valid command! :(");
    }
}
