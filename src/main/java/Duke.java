import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) throws DukeException, IOException {

        // load data from ./data/duke.txt
        File file = new File("./data/duke.txt");
        file.createNewFile();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;

        while ((str = br.readLine()) != null) {
            Task task;
            String[] data = str.split("\\|");
            if (data[0].equals("T")) {
                System.out.println(data[0]);
                System.out.println(data[1]);
                System.out.println(data[2]);
                task = createAndAddTask("todo", "todo " + data[2]);
            } else if (data[0].equals("E")){
                task = createAndAddTask("event", "event " + data[2] + " /at " + data[3]);
            } else {
                task = createAndAddTask("deadline", "deadline " + data[2] + " /by " + data[3]);
            }

            if (data[1].equals("1")) {
                task.markAsDone();
            }
        }


            String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        reply("    What can I do for you");

        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                handle(command);
            }
            catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }

        }

        // save data into ./data/duke.txt
        if (tasks.isEmpty()) {
            Files.write(Paths.get("./data/duke.txt"), ("").getBytes());

        } else {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (int i = 0; i < Task.totalTasks; i++) {
                bw.write(tasks.get(i).saveString());
                bw.newLine();
            }
            bw.close();
        }

        reply("    Bye. Hope to see you again soon!");
    }

    public static void handle(String string) throws DukeException{
        if (string.equals("list")) {
            printList();
        } else {
            String type = string.split(" ")[0];
            if (type.equals("done") || type.equals("delete")) {
                int taskNo = Integer.parseInt(string.split(" ")[1]);
                if (taskNo > tasks.size() || taskNo <= 0) {
                    throw new DukeException("☹ OOPS!! Not a valid number");
                } else {
                    if (type.equals("done")) {
                        tasks.get(taskNo - 1).markAsDone();
                        doneMessage(tasks.get(taskNo - 1));
                    } else if (type.equals("delete")) {
                        deleteMessage(tasks.get(taskNo - 1));
                        tasks.remove(taskNo - 1);
                        Task.totalTasks--;
                    }
                }
            } else {
                // create new task -> add to tasks -> reply
                Task task = createAndAddTask(type, string);
                addMessage(task);

            }
        }

    }

    public static void reply(String string) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        " + string);
        System.out.println("    ____________________________________________________________");
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Here are the tasks in your list:");
        int count = 1;
        for (Task task : tasks) {
            System.out.print("        " + count + ".");
            task.taskSummary();
            count++;
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void doneMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Nice! I've marked this task as done: ");
        System.out.print("        ");
        task.taskSummary();
        System.out.println("    ____________________________________________________________");
    }

    public static void addMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Got it. I've added this task:");
        System.out.print("            ");
        task.taskSummary();
        System.out.println("        Now you have " + Task.totalTasks + " " + (Task.totalTasks == 1? "task" : "tasks") + " in the list.");
        System.out.println("    ____________________________________________________________");

    }

    public static void deleteMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Noted. I've removed this task:");
        System.out.print("        ");
        task.taskSummary();
        System.out.println("        Now you have " + Task.totalTasks + " " + (Task.totalTasks == 1? "task" : "tasks") + " in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static Task createAndAddTask(String type, String whole) throws DukeException {
        Task task;
        if (whole.split(" ").length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        }

        if (type.equals("todo")) {
            String desc = whole.substring(5);
            task = new ToDo(desc);

        } else if (type.equals("event")) {
            String desc = whole.substring(6).split("/at ")[0];
            String dateAndTime = whole.substring(6).split("/at ")[1];
            LocalDate date = LocalDate.parse(dateAndTime.split(" ")[0]);
            task = new Event(desc, date);

        } else if (type.equals("deadline")) {
            String desc = whole.substring(9).split("/by ")[0];
            String dateAndTime = whole.substring(9).split("/by ")[1];
            LocalDate date = LocalDate.parse(dateAndTime.split(" ")[0]);
            task = new Deadline(desc, date);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(task);
        return task;
    }
}
