import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<>();
        System.out.println("Hi! I am Duke! What would you like to tell me today?:)");

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.contains("done")) {
                String[] strArr = command.split(" ");
                Task currTask = arr.get(Integer.parseInt(strArr[1]) - 1);
                currTask.setDone();
                System.out.println("Okay noted! You have completed the below task:");
                System.out.println(currTask);
            } else if (command.contains("todo")) {
                String[] cmd = command.split(" ", 2);
                Todo newTask = new Todo(cmd[1]);
                arr.add(newTask);
                System.out.println("Okay! I have taken note of the following:");
                System.out.println(newTask);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (command.contains("deadline")) {
                String[] strArr = command.split("/", 2);
                String deadline = strArr[1].split(" ", 2)[1];
                command = strArr[0].split(" ", 2)[1];
                Deadline newTask = new Deadline(command, deadline);
                arr.add(newTask);
                System.out.println("Okay! I have taken note of the following:");
                System.out.println(newTask);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (command.contains("event")) {
                String[] strArr = command.split("/", 2);
                String timing = strArr[1].split(" ", 2)[1];
                command = strArr[0].split(" ", 2)[1];
                Event newTask = new Event(command, timing);
                arr.add(newTask);
                System.out.println("Okay! I have taken note of the following:");
                System.out.println(newTask);
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
            } else if (command.equals("list")) {
                System.out.println("The below is what you have told me so far. Have you completed them?");
                for (Task task : arr) {
                    System.out.println(task);
                }
            }
            if (sc.hasNextLine()) {
                command = sc.nextLine();
            } else {
                break;
            }
        }
        System.out.println("Okay then! Goodbye!");
    }
}
