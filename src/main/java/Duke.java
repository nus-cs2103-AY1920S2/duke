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
            boolean hasInstruction = false;
            if (cmdInstructionArr.length == 2)
                hasInstruction = true;
            try {
                switch (command) {
                    case "bye": // Exit
                        respond(farewell);
                        return;
                    case "list": // List all tasks
                        respond(lstTasks.showTasks());
                        break;
                    case "done": // Mark task as done
                        respond(lstTasks.markTaskAsDone(cmdInstructionArr[1]));
                        break;
                    case "delete": // Delete task
                        respond(lstTasks.removeTask(cmdInstructionArr[1]));
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        if (!hasInstruction)
                            throw new MissingInfoException(command, false);
                        respond(lstTasks.addTask(command, cmdInstructionArr[1]));
                        break;
                    default:
                        throw new UnknownCommandException();
                }
            } catch (UnknownCommandException uce) {
                respond("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (MissingInfoException ede) {
                respond("OOPS!!! The " + ede.getMissingInfoName() + " of a " + ede.getTaskType() + " cannot be empty");
            }
        }
    }
}
