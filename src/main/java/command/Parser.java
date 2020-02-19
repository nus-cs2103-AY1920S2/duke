package command;

import java.util.Arrays;

public class Parser {
    private FriendlierSyntax friendlierSyntax;

    public Parser() {
        friendlierSyntax = new FriendlierSyntax();
    }

    public String getCommand(String input) {
        return friendlierSyntax.findCommand(input.split(" ")[0]);
    }

    public String getDescription(String input) {
        return input.split(" ", 2)[1];
    }

    public boolean hasDescription(String input) {
        return input.split(" ").length > 1;
    }

    public void validateInput(String input) {
        String[] parsedInput = input.split(" ", 2);
        String command = getCommand(input);
        switch (friendlierSyntax.findCommand(command)) {
            case "bye":
                return;
            case "alias":
                if (parsedInput.length < 2) {
                    throw new DukeException("Please include alias and command.");
                } else {
                    return;
                }
            case "list":
                return;
            case "delete":
                if (parsedInput.length < 2) {
                    throw new DukeException("\tThe task number cannot be empty.");
                } else {
                    return;
                }
            case "clear":
                return;
            case "done":
                if (parsedInput.length < 2) {
                    throw new DukeException("\tThe task number cannot be empty.");
                } else {
                    return;
                }
            case "find":
                if (parsedInput.length < 2) {
                    throw new DukeException("\tPlease indicate a keyword.");
                } else {
                    return;
                }
            case "tag":
                if (parsedInput.length < 2) {
                    throw new DukeException("\tPlease indicate a tag.");
                } else {
                    return;
                }
            case "todo":
                if (parsedInput.length < 2) {
                    throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    return;
                }
            case "deadline":
                if (parsedInput.length < 2) {
                    throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
                } else if (parsedInput[1].split("/").length < 1) {
                    throw new DukeException("\tThe date of a deadline cannot be empty.");
                } else {
                    return;
                }
            case "event":
                if (parsedInput.length < 2) {
                    throw new DukeException("\t☹ OOPS!!! The description of a event cannot be empty.");
                } else if (parsedInput[1].split("/").length < 2) {
                    throw new DukeException("\t☹ OOPS!!! The date of a event cannot be empty.");
                } else {
                    return;
                }
            default:
                throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public boolean checkForTags(String input) {
        return input.split(" ").length > 2;
    }
}
