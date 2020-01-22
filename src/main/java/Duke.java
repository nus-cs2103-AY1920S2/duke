import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String line = "    ____________________________________________________________";
    private static final String space = "    ";
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputs = input.split(" ");
            if (inputs[0].equals("bye")) {
                sayGoodbye();
                break;
            } else if (inputs[0].equals("list")){
                displayList();
            } else {
                addTask(input);
            }
        }
    }

    private static void printLine() {
        System.out.println(line);
    }
    private static void indent(String toIndent) {
        System.out.printf(space);
        System.out.println(toIndent);
    }

    private static void greet() {
        printLine();
        String logo = space
                + " ____        _        \n" + space
                + "|  _ \\ _   _| | _____ \n" + space
                + "| | | | | | | |/ / _ \\\n" + space
                + "| |_| | |_| |   <  __/\n" + space
                + "|____/ \\__,_|_|\\_\\___|\n";
        indent("Greetings, you may call me\n" + logo);
        indent("How may I help you in this fine day today?");
        printLine();
    }

    private static void sayGoodbye() {
        printLine();
        indent("I bid you adieu. Until the day we meet again.");
        printLine();
    }

    private static void displayList() {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            indent((i + 1) + ". " +tasks.get(i).toString());
        }
        printLine();
    }

    private static void addTask(String input) {
        tasks.add(new Task(input));
        printLine();
        indent("Acknowledged. I have added: " + input);
        printLine();
    }
}
