import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        List<String> texts = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        while (isRunning && sc.hasNextLine()) {
            String input = sc.nextLine();
            switch (input) {
            case "list": {
                StringBuilder output = new StringBuilder("List so far:\n");
                ListIterator<String> iterator = texts.listIterator();
                while (iterator.hasNext()) {
                    int index = iterator.nextIndex() + 1;
                    String text = iterator.next();
                    output.append(index + ". " + text + "\n");
                }
                System.out.print(output);
                break;
            }
            case "bye": {
                isRunning = false;
                break;
            }
            default: {
                texts.add(input);
                System.out.println("Added: '" + input + "'");
            }
            }
        }
        System.out.println("Goodbye!");
        sc.close();
    }
}
