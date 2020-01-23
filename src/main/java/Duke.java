import main.java.Deadline;
import main.java.DukeException;
import main.java.Task;
import main.java.Todo;
import main.java.Event;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) throws DukeException{
        
        /**Declaration of variables */
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> Tasks = new ArrayList<>();

        /**Welcome Message */
        System.out.println("-------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        
        /**Run program */
        while(true) {
            String[] x = sc.nextLine().split(" ",2);
            String command = x[0];
            if (command.equals("bye")) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println();
                System.out.println("-------------------------------------------------------------");
                System.out.println();
                sc.close();
                System.exit(0);
            } else if (command.equals("list")) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Tasks.size(); i++) {
                    System.out.println(i+1 + ". " + Tasks.get(i));
                }
                System.out.println();
                System.out.println("-------------------------------------------------------------");
            } else if (command.equals("done")) {
                if (x.length == 1) {
                    throw new DukeException("Tell me which task you have completed!!");
                } else {
                    String rest = x[1];
                    int index = Integer.valueOf(rest);
                    Tasks.get(index-1).markAsDone();
                }
            } else if (command.equals("todo")) {
                if (x.length == 1) {
                    throw new DukeException("Tell me the description of the task!!");
                } 
                String rest = x[1];
                Todo todo = new Todo(rest);
                Tasks.add(todo);
                System.out.println("-------------------------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(todo);
                System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
                System.out.println();
                System.out.println("-------------------------------------------------------------");
            } else if (command.equals("deadline")) {
                if (x.length == 1) {
                    throw new DukeException("Tell me the description of the task!!");
                } 
                String rest = x[1];
                String[] q = rest.split("/");
                Deadline deadline = new Deadline(q[0], q[1]);
                Tasks.add(deadline);
                System.out.println("-------------------------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(deadline);
                System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
                System.out.println();
                System.out.println("-------------------------------------------------------------");

            } else if (command.equals("event")) {
                if (x.length == 1) {
                    throw new DukeException("Tell me the description of the task!!");
                } 
                String rest = x[1];
                String[] q = rest.split("/");
                Event event = new Event(q[0], q[1]);
                Tasks.add(event);
                System.out.println("-------------------------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(event);
                System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
                System.out.println();
                System.out.println("-------------------------------------------------------------");
            } else if (command.equals("delete")) {
                if (x.length == 1) {
                    throw new DukeException("Tell me which task you want to delete!!");
                } else {
                    String rest = x[1];
                    int index = Integer.valueOf(rest);
                    Tasks.get(index-1).deleteTask();
                    System.out.println("Now you have " + Tasks.size()-1 + " tasks in the list.");
                    System.out.println();
                    System.out.println("-------------------------------------------------------------");
                    Tasks.remove(index-1);

                }
            } else {
                throw new DukeException("Oops I'm sorry, what is this?");
            }
        }

    }
}
