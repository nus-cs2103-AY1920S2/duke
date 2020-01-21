import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String botName = "Duke";
    public static final String listCommand = "list";
    public static final String byeCommand = "bye";

    private String padding = "       ";
    private String uselessLine = "-------------------------------------------------------------------";
    private String addedPhrase = "added: ";
    private ArrayList<Task> storedItems;

    public Duke() {
        // Place-holder constructor, may need to extend later
        storedItems = new ArrayList<>();
    }

    public void greet() {
        System.out.println(padding + uselessLine + '\n' +
                padding + "Greetings! This is " + botName + ", and I am your friend!\n" +
                padding + "You don't have to be formal. Relax and tell me how I can help you\n" +
                padding + uselessLine);
    }

    public void processUserInput(String str) {
        if (str.equals("")) {
            System.out.println(padding + uselessLine + '\n' +
                    padding + "Please type something. Don't leave it blank, plsss!\n" +
                    padding + uselessLine);
            return;
        }

        int pos = isMarkingTaskRequest(str);
        if (pos != -1) {
            markItemAsDone(pos);
        } else {
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
                    break; // Will throw error later? commandType already throws so idk
            }
        }
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
            else {
                // Place-holder clause. Will throw error later
            }
        }

        sc.close();
        return ret;
    }

    // Not very optimal handling these 3 methods are...
    // But usable hmm

    private void handleToDo(String str) {
        Scanner sc = new Scanner(str);
        sc.next();
        Task todo = new ToDo(sc.nextLine());
        storeUserInput(todo);
        sc.close();
    }

    private void handleDeadline(String str) {
        Scanner sc = new Scanner(str);

        sc.close();
    }

    private void handleEvent(String str) {
        Scanner sc = new Scanner(str);

        sc.close();
    }

    private void markItemAsDone(int pos) {
        assert (pos < storedItems.size() && pos >= 0);
        storedItems.get(pos).markDone();
        System.out.println(padding + uselessLine + '\n' +
                padding + "Nice nice. I've marked the task as done for you.\n" +
                padding + "   " + storedItems.get(pos) + '\n' +
                padding + uselessLine);
    }

    private int isMarkingTaskRequest(String str) {
        int ret = -1;
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
                ret = -1;
            break;
        }

        sc.close();
        return ret;
    }

    private void storeUserInput(Task task) {
        storedItems.add(task);
        System.out.println(padding + uselessLine + '\n' +
                padding + addedPhrase + task + '\n' +
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
        System.out.println(padding + uselessLine + '\n' +
                padding + str + '\n' +
                padding + uselessLine);
    }

    public void byeBye() {
        System.out.println(padding + uselessLine + '\n' +
                padding + "Bye-bye. It was nice talking to you. See ya soon!\n" +
                padding + uselessLine);
    }
}
