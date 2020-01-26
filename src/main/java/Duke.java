import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ArrayList<Task> taskList = new ArrayList<>();

        //Greet the user
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //Echo
        Scanner s = new Scanner(System.in);
        String command = "";
        while (s.hasNext()) {
            command = s.nextLine();
            if (command.equals("bye")) {
                //Exit
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                //List all tasks
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i + 1 + ". " + taskList.get(i));
                }
            } else if ((command.length() > 3) && (command.substring(0, 4).equals("done"))) {
                //Mark task as done
                int taskNumber = Integer.parseInt(command.substring(5));
                taskList.get(taskNumber-1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(taskNumber-1));
            } else if ((command.length() > 5) && (command.substring(0, 6).equals("delete"))) {
                //Delete task
                try {
                    int taskNumber = Integer.parseInt(command.substring(7));
                    Task temp = taskList.remove(taskNumber-1);
                    System.out.println("Nice! I have removed this task:");
                    System.out.println(temp);
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("Please add a task number to delete");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Cannot delete from an empty list");
                }
            } else {
                //Add task
                Task newTask = new Task("placeholder");
                if (command.contains("todo")) {
                    try {
                        newTask = new ToDo(command.substring(5));
                        taskList.add(newTask);
                        System.out.println("Got it. I've added this task: \n" + newTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("ToDo description cannot be empty");
                    }
                } else
                if (command.contains("deadline")) {
                    try {
                        int breakPos = command.indexOf("/");
                        if ((breakPos == -1) && (command.length() == 8)) throw new DukeException("No desc Deadline");
                        newTask = new Deadline(command.substring(0, breakPos - 1), command.substring(breakPos + 4));
                        taskList.add(newTask);
                        System.out.println("Got it. I've added this task: \n" + newTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list");
                    } catch (DukeException e) {
                        System.out.println("Deadline description cannot be empty");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Please re-enter the date for Deadline");
                    }
                } else
                if (command.contains("event")) {
                    try {
                        int breakPos = command.indexOf("/");
                        if ((breakPos == -1) && (command.length() == 5)) throw new DukeException("No desc Event");
                        newTask = new Event(command.substring(0, breakPos - 1), command.substring(breakPos + 4));
                        taskList.add(newTask);
                        System.out.println("Got it. I've added this task: \n" + newTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list");
                    } catch (DukeException e) {
                    System.out.println("Event description cannot be empty");
                    } catch (IndexOutOfBoundsException e) {
                      System.out.println("Please re-enter the date for Event");
                    }
                } else {
                    System.out.println("Please input a valid command");
                }
            }
        }
    }
}
