import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public enum typeOfError {
        TODO_NODESC, DEADLINE_NODESC, DEADLINE_NODEADLINE, EVENT_NODESC, EVENT_NODATEANDTIME, OTHERS, UNKTASKDONE, UNKTASKTODELETE
    }

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
                        throw new DukeException(typeOfError.UNKTASKDONE.ordinal());
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
                        throw new DukeException(typeOfError.TODO_NODESC.ordinal());
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
                        throw new DukeException(typeOfError.DEADLINE_NODESC.ordinal());
                    }
                    String[] cmdArr = strArr[1].split("/", 2);
                    try {
                        if (cmdArr.length == 1) {
                            throw new DukeException(typeOfError.DEADLINE_NODEADLINE.ordinal());
                        }
                        command = cmdArr[0];
                        String deadline = cmdArr[1].split(" ", 2)[1];
                        String deadlineDate = deadline.split(" ", 2)[0];
                        String deadlineTime = deadline.split(" ",2)[1];
                        Deadline newTask = new Deadline(command, deadlineDate, deadlineTime);
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
                        throw new DukeException(typeOfError.EVENT_NODESC.ordinal());
                    }
                    String[] cmdArr = strArr[1].split("/", 2);
                    try {
                        if (cmdArr.length == 1) {
                            throw new DukeException(typeOfError.EVENT_NODATEANDTIME.ordinal());
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
            } else if (command.contains("delete")) {
                String[] strArr = command.split(" ");
                try {
                    if (strArr.length == 1) {
                        throw new DukeException(typeOfError.UNKTASKTODELETE.ordinal());
                    }
                    Task currTask = arr.get(Integer.parseInt(strArr[1]) - 1);
                    arr.remove(currTask);
                    System.out.println("Okay noted! I have deleted the below task:");
                    System.out.println(currTask);
                    System.out.println("Now you have " + arr.size() + " tasks in the list.");
                } catch (DukeException ex) {
                    System.out.println(ex.toString());
                }
            } else {
                try {
                    throw new DukeException(typeOfError.OTHERS.ordinal());
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
