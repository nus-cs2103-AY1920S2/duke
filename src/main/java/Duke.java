import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
            String[] instructionWords = instruction.split(" ");
            if (instruction.equals("bye")) {
                break;
            } else if (instruction.equals("list")) {
                Task.printTaskList();
            } else if (instructionWords[0].equals("done")) {
                // 1. handle if done x, x is not int    https://www.baeldung.com/java-check-string-number
                // 2. handle if done x, x does not exist
                Task.setDone(Integer.valueOf(instructionWords[1]) - 1);
            } else {
                Task.addNewTask(instructionWords);
            }
        }
        System.out.println(exitMessage);
        scanner.close();
    }
}
