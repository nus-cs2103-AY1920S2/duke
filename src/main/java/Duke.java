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
            String[] arrString = userInput.split(" ", 2);
            if (arrString[0].equalsIgnoreCase("bye")) {
                System.out.println(botReplyLine + "\n Duke: I'll say goodnight now \n" + botReplyLine);
                break;
            } else if (arrString[0].equalsIgnoreCase("list")) {
                System.out.println(botReplyLine);
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(i + 1 + ". " + arr.get(i).toString());
                }
                System.out.println(botReplyLine);
            } else if (arrString[0].equalsIgnoreCase("done")) {
                int taskNumber = Integer.parseInt(arrString[1]) - 1;
                arr.get(taskNumber).doneTask();
                System.out.println(botReplyLine);
                System.out.println("Consider it done.");
                System.out.println(arr.get(taskNumber).toString());
                System.out.println(botReplyLine);
            } else if (arrString[0].equalsIgnoreCase("todo")) {
                Todo todo = new Todo(arrString[1]);
                arr.add(todo);
                System.out.println(botReplyLine + "\n Duke: added your command." + "\n" + botReplyLine);
                System.out.println(todo.toString());
            } else if (arrString[0].equalsIgnoreCase("event")) {
                String[] eventString = arrString[1].split("/");
                Event event = new Event(eventString[0], eventString[1].substring(3));
                arr.add(event);
                System.out.println(botReplyLine + "\n Duke: added your command." + "\n" + botReplyLine);
                System.out.println(event.toString());
            } else if (arrString[0].equalsIgnoreCase("deadline")) {
                String[] deadlineString = arrString[1].split("/");
                Deadline deadline = new Deadline(deadlineString[0], deadlineString[1].substring(3));
                arr.add(deadline);
                System.out.println(botReplyLine + "\n Duke: added your command." + "\n" + botReplyLine);
                System.out.println(deadline.toString());
            }
        }

    }
}

