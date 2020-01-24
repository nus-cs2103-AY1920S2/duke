import java.util.ArrayList;
import java.util.Scanner;

import main.java.Deadline;
import main.java.DukeException;
import main.java.DukeGreeting;
import main.java.Event;
import main.java.Task;
import main.java.Todo;

public class Duke {
    public static void main(String[] args) throws DukeException{
        
        /**Declaration of variables */
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> Tasks = new ArrayList<>();
        DukeGreeting dg = new DukeGreeting();

        /**Welcome Message */
        dg.showWelcomeMessage();
        
        /**Run program */
        while(true) {
            String[] x = sc.nextLine().split(" ",2);
            String command = x[0];
            if (command.equals("bye")) {
                dg.showGoodbyeMessage();
                sc.close();
                System.exit(0);
            } else if (command.equals("list")) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Tasks.size(); i++) {
                    System.out.println(i+1 + ". " + Tasks.get(i));
                }
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
                System.out.println(todo + "\n" + "Now you have " + Tasks.size() + " tasks in the list." + "\n");
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
                System.out.println(deadline +"\n" + "Now you have " + Tasks.size() + " tasks in the list." + "\n");
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
                System.out.println(event + "\n" + "Now you have " + Tasks.size() + " tasks in the list." + "\n");
                System.out.println("-------------------------------------------------------------");
            } else if (command.equals("delete")) {
                if (x.length == 1) {
                    throw new DukeException("Tell me which task you want to delete!!");
                } else {
                    String rest = x[1];
                    int index = Integer.valueOf(rest);
                    int currsize = Tasks.size()-1;
                    Tasks.get(index-1).deleteTask();
                    System.out.println("Now you have " + currsize + " tasks in the list." + "\n");
                    System.out.println("-------------------------------------------------------------");
                    Tasks.remove(index-1);
                }
            } else {
                throw new DukeException("Oops I'm sorry, what is this?");
            }
        }

    }
}
