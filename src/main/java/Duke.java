import java.util.Scanner;

public class Duke {

    public static final String FILEPATH = "data\\duke.txt";

    public static void run(String filePath) {
        Scanner scanner = new Scanner(System.in);
        Task.initTasks(FileHandler.loadFile(filePath));
        String prompt = "Anything Duke can do for you?";
        String border = new String(new char[50]).replace('\0', '_');;
        String input = "";
        while (true) {
            System.out.println(border);
            System.out.println(prompt);
            input = scanner.nextLine().toLowerCase();
            System.out.println(border);
            String[] inputWords = input.split(" ");
            String instruction = inputWords[0];
            try {
                if (instruction.equals("bye")) {
                    break;
                } else if (instruction.equals("list")) {
                    Task.printTaskList();
                } else if (instruction.equals("done")) {
                    // 1. handle if done x, x is not int    https://www.baeldung.com/java-check-string-number
                    // 2. handle if done x, x does not exist
                    Task.setDone(Integer.valueOf(inputWords[1]) - 1);
                } else if (instruction.equals("delete")) {
                    Task.deleteTask(Integer.valueOf(inputWords[1]) - 1);
                } else if ((instruction.equals("todo")
                        || instruction.equals("deadline")
                        || instruction.equals("event"))) {
                    Task.addNewTask(inputWords);
                } else {
                    throw new InvalidInstructionException("You have entered invalid input ☹.");
                }
            } catch (InvalidInstructionException e) {
                System.out.println(e.getMessage());
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        FileHandler.saveFile(filePath, Task.getTaskList());
        scanner.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String exitInfo = "Enter bye to exit!";
        String exitMessage = "Goodbye, see you again!";
        System.out.println(exitInfo);
        Duke.run(FILEPATH);
        System.out.println(exitMessage);
    }
}
