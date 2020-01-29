package duke.other;

import duke.command.*;
public class Parser {
    public Parser() {
    }

    public static Command parse(String fullCommand) throws DukeException {
            String[] replyArr = fullCommand.split(" ");
            String instruction = replyArr[0];
            switch(instruction) {
                case "bye":
                    Command c = new AddCommand(instruction, details(replyArr));
                    c.isExit = true;
                    System.out.println("    Bye! See ya later, alligator!");
                    return c;
                case "delete":
                    return new DeleteCommand(instruction, replyArr);
                case "deadline":
                case "todo":
                case "event":
                    return new AddCommand(instruction, details(replyArr));
                case "list":
                case "date":
                case "done":
                    return new ShowCommand(instruction, replyArr);
                default:
                    return new ShowCommand(instruction, replyArr);
            }
    }

    public static String details(String[] replyArr) {
        String details = "";
        for(int i = 1; i < replyArr.length; i++) {
            details += " " + replyArr[i];
        }
        return details;
    }


}
