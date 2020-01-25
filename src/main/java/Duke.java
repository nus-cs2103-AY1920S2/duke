import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Start the duke program
        System.out.println("Hello from\n" + logo);
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hey friend! I'm Duke V2.0.0 at your service\n" +
                "     What can I do for you today?\n" +
                "    ____________________________________________________________\n"
        );

        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));
        String input;
        List<Task> tasks = getListFromFile();

        // Parse each command by user
        while (true) {

            // Get command entered by user
            try {
                input = bufferedReader.readLine();
            } catch (IOException e) {
                System.out.println("Error in reading input");
                break;
            }

            String typeOfCommand = input.split(" ")[0];
            // If user enters "bye"
            if (typeOfCommand.equals("bye")) {
                // Exit program
                prettyPrint("Sorry to see you go. Hope to see you again soon! (＾▽＾)／");
                return;
            }

            // Execute command entered by user
            try {
                tasks = executeCommand(input, tasks);
            } catch (DukeException e) {
                prettyPrint(e.getMessage());
            }
        }
    }

    /**
     * Run the duke program, parse each command entered by user
     * and execute the command
     * @param  input   the input by the user
     * @param  tasks   the list of all tasks
     * @return a list of updated tasks
     */
    public static List<Task> executeCommand(String input, List<Task> tasks) throws DukeException {
        String[] inputTokens = input.split(" ");
        switch (inputTokens[0]) {
            case "list":
                String dateString = (inputTokens.length > 1) ? inputTokens[1] : "";
                List<Task> filteredTasks = filterTasksByDate(tasks, dateString);
                prettyPrintList(filteredTasks);
                break;
            case "done":
                // Mark the task with given index as done
                int doneIndex;
                try {
                    doneIndex = Integer.parseInt(inputTokens[1]) - 1;
                } catch (Exception e) {
                    throw new DukeException("☹ OOPS!!! No such task index!");
                }
                if (doneIndex < tasks.size()) {
                    Task task = tasks.get(doneIndex);
                    task.markAsDone(true);
                    if (!writeListToFile(tasks)) {
                        throw new DukeException("☹ OOPS!!! Failed to save list!");
                    }
                    prettyPrint("Nice! I've marked this task as done: \n" +
                            "       " + task);
                } else {
                    throw new DukeException("☹ OOPS!!! No such task index!");
                }
                break;
            case "todo":
                // Add a To-do task
                Task newTodoTask = makeNewTodoTask(input);
                tasks.add(newTodoTask);
                if (!writeListToFile(tasks)) {
                    throw new DukeException("☹ OOPS!!! Failed to save list!");
                }
                printAddTask(newTodoTask, tasks.size());
                break;
            case "deadline":
                // Add a Deadline task
                Task newDeadlineTask = makeNewDeadlineTask(input);
                tasks.add(newDeadlineTask);
                if (!writeListToFile(tasks)) {
                    throw new DukeException("☹ OOPS!!! Failed to save list!");
                }
                printAddTask(newDeadlineTask, tasks.size());
                break;
            case "event":
                // Add an Event task
                Task newEventTask = makeNewEventTask(input);
                tasks.add(newEventTask);
                if (!writeListToFile(tasks)) {
                    throw new DukeException("☹ OOPS!!! Failed to save list!");
                }
                printAddTask(newEventTask, tasks.size());
                break;
            case "delete":
                int deleteIndex;
                try {
                    deleteIndex = Integer.parseInt(inputTokens[1]) - 1;
                } catch (Exception e) {
                    throw new DukeException("☹ OOPS!!! No such task index!");
                }
                if (deleteIndex < tasks.size()) {
                    Task deleteTask = tasks.get(deleteIndex);
                    tasks.remove(deleteIndex);
                    printDeleteTask(deleteTask, tasks.size());
                    if (!writeListToFile(tasks)) {
                        throw new DukeException("☹ OOPS!!! Failed to save list!");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! No such task index!");
                }
                break;
            default:
                // Cannot parse command, throw exception
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return tasks;
    }

    /**
     * Get a list of tasks that occurs at/ needs to be fulfilled by a certain date
     *
     * @param  tasks   the list of tasks to be filtered
     * @param  dateString    the date criteria in string format
     */
    public static List<Task> filterTasksByDate(List<Task> tasks, String dateString) {
        List<Task> filteredTasks;

        // If there is a filter
        if (dateString != "") {
            LocalDate date = LocalDate.parse(dateString);
            filteredTasks = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task instanceof Event && ((Event) task).date.equals(date)) {
                    filteredTasks.add(task);
                } else if (task instanceof Deadline && ((Deadline) task).date.equals(date)) {
                    filteredTasks.add(task);
                }
            }
        } else {
            filteredTasks = tasks;
        }

        return filteredTasks;
    }

    /**
     * Try to make a new to-do task from the input
     *
     * @param  input   the input which contains the to-do task information
     * @return  task    the new to-do task
     */
    public static Todo makeNewTodoTask(String input) throws DukeException {
        String[] todoTokens = input.split(" ");

        // If there is a description
        if (todoTokens.length > 1) {
            Todo newTodoTask = new Todo(input.replaceFirst("^todo ", ""));
            return newTodoTask;
        }

        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Try to make a new event task from the input
     *
     * @param  input   the input which contains the event task information
     * @return  task    the new event task
     */
    public static Event makeNewEventTask(String input) throws DukeException {
        String[] eventTokens = input.split(" /at ");

        // If there is a description and a time/date
        if (eventTokens.length > 1) {
            String dateOrTime = eventTokens[1];
            String[] dateOrTimeTokens = dateOrTime.split(" ");

            // If there is a date and time
            if (dateOrTimeTokens.length <= 1) {
                throw new DukeException("☹ OOPS!!! Event tasks require a specific time and date.");
            }

            try {
                LocalDate date = LocalDate.parse(dateOrTimeTokens[0]);
                String description = eventTokens[0].replaceFirst("^event ", "");
                Event newEventTask = new Event(description, date, dateOrTimeTokens[1]);
                return newEventTask;
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Cannot parse date or time.");
            }
        }

        throw new DukeException("☹ OOPS!!! Event tasks require a description, and a specific time and date (e.g. 2019-12-12 2-4pm).");
    }

    /**
     * Try to make a new deadline task from the input
     *
     * @param  input   the input which contains the deadline task information
     * @return  task    the new deadline task
     */
    public static Deadline makeNewDeadlineTask(String input) throws DukeException {
        String[] deadlineTokens = input.split(" /by ");

        // If there is a description and a deadline
        if (deadlineTokens.length > 1) {
            String dateOrTime = deadlineTokens[1];
            String[] dateOrTimeTokens = dateOrTime.split(" ");

            // If there is a date and time
            if (dateOrTimeTokens.length <= 1) {
                throw new DukeException("☹ OOPS!!! Deadline tasks require a specific time and date.");
            }

            try {
                LocalDate date = LocalDate.parse(dateOrTimeTokens[0]);
                LocalTime time = LocalTime.parse(dateOrTimeTokens[1], DateTimeFormatter.ofPattern("HHmm"));
                String description = deadlineTokens[0].replaceFirst("^deadline ", "");
                Deadline newDeadlineTask = new Deadline(description, date, time);
                return newDeadlineTask;
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Cannot parse date or time.");
            }
        }

        throw new DukeException("☹ OOPS!!! Deadline tasks require a description, and a specific time and date (e.g. 2019-12-12 1800).");
    }

    /**
     * Get the string format of a list of task objects
     *
     * @param  list   the list of task objects
     * @return  listString    the list of tasks in string format
     */
    public static String convertListToString(List<Task> list) {
        String listString = "";
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            int isDone = (task.isDone) ? 1 : 0;
            if (task instanceof Deadline) {
                listString += "D | " + isDone + " | " + task.description + " | " +
                        ((Deadline) task).date + " " + ((Deadline) task).time;
            } else if (task instanceof Event) {
                listString += "E | " + isDone + " | " + task.description + " | " +
                        ((Event) task).date + " " + ((Event) task).fromTimeToTime;
            } else if (task instanceof Todo) {
                listString += "T | " + isDone + " | " + task.description;
            }
            if (i != list.size() - 1) {
                listString += "\n";
            }
        }
        return listString;
    }

    /**
     * Get a list of tasks from the string format of list
     *
     * @param  listString   the list of tasks in string format
     * @return  list    the list of task objects
     */
    public static List<Task> convertStringToList(String listString) throws DukeException {
        List<Task> list = new ArrayList<>();

        try {
            listString.lines().forEach((line) -> {
                String listItem = line;
                String[] taskItems = listItem.split(" \\| ");
                Task task = null;
                boolean isDone = (Integer.parseInt(taskItems[1]) == 0) ? false : true;
                String[] dateOrTimeTokens;

                switch (taskItems[0]) {
                    case "T":
                        task = new Todo(taskItems[2]);
                        break;
                    case "D":
                        dateOrTimeTokens = taskItems[3].split(" ");
                        try {
                            LocalDate deadlineDate = LocalDate.parse(dateOrTimeTokens[0]);
                            LocalTime time = LocalTime.parse(dateOrTimeTokens[1]);
                            task = new Deadline(taskItems[2], deadlineDate, time);
                        } catch (Exception e) {
                            break;
                        }
                        break;
                    case "E":
                        dateOrTimeTokens = taskItems[3].split(" ");
                        try {
                            LocalDate eventDate = LocalDate.parse(dateOrTimeTokens[0]);
                            task = new Event(taskItems[2], eventDate, dateOrTimeTokens[1]);
                        } catch (Exception e) {
                            break;
                        }
                        break;
                }
                if (task != null) {
                    task.isDone = isDone;
                    list.add(task);
                }
            });
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Data is corrupted.");
        }

        return list;
    }

    /**
     * Save the list of tasks to file on the disk
     *
     * @param   list    the list of tasks to be saved
     * @return  isSuccessful   whether save to file is successful
     */
    public static boolean writeListToFile(List<Task> list) {
        String listString = convertListToString(list);

        File dataPath = new File(System.getProperty("user.dir"));
        File newFile = new File(dataPath.getAbsolutePath() + File.separator + "duke.txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            writer.write(listString);

            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the list of tasks from file saved on disk
     *
     * @return  list   the list of tasks from file
     */
    public static List<Task> getListFromFile() {
        File dataPath = new File(System.getProperty("user.dir"));
        File newFile = new File(dataPath.getAbsolutePath() + File.separator + "duke.txt");
        List<Task> list = new ArrayList<>();
        String listString = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(newFile));
            String line;
            while ((line = reader.readLine()) != null) {
                listString += "\n" + line;
            }
            list = convertStringToList(listString.substring(1));
        } catch (Exception e) {
            return list;
        }
        return list;
    }

    /**
     * Format the given line into a pretty format and print it
     *
     * @param  line   the line to be formatted
     */
    public static void prettyPrint(String line) {
        System.out.println(
                "    _____________________________DUKE___________________________\n" +
                "     " + line + "\n" +
                "    _________★゜・。。・゜゜・。。・゜☆゜・。。・゜゜・。。・゜★_______\n"
        );
    }

    /**
     * Format the given list into a pretty format and print it
     *
     * @param  tasks   the list of tasks to be formatted
     */
    public static void prettyPrintList(List<Task> tasks) {
        String tasksString = "";
        int size = tasks.size();

        // Print out all items in list
        if (size > 0) {
            tasksString += "Here are the tasks in your list:\n     ";
        }
        for (int i = 0; i < size; i++) {
            tasksString += (i + 1) + "." + tasks.get(i);
            if (i != size - 1) {
                tasksString += "\n     ";
            }
        }
        tasksString = tasksString.equals("") ? "There is nothing on your list." : tasksString;
        prettyPrint(tasksString);
    }

    /**
     * Format the task added into a pretty format and print it
     *
     * @param  task   the task added
     * @param  size   the total number of tasks
     */
    public static void printAddTask(Task task, Integer size) {
        String taskWord = (size > 1) ? "tasks" : "task";
        prettyPrint("Got it. I've added this task: \n" +
                "       " + task + "\n"+
                "     Now you have " + size + " " + taskWord + " in the list.");
    }

    /**
     * Format the task deleted into a pretty format and print it
     *
     * @param  task   the task deleted
     * @param  size   the total number of tasks
     */
    public static void printDeleteTask(Task task, Integer size) {
        String taskWord = (size > 1) ? "tasks" : "task";
        prettyPrint("Noted. I've removed this task: \n" +
                "       " + task + "\n" +
                "     Now you have " + size + " " + taskWord + " in the list.");
    }
}
