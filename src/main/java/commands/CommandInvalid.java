package commands;

import main.DukeProcessor;

public class CommandInvalid implements DukeCommand {
    public void execute(DukeProcessor processor, String args) {
        System.out.println("Sorry, but I don't think you've entered a valid command! :(");
    }
}
