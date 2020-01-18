import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> stored_list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // Welcome message for the user
        String welcome_message = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" + "What can I do for you today?\n"+
                "____________________________________________________________";

        // Lines are for in between the two words
        String lines = "        ____________________________________________________________";
        String space = "        ";

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
                System.out.println(lines);
                stored_list.add(new_todo_task);
                new_todo_task.got_it_line();
                System.out.println(space + new_todo_task);
                System.out.println(space + " Now you have " + (stored_list.size()) + " tasks in the list.");
                System.out.println(lines);

            }

            // If the task is a deadline
            else if (input.contains("deadline")) {

                Deadline new_deadline = new Deadline(input, "");
                new_deadline.setDescription(input);
                new_deadline.setBy(input);
                System.out.println(lines);
                stored_list.add(new_deadline);
                new_deadline.got_it_line();
                System.out.println(space + new_deadline);
                System.out.println(space + " Now you have " + (stored_list.size()) + " tasks in the list.");
                System.out.println(lines);
            }

            else if(input.contains("event")) {
                Event new_event = new Event(input, "");
                new_event.setDescription(input);
                new_event.setAt(input);
                System.out.println(lines);
                stored_list.add(new_event);
                new_event.got_it_line();
                System.out.println(space + new_event);
                System.out.println(space + " Now you have " + (stored_list.size()) + " tasks in the list.");
                System.out.println(lines);

            }

            else {
                // Else we add new tasks to the list.
                stored_list.add(new Task(input));
                // Print out the input
                System.out.println(lines);
                System.out.println("         added: " + input);
                System.out.println(lines);
            }
        }
    }


}