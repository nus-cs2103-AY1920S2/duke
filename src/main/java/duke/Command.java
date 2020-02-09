package duke;

import java.io.IOException;

public interface Command {

    public void execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException;

    public boolean isExit();

}
