import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static List<Task> taskList;
    static String dataFilePath = Paths.get(Paths.get(System.getProperty("user.dir")).toString(),
            "data/task_data.txt").toString();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("NOTE: For all date/time input, please use the DD-MM-YYYY HH:MM format.");

        try {
            taskList = fileToTaskList(dataFilePath);
        } catch (FileNotFoundException e) {
            taskList = new ArrayList<>();
        }

        System.out.println("Hello! I'm Duke the dude.\nHow can I serve you?");
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            try {
                if (input.equals("list")) {
                    showList();
                } else if (input.equals("bye")) {
                    exit();
                    break;
                } else if (input.split(" ")[0].equals("done")) {
                    int taskNum = Integer.parseInt(input.split(" ")[1]);
                    done(taskNum);
                } else if (input.split(" ")[0].equals("todo") ||
                           input.split(" ")[0].equals("event") ||
                           input.split(" ")[0].equals("deadline")) {
                    add(input);
                } else if (input.split(" ")[0].equals("delete")) {
                    int taskNum = Integer.parseInt(input.split(" ")[1]);
                    delete(taskNum);
                } else {
                    throw new DukeException();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public static void add(String s) throws DukeException {
        String typeOfTask = s.split(" ", 2)[0];
        Task toAdd = new Task();
        if (typeOfTask.equals("todo")) {
            try {
                String task = s.split(" ", 2)[1];
                toAdd = new Todo(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new TodoException();
            }
        } else if (typeOfTask.equals("event")) {
            try {
                String task = s.split(" ", 2)[1];
                String[] taskParts = task.split(" /at ");
                toAdd = new Event(taskParts[0], taskParts[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EventException();
            }
        } else if (typeOfTask.equals("deadline")) {
            try {
                String task = s.split(" ", 2)[1];
                String[] taskParts = task.split(" /by ");
                toAdd = new Deadline(taskParts[0], taskParts[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DeadlineException();
            }
        }
        taskList.add(toAdd);
        System.out.println("Gotcha! Added this task:\n"
                + "  " + toAdd + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
        try {
            writeTaskListToFile(taskList, dataFilePath);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void delete(int i) {
        Task t = taskList.remove(i-1);
        System.out.println("Poof! This task is gone:\n"
                +  "  " + t + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
        try {
            writeTaskListToFile(taskList, dataFilePath);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void showList() {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task t : taskList) {
            System.out.println(count + "." + t);
            count++;
        }
    }

    public static void done(int i) {
        taskList.get(i-1).markAsDone();
        System.out.println("Nice! I've marked this task as done: \n" +
                "  " + taskList.get(i-1));
        try {
            writeTaskListToFile(taskList, dataFilePath);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to serve you again soon!");
    }

    public static String taskToString(Task t) {
        String str = "";
        int done = t.getIsDone() ? 1 : 0;
        if (t instanceof Todo)
            str = "T | " + done + " | " + t.getDescription();
        if (t instanceof Event)
            str = "E | " + done + " | " + t.getDescription() + " | " + ((Event) t).getDayTime();
        if (t instanceof Deadline)
            str = "D | " + done + " | " + t.getDescription() + " | " + ((Deadline) t).getDayTime();
        return str + "\n";
    }

    public static void writeTaskListToFile(List<Task> list, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : list)
            fw.write(taskToString(t));
        fw.close();
    }

    public static List<Task> fileToTaskList(String filePath) throws FileNotFoundException {
        List<Task> list = new ArrayList<>();
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] elements = str.split(" \\| ");
            Task t = new Task();
            switch (elements[0]) {
                case "T":
                    t = new Todo(elements[2]);
                    break;
                case "E":
                    t = new Event(elements[2], elements[3]);
                    break;
                case "D":
                    t = new Deadline(elements[2], elements[3]);
            }
            if (Integer.parseInt(elements[1]) == 1)
                t.markAsDone();
            list.add(t);
        }
        return list;
    }
}
