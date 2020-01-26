public class ByeCommandMethod implements CommandMethod {
    public static final String NAME = "bye";

    public String execute(Duke program, Command command) throws DukeException { 
        throw new DukeProgramTerminatedException();
    }
}