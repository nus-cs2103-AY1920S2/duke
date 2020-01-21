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
            if (userInput.equals("list")) {
                replyMessage = formatList(listOfTask);
                System.out.println(replyMessage);
            } else {
                Task currentTask = new Task(userInput);
                listOfTask.add(currentTask);
                replyMessage = currentTask.toString();
                System.out.println(formatReply(replyMessage));
            }
            userInput = scanner.nextLine();
        }

        System.out.println(formatReply(GOODBYE_MESSAGE));

        scanner.close();
    }

    public static String formatReply(String message) {
        String reply = INDENT + BORDER + "\n" + INDENT + " " + message + "\n" + INDENT + BORDER;
        return reply;
    }

    public static String formatList(List<Task> listOfTask) {
        StringBuilder stringBuilder = new StringBuilder();
        String currentTaskName;
        stringBuilder.append(INDENT + BORDER + "\n");
        for (int i = 0; i < listOfTask.size(); i++) {
            currentTaskName = listOfTask.get(i).getTask();
            stringBuilder.append(INDENT);
            stringBuilder.append(String.format(" %d. %s\n", i + 1, currentTaskName));
        }
        stringBuilder.append(INDENT + BORDER);
        return stringBuilder.toString();
    }

}
