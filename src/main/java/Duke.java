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
                    //[num_tasks++] = new ToDo(newTask);
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
                    //task_list[num_tasks++] = new Deadline(task_name, deadline);
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
                    //task_list[num_tasks++] = new Event(task_name, eventTime);
                    break;
                default:
                    break;
            }
            PrintWithIndent(hori_line);
            PrintWithIndent("Got it. I've added this task:");
            PrintWithIndent(task_list.get(task_list.size() - 1).toString());
            //PrintWithIndent(task_list[num_tasks - 1].toString());
            PrintWithIndent("Now you have " + task_list.size() + " tasks in the list.");
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
                //PrintWithIndent(i + "." + task_list[i - 1].toString());
            }
        } else {
            PrintWithIndent("Empty List. You are currently free! Upz lah!");
        }
        PrintWithIndent(hori_line);
    }

    private void DoneATask(int task_index) {
        if (task_index < task_list.size() && task_index >= 0) {
            task_list.get(task_index).MarkAsDone();
            //task_list[task_index].MarkAsDone();
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
            PrintWithIndent(hori_line);
            PrintWithIndent("Noted! I've removed this task:");
            PrintWithIndent(TaskToRemove);
            PrintWithIndent("Now you have " + task_list.size() + " tasks in the list.");
            PrintWithIndent(hori_line);

        } else {
            // Task does not exist
            PrintWithIndent(hori_line);
            PrintWithIndent("Sorry, mate! No such task.");
            PrintWithIndent(hori_line);
        }
    }
}
