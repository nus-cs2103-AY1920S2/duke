import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
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
                    System.out.println((i + 1) + ". " + "[" + todo.get(i).getStatusIcon() +"] " + todo.get(i).getDescription());
                }
            } else {
                if (readtext.contains("done")) {
                    String num = readtext.split(" ")[1];
                    System.out.println("Nice! I've marked this task as done:\n");
                    todo.get(Integer.parseInt(num)-1).markAsDone();
                    System.out.println(num + ". " + "[" + todo.get(Integer.parseInt(num)-1).getStatusIcon() +"] " + todo.get(Integer.parseInt(num)-1).getDescription());
                } else {
                    Task t = new Task(readtext);
                    todo.add(t);
                    System.out.println("added: "  + readtext);
                }
            }
        }

    }
}
