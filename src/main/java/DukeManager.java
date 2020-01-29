import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class DukeManager {

    public static final String LINE = "____________________________________________________________\n";
    static ArrayList<Task> tasks = new ArrayList<>();
    public static int totalTasks = 0;

    public DukeManager() {
        System.out.println(hello());
    }


    public static String hello() {
        String s1 = "Hello! i'm dUKE!";
        String s2 = "How can I help you!";
        return LINE + s1 + "\n" + s2 + "\n" + LINE;
    }

    public static String list() {
        StringBuilder listContent = new StringBuilder(LINE);
        listContent.append("Here are the tasks in your list:" + "\n");
        for (int i = 1; i <= totalTasks; i++) {
            Task curTask = tasks.get(i-1);
            listContent.append(i).append(".").append(curTask.toString()).append("\n");
        }
        listContent.append(LINE);
        return listContent.toString();
    }

    public static String bye() {
        return LINE + "\n" + "Bye see you again（ｉДｉ）" + "\n" + LINE;
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
            System.out.println(LINE + "Wow you finally completed something: " + "\n"
                    + comTask.toString() + "\n" + LINE);
        }
    }

    public void runTask(String input, String[] temp, String command) {
        try {
            String task = LINE + "Got it! I've added this task!:" + "\n";
            DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter inputTimeFormat = DateTimeFormatter.ofPattern("HHmm");
            if (temp.length <= 1 || (command.equals("deadline") && !input.contains("/by"))
                    || (command.equals("event") && !input.contains("/at"))) {
                throw new DukeException(input);

            } else if (command.equals("deadline")) {
                    String[] arr = input.split(" /by ");
                    LocalDate ld = LocalDate.parse(arr[1].substring(0, 10), inputDateFormat);
                    LocalTime lt = LocalTime.parse(arr[1].substring(11), inputTimeFormat);
                    tasks.add(new Deadline(arr[0].substring(9), ld, lt));

            } else if (command.equals("event")) {
                    String[] arr = input.split(" /at ");
                    LocalDate ld = LocalDate.parse(arr[1].substring(0, 10), inputDateFormat);
                    LocalTime lt = LocalTime.parse(arr[1].substring(11), inputTimeFormat);
                    tasks.add(new Event(arr[0].substring(6), ld, lt));

            } else {
                tasks.add(new Todo(input.substring(5)));
            }
            totalTasks++;
            task += tasks.get(totalTasks - 1) + "\n";
            task += "Now you have " + totalTasks + " tasks in the list." + "\n" + LINE;
            System.out.println(task);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(String input) {
        totalTasks--;
        int index = Integer.parseInt(input.substring(7)) - 1;
        String delete = LINE + "Noted! I've removed this task: \n" + tasks.get(index) + "\n"
                + "Now you have " + totalTasks + " tasks in the list. \n" + LINE;
        tasks.remove(index);
        System.out.println(delete);
    }

    public void taskSearch(LocalDate date) {
        String tasksByDateTime = LINE;
        for(Task t : tasks) {
            if (t instanceof Deadline) {
                if (((Deadline) t).getDate().isEqual(date)) {
                    tasksByDateTime += t + "\n";
                }
            }
            else if (t instanceof Event) {
                if (((Event) t).getDate().isEqual(date)) {
                    tasksByDateTime += t + "\n";
                }
            }
        }
        System.out.println(tasksByDateTime + LINE);

    }
}
