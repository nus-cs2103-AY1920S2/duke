import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String lines = "        ____________________________________________________________";
    private static String space = "        ";
    public static void main(String[] args) throws DukeException {
        ArrayList<Task> stored_list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // Welcome message for the user
        String welcome_message = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" + "What can I do for you today?\n"+
                "____________________________________________________________";

        // Lines are for in between the two words


        System.out.println(welcome_message);

        while(sc.hasNext()) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(lines);
                System.out.println("        Bye. Hope to see you again soon");
                System.out.println(lines);
                stored_list.clear();
                break;
            } else if (input.equals("list")) {
                System.out.println(lines);
                for(int i=0; i< stored_list.size(); i++) {
                    System.out.println(space + (i+1) + "." + stored_list.get(i));
                }
                System.out.println(lines);

            } else if (input.contains("done")) {
                // To split the done with the number
                // Will think of a smarter way tomorrow.
                String[] splited_string = input.split(" ");
                Integer number = Integer.valueOf(splited_string[1]);

                // If you want to do
                if(number > stored_list.size()-1) {
                    throw new DukeException("You have entered an invalid number!");
                }

                Task finished_task = stored_list.get(number-1);
                finished_task.setDone(true);
                System.out.println(lines);
                System.out.println(space +"Nice! I've marked this task as done");
                System.out.println(space + " [" + finished_task.getStatusIcon() + "]"
                        +" "+ finished_task.getDescription());
                System.out.println(lines);

            }
            // If the input consists of to-do
            else if(input.contains("todo")) {

                Task new_todo_task = new Todo(input);
                String todo_task = new_todo_task.format_tasks();
                new_todo_task.setDescription(todo_task);
                stored_list.add(new_todo_task);
                print(new_todo_task, stored_list);
            }

            // If the task is a deadline
            else if (input.contains("deadline")) {

                Deadline new_deadline = new Deadline(input, "");
                new_deadline.setDescription(input);
                new_deadline.setBy(input);
                stored_list.add(new_deadline);
                print(new_deadline, stored_list);
            }

            else if(input.contains("event")) {
                Event new_event = new Event(input, "");
                new_event.setDescription(input);
                new_event.setAt(input);
                stored_list.add(new_event);
                print(new_event, stored_list);
            }

            else if (input.contains("delete")) {
                String[] splited_string = input.split(" ");
                Integer number = Integer.valueOf(splited_string[1]);
                Task deleted_task = stored_list.get(number-1);
                stored_list.remove(deleted_task);
                System.out.println(lines);
                System.out.println(space + "Noted. I've removed this task:");
                System.out.println(space + deleted_task);
                System.out.println(space + "Now you have " + stored_list.size() + " tasks in the list.");
                System.out.println(lines);
            }

            else {
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