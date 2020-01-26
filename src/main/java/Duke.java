import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        ArrayList<Task> todo = new ArrayList<>();
        while(true) {
            String readtext = scanner.nextLine();
            if(readtext.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (readtext.equals("list")) {
                System.out.println("Here are the task in your list:");
                for(int i=0;i <todo.size(); i++){
                    System.out.println((i + 1) + ". " + todo.get(i).toString());
                }
            } else {
                if (readtext.contains("done")) {
                    String num = readtext.split(" ")[1];
                    System.out.println("Nice! I've marked this task as done:\n");
                    todo.get(Integer.parseInt(num)-1).markAsDone();
                    System.out.println(num + ". " + "[" + todo.get(Integer.parseInt(num)-1).getStatusIcon() +"] " + todo.get(Integer.parseInt(num)-1).getDescription());
                } else {
                    if (readtext.contains("todo")) {
                        String spli = readtext.split("todo")[1];
                        Task t = new Todo(spli);
                        todo.add(t);
                        System.out.println("Got it. I've added this task");
                        System.out.println(t.toString());
                        System.out.println("Now you have " + todo.size() + " tasks in the list");
                    } else if (readtext.contains("deadline")) {
                        String spli = readtext.split("deadline")[1];
                        String des = spli.split("/by")[0];
                        String fin = spli.split("/by")[1];
                        Task t = new Deadline(des, fin);
                        todo.add(t);
                        System.out.println("Got it. I've added this task");
                        System.out.println(t.toString());
                        System.out.println("Now you have " + todo.size() + " tasks in the list");
                    } else if (readtext.contains("event")) {
                        String spli = readtext.split("event")[1];
                        String des = spli.split("/at")[0];
                        String fin = spli.split("/at")[1];
                        Task t = new Event(des, fin);
                        todo.add(t);
                        System.out.println("Got it. I've added this task");
                        System.out.println(t.toString());
                        System.out.println("Now you have " + todo.size() + " tasks in the list");
                    }
                }
            }
        }

    }
}
