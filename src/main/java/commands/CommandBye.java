package commands;

import main.DukeProcessor;

public class CommandBye implements DukeCommand {

    public void execute(DukeProcessor processor, String args) {
        System.out.println("Ok see you! Hopefully I could help you in some way! :)");
        processor.disable();
    }
}
