package commands;

import main.DukeProcessor;

public class CommandInvalid implements DukeCommand {
    public void execute(DukeProcessor processor, String args) {
        System.out.println("The command you have entered is invalid! Try again?");
    }
}
