import java.util.Scanner;

public class Duke {
    static String space = "     ";
    static String line = space + "____________________________________________________________";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = line + "\n" + space + " Hello! I'm Duke\n" + space + " What can I do for you?\n" + line + "\n";
        System.out.print(greeting);
        Scanner sc = new Scanner(System.in);
        processlist(sc);
    }

    private static void processlist(Scanner sc) throws IllegalInstructionException {
        String[] lists = new String[100];
        List list = new List();
        int count = 0;
        while (!sc.hasNext("bye")) {
            try {
                String temp = sc.nextLine();
                String[] tmp = temp.split(" ");
                String task = "";
                for (int i = 1; i < tmp.length; i++) {
                    task += tmp[i];
                    if (i != tmp.length - 1) {
                        task += " ";
                    }
                }
                if (temp.equals("list")) {
                    System.out.println(line + "\n" + list);
                } else if (tmp[0].equals("done")) {
                    int index = Integer.parseInt(tmp[1]);
                    list.items[index - 1].markDone();
                } else if (tmp[0].equals("todo")) {
                    if (task.equals("")) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                                + line);
                    }
                    Todo todo = new Todo(task);
                    list.addItem(todo);
                } else if (tmp[0].equals("event")) {
                    if (task.equals("")) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The description of a event cannot be empty.\n"
                                + line);
                    }
                    String[] e = task.split(" /at ");
                    if (e.length < 2) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The time of a event cannot be empty.\n"
                                + line);
                    }
                    Event event = new Event(e[0], e[1]);
                    list.addItem(event);
                } else if (tmp[0].equals("deadline")) {
                    if (task.equals("")) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The description of a deadline cannot be empty.\n"
                                + line);
                    }
                    String[] d = task.split(" /by ");
                    if (d.length < 2) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The time of a deadline cannot be empty.\n"
                                + line);
                    }
                    Deadline ddl = new Deadline(d[0], d[1]);
                    list.addItem(ddl);
                } else {
                    throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                            + line);
                }
            } catch (IllegalInstructionException e) {
                System.err.println(e.getMessage());
            }
        }
        String bye = line + "\n" + space + " Bye. Hope to see you again soon!\n" + line;
        System.out.print(bye);
        return;
    }
}
