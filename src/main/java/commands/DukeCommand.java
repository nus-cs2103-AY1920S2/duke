package commands;

import exceptions.DukeException;
import main.DukeProcessor;

public interface DukeCommand {

    public void execute(DukeProcessor processor, String args) throws DukeException;
}
