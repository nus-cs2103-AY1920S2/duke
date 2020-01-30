package duke.main;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
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
    public static final String botName = "Duke";
    public static final String listCommand = "list";
    public static final String byeCommand = "bye";

    private String padding = "       ";
    private String uselessLine = "-------------------------------------------------------------------------------------";
    private String addedPhrase = "added: ";
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
    public void greet() {
        Ui.greet();
    }

    /**
     * Takes in the user input, run the Parser to determine what it means and acts accordingly
     * @param str
     * @throws InvalidCommandException
     * @throws OutOfBoundMarkingRequestException
     * @throws TaskErrorException
     */
    public void processUserInput(String str) throws InvalidCommandException, OutOfBoundMarkingRequestException, TaskErrorException {
        if (str.equals("")) {
            Ui.blankInput();
            return;
        }

        int markPos = parser.isMarkingTaskRequest(str);
        int delPos = parser.isDeleteTaskRequest(str);
        if (markPos != -2) {
            try {
                TaskList.markItemAsDone(markPos, storedItems);
            } catch (OutOfBoundMarkingRequestException e) {
                System.out.println(
                        String.format("markPos error\n%s%s\n%s%s\n%s%s",
                                padding,uselessLine,padding,e,padding,uselessLine));
            }
        } else if (delPos != -2) {
            try {
                TaskList.deleteItem(delPos, storedItems);
            } catch (OutOfBoundMarkingRequestException e) {
                System.out.println(
                        String.format("delPos error\n%s%s\n%s%s\n%s%s",
                                padding,uselessLine,padding,e,padding,uselessLine));
            }
        } else {
            try {
                Task.TaskType type = parser.commandType(str);
                switch (type) {
                    case toDo:
                        handleToDo(str);
                        break;
                    case deadline:
                        handleDeadline(str);
                        break;
                    case event:
                        handleEvent(str);
                        break;
                    default:
                        throw new InvalidCommandException(str);
                }
            } catch (InvalidCommandException e) {
                System.out.println(String.format("%s%s\n%s%s\n%sPlease type something legit\n%s%s",
                        padding,uselessLine,padding,e,padding,padding,uselessLine));
            } catch (TaskErrorException e) {
                System.out.println(String.format("%s%s\n%s%s\n%s%s",padding,uselessLine,padding,e,padding,uselessLine));
            }
        }
        storage.writeData(storedItems);
    }

    // Not very optimal handling these 3 methods are...
    // But usable hmm

    private void handleToDo(String str) throws TaskErrorException {
        Scanner sc = new Scanner(str);
        sc.next();
        if (!sc.hasNext())
            throw new TaskErrorException("Missing ToDo description");
        Task todo = new ToDo(sc.nextLine().trim());
        storeUserInput(todo);
        sc.close();
    }

    private void handleDeadline(String str) throws TaskErrorException, InvalidCommandException {
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
        storeUserInput(deadline);
        sc.close();
    }

    private void handleEvent(String str) throws TaskErrorException, InvalidCommandException {
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
        storeUserInput(event);
        sc.close();
    }

    private void storeUserInput(Task task) {
        storedItems.add(task);
        Ui.storeUserInput(task, storedItems);
    }

    /**
     * Calls Ui method to list tasks saved
     */
    public void listStoredItems() {
        Ui.listStoredItems(storedItems);
    }

    /**
     * Bot says goodbye, also calls Ui method
     */
    public void byeBye() {
        storage.writeData(storedItems);
        Ui.byeBye();
    }
}
