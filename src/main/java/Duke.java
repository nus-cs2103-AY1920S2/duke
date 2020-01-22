import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static int num = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String intro = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(intro);
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                // if the input is 1 word, usually "bye" or "list"
                if (input.split(" ").length == 1) {
                    if (input.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else if (input.equals("list")) {
                        for (Task t : list) {
                            int index = list.indexOf(t) + 1;
                            System.out.println("" + index + "." + t.toString());
                        }
                    } else if (input.equals("todo") || input.equals("event") || input.equals("deadline")) {
                        throw new DukeException("OOPS!!! The description of a " + input + " cannot be empty.");
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
                // if the input is > 1 word; eg deadline return book, done 3
                else {
                    String[] stringArr = input.split(" ", 2);
                    String action = stringArr[0];
                    String taskName = stringArr[1];
                    if (action.equals("done")) {
                        int val = Integer.parseInt(stringArr[1]);
                        Task task = list.get(val - 1);
                        task.setCheck();
                        System.out.println("Nice! I've marked this task as done:\n" + task.toString());

                    } else if (action.equals("delete")) {
                        int val = Integer.parseInt(stringArr[1]);
                        Task t = list.get(val - 1);
                        list.remove(val - 1);
                        int numTask = list.size();
                        System.out.println("Noted. I've removed this task:\n" + t.toString() + "\n" + "Now you have " + numTask + " tasks in the list.");
                    }
                    else if (action.equals("deadline")){
                        String[] splitTaskName = taskName.split("/"); // to obtain command when splitting
                        String[] splitTaskName2 = splitTaskName[1].split(" ", 2); // to obtain date when splitting
                        Deadline deadline = new Deadline(splitTaskName[0], splitTaskName2[1]);
                        list.add(deadline);
                        int numTasks = list.size();
                        System.out.println("Got it. I've added this task:\n" + deadline.toString() + "\n" + "Now you have " + numTasks + " tasks in the list.");
                    } else if (action.equals("event")) {
                        String[] splitTaskName = taskName.split("/"); // to obtain command when splitting
                        String[] splitTaskName2 = splitTaskName[1].split(" ", 2); // to obtain date when splitting
                        Event event = new Event(splitTaskName[0], splitTaskName2[1]);
                        list.add(event);
                        int numTasks = list.size();
                        System.out.println("Got it. I've added this task:\n" + event.toString() + "\n" + "Now you have " + numTasks + " tasks in the list.");
                    } else if (action.equals("todo")) {
                        Todo todo = new Todo(taskName);
                        list.add(todo);
                        int numTasks = list.size();
                        System.out.println("Got it. I've added this task:\n" + todo.toString() + "\n" + "Now you have " + numTasks + " tasks in the list.");
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}