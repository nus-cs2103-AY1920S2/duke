import java.io.IOException;

/**
 * Deals with interactions with the user
 */
public class Ui {
    Parser parser;
    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";
    public static final String DONE = "done";

    public Ui() {
        parser = new Parser();
    }

    public void greet() {
        System.out.println("Hi there, I'm Dodo!\nHow may I help you today?");
    }

    public void showLoadingError() {
        System.out.println("Loading error. Try again!");
    }

    public void handleCommands(String[] inputs, TaskList taskList) throws EmptyDescriptionException,
            InvalidTaskInputException, InvalidCommandException, TaskIndexOutOfBoundsException, IOException, InvalidDateException {
        String command = parser.processCommand(inputs[0].trim(), inputs);
        if (command.equals(TODO)) {
            String desc = inputs[1];
            taskList.addTodo(desc, "N");
        } else if (command.equals(DEADLINE)) {
            String desc = inputs[1];
            taskList.addDeadline(desc, "N");
        } else if (command.equals(EVENT)) {
            String desc = inputs[1];
            taskList.addEvent(desc, "N");
        } else if (command.equals(LIST)) {
            taskList.printList();
        } else if (command.equals(DONE)) {
            int index = Integer.parseInt(inputs[1]);
            taskList.markTaskAsDone(index);
        } else if (command.equals(DELETE)) {
            int index = Integer.parseInt(inputs[1]);
            taskList.deleteTask(index);
        } else {
            throw new InvalidCommandException();
        }
    }

}
