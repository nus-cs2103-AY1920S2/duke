import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String separator = "________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you? :)\n");

        Scanner scanner = new Scanner(System.in);

        //store user task
        ArrayList<Task> task = new ArrayList<>();
        String input = "";
        input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println(separator);
            String[] split_input = input.split(" ");
            if (input.equals("list")) {
                for (int i = 0; i < task.size(); i++) {
                    System.out.println((i+1) + ". " + task.get(i));
                }
            } else if (split_input[0].equals("done")) {
                try {
                    int num = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (num >= task.size() || num < 0) {
                        throw new DoneException();
                    } else {
                        System.out.println("Nice! I've marked this task as done: ");
                        task.get(num).markDone();
                        System.out.println("  " + task.get(num));
                    }
                } catch (DoneException e) {
                    System.out.println(e.toString());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("☹ OOPS!!! The number of a done cannot be empty.");
                }
            } else if (split_input[0].equals("todo")) {
                try {
                    task.add(new Todo(input.split(" ", 2)[1]));

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(task.get(task.size()-1));
                    System.out.printf("Now you have %d tasks in the list.\n", task.size());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (split_input[0].equals("deadline")) {
                try {
                    String[] ori = input.split(" ", 2)[1].split("/by ");
                    String desc = ori[0];
                    String by = ori[1];
                    task.add(new Deadline(desc, by));

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(task.get(task.size()-1));
                    System.out.printf("Now you have %d tasks in the list.\n", task.size());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }


            } else if (split_input[0].equals("event")) {
                try {
                    String[] ori = input.split(" ", 2)[1].split("/at ");
                    String desc = ori[0];
                    String at = ori[1];
                    task.add(new Event(desc, at));

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(task.get(task.size()-1));
                    System.out.printf("Now you have %d tasks in the list.\n", task.size());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                }
            } else if (split_input[0].equals("delete")) {
                try {
                    int num = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (num >= task.size() || num < 0) {
                        throw new DeleteException();
                    } else {
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(task.get(num));
                        task.remove(num);
                        System.out.printf("Now you have %d tasks in the list.\n", task.size());
                    }
                } catch (DeleteException e) {
                    System.out.println(e.toString());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("☹ OOPS!!! The number of a done cannot be empty.");
                }
            } else {
                //display reply
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            //next input
            System.out.println(separator);
            input = scanner.nextLine();
        }

        // exit the app
        System.out.println(separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);

    }
}
