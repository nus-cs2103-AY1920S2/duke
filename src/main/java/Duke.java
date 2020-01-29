import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

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

    public Duke() {
        // Place-holder constructor, may need to extend later
        storedItems = new ArrayList<>();
        storage = new Storage();
        storage.getDataFile(storedItems);
        parser = new Parser();
    }

    public void greet() {
        Ui.greet();
    }

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

    public void listStoredItems() {
        Ui.listStoredItems(storedItems);
    }

    public void byeBye() {
        storage.writeData(storedItems);
        Ui.byeBye();
    }
}
