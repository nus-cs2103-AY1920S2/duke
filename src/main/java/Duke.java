import java.util.Scanner;

public class Duke {
    private static void respond(String response) {
        int maxLength = 0;
        String botResponse = "";
        String lines[] = response.split("\\r?\\n");
        for (String s: lines) {
            if (s.length() > maxLength)
                maxLength = s.length();
        }
        botResponse += "    ";
        for (int i=0; i<maxLength; i++)
            botResponse += '-';
        botResponse += '\n';
        for (String line: lines)
            botResponse += ("    " + line + '\n');
        botResponse += "    ";
        for (int i=0; i<maxLength; i++)
            botResponse += '-';
        System.out.println(botResponse);
    }

    public static void main(String[] args) {
        TaskManager lstTasks = new TaskManager();

        String greeting = "Hello! I'm Woody and I'm always here to keep you company.\n"
                + "Let me know what you need and I'll be right on it.\n";
        String farewell = "Good day my friend! I'm here anytime you need me :)";
        respond(greeting);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userCmd = sc.nextLine();
            String cmdInstructionArr[] = userCmd.split(" ", 2);
            String command = cmdInstructionArr[0];
            switch (command) {
                case "bye": // Exit
                    respond(farewell);
                    return;
                case "list": // List all tasks
                    respond(lstTasks.showTasks());
                    break;
                case "done": // Mark task as done
                    int taskArrIndex = Integer.parseInt(cmdInstructionArr[1]) - 1; // Array index of required task
                    respond(lstTasks.markTaskAsDone(taskArrIndex));
                    break;
                case "todo": // Add ToDo task
                    respond(lstTasks.addTask(new ToDo(cmdInstructionArr[1])));
                    break;
                case "deadline": // Add Deadline task
                    String descByArr[] = cmdInstructionArr[1].split(" /by ", 2);
                    respond(lstTasks.addTask(new Deadline(descByArr[0], descByArr[1])));
                    break;
                case "event": // Add Event task
                    String descAtArr[] = cmdInstructionArr[1].split(" /at ", 2);
                    respond(lstTasks.addTask(new Event(descAtArr[0], descAtArr[1])));
                    break;
                default: // Add new task
                    respond(lstTasks.addTask(new Task(userCmd)));
            }
        }
    }
}
