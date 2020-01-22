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
        List<String> tasks = new ArrayList<>();
        List<Boolean> areTasksCompleted = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        while (isRunning && sc.hasNextLine()) {
            String input = sc.nextLine();
            String command = input;
            String[] arguments = new String[0];
            if (input.contains(" ")) {
                String[] inputs = input.split(" ", 2);
                command = inputs[0];
                if (inputs[1].contains(" ")) {
                    arguments = inputs[1].split(" ");
                } else {
                    arguments = new String[] {inputs[1]};
                }
            }
            switch (command) {
            case "list": {
                StringBuilder output = new StringBuilder("Tasks so far:\n");
                ListIterator<String> iterator = tasks.listIterator();
                while (iterator.hasNext()) {
                    int index = iterator.nextIndex();
                    String symbol = areTasksCompleted.get(index) ? "X" : " ";
                    String task = iterator.next();
                    output.append(String.format(
                            "%d.[%s] %s\n",
                            (index + 1), symbol, task
                    ));
                }
                System.out.print(output);
                break;
            }
            case "done": {
                int taskIndex;
                try {
                    taskIndex = Integer.parseInt(arguments[0]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println(String.format(
                            "'%s' is not valid task number!",
                            arguments[0]
                    ));
                    break;
                }
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    System.out.println(String.format(
                            "Task #%d supplied is not within 1 to %d!",
                            (taskIndex + 1), tasks.size()
                    ));
                    break;
                }
                String task = tasks.get(taskIndex);
                areTasksCompleted.set(taskIndex, true);
                System.out.println(String.format("Marked '%s' as done", task));
                break;
            }
            case "bye": {
                isRunning = false;
                break;
            }
            default: {
                tasks.add(input);
                areTasksCompleted.add(false);
                System.out.println(String.format("Added: '%s'", input));
            }
            }
        }
        System.out.println("Goodbye!");
        sc.close();
    }
}
