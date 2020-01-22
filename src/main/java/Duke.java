import java.util.*;

public class Duke {
    public static final String line = "    ____________________________________________________________";

    public static String hello() {
        String s1 = "    hello! i'm dUKE!";
        String s2 = "    what do you want from me (・∀・)";
        return line + "\n" + s1 + "\n" + s2 + "\n" + line + "\n";
    }

    public static String list() {
        return line + "\n" + "    there's nothing to list here" + "\n" + line + "\n";
    }

    public static String blah() {
        return line + "\n" + "    blah blah" + "\n" + line + "\n";
    }

    public static String bye() {
        return line + "\n" + "    bye see you again（ｉДｉ）" + "\n" + line + "\n";
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(hello());

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println(list());
            } else if (input.equals("blah")) {
                System.out.println(blah());
            } else {
                System.out.println(bye());
                break;
            }
        }

        sc.close();

    }
}
