package duke.main;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.ui.Ui;
import duke.exception.InvalidCommandException;
import duke.exception.OutOfBoundMarkingRequestException;
import duke.exception.TaskErrorException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String BOT_NAME = "Duke";
    public static final String LIST_COMMAND = "list";
    public static final String BYE_COMMAND = "bye";

    //    private static String PADDING = "       ";
    private static String PADDING = "";
    private String USELESS_LINE = "-------------------------------------------------------------------------------------";
    private String ADDED_PHRASE = "added: ";
    private ArrayList<Task> storedItems;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor
     */
    public Duke() {
        // Place-holder constructor, may need to extend later
        storedItems = new ArrayList<>();
        storage = new Storage();
        storage.getDataFile(storedItems);
        parser = new Parser();
    }

    /**
     * Let the bot greet, calls to Ui method
     */
    public String greet() {
        return Ui.greet();
    }

    /**
     * Takes in the user input, run the Parser to determine what it means and acts accordingly
     * @param str
     * @throws InvalidCommandException
     * @throws OutOfBoundMarkingRequestException
     * @throws TaskErrorException
     */
    public String processUserInput(String str) throws InvalidCommandException, OutOfBoundMarkingRequestException, TaskErrorException {
        if (str.equals("")) {
            return Ui.blankInput();
//            return;
        }

        if (parser.isFindRequest(str)) {
            return TaskList.findItem(str, storedItems);
//            return;
        }

        int markPos = parser.isMarkingTaskRequest(str);
        int delPos = parser.isDeleteTaskRequest(str);
        String ret = "";

        if (markPos != -2) {
            try {
                ret = TaskList.markItemAsDone(markPos, storedItems);
            } catch (OutOfBoundMarkingRequestException e) {
                System.out.println(
                        String.format("markPos error\n%s%s\n%s%s\n%s%s",
                                PADDING, USELESS_LINE, PADDING, e, PADDING, USELESS_LINE));
            }
        } else if (delPos != -2) {
            try {
                ret = TaskList.deleteItem(delPos, storedItems);
            } catch (OutOfBoundMarkingRequestException e) {
                System.out.println(
                        String.format("delPos error\n%s%s\n%s%s\n%s%s",
                                PADDING, USELESS_LINE, PADDING, e, PADDING, USELESS_LINE));
            }
        } else {
            try {
                Task.TaskType type = parser.commandType(str);
                switch (type) {
                    case TODO:
                        ret = handleToDo(str);
                        break;
                    case DEADLINE:
                        ret = handleDeadline(str);
                        break;
                    case EVENT:
                        ret = handleEvent(str);
                        break;
                    default:
                        throw new InvalidCommandException(str);
                }
            } catch (InvalidCommandException e) {
                System.out.println(String.format("%s%s\n%s%s\n%sPlease type something legit\n%s%s",
                        PADDING, USELESS_LINE, PADDING, e, PADDING, PADDING, USELESS_LINE));
            } catch (TaskErrorException e) {
                System.out.println(String.format("%s%s\n%s%s\n%s%s",
                        PADDING, USELESS_LINE, PADDING, e, PADDING, USELESS_LINE));
            }
        }

//        storage.writeData(storedItems);
        return ret;
    }

    // Not very optimal handling these 3 methods are...
    // But usable hmm

    private String handleToDo(String str) throws TaskErrorException {
        Scanner sc = new Scanner(str);
        sc.next();
        if (!sc.hasNext())
            throw new TaskErrorException("Missing ToDo description");
        Task todo = new ToDo(sc.nextLine().trim());
//        storeUserInput(todo);
        sc.close();
        return storeUserInput(todo);
    }

    private String handleDeadline(String str) throws TaskErrorException, InvalidCommandException {
        Scanner sc = new Scanner(str);
        sc.next();
        String[] parts = sc.nextLine().trim().split("/");

        if (parts.length == 1 || parts[1].length() == 0 || !parts[1].contains("by") || parts[1].equals("by"))
            throw new TaskErrorException("Missing Deadline 'By' time");
        if (!parts[1].substring(0, 2).equals("by"))
            throw new InvalidCommandException(str);
        if (parts[0].equals(""))
            throw new TaskErrorException("Missing Deadline description");

        Task deadline = new Deadline(parts[0], parts[1]);
//        storeUserInput(deadline);
        sc.close();
        return storeUserInput(deadline);
    }

    private String handleEvent(String str) throws TaskErrorException, InvalidCommandException {
        Scanner sc = new Scanner(str);
        sc.next();
        String[] parts = sc.nextLine().trim().split("/");

        if (parts.length == 1 || parts[1].length() == 0 || parts[1].equals("at"))
            throw new TaskErrorException("Missing Event 'At' time");
        if (!parts[1].substring(0,2).equals("at"))
            throw new InvalidCommandException(str);
        if (parts[0].equals(""))
            throw new TaskErrorException("Missing Event description");

        Task event = new Event(parts[0], parts[1]);
//        storeUserInput(event);
        sc.close();
        return storeUserInput(event);
    }

    private String storeUserInput(Task task) {
        storedItems.add(task);
        storage.writeData(storedItems);
        return Ui.storeUserInput(task, storedItems);
    }

    /**
     * Calls Ui method to list tasks saved
     */
    public String listStoredItems() {
        return Ui.listStoredItems(storedItems);
    }

    /**
     * Bot says goodbye, also calls Ui method
     */
    public String byeBye() {
        storage.writeData(storedItems);
        return Ui.byeBye();
    }

    public String getResponse(String userInput) throws OutOfBoundMarkingRequestException, TaskErrorException, InvalidCommandException {
        if (userInput.equals(Duke.BYE_COMMAND))
            return byeBye();
        if (userInput.equals(Duke.LIST_COMMAND)) {
            return listStoredItems();
        }

        return processUserInput(userInput);
    }
}
