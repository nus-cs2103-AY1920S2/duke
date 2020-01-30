import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LF = "\n";
    private static final String PIPE = "|";
    private static final String WELCOME_MSG = "Hello! I'm Duke" + LF + "What can I do for you?" + LF;
    private static final String BYE_MSG = "Bye, hope to see you again soon!" + LF;
    private static final String BYE_CMD = "bye";
    private static final String TODO_CMD = "todo";
    private static final String EVENT_CMD = "event";
    private static final String DEADLINE_CMD = "deadline";
    private static final String LIST_CMD = "list";
    private static final String DELETE_CMD = "delete";
    private static final String DONE_CMD = "done";
    private static final String DUKE_TXT_FILE_PATH = "data/duke.txt";


    private static void addAndPrintTask(Task t, List<Task> tasks) {
        tasks.add(t);

        System.out.println("Got it! I've added this task:" + LF + "    " + t + LF
                + "Now, you have " + tasks.size() + " item(s) in your list." + LF);

    }

    private static void saveTasksToFile(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(DUKE_TXT_FILE_PATH);

        StringBuilder sb = new StringBuilder();

        int len = tasks.size();

        for (int i = 0; i < len; ++i) {
            Task t = tasks.get(i);

            String name = t.getName();
            String doneStatus = t.getDone() ? "1" : "0";
            String mnemonic = t.getMnemonic();

            if (t instanceof Todo) {
                sb.append(mnemonic + PIPE + doneStatus + PIPE + name);
            } else if (t instanceof Event) {
                sb.append(mnemonic + PIPE + doneStatus + PIPE + name + PIPE + ((Event) t).getAt());
            } else if (t instanceof Deadline) {
                sb.append(mnemonic + PIPE + doneStatus + PIPE + name + PIPE + ((Deadline) t).getBy());
            }

            if (i < (len - 1)) {
                sb.append(LF);
            }
        }

        fw.write(sb.toString());
        fw.close();
    }

    private static boolean canSplitStr(String str, String regex) {
        String[] strArr = str.split(regex);

        return (strArr.length == 2);
    }

    private static boolean validId(String strId, List<Task> tasks) {
        int id;
        try {
            id = Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            return false;
        }

        if ((id - 1) > tasks.size() - 1 || (id - 1) < 0) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(WELCOME_MSG);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Task> tasks = new ArrayList<>();

        String str1;

        String[] strArr;

        Task t;

        try {
            File f = new File(DUKE_TXT_FILE_PATH);

            if (!f.exists()) {
                // Create file if it does not already exists
                f.createNewFile();
            } else {
                // Populate task list with tasks from file
                Scanner s = new Scanner(f);

                while (s.hasNext()) {
                    String line = s.nextLine();
                    strArr = line.split("\\|");

                    String type = strArr[0];
                    boolean isDone = strArr[1].equals("1");
                    String name = strArr[2];
                    t = null;

                    if (type.equals("T")) {
                        t = new Todo(name, isDone);
                    } else if (type.equals("E")) {
                        t = new Event(name, strArr[3], isDone);
                    } else if (type.equals("D")) {
                        t = new Deadline(name, strArr[3], isDone);
                    }

                    // Add task t to ArrayList
                    tasks.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("Sorry, an error has occurred:");
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                String cmd = br.readLine().trim();

                if (cmd.equals(LIST_CMD)) {
                    System.out.println("Here are your task(s):");

                    int len = tasks.size();

                    for (int i = 0; i < len; ++i) {
                        t = tasks.get(i);
                        System.out.println("    " + (i + 1) + ". " + t);
                    }

                    System.out.println();
                } else if (cmd.startsWith(DONE_CMD)) {
                    if (!canSplitStr(cmd, "done\\s+")) {
                        throw new DukeException("The task to mark done cannot be empty");
                    }

                    str1 = cmd.split("done\\s+")[1];

                    if (!validId(str1, tasks)) {
                        throw new DukeException("The task to mark done is not in the list");
                    }

                    int id = Integer.parseInt(str1);

                    t = tasks.get(id - 1);

                    t.markAsDone();

                    System.out.println("Nice! I've marked this task as done:" + LF + "    " + t + LF);
                    saveTasksToFile(tasks);
                } else if (cmd.startsWith(DELETE_CMD)) {
                    if (!canSplitStr(cmd, "delete\\s+")) {
                        throw new DukeException("The task to delete cannot be empty");
                    }

                    str1 = cmd.split("delete\\s+")[1];

                    if (!validId(str1, tasks)) {
                        throw new DukeException("The task to mark delete is not in the list");
                    }

                    int id = Integer.parseInt(str1);

                    t = tasks.get(id - 1);

                    tasks.remove(id - 1);

                    System.out.println("Noted! I've removed this task:" + LF + "    " + t + LF
                            + "Now, you have " + tasks.size() + " item(s) in your list." + LF);

                    saveTasksToFile(tasks);
                } else if (cmd.startsWith(TODO_CMD)) {
                    if (!canSplitStr(cmd, "todo\\s+")) {
                        throw new DukeException("The description of a todo cannot be empty");
                    }

                    str1  = cmd.split("todo\\s+")[1];

                    t = new Todo(str1);

                    addAndPrintTask(t, tasks);
                    saveTasksToFile(tasks);
                } else if (cmd.startsWith(DEADLINE_CMD)) {
                    if (!canSplitStr(cmd, "deadline\\s+")) {
                        throw new DukeException("The description and timing of a deadline cannot be empty");
                    }

                    str1 = cmd.split("deadline\\s+")[1];

                    if (!canSplitStr(str1, "\\s+/by\\s+")) {
                        throw new DukeException("Both the description and timing of a deadline cannot be empty");
                    }

                    strArr = str1.split("\\s+/by\\s+");

                    String name = strArr[0];
                    String by = strArr[1];

                    t = new Deadline(name, by);

                    addAndPrintTask(t, tasks);
                    saveTasksToFile(tasks);
                } else if (cmd.startsWith(EVENT_CMD)) {
                    if (!canSplitStr(cmd, "event\\s+")) {
                        System.out.println("went here");
                        throw new DukeException("The description and timing of an event cannot be empty");
                    }

                    str1 = cmd.split("event\\s+")[1];

                    if (!canSplitStr(str1, "\\s+/at\\s+")) {
                        System.out.println("went here 2");
                        throw new DukeException("Both the description and timing of an event cannot be empty");
                    }

                    strArr = str1.split("\\s+/at\\s+");

                    String name = strArr[0];
                    String at = strArr[1];

                    t = new Event(name, at);

                    addAndPrintTask(t, tasks);
                    saveTasksToFile(tasks);
                } else if (cmd.equals(BYE_CMD)) {
                    System.out.println(BYE_MSG);
                    break;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (IOException e) {
                System.out.println("Sorry, an error has occurred:");
                e.printStackTrace();
                break;
            } catch (DukeException e) {
                System.out.println(e + LF);
            }
        }
    }
}
