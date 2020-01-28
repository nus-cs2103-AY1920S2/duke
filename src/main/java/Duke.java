import java.util.*;

public class Duke {
    public static void main(String[] args) throws DukeException {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        Scanner sc = new Scanner(System.in);
        String Hello = "Hello! I'm Duke\nWhat can i do for you?";
        System.out.println(Hello);

        int flag = 0;
        ArrayList<task> mylist = new ArrayList<>();

        while(flag == 0) {
            String input = sc.nextLine();
            String[] inarr = input.split(" ");
            String Line = "_________________________________________________________________";

            System.out.println(Line);
            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    flag = 1;
                } else if (input.equals("list")) {
                    printlist(mylist);
                } else if (inarr[0].equals("done")) {
                    try {
                        int tocheck = Integer.parseInt(inarr[1]);
                        task temp = mylist.get(tocheck - 1);
                        temp.markDone();

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(mylist.get(tocheck - 1));
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! That task doesn't exist or you failed to include a number.", e);
                    }
                } else if (inarr[0].equals("todo")) {
                    try {
                        ToDo temp = new ToDo(input.replaceFirst("todo ", ""));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(temp);
                        System.out.println("Now you have " + (mylist.size() + 1) + " tasks in the list.");
                        mylist.add(temp);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! You're missing some descriptions for your todo.", e);
                    }
                } else if (inarr[0].equals("deadline")) {
                    try {
                        String wodl = input.replaceFirst("deadline ", "");
                        String[] myarr = wodl.split(" /by ");
                        DeadLine temp = new DeadLine(myarr[0], myarr[1]);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(temp);
                        System.out.println("Now you have " + (mylist.size() + 1) + " tasks in the list.");
                        mylist.add(temp);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! You're missing some descriptions for your deadline.", e);
                    }
                } else if (inarr[0].equals("event")) {
                    try {
                        String woe = input.replaceFirst("event ", "");
                        String[] myarr = woe.split(" /at ");
                        Event temp = new Event(myarr[0], myarr[1]);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(temp);
                        System.out.println("Now you have " + (mylist.size() + 1) + " tasks in the list.");
                        mylist.add(temp);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! You're missing some descriptions for your event.", e);
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }

            System.out.println(Line);
        }
    }

    private static void printlist(ArrayList<task> ls) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < ls.size(); i++) {
            System.out.println((i + 1) + "." + ls.get(i));
        }
    }
}
