import java.io.IOException;


public class Parser {
    public Parser() {
        //deals with making sense of the user command
    }

    /**
     * Returns the corresponding command for the user's input.
     * Aims to sort user command into List, Delete, Done and Add.
     * Add consist of everything else, including illegal commands, which will be handled in the TaskList class.
     *
     * @param command The user's input.
     * @return Returns a string stating if its a list, done, delete, or add (todo, event, deadline) command.
     */
    public String makeSenseOfUserCommand(String command) {
        command = command.trim();

        if (command.equals("list")) {
            return Ui.LIST;
        } else {
            //ADD, DONE, DELETE,
            if (command.length() >= 4 && command.substring(0, 4).equals("done")) {
                return Ui.DONE;
            } else if (command.length() >= 4 && command.substring(0, 4).equals("find")) {
                return  Ui.FIND;
            } else if (command.length() >= 6 && command.substring(0, 6).equals("delete")) {
                return Ui.DELETE;
            } else {
                return Ui.ADD;
            }
        }
    }
}
