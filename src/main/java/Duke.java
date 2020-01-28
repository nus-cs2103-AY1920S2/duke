//import java.io.FileNotFoundException;
import java.time.*;
import java.io.*;
import java.util.Scanner;

public class Duke {

    public static int counter = 0; // this has to be a global variable
    public static Task[] tasks = new Task[100]; // another global variable

    public static void main(String[] args) {

        try {
            loadFile(); // populate with existing tasks first
            // the storing convention is: there will be a 2nd slash followed immediately by a letter
            // eg. deadline borrow book /by sunday/y
            // the letter indicates whether or not the task is done!
            //n stands for not done! y stands for yes, done
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found!");
        }
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        processRequests();
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
                if (splitstr[0].toLowerCase().equals("todo")) {
                    try {
                        addTodo(user_input, time_split, splitstr);
                    }
                    catch (DukeException exception) {
                        System.out.println(exception);
                    }
                }
                else if (splitstr[0].toLowerCase().equals("deadline")) {
                    try {
                        addDeadline(user_input, time_split, splitstr);
                    }
                    catch (DukeException exception) {
                        System.out.println(exception);
                    }
                }
                else if (splitstr[0].toLowerCase().equals("event")) {
                    try {
                        addEvent(user_input, time_split, splitstr);
                    }
                    catch (DukeException exception) {
                        System.out.println(exception);
                    }
                }
                else {
                    System.out.println("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                //System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
            }
            user_input = sc.nextLine();
        }
    }

    // takes in a Task[] array and loads the array with stuff
    // in this method, remember to update the counter
    public static void loadFile() throws FileNotFoundException {
        File file = new File("./src/main/java/duke.txt"); // not sure if the pathname is correct
        Scanner sc = new Scanner(file);

        int task_number = 0; // this will be the counter
        while (sc.hasNextLine()) {
            String task = sc.nextLine();

            String task_type = task.split(" ")[0]; // todo, deadline, or event
            //String deadline = task.split("/")[1]; // the 2nd half contains the deadline
            if (task_type.toLowerCase().equals("todo")) {

                //addTodo(task, task.split("/"), task.split(" "));
                String task_description = task.replace(task_type, "");
                String raw_task_input = task.replace("/" + task.split("/")[1], "");

                tasks[counter] = new Todo(task_description.split("/")[0], "[T]", raw_task_input); // no deadline for todo
                if (task.split("/")[1].equals("y")) {
                    tasks[counter].updateisDone(true);
                }
                counter++;
            }
            else if (task_type.toLowerCase().equals("deadline")) {
                String[] time_split = task.split("/"); // the 2nd half contains the deadline

                String task_description = time_split[0].replace(task_type, "");
                String deadline = time_split[1].replace("by ","");
                String raw_task_input = task.replace("/" + task.split("/")[2], "");

                tasks[counter] = new Deadline(task_description, "[D]", deadline, raw_task_input);
                if (task.split("/")[2].equals("y")) {
                    tasks[counter].updateisDone(true);
                }
                counter++;
            }
            else {
                String[] time_split = task.split("/"); // the 2nd half contains the deadline

                String task_description = time_split[0].replace(task_type, "");
                String deadline = time_split[1].replace("at ","");
                String raw_task_input = task.replace("/" + task.split("/")[2], "");

                tasks[counter] = new Event(task_description, "[E]", deadline, raw_task_input);
                if (task.split("/")[2].equals("y")) {
                    tasks[counter].updateisDone(true);
                }
                counter++;
            }
        }
    }

    public static void addTodo(String user_input, String[] time_split, String[] splitstr) throws DukeException {
        if (splitstr.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        String task_description = user_input.replace(splitstr[0], "");

        tasks[counter] = new Todo(task_description, "[T]", user_input); // no deadline
        Task current_task = tasks[counter]; // we just use this for reference
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println(current_task.getTaskType() + current_task.getCompletionStatus()
                + current_task.getDescription());
        System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
    }

    public static void addDeadline(String user_input, String[] time_split, String[] splitstr) throws DukeException {
        if (splitstr.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        }
        if (time_split.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The by: field of a deadline cannot be empty.");
        }
        String task_description = time_split[0].replace(splitstr[0], "");
        String deadline = time_split[1].replace("by ","");

        tasks[counter] = new Deadline(task_description, "[D]", deadline, user_input);
        Task current_task = tasks[counter];
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println(current_task.getTaskType() + current_task.getCompletionStatus()
                + current_task.getDescription() + " (by: " + deadline + ")");
        //print something else here
        System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
    }

    public static void addEvent(String user_input, String[] time_split, String[] splitstr) throws DukeException {
        if (splitstr.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
        }
        if (time_split.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The at: field of an event cannot be empty.");
        }
        String task_description = time_split[0].replace(splitstr[0], "");
        String deadline = time_split[1].replace("at ","");

        tasks[counter] = new Event(task_description, "[E]", deadline, user_input);
        Task current_task = tasks[counter];
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println(current_task.getTaskType() + current_task.getCompletionStatus()
                + current_task.getDescription() + " (at: " + deadline + ")");
        // print something else here
        System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
    }

    public static void list(int counter) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            String status = tasks[i].checkIfComplete();
            System.out.println(Integer.toString(i+1) + ". " + tasks[i].getTaskType() + "[" + status +
                    "] " + tasks[i].getDescription() + tasks[i].getDeadline());
        }
    }

    public static void markCompleted(int taskNum) {
        System.out.println("Nice! I've marked this task as done:");
        tasks[taskNum].updateisDone(true);
        System.out.println("[" + tasks[taskNum].checkIfComplete() + "] " + tasks[taskNum].getDescription() + tasks[taskNum].getDeadline());
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
                String line = tasks[i].getRawInput();
                String completion = tasks[i].getCompletionStatus();
                if (completion.equals("[\u2713]")) {
                    line = line + "/y";
                }
                else { line = line + "/n"; }

                writer.write(line + "\n");
                // then now you need to write.
            }
            writer.close();
        }
        catch (IOException exception) { System.out.println("File not found, cannot be written to!"); }
    }

}