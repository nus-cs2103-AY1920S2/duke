import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public final static String INDENT = "    ";
    public final static String BORDER = "____________________________________________________________";
    public final static String EXIT = "bye";
    public final static String GOODBYE_MESSAGE = "Goodbye and have a beautiful time!";

    public static void main(String[] args) {
        String logo = INDENT + "  _____  __    __  _____ \n"
                + INDENT + " |  ___||  \\  /  ||  ___| \n"
                + INDENT + " | |__   \\  \\/  / | |__\n"
                + INDENT + " |  __|   |    |  |  __|\n"
                + INDENT + " | |___  /  /\\  \\ | |___\n"
                + INDENT + " |_____||__/  \\__\\|_____| \n";
        System.out.println(INDENT + BORDER);
        System.out.println(logo);
        System.out.println(INDENT + "Hello! I'm EXE, I'll execute anything on your command! :)");
        System.out.println(INDENT + "What do you want to exe?");
        System.out.println(INDENT + BORDER);

        Scanner scanner = new Scanner(System.in);
        List<Task> listOfTask = new ArrayList<>();

        String userInput = scanner.nextLine();
        String replyMessage;
        while (!userInput.equals(EXIT)) {
            String[] inputArr = userInput.split(" ");
            if (inputArr.length > 0) {
                String command = inputArr[0];
                if (validateCommand(command)) {
                    int taskNumber = Integer.parseInt(inputArr[1]) - 1;
                    try {
                        Task currentTask = listOfTask.get(taskNumber);
                        currentTask.markAsDone();
                        System.out.println(formatAddedTaskReply(currentTask.getStatusIcon(), currentTask.getTask()));
                    } catch (IndexOutOfBoundsException exception) {
                        System.out.println(exception.getMessage());
                    }
                } else if (userInput.equals("list")) {
                    replyMessage = formatListReply(listOfTask);
                    System.out.println(replyMessage);
                } else {
                    Task currentTask = new Task(userInput);
                    listOfTask.add(currentTask);
                    replyMessage = currentTask.toString();
                    System.out.println(formatReply(replyMessage));
                }
                userInput = scanner.nextLine();
            } else {
                System.out.println("Please give me something to exe :D");
            }
        }

        System.out.println(formatReply(GOODBYE_MESSAGE));

        scanner.close();
    }

    // for bye and adding stuff into list
    public static String formatReply(String message) {
        String reply = INDENT + BORDER + "\n" + INDENT + " " + message + "\n" + INDENT + BORDER;
        return reply;
    }

    // for printing list
    public static String formatListReply(List<Task> listOfTask) {
        StringBuilder stringBuilder = new StringBuilder();
        String currentTaskName;
        Task currentTask;
        stringBuilder.append(INDENT + BORDER + "\n");
        for (int i = 0; i < listOfTask.size(); i++) {
            currentTask = listOfTask.get(i);
            currentTaskName = currentTask.getTask();
            stringBuilder.append(INDENT);
            stringBuilder.append(String.format(" %d.[%s] %s\n", i + 1, currentTask.getStatusIcon(),currentTaskName));
        }
        stringBuilder.append(INDENT + BORDER);
        return stringBuilder.toString();
    }

    public static boolean validateCommand(String command) {
        if (command.equals("done")) {
            return true;
        }
        return false;
    }

    public static String formatAddedTaskReply(String markedIcon, String taskName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + BORDER + "\n");
        stringBuilder.append("Very productive! You do, I execute! This task has been slayed:\n");
        stringBuilder.append(String.format("[%s] %s\n", markedIcon, taskName));
        return stringBuilder.toString();
    }
}
