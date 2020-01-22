import java.util.*;

public class Duke {
    public static final String line = "    ____________________________________________________________";
    static Task[] tasks = new Task[100];
    public static int totalTasks = 0;

    public static String hello() {
        String s1 = "     Hello! i'm dUKE!";
        String s2 = "     How can I help you!";
        return line + "\n" + s1 + "\n" + s2 + "\n" + line + "\n";
    }

    public static String list() {
        StringBuilder listContent = new StringBuilder(line + "\n");
        listContent.append("     Here are the tasks in your list:" + "\n");
        for (int i = 1; i <= totalTasks; i++) {
            Task curTask = tasks[i-1];
            listContent.append("     ").append(i).append(".").append(curTask.toString()).append("\n");
        }
        listContent.append(line + "\n");
        return listContent.toString();
    }

    public static String blah() {
        return line + "\n" + "     blah blah" + "\n" + line + "\n";
    }

    public static String bye() {
        return line + "\n" + "     Bye see you again（ｉДｉ）" + "\n" + line;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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
            } else if (input.substring(0,4).equals("done")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task comTask = tasks[index];
                comTask.markAsDone();
                System.out.println(line + "\n" + "     Wow you finally completed something: " + "\n"
                            + "       " + comTask.toString() + "\n" + line + "\n");
            } else {
                String task = line + "\n" + "     Got it! i've added this task!:" + "\n";
                if (input.contains("/by")) {
                    String[] temp = input.split(" /by ");
                    tasks[totalTasks] = new Deadline(temp[0].substring(9), temp[1]);
                } else if (input.contains(" /at")) {
                    String[] temp = input.split(" /at ");
                    tasks[totalTasks] = new Event(temp[0].substring(6), temp[1]);
                } else { //todoTask
                    tasks[totalTasks] = new Todo(input.substring(5));
                }
                totalTasks++;
                task += "       " + tasks[totalTasks-1] + "\n";
                task += "     Now you have " + totalTasks + " tasks in the list." + "\n" + line + "\n";
                System.out.println(task);
            }
        }

        sc.close();

    }
}
