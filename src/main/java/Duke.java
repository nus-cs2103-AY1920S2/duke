import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String indent = "    ";
    private static String line = "    ____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> toDo = new ArrayList<Task>();

        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "\n" + indent + "Hello! I'm Snow\n" + indent + "What can I do for you?\n" + logo + line + "\n");

        String input = "";
        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")){
                System.out.println(line);
                System.out.println(indent + "Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")){
                System.out.println(line);
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 0; i < toDo.size(); i++){
                    System.out.println(indent + String.valueOf(i + 1) + "." + toDo.get(i));
                }
                System.out.println(line);
            } else if (input.split(" ")[0].equals("done")){
                int num = Integer.parseInt(input.split(" ")[1]);
                toDo.get(num - 1).markAsDone();
                System.out.println(line);
                System.out.println(indent + "Nice! I've marked this task as done: ");
                System.out.println(indent + indent + toDo.get(num - 1));
                System.out.println(line);
            } else {
                Task newTask = new Task(input);
                toDo.add(newTask);
                System.out.println(line);
                System.out.println(indent + "added: " + input);
                System.out.println(line);
            }
        }


    }
}
