import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String lines = "        ____________________________________________________________";
    private static String space = "        ";

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }

    private void run() throws DukeException{
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        // Welcome message for the user
        String welcome_message = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" + "What can I do for you today?\n" +
                "____________________________________________________________";

        // Lines are for in between the two words
        System.out.println(welcome_message);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(lines);
                System.out.println("        Bye. Hope to see you again soon");
                System.out.println(lines);
                taskList.getList().clear();
                break;
            } else if (input.equals("list")) {
                System.out.println(lines);
                taskList.print_elements();
                System.out.println(lines);

            } else if (input.contains("done")) {
                // To split the done with the number
                // Will think of a smarter way tomorrow.
                String[] splited_string = input.split(" ");
                Integer number = Integer.valueOf(splited_string[1]);

                // If you want to do
                if (number > taskList.size_of_list() - 1) {
                    throw new DukeException("You have entered an invalid number!");
                }

                Task finished_task = taskList.getList().get(number - 1);
                finished_task.setDone(true);
                System.out.println(lines);
                System.out.println(space + "Nice! I've marked this task as done");
                System.out.println(space + " [" + finished_task.getStatusIcon() + "]"
                        + " " + finished_task.getDescription());
                System.out.println(lines);

            }
            // If the input consists of to-do
            else if (input.contains("todo")) {

                Task new_todo_task = new Todo(input);
                String todo_task = new_todo_task.format_tasks();
                new_todo_task.setDescription(todo_task);
                taskList.add_to_list(new_todo_task);
                print(new_todo_task, taskList.getList());
            }

            // If the task is a deadline
            else if (input.contains("deadline")) {

                Deadline new_deadline = new Deadline(input, "");
                new_deadline.setDescription(input);
                new_deadline.setBy(input);
                taskList.add_to_list(new_deadline);
                new_deadline.setD1();
                print(new_deadline, taskList.getList());



            } else if (input.contains("event")) {
                Event new_event = new Event(input, "");
                new_event.setDescription(input);
                new_event.setAt(input);
                taskList.add_to_list(new_event);
                print(new_event, taskList.getList());
                new_event.setD1();

            } else if (input.contains("delete")) {
                String[] splited_string = input.split(" ");
                Integer number = Integer.valueOf(splited_string[1]);
                Task deleted_task = taskList.getList().get(number - 1);
                taskList.remove_from_list(deleted_task);
                System.out.println(lines);
                System.out.println(space + "Noted. I've removed this task:");
                System.out.println(space + deleted_task);
                System.out.println(space + "Now you have " + taskList.size_of_list() + " tasks in the list.");
                System.out.println(lines);
            } else {
                System.out.println(lines);
                throw new DukeException(" OOPS!!! I'm sorry but I do not know what taht means :-(");
            }
        }
    }

    // For the print formatting for to-do, deadline and event
    private static void print(Task task, ArrayList<Task> list) {
        System.out.println(lines);
        task.got_it_line();
        System.out.println(space + task);
        System.out.println(space + " Now you have " + (list.size()) + " tasks in the list.");
        System.out.println(lines);
    }


}