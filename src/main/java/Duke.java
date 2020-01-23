import java.util.Scanner;

public class Duke {
    private static boolean dukeActive = true;
    private static final Scanner scanner = new Scanner(System.in);
    private static String userInput = "";
    private static String userArgs = "";
    private static Task[] items = new Task[100];
    private static int itemsCounter = 0;

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
                userInput = splitString[0];
                userArgs = splitString[1];

                switch (userInput) {
                case "done":
                    int taskNo = Integer.parseInt(splitString[1]) - 1;
                    markAsDone(taskNo);
                    dukePrompt(new String[]{"Got it boss! Just to confirm, this is the one I marked as done",
                        items[taskNo].toString()});
                    break;
                case "deadline":
                    items[itemsCounter] = new Deadline(userArgs);
                    itemsCounter++;
                    dukePrompt(new String[]{"Oooh, this one's important eh, boss? I got it",
                        items[(itemsCounter - 1)].toString(),
                        "Now you have " + itemsCounter + " tasks in the list"});
                    break;
                case "todo":
                    items[itemsCounter] = new ToDo(userArgs);
                    itemsCounter++;
                    dukePrompt(new String[]{"Got it, boss. I'll write this one down",
                        items[(itemsCounter - 1)].toString(),
                        "Now you have " + itemsCounter + " tasks in the list"});
                    break;
                case "event":
                    items[itemsCounter] = new Event(userArgs);
                    itemsCounter++;
                    dukePrompt(new String[]{
                        "A special event I see. Don't worry boss, I'll remind you",
                        items[(itemsCounter - 1)].toString(),
                        "Now you have " + itemsCounter + " tasks in the list"});
                    break;
                default:
                    break;
                };
            }
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
        if (itemsCounter == 0) {
            temp = new String[]{"Boss, my notepad is empty. You sure you told me anything?"};
        } else {
            temp = new String[itemsCounter + 1];
            temp[0] = "Here's what I've written down, boss.";
            for (int i = 0; i < itemsCounter; i++) {
                temp[i + 1] = (i + 1) + ". " + items[i];
            }
        }
        return temp;
    }

    private static void markAsDone(int taskNo) {
        items[taskNo].setTaskDone();
    }
}
