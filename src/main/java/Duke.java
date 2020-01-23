import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void print(String toPrint) {
        String line = "\n______________________________________ \n";
        System.out.println(line + toPrint + line);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList(new ArrayList<Task>());

        print("Hello! I'm Duke \nWhat can I do for you?");

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] split = input.split("\\s+");
            String command = split[0];
            int numOfTask;

            if (command.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            
            } else if (command.equals("list")) {
                print(taskList.toString());
            
            } else if (command.equals("done")) {
                int index = Integer.parseInt(split[1]) - 1;
                Task done = taskList.list.get(index);
                done.markAsDone();
                print("Nice! I've marked this task as done: \n" + done);

            } else if (command.equals("todo")) {
                String desc = input.substring(5);
                Task t = new ToDoTask(desc);
                taskList.list.add(t);
                numOfTask = taskList.list.size();
                print("Got it. I've added this task: \n" + t + "\nNow you have " 
                        + numOfTask + " tasks in the list.");

            } else if (command.equals("deadline")) {
                String[] arr = input.split("/by");
                Task t = new DeadlineTask(arr[0].substring(9), arr[1]);
                taskList.list.add(t);
                numOfTask = taskList.list.size();
                print("Got it. I've added this task: \n" + t + "\nNow you have " 
                        + numOfTask + " tasks in the list.");

            } else if (command.equals("event")) {
                String[] arr = input.split("/at");
                Task t = new EventTask(arr[0].substring(5), arr[1]);
                taskList.list.add(t);
                numOfTask = taskList.list.size();
                print("Got it. I've added this task: \n" + t + "\nNow you have " 
                        + numOfTask + " tasks in the list.");
            } 
        }
        scanner.close();
    }
}
