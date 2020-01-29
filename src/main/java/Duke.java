import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Duke
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class Duke {
    static String separator = "____________________________________________________________";
    static List<Task> tasks = new ArrayList<>();

    /**
     * The main method is where Duke introduces itself.
     *
     * @param args not used.
     */
    public static void main(String[] args) throws IOException {
        System.out.println(separator + "\nHello! I'm Duke\nWhat can I do for you?\n" + separator);
        loadTasks();
        handleInput();
    }

    /**
     * Handles input from user.
     */
    private static void handleInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            StringBuilder sb = new StringBuilder(separator + "\n");
            String grammar = tasks.size() > 1 ? " tasks" : " task";
            String[] splitInput = input.trim().split("\\s+", 2);
            String cmd = splitInput[0];

            try {
                if (cmd.equals("list")) {
                    listTasks(sb);
                } else if (cmd.equals("delete")) {
                    deleteTask(sb, grammar, splitInput);
                } else if (cmd.equals("done")) {
                    markTaskAsDone(sb, splitInput);
                } else if (cmd.equals("todo")) {
                    if (splitInput.length < 2) {
                        throw new NoDescriptionException();
                    } else {
                        Task toSave = new Todo(splitInput[1]);
                        saveTask(toSave, true);
                        tasks.add(toSave);
                        actionConfirmation(sb, grammar);
                    }
                } else {
                    if (cmd.equals("deadline")) {
                        addDeadlineOrEvt(sb, splitInput, " /by ");
                    } else if (cmd.equals("event")) {
                        addDeadlineOrEvt(sb, splitInput, " /at ");
                    } else {
                        throw new InvalidCommandException();
                    }
                }
                sb.append(separator);
                System.out.println(sb);
            } catch (DukeException | IOException e) {
                System.err.println(separator + "\n" + e.toString() + separator);
            } finally {
                input = sc.nextLine();
            }
        }

        if (input.equals("bye")) {
            bye();
        }
    }

    private static void saveTask(Task task, boolean isAppendMode) throws IOException {
        FileOutputStream ops = new FileOutputStream(new File("../data/duke.txt"), isAppendMode);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ops));
        String[] toSave = new String[4];
        toSave[1] = task.isDone ? "1" : "0";
        toSave[2] = task.getDescription();

        if (task instanceof Event) {
            toSave[0] = "E";
            toSave[3] = ((Event) task).getScheduledTime();
        } else if (task instanceof Deadline) {
            toSave[0] = "D";
            toSave[3] = ((Deadline) task).getDueDate();
        } else {
            toSave[0] = "T";
        }

        if (isAppendMode) {
            bw.newLine();
        }
        bw.write(String.join(" | ", toSave));
        bw.close();
    }

    private static void loadTasks() throws IOException {
        FileInputStream ips = new FileInputStream(new File("../data/duke.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(ips));
        String line;
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(" \\| ");
            String details = arr[2];
            Task getFromDisk;

            switch(arr[0]) {
            case "T":
                getFromDisk = new Todo(details);
                break;
            case "D":
                getFromDisk = new Deadline(details, arr[3]);
                break;
            default:
                getFromDisk = new Event(details, arr[3]);
                break;
            }

            if (arr[1].equals("1")) {
                getFromDisk.markAsDone();
            }
            tasks.add(getFromDisk);
        }
        br.close();
    }

    private static void clearAllData() throws IOException {
        FileOutputStream ops = new FileOutputStream(new File("../data/duke.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ops));
        bw.close();
    }

    private static void updateData() throws IOException {
        for (int i = 0; i < tasks.size(); i++) {
            if (i != 0) {
                saveTask(tasks.get(i), true);
            } else {
                saveTask(tasks.get(i), false);
            }
        }
    }

    private static void listTasks(StringBuilder sb) {
        sb.append("Below is your task list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\t");
            sb.append(i + 1);
            sb.append("." + tasks.get(i));
        }
    }

    private static void deleteTask(StringBuilder sb, String grammar, String[] splitInput)
            throws NoTaskNumberException, InvalidIndexException, IOException {
        int size = tasks.size();
        if (splitInput.length > 1) {
            int n = Integer.parseInt(splitInput[1]);
            if (n < 1 || n > size) {
                throw new InvalidIndexException(n, size);
            } else {
                sb.append("Noted. I've removed this task:\n\t" + tasks.get(n - 1));
                tasks.remove(n - 1);
                sb.append("You now have " + tasks.size() + grammar + " in the list.\n");
            }
        } else {
            throw new NoTaskNumberException();
        }
        clearAllData();
        updateData();
    }

    private static void markTaskAsDone(StringBuilder sb, String[] splitInput)
            throws NoTaskNumberException, InvalidIndexException, IOException {
        int size = tasks.size();
        if (splitInput.length > 1) {
            int taskNum = Integer.parseInt(splitInput[1]);
            if (taskNum < 1 || taskNum > size) {
                throw new InvalidIndexException(taskNum, size);
            }
            sb.append("Nice! I've marked this task as done:\n\t");
            Task t = tasks.get(taskNum - 1);
            t.markAsDone();
            sb.append(t);
        } else {
            throw new NoTaskNumberException();
        }
        clearAllData();
        updateData();
    }

    private static void addDeadlineOrEvt(StringBuilder sb, String[] splitInput, String id)
            throws NoDescriptionException, NoDateProvidedException, IOException {
        if (splitInput.length < 2) {
            throw new NoDescriptionException();
        } else {
            if (splitInput[1].contains(id)) {
                String[] temp = splitInput[1].split(id);
                if (temp.length < 2) {
                    throw new NoDateProvidedException(id.trim().replace("/", ""));
                }
                if (id.equals(" /by ")) {
                    Task toSave = new Deadline(temp[0].trim().toString(), temp[1]);
                    saveTask(toSave, true);
                    tasks.add(toSave);
                } else {
                    Task toSave = new Event(temp[0].trim().toString(), temp[1]);
                    saveTask(toSave, true);
                    tasks.add(toSave);
                }
                actionConfirmation(sb, tasks.size() > 1 ? " tasks" : " task");
            } else {
                throw new NoDateProvidedException(id.trim().replace("/", ""));
            }
        }
    }

    private static void actionConfirmation(StringBuilder sb, String grammar) {
        int size = tasks.size();
        sb.append("Got it. I've added this task:\n\t" + tasks.get(size - 1));
        sb.append("You now have " + size + grammar + " in the list.\n");
    }

    private static void bye() {
        boolean flag = true;
        for (Task t : tasks) {
            if (t.getStatusIcon().equals("\u2718")) {
                System.out.println("Come back! You still have unfinished tasks to complete!");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("Bye! Come back again if you have more tasks to complete!");
        }
    }
}
