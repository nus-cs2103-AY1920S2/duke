import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final String DATAFILE = "data/file.txt";

    public static void main(String[] args) {
        runDuke();
    }

    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hi, I'm Duke!");
        System.out.println("What can I do for you?\n");
    }

    public static void runDuke() {
        try {
            loadSavedTasksFromFile();
        } catch (IOException e) {
            System.out.println("Error: Unable to create or read saved tasks file.");
            return;
        }

        printGreeting();
        
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();

            try {
                String command = extractCommand(input);  // commands: bye, list, done, todo, deadline, event
                if (command.equals("bye")) {
                    break;
                }
                processInstruction(input);
            } catch (InvalidInstructionException e) {
                System.out.format("\tError: %s. Please try again.%n%n", e.getMessage());
            }
        }

        System.out.println("\tHave a nice day!");
    }
    
    public static void loadSavedTasksFromFile() throws IOException {
        File file = new File(DATAFILE);
        
        if (!file.createNewFile()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                Task.loadInTaskFromFileLine(line);
            }
            sc.close();
        }
    }
    
    public static void processInstruction(String input) throws InvalidInstructionException {
        String command = extractCommand(input);

        switch (command) {
        case "list":
            Task.printTasks();
            break;
        case "done":
            try {
                int doneTaskNum = Integer.parseInt(extractFirstParam(input));

                if (doneTaskNum <= 0 || doneTaskNum > Task.getTotalNumOfTasks()) {
                    throw new InvalidInstructionException(
                            String.format("Task #%d does not exist", doneTaskNum));
                }
                
                Task.setAsDone(doneTaskNum);
            } catch (NumberFormatException e) {
                throw new InvalidInstructionException("Task number given is not an integer");
            }
            break;
        case "todo": {
            String description = extractDescription(input);
            Task todo = new Todo(description);
            Task.addTask(todo);
            break;
        }
        case "deadline": {
            String description = extractDescription(input);
            LocalDate by = extractDate(input);

            Task deadline = new Deadline(description, by);
            Task.addTask(deadline);
            break;
        }
        case "event": {
            String description = extractDescription(input);
            LocalDate at = extractDate(input);

            Task event = new Event(description, at);
            Task.addTask(event);
            break;
        }
        case "delete":
            try {
                int delTaskNum = Integer.parseInt(extractFirstParam(input));

                if (delTaskNum <= 0 || delTaskNum > Task.getTotalNumOfTasks()) {
                    throw new InvalidInstructionException(
                            String.format("Task #%d does not exist", delTaskNum));
                }

                Task.removeTask(delTaskNum);
            } catch (NumberFormatException e) {
                throw new InvalidInstructionException("Task number given is not an integer");
            }
            break;
        default:
            throw new InvalidInstructionException(
                    String.format("Command \"%s\" is not recognized", command));
        }
    }

    public static String extractCommand(String input) {
        return input.split(" ")[0];
    }

    public static String extractDescription(String input) throws InvalidInstructionException {
        String[] inputArr = input.split(" ");
        
        if (inputArr.length <= 1) {
            throw new InvalidInstructionException("No description given");
        }
        
        String descPortion = input.split(" /")[0];
        String[] descPortionArr = descPortion.split(" ");
        String[] descArr = Arrays.copyOfRange(descPortionArr, 1, descPortionArr.length);

        return String.join(" ", descArr);
    }

    public static LocalDate extractDate(String input) throws InvalidInstructionException {
        String[] inputArr = input.split(" /");
        
        if (inputArr.length <= 1) {
            throw new InvalidInstructionException("No time flag (/by /at) given");
        }
        
        String datePortion = inputArr[1];
        String[] datePortionArr = datePortion.split(" ");
        
        if (datePortionArr.length <= 1) {
            throw new InvalidInstructionException("No timing given");
        }
        
        return LocalDate.parse(datePortionArr[1]);
    }

    public static String extractFirstParam(String input) throws InvalidInstructionException {
        String[] inputArr = input.split(" ");
        
        if (inputArr.length <= 1) {
            throw new InvalidInstructionException("No parameters given");
        }
        
        return input.split(" ")[1];
    }
}
