import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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

    private static String createList(List<String> tasks) {
        String lst = "";
        int counter = 1;
        for (String t: tasks) {
            lst += (counter + "."); // Index of task
            lst += (" " + t + '\n'); // Description of task and whether it is done
            counter++;
        }
        return lst;
    }

    public static void main(String[] args) {
        ArrayList<String> arrTasks = new ArrayList<>();

        String greeting = "Hello! I'm Woody and I'm always here to keep you company.\n"
                + "Let me know what you need and I'll be right on it.\n";
        String farewell = "Good day my friend! I'm here anytime you need me :)";
        respond(greeting);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userCmd = sc.nextLine();
            switch (userCmd) {
                case "bye": // Exit
                    respond(farewell);
                    return;
                case "list": // List all tasks
                    respond(createList(arrTasks));
                    break;
                default: // Add new task
                    arrTasks.add(userCmd);
                    respond("added: " + userCmd);
            }
        }
    }
}
