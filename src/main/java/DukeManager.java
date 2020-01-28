import java.util.ArrayList;

public class DukeManager {


    public DukeManager() {
        System.out.println(hello());
    }

    public static final String line = "____________________________________________________________\n";
    //static Task[] tasks = new Task[100];
    static ArrayList<Task> tasks = new ArrayList<>();
    public static int totalTasks = 0;

    public static String hello() {
        String s1 = "Hello! i'm dUKE!";
        String s2 = "How can I help you!";
        return line + s1 + "\n" + s2 + "\n" + line;
    }

    public static String list() {
        StringBuilder listContent = new StringBuilder(line);
        listContent.append("Here are the tasks in your list:" + "\n");
        for (int i = 1; i <= totalTasks; i++) {
            Task curTask = tasks.get(i-1);
            listContent.append(i).append(".").append(curTask.toString()).append("\n");
        }
        listContent.append(line);
        return listContent.toString();
    }

    public static String bye() {
        return line + "\n" + "Bye see you again（ｉДｉ）" + "\n" + line;
    }

    public void run(String input, String command) {
        if (command.equals("list")) {
            System.out.println(list());
        } else if (command.equals("bye")) {
            System.out.println(bye());
        } else if (command.equals("done")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task comTask = tasks.get(index);
            comTask.markAsDone();
            System.out.println(line + "Wow you finally completed something: " + "\n"
                    + comTask.toString() + "\n" + line);
        }
    }

    public void runTask(String input, String[] temp, String command) {
        try {
            String task = line + "Got it! I've added this task!:" + "\n";
            if (temp.length <= 1 || (command.equals("deadline") && !input.contains("/by"))
                    || (command.equals("event") && !input.contains("/at"))) {
                throw new DukeException(input);
            } else if (command.equals("deadline")) {
                String[] arr = input.split(" /by ");
                tasks.add(new Deadline(arr[0].substring(9), arr[1]));
            } else if (command.equals("event")) {
                String[] arr = input.split(" /at ");
                tasks.add(new Event(arr[0].substring(6), arr[1]));
            } else {
                tasks.add(new Todo(input.substring(5)));
            }
            totalTasks++;
            task += tasks.get(totalTasks - 1) + "\n";
            task += "Now you have " + totalTasks + " tasks in the list." + "\n" + line;
            System.out.println(task);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(String input) {
        totalTasks--;
        int index = Integer.parseInt(input.substring(7)) - 1;
        String delete = line + "Noted! I've removed this task: \n" + tasks.get(index) + "\n"
                + "Now you have " + totalTasks + " tasks in the list. \n" + line;
        tasks.remove(index);
        System.out.println(delete);
    }
}
