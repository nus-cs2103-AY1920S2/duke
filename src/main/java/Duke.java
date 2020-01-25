import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String indentation = "    ";
    private static String hori_line = "______________________________________";
    private ArrayList<Task> task_list;
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    private File saveFile;

    public static void main(String[] args) {

        // Init Duke
        Duke d = new Duke();
        // Greet user on start
        d.Greet();
        // Duke's behaviour loop
        d.Loop();
    }

    private static void PrintWithIndent(String toPrint) {
        System.out.println(indentation + toPrint);
    }

    Duke() {
        // Assume there will be <= 100 tasks at any given time
        task_list = new ArrayList<Task>(100);
        CheckLocalData();
        GenerateTasksFromFile();
    }

    private void CheckLocalData() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        saveFile = new File(dir, "userdata.txt");
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    private void SaveTaskToFile(Task task) {
        FileWriter writer;
        try {
            writer = new FileWriter(saveFile, true);
            PrintWriter pw = new PrintWriter(writer);
            pw.println(task.toSaveString());
            pw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void SaveTaskListToFile() {
        FileWriter writer;
        PrintWriter pw;
        try {
            // Clear save
            writer = new FileWriter(saveFile);
            pw = new PrintWriter(writer);
            pw.print("");
            // Save from scratch
            writer = new FileWriter(saveFile, true);
            pw = new PrintWriter(writer);
            pw.print("");
            for (Task t: task_list) {
                pw.println(t.toSaveString());
            }
            pw.close();
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    private void AddStringAsTask(String taskString) {
        // Takes in a string representation of a task and adds to list
        String task_info;
        String[] sep;
        switch (taskString.charAt(0)) {
            case 'T':
                task_list.add(new ToDo(taskString.substring(2, taskString.length())));
                break;
            case 'D':
                task_info = taskString.substring(2, taskString.length());
                sep = task_info.split("@");
                task_list.add(new Deadline(sep[0], LocalDateTime.parse(sep[1])));
                break;
            case 'E':
                task_info = taskString.substring(2, taskString.length());
                sep = task_info.split("@");
                task_list.add(new Event(sep[0], LocalDateTime.parse(sep[1])));
                break;
            default:
                break;
        }
        if (taskString.charAt(1) == '1')
            task_list.get(task_list.size() - 1).MarkAsDone();
    }

    private void GenerateTasksFromFile() {
        try {
            Scanner reader = new Scanner(saveFile);
            String data;
            while (reader.hasNextLine()) {
                data = reader.nextLine();
                AddStringAsTask(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void Greet() {
        PrintWithIndent(hori_line);
        PrintWithIndent("Hello! I'm Duke");
        PrintWithIndent("What can I do for you?");
        PrintWithIndent(hori_line);
    }

    private void Exit() {
        PrintWithIndent(hori_line);
        PrintWithIndent("Bye. Hope to see you again soon!");
        PrintWithIndent(hori_line);
        SaveTaskListToFile();
    }

    private void Loop() {
        Scanner in = new Scanner(System.in);
        String input = "";
        boolean is_exiting = false;

        do {
            input = in.next();

            try {
                switch (input.toLowerCase()) {
                    case "list":
                        ShowList();
                        break;
                    case "bye":
                        is_exiting = true;
                        break;
                    case "done":
                        int doneTaskNum = in.nextInt();
                        DoneATask(doneTaskNum - 1);
                        break;
                    case "todo":
                        AddToList(in.nextLine().trim(), TaskType.TODO);
                        break;
                    case "deadline":
                        AddToList(in.nextLine(), TaskType.DEADLINE);
                        break;
                    case "event":
                        AddToList(in.nextLine(), TaskType.EVENT);
                        break;
                    case "delete":
                        int deleteTaskNum = in.nextInt();
                        DeleteTask(deleteTaskNum - 1);
                        break;
                    case "date":
                        PrintAllOnDate(in.nextLine().trim());
                        break;
                    default:
                        in.nextLine();
                        throw new DukeException.InvalidCommand();
                }
            } catch (DukeException.InvalidCommand e) {
                PrintWithIndent(hori_line);
                PrintWithIndent(e.getMessage());
                PrintWithIndent(hori_line);
            }
        } while (!is_exiting);

        Exit();
    }

    private void AddToList(String newTask, TaskType taskType) {
        String[] str_arr;
        String task_name;
        try {
            switch (taskType) {
                case TODO:
                    if (newTask.isBlank())
                        throw new DukeException.EmptyToDo();
                    task_list.add(new ToDo(newTask));
                    break;
                case DEADLINE:
                    // newTask string consists of "<actual task name> /by <deadline>"
                    str_arr = newTask.split("/by");
                    task_name = str_arr[0].trim();
                    if (task_name.isBlank())
                        throw new DukeException.EmptyDeadlineName();
                    String deadline;
                    try {
                        deadline = str_arr[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // This will occur when user did not use a /by command
                        throw new DukeException.NoDeadlineTime();
                    }
                    // /by was used but is followed by blank
                    if (deadline.isBlank())
                        throw new DukeException.NoDeadlineTime();
                    task_list.add(new Deadline(task_name, deadline));
                    break;
                case EVENT:
                    // newTask string consists of "<actual task name> /at <datetime>"
                    str_arr = newTask.split("/at");
                    task_name = str_arr[0].trim();
                    if (task_name.isBlank())
                        throw new DukeException.EmptyEvent();
                    String eventTime;
                    try {
                        eventTime = str_arr[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // This will occur when user did not use a /at command
                        throw new DukeException.NoEventDatetime();
                    }
                    // /at was used but is followed by blank
                    if (eventTime.isBlank())
                        throw new DukeException.NoEventDatetime();
                    task_list.add(new Event(task_name, eventTime));
                    break;
                default:
                    break;
            }
            // Save with every new task added
            SaveTaskToFile(task_list.get(task_list.size() - 1));
            PrintWithIndent(hori_line);
            PrintWithIndent("Got it. I've added this task:");
            PrintWithIndent(task_list.get(task_list.size() - 1).toString());
            PrintWithIndent("Now you have " + task_list.size() + " task" + (task_list.size() != 1 ? "s" : "") + " in the list.");
            PrintWithIndent(hori_line);
        } catch (DukeException.EmptyToDo | DukeException.EmptyDeadlineName | DukeException.NoDeadlineTime | DukeException.EmptyEvent | DukeException.NoEventDatetime e) {
            // currently all exceptions are handled just by relaying a message. Nothing special, yet.
            PrintWithIndent(hori_line);
            PrintWithIndent(e.getMessage());
            PrintWithIndent(hori_line);
        }
    }

    private void ShowList() {
        PrintWithIndent(hori_line);
        if (!task_list.isEmpty()) {
            for (int i = 1; i <= task_list.size(); i++) {
                PrintWithIndent(i + "." + task_list.get(i - 1).toString());
            }
        } else {
            PrintWithIndent("Empty List. You are currently free! Upz lah!");
        }
        PrintWithIndent(hori_line);
    }

    private void DoneATask(int task_index) {
        if (task_index < task_list.size() && task_index >= 0) {
            task_list.get(task_index).MarkAsDone();
            SaveTaskListToFile();
            PrintWithIndent(hori_line);
            PrintWithIndent("Nice! I've marked this task as done:");
            PrintWithIndent(task_list.get(task_index).toString());
            //PrintWithIndent(task_list[task_index].toString());
            PrintWithIndent(hori_line);
        } else {
            // Task does not exist
            PrintWithIndent(hori_line);
            PrintWithIndent("Sorry, mate! No such task.");
            PrintWithIndent(hori_line);
        }
    }

    private void DeleteTask(int task_index) {
        if (task_index < task_list.size() && task_index >= 0) {
            String TaskToRemove = task_list.get(task_index).toString();
            task_list.remove(task_index);
            SaveTaskListToFile();
            PrintWithIndent(hori_line);
            PrintWithIndent("Noted! I've removed this task:");
            PrintWithIndent(TaskToRemove);
            PrintWithIndent("Now you have " + task_list.size() + " task" + (task_list.size() != 1 ? "s" : "") + " in the list.");
            PrintWithIndent(hori_line);

        } else {
            // Task does not exist
            PrintWithIndent(hori_line);
            PrintWithIndent("Sorry, mate! No such task.");
            PrintWithIndent(hori_line);
        }
    }

    private void PrintAllOnDate(String dateStr) {
        try {
            LocalDate date =  LocalDate.parse(dateStr);
            PrintWithIndent(hori_line);
            int count = 0;
            for (Task t: task_list) {
                if (t instanceof Deadline) {
                    Deadline d = (Deadline)t;
                    if (d.getDeadline().toLocalDate().isEqual(date)) {
                        count++;
                        PrintWithIndent(count + "." + d.toString());
                    }
                } else if (t instanceof Event) {
                    Event e = (Event)t;
                    if (e.getDatetime().toLocalDate().isEqual(date)) {
                        count++;
                        PrintWithIndent(count + "." + e.toString());
                    }
                }
            }
            PrintWithIndent("You have " + count + " thing" + (count != 1 ? "s" : "")
                    + " happening on: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            PrintWithIndent(hori_line);
        } catch (DateTimeParseException e) {
            PrintWithIndent(hori_line);
            PrintWithIndent("Please in put a valid date. E.g. 2020-12-26");
            PrintWithIndent(hori_line);
        }
    }
}
