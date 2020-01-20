import java.util.*;
public class Duke {
    public static final String string = "    ____________________________________________________________";
    public static final String space = "     ";
    public static ArrayList<String> lst = new ArrayList<>();
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void Greeting() {
        String greeting = string + "\n"+ space +
                "Hello! I'm Duke\n" + space +
                "What can I do for you?\n" +
                string;
        System.out.println(greeting);
    }

    public static void Bye() {
        String bye = string + "\n" + space +
                "Bye. Hope to see you again soon!\n" +
                string;
        System.out.println(bye);
    }

    public static void List() {
        System.out.println(string);
        System.out.println(space + "Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i - 1);
            System.out.println(space + i + "." + t.toString());
        }
        System.out.println(string);
    }

//    public static void Add(String action) {
//        String add = string + "\n" + space +
//                "added: " + action + "\n" +
//                string;
//        lst.add(action);
//        Task task = new Task(action);
//        tasks.add(task);
//        System.out.println(add);
//    }

    public static void Done(String str) {
        System.out.println(string);
        int num = Integer.parseInt(str);
        Task task = tasks.get(num - 1);
        task.markDone();
        System.out.println(space + "Nice! I've marked this done as done: ");
        System.out.println(space + "  " + task.toString());
        System.out.println(string);
    }

    public static void Deadline(String action, String by) {
        System.out.println(string);
        String[] b = by.split(" ", 2);
        Deadline deadline = new Deadline(action, b[1]);
        System.out.println(space + "Got it. I've added this task:");
        System.out.println(space + "  " + deadline.toString());
        tasks.add(deadline);
        if (tasks.size() == 1 || tasks.size() == 0) {
            System.out.println(space + "Now you have " + tasks.size() + " task in the list");
        } else {
            System.out.println(space + "Now you have " + tasks.size() + " tasks in the list");
        }
        System.out.println(string);
    }

    public static void Event(String action, String at) {
        System.out.println(string);
        String[] a = at.split(" ", 2);
        Event event = new Event(action, a[1]);
        System.out.println(space + "Got it. I've added this task:");
        System.out.println(space + "  " + event.toString());
        tasks.add(event);
        if (tasks.size() == 1 || tasks.size() == 0) {
            System.out.println(space + "Now you have " + tasks.size() + " task in the list");
        } else {
            System.out.println(space + "Now you have " + tasks.size() + " tasks in the list");
        }
        System.out.println(string);
    }

    public static void Todo(String action) {
        System.out.println(string);
        Todo todo = new Todo(action);
        System.out.println(space + "Got it. I've added this task:");
        System.out.println(space + "  " + todo.toString());
        tasks.add(todo);
        if (tasks.size() == 1 || tasks.size() == 0) {
            System.out.println(space + "Now you have " + tasks.size() + " task in the list");
        } else {
            System.out.println(space + "Now you have " + tasks.size() + " tasks in the list");
        }
        System.out.println(string);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        Greeting();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] str = line.split(" ");
            if (line.equals("list")) {
                List();
            } else if (line.equals("bye")){
                Bye();
                break;
            } else if (str[0].equals("done")){
                try {
                    if (line.length() <= 4) {
                        throw new DoneException();
                    } else {
                        Done(str[1]);
                    }
                } catch (DoneException doneErr){
                    System.out.println(doneErr);
                }
            } else if (str[0].equals("deadline")) {
                try {
                    if (line.length() <= 8) {
                        throw new EmptyException();
                    } else {
                        String[] d = line.split(" ", 2);
                        String[] helper = d[1].split("/");
                        Deadline(helper[0], helper[1]);
                    }
                } catch (EmptyException emErr) {
                    System.out.println(emErr);
                }
            } else if (str[0].equals("event")) {
                try {
                    if (line.length() <= 5) {
                        throw new EmptyException();
                    } else {
                        String[] e = line.split(" ", 2);
                        String[] helper = e[1].split("/");
                        Event(helper[0], helper[1]);
                    }
                } catch (EmptyException emErr){
                    System.out.println(emErr);
                }
            } else if (str[0].equals("todo")) {
                try {
                    if (line.length() <= 4) {
                        throw new EmptyException();
                    } else {
                        String[] e = line.split(" ", 2);
                        Todo(e[1]);
                    }
                } catch (EmptyException emErr){
                    System.out.println(emErr);
                }
            } else {
                try {
                    throw new UnknownException();
                } catch (UnknownException unknownErr) {
                    System.out.println(unknownErr);
                }
            }
        }
        sc.close();
    }
}
