import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    static String separator = "____________________________________________________________";
    static List<Task> tasks = new ArrayList<>();

    /**
     * The main method is where Duke introduces itself
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println(separator + "\nHello! I'm Duke\nWhat can I do for you?\n" + separator);
        handleInput();
    }

    /**
     * Handles input from user.
     */
    private static void handleInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            StringBuilder sb = new StringBuilder(separator + "\n");
            String grammar = tasks.size() > 1 ? " tasks" : " task";
            String[] splitInput = input.split(" ", 2);
            String cmd = splitInput[0];

            try {
                if (cmd.equals("list")) {
                    listTasks(sb, grammar);
                } else if (cmd.equals("done")) {
                    markTaskAsDone(sb, grammar, splitInput);
                } else if (cmd.equals("todo")) {
                    if (splitInput.length < 2) {
                        throw new NoDescriptionException();
                    } else {
                        tasks.add(new Todo(splitInput[1]));
                        actionConfirmation(sb, grammar);
                    }
                } else {
                    if (cmd.equals("deadline")) {
                        generateDeadlineEvent(sb, grammar, splitInput, " /by ");
                    } else if (cmd.equals("event")) {
                        generateDeadlineEvent(sb, grammar, splitInput, " /at ");
                    } else {
                        throw new InvalidCommandException();
                    }
                }
                sb.append(separator);
                System.out.println(sb);
            } catch (DukeException e) {
                System.err.println(separator + "\n" + e.toString() + separator);
            } finally {
                input = sc.nextLine();
            }
        }

        if (input.equals("bye")) {
            if (tasks.size() > 0) {
                System.out.println(separator + "\nCome back! You still have unfinished tasks to complete!\n" + separator);
            } else {
                System.out.println(separator + "\nBye! Do come back to track more tasks!!\n" + separator);
            }
        }
    }

    private static void generateDeadlineEvent(StringBuilder sb, String grammar, String[] splitInput, String id) throws NoDescriptionException, NoDateProvidedException {
        if (splitInput.length < 2) {
            throw new NoDescriptionException();
        } else {
            if (splitInput[1].contains(id)) {
                String[] temp = splitInput[1].split(id);
                tasks.add(new Deadline(temp[0].trim().toString(), temp[1]));
                actionConfirmation(sb, grammar);
            } else {
                throw new NoDateProvidedException(id.trim().replace("/", ""));
            }
        }
    }

    private static void markTaskAsDone(StringBuilder sb, String grammar, String[] splitInput) throws NoTaskNumberException, InvalidIndexException {
        if (splitInput.length > 1) {
            int taskNum = Integer.parseInt(splitInput[1]);
            if (taskNum < 1 || taskNum > tasks.size()) {
                throw new InvalidIndexException(tasks.size());
            }
            sb.append("Nice! I've marked this task as done:\n\t");
            Task t = tasks.get(taskNum);
            t.markAsDone();
            sb.append(t);
        } else {
            throw new NoTaskNumberException();
        }
    }

    private static void listTasks(StringBuilder sb, String grammar) {
        sb.append("Below is your list of " + grammar + ":\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\t");
            sb.append(i + 1);
            sb.append("." + tasks.get(i));
        }
    }

    private static void actionConfirmation(StringBuilder sb, String grammar) {
        int size = tasks.size();
        sb.append("Got it. I've added this task:\n\t" + tasks.get(size - 1));
        sb.append("Now you have " + size + grammar + " in the list.\n");
    }
}
