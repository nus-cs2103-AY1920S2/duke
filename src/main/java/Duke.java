import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintStream;

public class Duke {
    public static void main(String[] args) {
        //Greeting message
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    printBye();
                    break;
                } else if (input.equals("list")) {
                    printList(list);
                } else if (input.contains("done")) {
                    String[] inputArr = input.split(" ");
                    int num = Integer.parseInt(inputArr[1]);
                    if (num > list.size()) {
                        throw new DukeException("Sorry! That is an invalid task number");
                    } else {
                        doneAction(list, num);
                    }
                } else if (input.contains("todo")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Description of todo cannot be empty");
                    } else {
                        String name = input.substring(5);
                        Task todo = new ToDos(name);
                        addToList(todo, list);
                    }
                } else if (input.contains("deadline")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Description of deadline cannot be empty");
                    } else {
                        int index = input.indexOf("/");
                        if (index == -1) {
                            throw new DukeException("Sorry! Please enter a deadline");
                        } else {
                            String by = input.substring(index + 1);
                            String name = input.substring(9, index);
                            Task deadline = new Deadline(name, by);
                            addToList(deadline, list);
                        }
                    }
                } else if (input.contains("event")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Description of event cannot be empty");
                    } else {
                        int index = input.indexOf("/");
                        if (index == -1) {
                            throw new DukeException("Sorry! Please enter a date and time");
                        } else {
                            String by = input.substring(index + 1);
                            String name = input.substring(6, index);
                            Task event = new Event(name, by);
                            addToList(event, list);
                        }
                    }
                } else if (input.contains("delete")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Please specify a task number");
                    } else {
                        String[] inputArr = input.split(" ");
                        int num = Integer.parseInt(inputArr[1]);
                        if (num > list.size()) {
                            throw new DukeException("Sorry! That is an invalid task number");
                        } else {
                            removeAction(list, num);
                        }
                    }
                } else {
                    throw new DukeException("Sorry! I dont know what that means");
                }
            } catch (DukeException e){
                System.out.println(e);
            }
        }
    }


    public static void addToList(Task task, ArrayList<Task> list) {
        list.add(task);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + list.size() + " tasks in the list");
        System.out.println("    ____________________________________________________________");
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for (Task s : list) {
            int listNo = list.indexOf(s) + 1;
            System.out.println("    " + listNo + "." + s);
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void doneAction(ArrayList<Task> list, int num) {
        Task doneTask = list.get(num - 1);
        doneTask.mark();
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as completed:");
        System.out.println("      " + doneTask);
        System.out.println("    ____________________________________________________________");
    }

    public static void removeAction(ArrayList<Task> list, int num) {
        Task removed = list.get(num - 1);
        list.remove(num - 1);
        System.out.println("    ____________________________________________________________");
        System.out.println("      " + removed);
        System.out.println("    Now you have " + list.size() + " tasks in the list");
        System.out.println("    ____________________________________________________________");

    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
