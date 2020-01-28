import java.util.HashMap;

public class Parser {

    private Ui ui;
    private String fullcommand;
    private HashMap<String, String> commandpairing = new HashMap<String, String>();

    public Parser (String fullcommand, Ui ui) {
        this.fullcommand = fullcommand;
        this.ui = ui;

        commandpairing.put("list", "1");
        commandpairing.put("done", "2");
        commandpairing.put("todo", "2");
        commandpairing.put("event", "/at");
        commandpairing.put("deadline", "/by");
        commandpairing.put("delete", "2");
    }


    public boolean checkForError(TaskList tasklist) {
        String[] arguments = fullcommand.split(" ");

        try {
            if (!commandpairing.containsKey((arguments[0]).toLowerCase()))
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

            // Execute the error-checking
            DukeEnumExceptions.valueOf(arguments[0].toUpperCase())
                    .checkerror(fullcommand, commandpairing.get(arguments[0]), tasklist);
        } catch (DukeException ex){
            ui.showErrorMessage(ex.getMessage());
            return false;
        }

        return true;
    }

}
