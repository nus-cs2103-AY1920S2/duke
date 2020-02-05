import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Duke {
    private static String taskDataPath = "./data/duke.txt";
    static ArrayList<Task> tasks = new ArrayList<>();
    static {
        try {
            tasks = loadTasks(taskDataPath);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    static HashMap<String, Command> commands = new HashMap<>();
    static {
        commands.put("bye", Command.BYE);
        commands.put("list", Command.LIST);
        commands.put("done", Command.DONE);
        commands.put("todo", Command.TODO);
        commands.put("deadline", Command.DEADLINE);
        commands.put("event", Command.EVENT);
        commands.put("delete", Command.DELETE);
    }

    static ArrayList<Task> loadTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNextLine()) {
            tasks.add(parseText(s.nextLine()));
        }
        return tasks;
    }

    private static Task parseText(String text) {
        String[] taskElements = text.split(" # ");
        Task task;
        switch (taskElements[0]) {
            case "T":
                task = new Todo(taskElements[2]);
                break;
            case "D":
                task = new Deadline(taskElements[2], taskElements[3]);
                break;
            case "E":
                task = new Event(taskElements[2], taskElements[3]);
                break;
            default:
                task = new Task("default");
        }
        if (taskElements[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    private static void writeToFile(String filePath, String text) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }

    private static String tasksToString(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toStringInFile()).append("\n");
        }
        return sb.toString();
    }

    private static void updateTaskData(String filePath, ArrayList<Task> tasks) {
        try {
            writeToFile(filePath, tasksToString(tasks));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(greetings);

        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        try {
            while (!exit) {
                String line = sc.nextLine();
                StringTokenizer st = new StringTokenizer(line);
                String str = st.nextToken();
                Command command = commands.get(str);
                switch (command) {
                    case BYE:
                        exit = true;
                        System.out.println("See you next time! xD");
                        break;
                    case LIST:
                        displayTasks(tasks);
                        break;
                    case DONE:
                        int taskNum = Integer.parseInt(st.nextToken());
                        Task t = tasks.get(taskNum - 1);
                        t.markAsDone();
                        System.out.println("Nice! Congratulations for completing this task:\n" + t);
                        updateTaskData(taskDataPath, tasks);
                        break;
                    case TODO:
                        String todoStuff = st.nextToken("").trim();
//                        if (todoStuff.equals("")) {
//                            throw new IndexOutOfBoundsException("OOPS!!! The description of a todo cannot be empty.");
//                        }
                        Todo todo = new Todo(todoStuff);
                        tasks.add(todo);
                        String todoMsg = "Got it. I've added this task:\n" + todo
                                + String.format("\nnow you have %d tasks in the list.", tasks.size());
                        System.out.println(todoMsg);
                        updateTaskData(taskDataPath, tasks);
                        break;
                    case DEADLINE:
                        String ddlStuff = st.nextToken("/").trim();
                        String ddlTime = st.nextToken("/").trim().substring(3);
                        Deadline ddl = new Deadline(ddlStuff, ddlTime);
                        tasks.add(ddl);
                        String ddlMsg = "Got it. I've added this task:\n" + ddl
                                + String.format("\nnow you have %d tasks in the list.", tasks.size());
                        System.out.println(ddlMsg);
                        updateTaskData(taskDataPath, tasks);
                        break;
                    case EVENT:
                        String eventStuff = st.nextToken("/").trim();
                        String eventTime = st.nextToken("/").trim().substring(3);
                        Event event = new Event(eventStuff, eventTime);
                        tasks.add(event);
                        String eventMsg = "Got it. I've added this task:\n" + event
                                + String.format("\nnow you have %d tasks in the list.", tasks.size());
                        System.out.println(eventMsg);
                        updateTaskData(taskDataPath, tasks);
                        break;
                    case DELETE:
                        int num = Integer.parseInt(st.nextToken());
                        Task del = tasks.get(num - 1);
                        tasks.remove(del);
                        String deleteMsg = "Noted. I've removed this task:\n" + del
                                + String.format("\nnow you have %d tasks in the list.", tasks.size());
                        System.out.println(deleteMsg);
                        updateTaskData(taskDataPath, tasks);
                        break;
                    default:
                        throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println(e);
        }

    }

    static void displayTasks(ArrayList<Task> tasks) {
        int index = 0;
        for (Task task : tasks) {
            String output = String.format("%d. ", ++index) + task;
            System.out.println(output);
        }
    }
}
