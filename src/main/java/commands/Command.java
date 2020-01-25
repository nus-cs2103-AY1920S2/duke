package commands;

import exceptions.DukeException;
import processor.DukeProcessor;

public interface Command {

    public void execute(DukeProcessor processor, String args) throws DukeException;
}
