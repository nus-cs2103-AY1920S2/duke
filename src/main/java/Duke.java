import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static boolean dukeActive = true;
    private static final Scanner scanner = new Scanner(System.in);
    private static String userInput = "";
    private static String userArgs = "";
    private static ArrayList<Task> items = new ArrayList<Task>();

    /**
     * Main method. Entry point for the Duke program.
     * @param args Command-line arguments. Unused.
     */
    public static void main(final String[] args) {
        final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__._|_|\\_\\___|\n";
        final String logoDivider = "++++++++++++++++++++++\n";

        System.out.println(logoDivider + logo + "\n" + logoDivider);

        dukePrompt(new String[]{"Hey boss! Duke here, at your service.", "What do you need me to do?"});
        while (dukeActive) {
            try {
                userInput = readUserInput();
                System.out.println();

                switch (userInput) {
                case "list":
                    dukePrompt(listItems());
                    break;
                case "bye":
                    dukeActive = false;
                    dukePrompt("Good bye, boss! Call me if you need me. I'll be waiting!");
                    break;
                default:
                    String[] splitString = userInput.split(" ", 2);
                    if (splitString.length != 2) {
                        throw new DukeException(1);
                    }
                    userInput = splitString[0];
                    userArgs = splitString[1];

                    switch (userInput) {
                    case "done":
                        int taskNo = Integer.parseInt(userArgs) - 1;
                        System.out.println(taskNo);
                        if (taskNo < 0 || taskNo >= items.size()) {
                            throw new DukeException(4);
                        } else {
                            markAsDone(taskNo);
                            dukePrompt(new String[]{"Got it boss! Just to confirm, this is the one I marked as done",
                                items.get(taskNo).toString()});
                        }
                        break;
                    case "deadline":
                        items.add(new Deadline(userArgs));
                        dukePrompt(new String[]{"Oooh, important deadline eh, boss? Don't worry, I got it",
                            items.get(items.size() - 1).toString(),
                            "Now you have " + items.size() + " tasks in the list"});
                        break;
                    case "todo":
                        items.add(new ToDo(userArgs));
                        dukePrompt(new String[]{"Got it, boss. I'll write this one down",
                            items.get(items.size() - 1).toString(),
                            "Now you have " + items.size() + " tasks in the list"});
                        break;
                    case "event":
                        items.add(new Event(userArgs));
                        dukePrompt(new String[]{
                            "A special event I see. Don't worry boss, I'll remind you",
                            items.get(items.size() - 1).toString(),
                            "Now you have " + items.size() + " tasks in the list"});
                        break;
                    default:
                        throw new DukeException(0);
                    };
                }
            } catch (DukeException e) {
                dukePrompt(e.getMessage());
                //TODO: Implement "help" command
            }
            userInput = "";
            userArgs = "";
        }
    }

    private static void dukePrompt(String prompt) {
        System.out.println("Duke: " + prompt);
    }

    private static void dukePrompt(String[] prompts) {
        for (int i = 0; i < prompts.length; i++) {
            if (i == 0) {
                System.out.println("Duke: " + prompts[i]);
            } else {
                System.out.println("      " + prompts[i]);
            }
        }
    }

    private static String readUserInput() {
        System.out.printf("\nYou:  ");
        return scanner.nextLine();
    }

    private static String[] listItems() {
        String[] temp;
        if (items.size() == 0) {
            temp = new String[]{"Boss, my notepad is empty. You sure you told me anything?"};
        } else {
            temp = new String[items.size() + 1];
            temp[0] = "Here's what I've written down, boss.";
            for (int i = 0; i < items.size(); i++) {
                temp[i + 1] = (i + 1) + ". " + items.get(i).toString();
            }
        }
        return temp;
    }

    private static void markAsDone(int taskNo) {
        items.get(taskNo).setTaskDone();
    }
}
