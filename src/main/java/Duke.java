import java.util.*;
public class Duke {
    public static final String string = "    _________________________________________";
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

    public static void Blah() {
        String blah = string + "\n" + space +
                "blah\n" +
                string;
        System.out.println(blah);
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

    public static void Add(String action) {
        String add = string + "\n" + space +
                "added: " + action + "\n" +
                string;
        lst.add(action);
        Task task = new Task(action);
        tasks.add(task);
        System.out.println(add);
    }

    public static void Done(String str) {
        System.out.println(string);
        int num = Integer.parseInt(str);
        Task task = tasks.get(num - 1);
        task.markDone();
        System.out.println(space + "Nice! I've marked this done as done: ");
        System.out.println(space + "  " + task.toString());
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
            } else if (line.equals("blah")) {
                Blah();
            } else if (line.equals("bye")){
                Bye();
                break;
            } else if (str[0].equals("done")){
                Done(str[1]);
            } else {
                Add(line);
            }
        }
        sc.close();
    }
}
