
import java.nio.file.Path;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * For level-1 Greet, Echo, Exit.
 */
public class Duke {

    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        ArrayList<Task> mylist = new ArrayList<>();
        String home = System.getProperty("user.home");

        System.out.println("Hello! I'm Duke\n"
                + "     What can I do for you?");

        Path path = Paths.get(home, "Desktop", "NUS Y2S2", "CS2103T", "project", "duke", "data", "duke.txt");
        try {
            readFile(path, mylist);
        } catch (IOException e) {
            System.out.println("File not found");
        }

        while (true) {
            String response = sc.nextLine();
            try {
                String messageType = checkMessageType(response);
                if (messageType.equals("bye")) {
                    System.out.println("     ____________________________________________________________");
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println("     ____________________________________________________________");
                    break;

                } else {
                    printAction(messageType, mylist, response);
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }


        }

        try {
            writeToHardDisk(path, mylist);
        } catch (IOException e) {
            System.out.println("File not found");
        }

    }

    /**
     * Read the text file stored in the filepath, and create a list that is already stored in the hard-drive
     * @param filepath the file path of the txt file
     * @param mylist arraylist to store the Tasks
     * @throws IOException if no file is found
     */
    private static void readFile(Path filepath, ArrayList<Task> mylist) throws IOException {
        File f = filepath.toFile();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String tasks = s.nextLine();
            createStartUpList(tasks, mylist);
        }

    }

    /**
     * Create list from start-up from hard-drive information
     */
    private static void createStartUpList(String tasks, ArrayList<Task> mylist) {

        String[] tasksArr = tasks.split("\\|");
        String taskType = tasksArr[0].trim();
        String taskCompleted = tasksArr[1].trim();
        String taskAction = tasksArr[2].trim() + " ";

        if (taskType.equals("T")) {

            Task newTask = new Todo(taskAction);
            if (taskCompleted.equals("1")) {
                newTask.completedTask();
            }
            mylist.add(newTask);

        } else if (taskType.equals("D")) {

            String deadline = tasksArr[3];
            String date = deadline.split("  ")[0].trim();
            String time = deadline.split("  ")[1].trim();
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM d yyyy");
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm a");
            LocalDate standardDateFormat = LocalDate.parse(date, formatterDate);
            LocalTime standardTimeFormat = LocalTime.parse(time, formatterTime);
            String dateAndTime = standardDateFormat + " " + standardTimeFormat;

            Task newTask = new Deadline(taskAction, "by " + dateAndTime.replace(":", ""));


            if (taskCompleted.equals("1")) {
                newTask.completedTask();
            }

            mylist.add(newTask);

        } else if (taskType.equals("E")) {

            String eventTiming = tasksArr[3];
            Task newTask = new Event(taskAction, "at"+ eventTiming);

            if (taskCompleted.equals("1")) {
                newTask.completedTask();
            }

            mylist.add(newTask);


        }


    }

    /**
     * Store the current items that is in the list back into the hard disk
     * @param filepath filepath of the txt file
     * @param mylist task arraylist that stores all the tasks
     * @throws IOException
     */
    private static void writeToHardDisk (Path filepath, ArrayList<Task> mylist) throws IOException {
        File f = filepath.toFile();
        FileWriter writer = new FileWriter(f);

        for (Task tasks : mylist) {
            writer.write(tasks.saveToHardDiskFormat() + System.lineSeparator());
        }
        writer.close();


    }


    private static String checkMessageType(String response) throws DukeException {
        if (response.contains("bye")) {
            return "bye";
        }
        if (response.contains("list")) {
            return "list";
        }

        if (response.contains("done")) {
            return "done";
        }

        if (response.contains("todo")) {
            return "todo";
        }

        if (response.contains("deadline")) {
            return "deadline";
        }

        if (response.contains("event")) {
            return "event";
        }

        if (response.contains("delete")) {
            return "delete";
        }

        throw new DukeException("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________\n");
    }


    private static void printAction(String messageType, ArrayList<Task> mylist, String response) throws DukeException {
        if (messageType.equals("list")) {

            printFormatting();
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < mylist.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + mylist.get(i));
            }
            printFormatting();

        } else if (messageType.equals("done")) {

            int taskNumberDone = Integer.parseInt(response.split(" ")[1]);
            Task doneTask = mylist.get(taskNumberDone - 1);
            doneTask.completedTask();
            printFormatting();
            System.out.println("      Nice! I've marked this task as done: ");
            System.out.println("        " + doneTask);
            printFormatting();

        } else if (messageType.equals("todo")) {

            createTodo(response, mylist);

        } else if (messageType.equals("deadline")) {

            createDeadline(response, mylist);

        } else if (messageType.equals("event")) {

            createEvent(response, mylist);

        } else if (messageType.equals("delete")) {

            delete(response, mylist);

        }

    }

    private static void printFormatting() {
        System.out.println("     ____________________________________________________________");
    }

    private static void createTodo(String response, ArrayList<Task> mylist) throws DukeException {

        String description = response.replace("todo", "").trim();

        if (!description.equals("")) {
            Task newTask = new Todo(description);
            mylist.add(newTask);
            printFormatting();
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.printf("     Now you have %d tasks in the list.\n", mylist.size());
            printFormatting();
        } else {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");
        }
    }

    private static void createDeadline(String response, ArrayList<Task> mylist) throws DukeException {

        try {

            String deadline = response.split("/")[1];
            String description = response.split("/")[0].replace("deadline ", "");
            Task newTask = new Deadline(description, deadline);
            mylist.add(newTask);
            printFormatting();
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.printf("     Now you have %d tasks in the list.\n", mylist.size());
            printFormatting();

        } catch (DateTimeException ex) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! Please format your date and time correctly.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");

        } catch (Exception ex) {

            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description or deadline of a deadline cannot be empty.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");

        }

    }

    private static void createEvent(String response, ArrayList<Task> mylist) throws DukeException {

        try {

            String eventTiming = response.split("/")[1];
            String description = response.split("/")[0].replace("event ", "");
            Task newTask = new Event(description, eventTiming);
            mylist.add(newTask);
            printFormatting();
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.printf("      Now you have %d tasks in the list.\n", mylist.size());
            printFormatting();

        } catch (Exception ex) {

            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description or event timing of a event cannot be empty.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");

        }

    }

    private static void delete(String response, ArrayList<Task> mylist) throws DukeException {

        int numberToDelete = Integer.parseInt(response.replace("delete" , "").trim());

        if (numberToDelete <= 0) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! Please key in value bigger than 0.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");
        }

        if (mylist.size() < numberToDelete) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! number does not exist in the list.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");
        }


        Task removed = mylist.remove(numberToDelete - 1 );

        printFormatting();
        System.out.println("      Noted. I've removed this task: ");
        System.out.println("       " + removed);
        System.out.printf("      Now you have %d tasks in the list.\n", mylist.size());
        printFormatting();


    }


}
