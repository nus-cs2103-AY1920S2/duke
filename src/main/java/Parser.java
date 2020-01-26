import java.io.IOException;


public class Parser {
    public Parser() {
        //deals with making sense of the user command
    }

    public String makeSenseOfUserCommand(String command) {
        command = command.trim();

        if (command.equals("list")) {
            return Ui.LIST;
        } else {
//            ADD,
//            DONE,
//            DELETE,
            if (command.length() >= 4 && command.substring(0, 4).equals("done")) {
                return Ui.DONE;
            } else if (command.length() >= 6 && command.substring(0, 6).equals("delete")) {
                return Ui.DELETE;
            } else {
                return Ui.ADD;
            }
        }
    }
}
