import java.util.*;
public class Duke {
    public static final String string = "    _________________________________________";
    public static final String space = "     ";

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
        String list = string + "\n" + space +
                "list\n" +
                string;
        System.out.println(list);
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
            if (line.equals("list")) {
                List();
            } else if (line.equals("blah")) {
                Blah();
            } else {
                Bye();
                break;
            }
        }
        sc.close();
    }
}
