package duke;

public interface CommandMethod {
    String execute(Duke program, Command command) throws DukeException;
}