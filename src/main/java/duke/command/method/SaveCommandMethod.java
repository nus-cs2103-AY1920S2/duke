package duke.command.method;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeFileNotFoundException;
import duke.exception.DukeWriteFileException;

public class SaveCommandMethod implements CommandMethod {
    public static final String NAME = "save";

    public String execute(Duke program, Command command) throws DukeException {
        try {
            FileOutputStream fos = new FileOutputStream("data/data");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(program.getTaskList());
            oos.close();
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException(e);
        } catch (IOException e) {
            throw new DukeWriteFileException(e);
        }
        return "Saved tasks to file on hard disk";
    }
}
