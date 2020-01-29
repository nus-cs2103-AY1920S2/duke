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

    public Duke() {
        // Place-holder constructor, may need to extend later
        storedItems = new ArrayList<>();
        getDataFile();
    }

    private void getDataFile() {
        String home = System.getProperty("user.home");

        Path dirPath = Paths.get(home, "Documents", "Duke");
        Path filePath = Paths.get(home, "Documents", "Duke", "dukeData.txt");
        if (Files.exists(dirPath)) {
            if (Files.notExists(filePath))
                try {
                    Files.createFile(filePath);
                } catch (IOException e) {
                    System.out.println(e);
                    exit(1);
                }

            readDataFile(filePath);
        } else {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                System.out.println(e);
                exit(1);
            } finally {
                getDataFile();
            }
        }
    }

    private void writeData() {
        String home = System.getProperty("user.home");
        Path filePath = Paths.get(home, "Documents", "Duke", "dukeData.txt");

        StringBuilder data = new StringBuilder();
        for (Task item : storedItems) {
            data.append(item.encoder());
        }

        try {
            Files.writeString(filePath, data.toString());
        } catch (IOException e) {
            System.out.println(e);
            exit(1);
        }
    }

    private void readDataFile(Path filePath) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println(e);
            exit(1);
        } finally {
            if (lines.isEmpty())
                return;

            for (String line : lines) {
                String[] lineParts = line.split(":");
                if (lineParts[0].equals("T"))
                    storedItems.add(new Task(lineParts[1], Integer.parseInt(lineParts[2]) == 1));
                else if (lineParts[0].equals("D"))
                    storedItems.add(new Deadline(lineParts[1], Integer.parseInt(lineParts[2]) == 1, lineParts[3]));
                else if (lineParts[0].equals("E"))
                    storedItems.add(new Event(lineParts[1], Integer.parseInt(lineParts[2]) == 1, lineParts[3]));
            }
        }
    }

    public void greet() {
        System.out.println(padding + uselessLine + "\n" +
                padding + "Greetings! This is " + botName + ", and I am your friend!\n" +
                padding + "You don't have to be formal. Relax and tell me how I can help you\n" +
                padding + uselessLine);
    }

    public void processUserInput(String str) throws InvalidCommandException, OutOfBoundMarkingRequestException, TaskErrorException {
        if (str.equals("")) {
            System.out.println(padding + uselessLine + "\n" +
                    padding + "Please type something. Don't leave it blank, plsss!\n" +
                    padding + uselessLine);
            return;
        }

        int markPos = isMarkingTaskRequest(str);
        int delPos = isDeleteTaskRequest(str);
        if (markPos != -2) {
            try {
                markItemAsDone(markPos);
            } catch (OutOfBoundMarkingRequestException e) {
                System.out.println(
                        String.format("markPos error\n%s%s\n%s%s\n%s%s",
                                padding,uselessLine,padding,e,padding,uselessLine));
            }
        } else if (delPos != -2) {
            try {
                deleteItem(delPos);
            } catch (OutOfBoundMarkingRequestException e) {
                System.out.println(
                        String.format("delPos error\n%s%s\n%s%s\n%s%s",
                                padding,uselessLine,padding,e,padding,uselessLine));
            }
        } else {
            try {
                Task.TaskType type = commandType(str);
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
        writeData();
    }


    private Task.TaskType commandType(String str) {
        Task.TaskType ret = Task.TaskType.unknown;
        Scanner sc = new Scanner(str);

        while (sc.hasNext()) {
            String ss = sc.next();
            if (ss.equals(Task.toDoCommand))
                ret = Task.TaskType.toDo;
            else if (ss.equals(Task.deadlineCommand))
                ret = Task.TaskType.deadline;
            else if (ss.equals(Task.eventCommand))
                ret = Task.TaskType.event;
            break;
        }

        sc.close();
        return ret;
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

    private void deleteItem(int pos) throws OutOfBoundMarkingRequestException {
        if (pos >= storedItems.size() || pos < 0)
            throw new OutOfBoundMarkingRequestException(pos+1);
        Task t = storedItems.remove(pos);
        System.out.println(String.format("%s%s\n%sI've removed this task for you\n%s   %s\n%sYou have %d tasks left\n%s%s",
                padding, uselessLine, padding, padding, t.toString(),padding, storedItems.size(), padding, uselessLine));
    }

    private void markItemAsDone(int pos) throws OutOfBoundMarkingRequestException {
        if (pos >= storedItems.size() || pos < 0)
            throw new OutOfBoundMarkingRequestException(pos+1);
        storedItems.get(pos).markDone();
        System.out.println(padding + uselessLine + "\n" +
                padding + "Nice nice. I've marked the task as done for you.\n" +
                padding + "   " + storedItems.get(pos) + "\n" +
                padding + uselessLine);
    }

    private int isDeleteTaskRequest(String str) {
        int ret = -2;
        String ss = "";
        Scanner sc = new Scanner(str);

        while (sc.hasNext()) {
            ss = sc.next();
            if (!ss.equals("delete"))
                break;
            if (!sc.hasNextInt())
                break;
            ret = sc.nextInt() - 1;
            if (sc.hasNext())
                ret = -2;
            break;
        }

        sc.close();
        return ret;
    }

    private int isMarkingTaskRequest(String str) {
        int ret = -2;
        String ss = "";
        Scanner sc = new Scanner(str);

        while (sc.hasNext()) {
            ss = sc.next();
            if (!ss.equals("done"))
                break;
            if (!sc.hasNextInt())
                break;
            ret = sc.nextInt() - 1;
            if (sc.hasNext())
                ret = -2;
            break;
        }

        sc.close();
        return ret;
    }

    private void storeUserInput(Task task) {
        storedItems.add(task);
        System.out.println(padding + uselessLine + "\n" +
                padding + addedPhrase + task + "\n" +
                padding + "Now you have " + storedItems.size() + " tasks\n" +
                padding + uselessLine);
    }

    public void listStoredItems() {
        System.out.println(padding + uselessLine);
        if (storedItems.isEmpty()) {
            System.out.println(padding + "Your list is empty!");
        } else {
            System.out.println(padding + "Here is your list:");
            int i = 1;
            for (Task task : storedItems) {
                System.out.println(padding + i + ". " + task);
                i++;
            }
        }
        System.out.println(padding + uselessLine);
    }

    private void echo(String str) {
        System.out.println(padding + uselessLine + "\n" +
                padding + str + "\n" +
                padding + uselessLine);
    }

    public void byeBye() {
        writeData();
        System.out.println(padding + uselessLine + "\n" +
                padding + "Bye-bye. It was nice talking to you. See ya soon!\n" +
                padding + uselessLine);
    }
}
