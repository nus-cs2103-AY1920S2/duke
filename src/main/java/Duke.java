import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.Todo;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________");
        String command = sc.nextLine();
        Task[] tasks = new Task[100];
        int size = 0;
        while(!command.equals("bye")) {
            int breakPoint = command.indexOf('/');
            String[] nextLine = command.split(" ");
            if(nextLine[0].equals("list")){
                System.out.println("____________________________");
                for(int i = 0; i < size; i++) {
                    System.out.println(i + 1 + "." + tasks[i].printTask());
                }
                System.out.println("____________________________");
            } else if (nextLine[0].equals("done")) {
                int index = Integer.parseInt(nextLine[1]) - 1;
                tasks[index].markAsDone(true);
                System.out.println("____________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(index + 1 + "." + tasks[index].printTask());
                System.out.println("____________________________");
            } else {
                if (nextLine[0].equals("todo")) {
                    tasks[size] = new Todo(command.substring(5));
                    size++;
                    System.out.println("____________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[size-1].printTask());
                    System.out.println("Now you have " + size + " tasks in the list.");
                    System.out.println("____________________________");
                } else if (nextLine[0].equals("event") && breakPoint != -1) {
                    tasks[size] = new Event(command.substring(6, breakPoint), command.substring(breakPoint + 4));
                    size++;
                    System.out.println("____________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[size-1].printTask());
                    System.out.println("Now you have " + size + " tasks in the list.");
                    System.out.println("____________________________");
                } else if (nextLine[0].equals("deadline") && breakPoint != -1) {
                    tasks[size] = new Deadline(command.substring(9, breakPoint), command.substring(breakPoint + 4));
                    size++;
                    System.out.println("____________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[size-1].printTask());
                    System.out.println("Now you have " + size + " tasks in the list.");
                    System.out.println("____________________________");
                } else {
                    System.out.println("____________________________");
                    System.out.println("Invalid command for task creation: " + command);
                    System.out.println("____________________________");

                }
            }
            command = sc.nextLine();
        }
        System.out.println("____________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________");
    }
}