package command;

public class Parser {
    private FriendlierSyntax friendlierSyntax;

    public Parser(Duke duke) {
        friendlierSyntax = duke.getFriendlierSyntax();
    }

    public Parser() {

    }

    /**
     * Returns the first command input by the user.
     *
     * @param input input specified by user.
     * @return the first command input by the user.
     */
    public String getCommand(String input) {
        return friendlierSyntax.findCommand(input.split(" ")[0]);
    }

    /**
     * Splits the user input to return the description.
     *
     * @param input input specified by user.
     * @return description for command.
     */
    public String getDescription(String input) {
        return input.split(" ", 2)[1];
    }

    /**
     * Checks if command contains description.
     *
     * @param input input specified by user.
     * @return true if the input has a description.
     */
    public boolean hasDescription(String input) {
        return input.split(" ").length > 1;
    }

    /**
     * Validates user input by verifying that all arguments are present for each command.
     *
     * @param input input specified by user.
     */
    public void validateInput(String input) {
        String[] parsedInput = input.split(" ", 2);
        String command = getCommand(input);
        switch (friendlierSyntax.findCommand(command)) {
            case "bye":
                return;
            case "alias":
                if (parsedInput.length < 2) {
                    throw new DukeException("Pawdon me, I think you furgot to include the alias and command");
                } else {
                    return;
                }
            case "list":
                return;
            case "hello":
                return;
            case "delete":
                if (parsedInput.length < 2) {
                    throw new DukeException("Pawdon me, I think you furgot to include the task number.");
                } else {
                    return;
                }
            case "clear":
                return;
            case "done":
                if (parsedInput.length < 2) {
                    throw new DukeException("Pawdon me, I think you furgot to include the task number.");
                } else {
                    return;
                }
            case "find":
                if (parsedInput.length < 2) {
                    throw new DukeException("Pawdon me, I think you furgot to include the keyword.");
                } else {
                    return;
                }
            case "help":
                return;
            case "tag":
                if (parsedInput.length < 2) {
                    throw new DukeException("Pawdon me, I think you furgot to include the tag.");
                } else {
                    return;
                }
            case "todo":
                if (parsedInput.length < 2) {
                    throw new DukeException("\tPawdon me, I think you furgot to include the description.");
                } else {
                    return;
                }
            case "deadline":
                if (parsedInput.length < 2) {
                    throw new DukeException("Pawdon me, "
                            + " I think you furgot to include the description of the deadline.");
                } else if (parsedInput[1].split("/").length < 1) {
                    throw new DukeException("Pawdon me, "
                            + "I think you fur-got to include the date of the deadline");
                } else {
                    return;
                }
            case "event":
                if (parsedInput.length < 2) {
                    throw new DukeException("Pawdon me, "
                            + "I think you furgot to include the description of the event.");
                } else if (!parsedInput[1].contains("/")) {
                    throw new DukeException("Pawdon me, "
                            + "I think you furgot to include the date of the event.");
                } else {
                    return;
                }
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :(");
        }
    }

    /**
     * Checks if user input any tags through number of arguments.
     *
     * @param input input specified by user.
     * @return true if user input tags and false otherwise.
     */
    public boolean checkForTags(String input) {
        return input.contains("/t");
    }
}
