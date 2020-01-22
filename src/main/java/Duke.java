import java.util.*;

public class Duke {
    public static final String line = "    ____________________________________________________________";
    static ArrayList<Task> newList = new ArrayList<>();

    public static String hello() {
        String s1 = "     hello! i'm dUKE!";
        String s2 = "     what do you want from me (・∀・)";
        return line + "\n" + s1 + "\n" + s2 + "\n" + line + "\n";
    }

    public static String list() {
        String listContent = line + "\n";
        for (int i = 1; i <= newList.size(); i++) {
            Task curTask = newList.get(i-1);
            listContent += "     " + i + ".[" + curTask.getStatusIcon()
                                + "] " + newList.get(i-1) + "\n";
        }
        listContent += line + "\n";
        return listContent;
    }

    public static String blah() {
        return line + "\n" + "     blah blah" + "\n" + line + "\n";
    }

    public static String bye() {
        return line + "\n" + "     bye see you again（ｉДｉ）" + "\n" + line + "\n";
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
            } else if (input.equals("bye")) {
                System.out.println(bye());
                break;
            } else if (input.length() > 5) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task comTask = newList.get(index);
                comTask.markAsDone();
                System.out.println(line + "\n" + "     wow you finally completed something: " + "\n"
                            + "      [" + comTask.getStatusIcon() + "] " + comTask.toString()
                            + "\n" + line + "\n");
            } else {
                System.out.println(line + "\n" + "     adding into list: " + input+ "\n" + line + "\n");
                Task t = new Task(input);
                newList.add(t);
            }
        }

        sc.close();

    }
}
