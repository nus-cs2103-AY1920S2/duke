import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n I am at your service\n");
        String botReplyLine = "----------------------------------------------";


        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<>();


        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            String[] arrString = userInput.split(" ");
            if (arrString[0].equalsIgnoreCase("bye")) {
                System.out.println(botReplyLine + "\n Duke: I'll say goodnight now \n" + botReplyLine);
                break;
            } else if (arrString[0].equalsIgnoreCase("list")) {
                System.out.println(botReplyLine);
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(i + 1 + ". [" + arr.get(i).getStatusIcon() + "] " + arr.get(i).description);
                }
                System.out.println(botReplyLine);
            } else if (arrString[0].equalsIgnoreCase("done")) {
                int taskNumber = Integer.parseInt(arrString[1]) - 1;
                arr.get(taskNumber).doneTask();
                System.out.println(botReplyLine);
                System.out.println("Consider it done.");
                System.out.println("[" + arr.get(taskNumber).getStatusIcon() + "] " + arr.get(taskNumber).description);
                System.out.println(botReplyLine);
            } else {
                Task task = new Task(userInput);
                arr.add(task);
                System.out.println(botReplyLine + "\n Duke: added " + userInput + "\n" + botReplyLine);
            }
        }

    }
}

