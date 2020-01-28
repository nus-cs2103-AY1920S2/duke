public class Parser {
    public Parser() {

    }

    public String processCommand(String command, String[] inputs)
            throws EmptyDescriptionException, InvalidCommandException, InvalidTaskInputException,
            TaskIndexOutOfBoundsException {
        if (command.equalsIgnoreCase("todo")) {
            if (inputs.length == 1) {
                throw new EmptyDescriptionException();
            }
            return Ui.TODO;
        } else if (command.equalsIgnoreCase("deadline")) {
            if (inputs.length == 1) {
                throw new EmptyDescriptionException();
            }
            return Ui.DEADLINE;
        } else if (command.equalsIgnoreCase("event")) {
            if (inputs.length == 1) {
                throw new EmptyDescriptionException();
            }
            return Ui.EVENT;
        } else if (command.equalsIgnoreCase("list")) {
            return Ui.LIST;
        } else if (command.equalsIgnoreCase("done")) {
            if (inputs.length == 1) {
                throw new EmptyDescriptionException();
            } else if (!isNumeric(inputs[1])) {
                throw new InvalidTaskInputException();
            }
            int index = Integer.parseInt(inputs[1]);
            if (index < 1 || index > TaskList.tasks.size()) {
                throw new TaskIndexOutOfBoundsException();
            }
            return Ui.DONE;
        } else if (command.equalsIgnoreCase("delete")) {
            if (inputs.length == 1) {
                throw new EmptyDescriptionException();
            } else if (!isNumeric(inputs[1])) {
                throw new InvalidTaskInputException();
            }
            int index = Integer.parseInt(inputs[1]);
            if (index < 1 || index > TaskList.tasks.size()) {
                throw new TaskIndexOutOfBoundsException();
            }
            return Ui.DELETE;
        } else {
            throw new InvalidCommandException();
        }
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int intNum = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
