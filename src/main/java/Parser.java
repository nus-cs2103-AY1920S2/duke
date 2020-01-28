import java.util.Scanner;

public class Parser {
    public Parser() {
    }

    private Scanner sc = new Scanner(System.in);

    public String checkCommand(String command) throws DukeInvalidCommandException {
        if (command.split(" ").length == 1) {
            String wrongCmd = command.split(" ")[0];
            if (wrongCmd.equals("todo") || wrongCmd.equals("deadline") || wrongCmd.equals("event")) {
                throw new DukeInvalidCommandException("☹ OOPS!!! The description of a " + wrongCmd + " cannot be empty.");
            } else {
                throw new DukeInvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return command.split(" ")[0];
    }

    public String getCommand() {
        return sc.nextLine();
    }

}


