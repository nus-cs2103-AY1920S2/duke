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
                try {
                    if (strArr.length == 1) {
                        throw new DukeException(7);
                    }
                    Task currTask = arr.get(Integer.parseInt(strArr[1]) - 1);
                    currTask.setDone();
                    System.out.println("Okay noted! You have completed the below task:");
                    System.out.println(currTask);
                } catch (DukeException ex) {
                    System.out.println(ex.toString());
                }
            } else if (command.contains("todo")) {
                String[] strArr = command.split(" ", 2);
                try {
                    if (strArr.length == 1) {
                        throw new DukeException(1);
                    }
                    Todo newTask = new Todo(strArr[1]);
                    arr.add(newTask);
                    System.out.println("Okay! I have taken note of the following:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + arr.size() + " tasks in the list.");
                } catch (DukeException ex) {
                    System.out.println(ex.toString());
                }
            } else if (command.contains("deadline")) {
                String[] strArr = command.split(" ", 2);
                try {
                    if (strArr.length == 1) {
                        throw new DukeException(2);
                    }
                    String[] cmdArr = strArr[1].split("/", 2);
                    try {
                        if (cmdArr.length == 1) {
                            throw new DukeException(3);
                        }
                        command = cmdArr[0];
                        String deadline = cmdArr[1].split(" ", 2)[1];
                        Deadline newTask = new Deadline(command, deadline);
                        arr.add(newTask);
                        System.out.println("Okay! I have taken note of the following:");
                        System.out.println(newTask);
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    } catch (DukeException ex) {
                        System.out.println(ex.toString());
                    }
                } catch (DukeException ex) {
                    System.out.println(ex.toString());
                }
            } else if (command.contains("event")) {
                String[] strArr = command.split(" ", 2);
                try {
                    if (strArr.length == 1) {
                        throw new DukeException(4);
                    }
                    String[] cmdArr = strArr[1].split("/", 2);
                    try {
                        if (cmdArr.length == 1) {
                            throw new DukeException(5);
                        }
                        command = cmdArr[0];
                        String timing = cmdArr[1].split(" ", 2)[1];
                        Event newTask = new Event(command, timing);
                        arr.add(newTask);
                        System.out.println("Okay! I have taken note of the following:");
                        System.out.println(newTask);
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    } catch (DukeException ex) {
                        System.out.println(ex.toString());
                    }
                } catch (DukeException ex) {
                    System.out.println(ex.toString());
                }
            } else if (command.equals("list")) {
                System.out.println("The below is what you have told me so far. Have you completed them?");
                for (Task task : arr) {
                    System.out.println(task);
                }
            } else {
                try {
                    throw new DukeException(6);
                } catch (DukeException ex) {
                    System.out.println(ex.toString());
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
