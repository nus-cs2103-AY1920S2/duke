import java.lang.String;

public class Parser {

    String[] commandArr;
    String command;
    String date;

    public Parser() {

    }

    public String parseCommand(String command) {
        this.command = command;
        int index = command.indexOf("/");

        if (index == -1) {
            return command;
        } else {
            int whiteSpaceIndex = command.indexOf(" ");
            return command.substring(whiteSpaceIndex, index);
        }
    }

    public String parseDate() {
        int index = command.indexOf("/");

        if (index == -1) {
            return null;
        }
        String substring = command.substring(index + 4);
        return substring;
    }


}
