import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> taskList = new ArrayList<>(100);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String prompt = "Anything Duke can do for you?";
        String exitInfo = "Enter bye to exit!";
        String exitMessage = "Goodbye, see you again!";
        String instruction = "";
        System.out.println(exitInfo);
        while (true) {
            System.out.println(prompt);
            instruction = scanner.nextLine().toLowerCase();
            if (instruction.equals("bye")) {
                break;
            } else if (instruction.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.format("%s." + taskList.get(i) + '\n', String.valueOf(i + 1));
                }
            } else {
                taskList.add(instruction);
                System.out.print("Added: ");
                System.out.println(instruction);
            }
        }
        System.out.println(exitMessage);
        scanner.close();
    }
}
