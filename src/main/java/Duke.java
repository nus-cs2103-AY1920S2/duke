import java.io.*;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Duke {

    public static int counter = 0; // this has to be a global variable
    public static Task[] tasks = new Task[100]; // another global variable

    public static void main(String[] args) {

        try {
            loadFile(); // load the file first
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found!");
        }
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        processRequests(); // process all user requests

        updateFile(); //then need to write back the new list to the file

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void processRequests() {
        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine(); // read in the user input
        while (!user_input.toLowerCase().equals("bye")) {
            String[] splitstr = user_input.split(" "); // split the string first

            if (user_input.toLowerCase().equals("list")) { // list
                list(counter);
            }
            else if (splitstr[0].toLowerCase().equals("done")) { // done
                markCompleted(Integer.parseInt(splitstr[1])-1); // whichever task is marked completed
            }
            else if (splitstr[0].toLowerCase().equals("delete")) { // delete
                deleteTask(Integer.parseInt(splitstr[1])-1); // whichever task you want to delete
            }
            else {
                String[] time_split = user_input.split("/"); // the 2nd half contains the deadline
                if (splitstr[0].toLowerCase().equals("todo")) { // TODO =================================
                    try {
                        if (splitstr.length == 1) { // let me catch the exception in addTodo()
                            addTodo("");
                        }
                        else {
                            String description = user_input.substring(5); // something something
                            addTodo(description);
                        }
                    }
                    catch (DukeException exception) {
                        System.out.println(exception);
                    }
                } // END OF TODO ========================================================================
                else if (splitstr[0].toLowerCase().equals("deadline")) { // DEADLINE ====================
                    String description = user_input.substring(9, user_input.indexOf("/"));
                    String deadline = user_input.split("by ")[1];
                    addDeadline(description, deadline);
                } // END OF DEADLINE ====================================================================
                else if (splitstr[0].toLowerCase().equals("event")) { // EVENT ==========================
                    String description = user_input.substring(6, user_input.indexOf("/"));
                    String deadline = user_input.split("at ")[1];
                    addEvent(description, deadline);
                } // END OF EVENT =======================================================================
                else {
                    System.out.println("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                //System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
            }
            user_input = sc.nextLine();
        }
    }

    // takes in a Task[] array and loads the array with stuff
    // cleaned up the loadfile method
    public static void loadFile() throws FileNotFoundException {
        File file = new File("./src/main/java/duke.txt"); // not sure if the pathname is correct
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String task = sc.nextLine();

            // we extract out the class type, completion status, task description and deadline
            String task_type = task.substring(0, 3); // this prints out [D], [T], or [E]
            String completion_status = task.substring(3, 6); // this prints out [✓] or [✘]

            if (task_type.equals("[T]")) { // it's a todo
                String description = task.substring(8, task.length());
                tasks[counter] = new Todo(description, "[T]");
                if (completion_status.equals("[\u2713]")) {
                    tasks[counter].updateisDone(true);
                }
                counter++;
            }
            else if (task_type.equals("[D]")) { // it's a deadline
                String task_description = task.substring(8, task.indexOf("("));

                String[] split_str = task.split("by:");
                String deadline = split_str[1].replace(")", ""); // this is the deadline which you should be doing date recognition

                tasks[counter] = new Deadline(task_description, "[D]", deadline);
                if (completion_status.equals("[\u2713]")) {
                    tasks[counter].updateisDone(true);
                }
                counter++;
            }
            else { // it's an event already
                String task_description = task.substring(8, task.indexOf("("));

                String[] split_str = task.split("at:");
                String deadline = split_str[1].replace(")", ""); // this is the deadline which you should be doing date recognition

                tasks[counter] = new Event(task_description, "[E]", deadline);
                if (completion_status.equals("[\u2713]")) {
                    tasks[counter].updateisDone(true);
                }
                counter++;
            }
        }
    }

    public static void addTodo(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        tasks[counter] = new Todo(description, "[T]");
        Task current_task = tasks[counter];
        counter++;

        System.out.println("Got it. I've added this task:");
        System.out.println(current_task.getTaskType() + current_task.getCompletionStatus()
                + current_task.getDescription());
        System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
    }

    public static void addDeadline(String description, String deadline) {

        tasks[counter] = new Deadline(description, "[D]", deadline);
        Task current_task = tasks[counter];
        counter++;
        System.out.println("Got it. I've added this task:");

        String[] deadline_split = deadline.split("-");
        if (deadline_split.length == 3) {
            //LocalDate date = resolveDeadline(deadline);
            System.out.print(current_task.getTaskType() + current_task.getCompletionStatus()
                    + " " + current_task.getDescription() + " (by: ");
            printDeadline(deadline);
        }
        else {
            System.out.println(current_task.getTaskType() + current_task.getCompletionStatus()
                    + current_task.getDescription() + " (by: " + deadline + ")");
        }

        System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
    }

    public static void addEvent(String description, String deadline) {

        tasks[counter] = new Event(description, "[E]", deadline);
        Task current_task = tasks[counter];
        counter++;
        System.out.println("Got it. I've added this task:");

        String[] deadline_split = deadline.split("-");
        if (deadline_split.length == 3) {
            //LocalDate date = resolveDeadline(deadline);
            System.out.print(current_task.getTaskType() + current_task.getCompletionStatus()
                    + " " + current_task.getDescription() + " (at: ");
            printDeadline(deadline);
        }
        else {
            System.out.println(current_task.getTaskType() + current_task.getCompletionStatus()
                    + current_task.getDescription() + " (at: " + deadline + ")");
        }
        // print something else here
        System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
    }

    // this method takes in a deadline and resolves it to a LocalDate object
    // After which it converts the local date object back to a date
    public static void printDeadline(String deadline) {
        // we only accept yyyy-mm-dd formats (which is the minimal)
        // other than this, we don't accept already
        LocalDate date = LocalDate.parse(deadline);
        System.out.println(date.format(DateTimeFormatter.ofPattern("MMM d yyyy)")));
    }

    public static void list(int counter) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            String status = tasks[i].checkIfComplete();
            if (tasks[i].getTaskType().equals("[T]")) {
                System.out.println(Integer.toString(i+1) + ". " + tasks[i].getTaskType() + "[" + status +
                        "] " + tasks[i].getDescription() + tasks[i].getDeadline());
            }
            else if (tasks[i].getTaskType().equals("[D]")) {
                String deadline = tasks[i].getDeadline();
                String[] deadline_split = deadline.split("-");
                if (deadline_split.length == 3) {
                    System.out.print(Integer.toString(i+1) + ". " + tasks[i].getTaskType() + "[" + status +
                            "] " + tasks[i].getDescription() + " (by: ");
                    printDeadline(tasks[i].getDeadline());
                }
                else {
                    System.out.println(Integer.toString(i+1) + ". " + tasks[i].getTaskType() + "[" + status +
                            "] " + tasks[i].getDescription() + " (by: " + deadline + ")");
                }

            }
            else {

                String deadline = tasks[i].getDeadline();
                String[] deadline_split = deadline.split("-");
                if (deadline_split.length == 3) {
                    System.out.print(Integer.toString(i+1) + ". " + tasks[i].getTaskType() + "[" + status +
                            "] " + tasks[i].getDescription() + " (at: ");
                    printDeadline(tasks[i].getDeadline());
                }
                else {
                    System.out.println(Integer.toString(i+1) + ". " + tasks[i].getTaskType() + "[" + status +
                            "] " + tasks[i].getDescription() + " (at: " + deadline + ")");
                }
            }
        }
    }

    public static boolean qualifiesForPrint(String deadline) {
        String[] deadline_split = deadline.split("-");
        if (deadline_split.length == 3) {
            return true; //qualifies
        }
        return false;
    }

    public static void markCompleted(int taskNum) {
        System.out.println("Nice! I've marked this task as done:");
        tasks[taskNum].updateisDone(true);
        String deadline = tasks[taskNum].getDeadline();

        if (tasks[taskNum].getTaskType().equals("[T]")) {
            System.out.print("[T][" + tasks[taskNum].checkIfComplete() + "]" + tasks[taskNum].getDescription());
        }
        else if (tasks[taskNum].getTaskType().equals("[D]")) {
            if (qualifiesForPrint(deadline)) {
                System.out.print("[D][" + tasks[taskNum].checkIfComplete() + "]" + tasks[taskNum].getDescription() + " (by: ");
                printDeadline(deadline);
            } else {
                System.out.print("[D][" + tasks[taskNum].checkIfComplete() + "]" + tasks[taskNum].getDescription() + " ( by: " + deadline + ")");
            }
        }
        else {
            if (qualifiesForPrint(deadline)) {
                System.out.print("[E][" + tasks[taskNum].checkIfComplete() + "]" + tasks[taskNum].getDescription() + " (at: ");
                printDeadline(deadline);
            } else {
                System.out.print("[E][" + tasks[taskNum].checkIfComplete() + "]" + tasks[taskNum].getDescription() + " ( by: " + deadline + ")");
            }
        }

    }

    public static void deleteTask(int taskNum) {
        System.out.println("Noted. I've removed this task:");
        if (tasks[taskNum].getTaskType().equals("[T]")) { // if it's a todo
            System.out.println(tasks[taskNum].getTaskType() + tasks[taskNum].getCompletionStatus()
                    + tasks[taskNum].getDescription());
        }
        else if (tasks[taskNum].getTaskType().equals("D")) { // it's a deadline
            System.out.println(tasks[taskNum].getTaskType() + tasks[taskNum].getCompletionStatus()
                    + tasks[taskNum].getDescription() + tasks[taskNum].getDeadline());
        }
        else { // it's an event liao
            System.out.println(tasks[taskNum].getTaskType() + tasks[taskNum].getCompletionStatus()
                    + tasks[taskNum].getDescription() + tasks[taskNum].getDeadline());
        }

        // here, we implement some for loop to shift all the tasks forward
        for (int i = taskNum; i < counter-1; i++) {
            tasks[i] = tasks[i+1];
        }
        counter--;
        System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
    }

    // this one need to change
    public static void updateFile() {

        // delete everything in the file first
        File file = new File("./src/main/java/duke.txt"); //
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.close();
        }
        catch (FileNotFoundException exception) { System.out.println("File not found, cannot be cleared out!"); }

        // now we create a file writer and we write one by one to the file
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0 ; i < counter; i++) {
                // for everything in the counter.
                String line = tasks[i].getTaskType() + tasks[i].getCompletionStatus() + " " + tasks[i].getDescription() + tasks[i].getDeadline();
                writer.write(line + "\n");
                // then now you need to write.
            }
            writer.close();
        }
        catch (IOException exception) { System.out.println("File not found, cannot be written to!"); }
    }

}