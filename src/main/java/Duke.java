import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
                "Hello! I'm Duke\n" + "What can I do for you today?\n" +
                "____________________________________________________________";

        // Lines are for in between the two words


        System.out.println(welcome_message);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                Print.print_bye();
                stored_list.clear();
                break;
            } else if (input.equals("list")) {
                System.out.println(lines);
                for (int i = 0; i < stored_list.size(); i++) {
                    System.out.println(space + (i + 1) + "." + stored_list.get(i));
                }
                System.out.println(lines);

            } else if (input.contains("done")) {
                // To split the done with the number
                // Will think of a smarter way tomorrow.
                String[] splited_string = input.split(" ");
                Integer number = Integer.valueOf(splited_string[1]);

                // If you want to do
                if (number > stored_list.size()) {
                    throw new DukeException("You have entered an invalid number!");
                }

                Task finished_task = stored_list.get(number - 1);
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
                stored_list.add(new_todo_task);
                Print.print(new_todo_task, stored_list);
                new_todo_task.saveTask();
            }

            // If the task is a deadline
            else if (input.contains("deadline")) {

                Deadline new_deadline = new Deadline(input, "");
                new_deadline.setDescription(input);
                new_deadline.setBy(input);
                stored_list.add(new_deadline);
                Print.print(new_deadline, stored_list);
                new_deadline.saveTask();
            } else if (input.contains("event")) {
                Event new_event = new Event(input, "");
                new_event.setDescription(input);
                new_event.setAt(input);
                stored_list.add(new_event);
                Print.print(new_event, stored_list);
                new_event.saveTask();
            } else if (input.contains("delete")) {
                String[] splited_string = input.split(" ");
                Integer number = Integer.valueOf(splited_string[1]);
                Task deleted_task = stored_list.get(number - 1);
                stored_list.remove(deleted_task);
                Print.print_delete(deleted_task, stored_list.size());

            } else {
                System.out.println(lines);
                throw new DukeException(" OOPS!!! I'm sorry but I do not know what taht means :-(");
            }
        }
    }


    // To print file contents, saved here if needed to use in the future.
//    private static void printFileContents(String filePath) throws FileNotFoundException {
//        File f = new File(filePath);
//        Scanner s = new Scanner(f);
//        while (s.hasNext()) {
//            System.out.println(s.nextLine());
//        }
//    }
    //        To print the files
//        try{
//            printFileContents("data/fruits.txt");
//        } catch (FileNotFoundException e) {
//            System.out.println("File not Found");
//        }

}

