import java.io.*;
import java.util.ArrayList;

public class DukeManager {

    public static final String LINE = "____________________________________________________________\n";
    public static final String FILE_PATH = "/Users/jadetay/duke/data/tasks.txt";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public int totalTasks = 0;

    public DukeManager() {
        System.out.println(hello());
    }

    public void loadTasks() {
        try {
            File file = new File(FILE_PATH);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                totalTasks++;
                String[] temp = line.split(" \\| ");
                String task = temp[0];
                Integer isDone = Integer.parseInt(temp[1]);
                String description = temp[2];
                if (task.equals("T")) {
                    Task t = new Todo(description);
                    tasks.add(t);
                    if (isDone == 1) {
                        t.markAsDone();
                    }
                } else if (task.equals("D")) {
                    Task d = new Deadline(description, temp[3]);
                    tasks.add(d);
                    if (isDone == 1) {
                        d.markAsDone();
                    }
                } else {
                    Task e = new Event(description, temp[3]);
                    tasks.add(e);
                    if (isDone == 1) {
                        e.markAsDone();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No file found!");
        }
    }

    public static String hello() {
        String s1 = "Hello! i'm dUKE!";
        String s2 = "How can I help you!";
        return LINE + s1 + "\n" + s2 + "\n" + LINE;
    }

    public static String list() {
        StringBuilder listContent = new StringBuilder(LINE);
        listContent.append("Here are the tasks in your list:" + "\n");
        for (int i = 1; i <= tasks.size(); i++) {
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
            saveTasks(tasks);

        }
    }

    public void runTask(String input, String[] temp, String command) {
        try {
            String task = LINE + "Got it! I've added this task!:" + "\n";
            if (temp.length <= 1 || (command.equals("deadline") && !input.contains("/by"))
                    || (command.equals("event") && !input.contains("/at"))) {
                throw new DukeException(input);
            } else if (command.equals("deadline")) {
                String[] arr = input.split(" /by ");
                Task d = new Deadline(arr[0].substring(9), arr[1]);
                tasks.add(d);
                saveTasks(tasks);
            } else if (command.equals("event")) {
                String[] arr = input.split(" /at ");
                Task e = new Event(arr[0].substring(6), arr[1]);
                tasks.add(e);
                saveTasks(tasks);
            } else {
                Task t = new Todo(input.substring(5));
                tasks.add(t);
                saveTasks(tasks);
            }
            totalTasks++;
            task += tasks.get(totalTasks - 1) + "\n";
            task += "Now you have " + totalTasks + " tasks in the list." + "\n" + LINE;
            System.out.println(task);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task t : tasks) {
                fw.write(formatSavedFile(t) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Write error!");
        }

    }

    private static String formatSavedFile(Task task) {
        String toAdd = "";
        int isDone = 0;
        if (task.isDone()) {
            isDone = 1;
        }

        if (task instanceof Todo) {
            toAdd += "T | " + isDone + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            toAdd += "D | " + isDone + " | " + d.getDescription() + " | " + d.getDeadline();
        } else if (task instanceof Event) {
            Event e = (Event) task;
            toAdd += "E | " + isDone + " | " + e.getDescription() + " | " + e.getEvent();
        }
        return toAdd;
    }

    public void delete(String input) {
        totalTasks--;
        int index = Integer.parseInt(input.substring(7)) - 1;
        String delete = LINE + "Noted! I've removed this task: \n" + tasks.get(index) + "\n"
                + "Now you have " + totalTasks + " tasks in the list. \n" + LINE;
        tasks.remove(index);
        saveTasks(tasks);
        System.out.println(delete);
    }
}
