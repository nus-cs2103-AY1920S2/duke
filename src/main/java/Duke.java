import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________");
        String command = sc.nextLine();
        List<Task> tasks = new ArrayList<Task>();
        while(!command.equals("bye")) {
            int breakPoint = command.indexOf('/');
            String[] nextLine = command.split("\\s+");
            if(nextLine[0].equals("list")) {
                System.out.println("____________________________");
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).printTask());
                }
                System.out.println("____________________________");
            } else if (nextLine[0].equals("done")) {
                int index = Integer.parseInt(nextLine[1]) - 1;
                tasks.get(index).markAsDone(true);
                System.out.println("____________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(index + 1 + "." + tasks.get(index).printTask());
                System.out.println("____________________________");
            } else if (nextLine[0].equals("delete")) {
                int index = Integer.parseInt(nextLine[1]) - 1;
                Task toBeDeleted = tasks.remove(index);
                System.out.println("____________________________");
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + toBeDeleted.printTask());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________");

            } else {
                if (nextLine[0].equals("todo")) {
                    if (nextLine.length == 1) {
                        System.out.println("____________________________");
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("____________________________");
                    } else {
                        tasks.add(new Todo(command.substring(5)));
                        System.out.println("____________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1).printTask());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________");
                    }
                } else if (nextLine[0].equals("event")) {
                    if (breakPoint != -1) {
                        tasks.add(new Event(command.substring(6, breakPoint), command.substring(breakPoint + 4)));
                        System.out.println("____________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1).printTask());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________");
                    } else {
                        System.out.println("____________________________");
                        System.out.println("☹ OOPS!!! Information about the event is missing");
                        System.out.println("____________________________");
                    }
                } else if (nextLine[0].equals("deadline")) {
                    if (breakPoint != -1) {
                        tasks.add(new Deadline(command.substring(9, breakPoint), command.substring(breakPoint + 4)));
                        System.out.println("____________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1).printTask());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________");
                    } else {
                        System.out.println("____________________________");
                        System.out.println("☹ OOPS!!! Information about the deadline is missing");
                        System.out.println("____________________________");
                    }
                } else {
                    System.out.println("____________________________");
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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