import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<Task>();
    static Scanner sc = new Scanner(System.in);

    static void printIntro() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Hello! I'm Duke\n" +
                        "What can I do for you?\n" +
                        "____________________________________________________________\n\n");
    }

    static void printReply(Task task) {
        System.out.print(
                "____________________________________________________________\n" +
                "Got it! I've added the task: \n" + task.toString() + "\nNow you have " + Integer.toString(tasks.size()) +
                        " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    static void printGoodbye() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!" +
                        "\n____________________________________________________________\n");
    }

    static void printList(ArrayList<Task> tasks){
        System.out.print("____________________________________________________________\n" +
                "Here are the tasks in your list:\n");
        for (int i =  0; i < tasks.size(); i++){
            System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.print("____________________________________________________________\n");
    }

    static void markTaskDone(Task task){
        task.markAsDone();
        System.out.println("____________________________________________________________\n"
            + "Nice! I've marked this task as done:\n" + task.toString()
                + "\n____________________________________________________________\n");

    }

    static void addTask(String input) throws DukeException, TodoException{
        try {
            if (input.toLowerCase().equals("list")) {
                printList(tasks);
            } else if (input.split(" ")[0].equals("done")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                markTaskDone(tasks.get(taskNumber));
            } else if (input.split(" ")[0].equals("todo")) {
                if (input.split(" ").length == 1){
                    throw new TodoException(input);
                }
                Task task = new Todo(input.split(" ", 2)[1]);
                tasks.add(task);
                printReply(task);
            } else if (input.split(" ")[0].equals("deadline")) {
                LocalDate date = LocalDate.parse(input.split("/by", 2)[1]);
                Task task = new Deadline(input.split("/by", 2)[0].split(" ", 2)[1], date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
                tasks.add(task);
                printReply(task);
            } else if (input.split(" ")[0].equals("event")) {
                LocalDate date = LocalDate.parse(input.split("/by", 2)[1]);
                Task task = new Event(input.split("/at", 2)[0].split(" ", 2)[1], date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
                tasks.add(task);
                printReply(task);
            } else {
                throw new DukeException(input);
            }
        }
        catch (TodoException e){
            System.out.println(e.toString());
        }
        catch(DukeException e){
            System.out.println(e.toString());
        }
    }

    static void deleteTask(String input){
        int pos = Integer.parseInt(input.split(" ")[1]);
        Task task = tasks.remove(pos-1);
        System.out.println("____________________________________________________________\n"
                + " Noted. I've removed this task: \n  "
                + task.toString() + "\n Now you have 4 tasks in the list."
                + "____________________________________________________________");
    }

    public static void main(String[] args) throws DukeException {
        printIntro();
        String input = sc.nextLine();
            while (!input.toLowerCase().equals("bye")) {
                if (input.split(" ")[0].toLowerCase().equals("delete")) {
                    deleteTask(input);
                }
                else {
                    addTask(input);
                }
            input = sc.nextLine();
        }
        printGoodbye();
    }
}
