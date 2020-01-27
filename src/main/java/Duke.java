import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        printGreeting();
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

    public static void processInstruction(String input) throws InvalidInstructionException {
        String command = extractCommand(input);
        
        if (command.equals("list")) {
            Task.printTasks();
        } else if (command.equals("done")) {
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
        } else if (command.equals("todo")) {
            String description = extractDescription(input);
            Task todo = new Todo(description);

            Task.addTask(todo);
        } else if (command.equals("deadline")) {
            String description = extractDescription(input);
            LocalDate by = extractDate(input);

            Task deadline = new Deadline(description, by);
            Task.addTask(deadline);
        } else if (command.equals("event")) {
            String description = extractDescription(input);
            LocalDate at = extractDate(input);

            Task event = new Event(description, at);
            Task.addTask(event);
        } else if (command.equals("delete")) {
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
        } else {
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
        
        String timePortion = inputArr[1];
        String[] timePortionArr = timePortion.split(" ");
        
        if (timePortionArr.length <= 1) {
            throw new InvalidInstructionException("No timing given");
        }
        
        return convertStringToLocalDate(timePortionArr[1]);
    }
    
    public static LocalDate convertStringToLocalDate(String date) {
        String[] dateArr = date.split("-");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int day = Integer.parseInt(dateArr[2]);

        return LocalDate.of(year, month, day);
    }

    public static String extractFirstParam(String input) throws InvalidInstructionException {
        String[] inputArr = input.split(" ");
        
        if (inputArr.length <= 1) {
            throw new InvalidInstructionException("No parameters given");
        }
        
        return input.split(" ")[1];
    }
}
