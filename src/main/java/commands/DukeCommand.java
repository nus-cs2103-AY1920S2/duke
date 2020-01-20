package commands;

import main.DukeProcessor;

public interface DukeCommand {

    public void execute(DukeProcessor processor, String args);
}
