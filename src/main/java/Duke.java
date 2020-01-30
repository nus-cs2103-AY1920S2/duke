import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Duke {

    public static void run(Scanner sc, TaskList taskList, String tasks) throws DukeException, IOException {

        String input = sc.nextLine();
        String[] split = input.split("\\s+");
        String command = split[0];
        int numOfTask;
        Storage storage = new Storage();
        Ui ui = new Ui();

        if (command.equals("bye")) {
            ui.print("Bye. Hope to see you again soon!");
            System.exit(0);

        } else if (command.equals("list")) {
            ui.print(taskList.toString());
        
        } else if (command.equals("done")) {
            int index = Integer.parseInt(split[1]) - 1;
            Task done = taskList.list.get(index);
            done.markAsDone();
            ui.print("Nice! I've marked this task as done: \n" + done);

            tasks = storage.readFromFile();
            storage.changeToDone(tasks, index);

        } else if (command.equals("delete")) {
            int index = Integer.parseInt(split[1]) - 1;
            Task remove = taskList.list.remove(index);
            numOfTask = taskList.list.size();
            ui.print("Noted. I've removed this task: \n" + remove 
                + "\nNow you have " + numOfTask + " task(s) in the list");

            tasks = storage.readFromFile();
            storage.removeLine(tasks, index);
        
        } else if (command.equals("todo")) {
            if (split.length == 1) {
                throw new DukeException("\u2639 OOPS!!!"
                    + " The description of a todo cannot be empty. \n");
            }
            String desc = input.substring(5);
            Task t = new ToDoTask(desc);
            taskList.list.add(t);
            numOfTask = taskList.list.size();
            ui.print("Got it. I've added this task: \n" + t + "\nNow you have " 
                    + numOfTask + " task(s) in the list.");
            
            storage.writeToFile("T | 0 | " + desc);

        } else if (command.equals("deadline")) {
            String[] arr = input.split(" /by ");
            String desc = arr[0].substring(9);
            String time = arr[1];
            Task t = new DeadlineTask(desc, LocalDate.parse(time));
            taskList.list.add(t);
            numOfTask = taskList.list.size();
            ui.print("Got it. I've added this task: \n" + t + "\nNow you have " 
                    + numOfTask + " task(s) in the list.");

            storage.writeToFile("D | 0 | " + desc + " | " + time);

        } else if (command.equals("event")) {
            String[] arr = input.split(" /at ");
            String desc = arr[0].substring(6);
            String time = arr[1];
            Task t = new EventTask(desc, LocalDate.parse(time));
            taskList.list.add(t);
            numOfTask = taskList.list.size();
            ui.print("Got it. I've added this task: \n" + t + "\nNow you have " 
                    + numOfTask + " task(s) in the list.");
        
            storage.writeToFile("E | 0 | " + desc + " | " + time);
        
        } else if (command.equals("find")) {
            String keyword = split[1];
            tasks = storage.readFromFile();
            String found = storage.findTask(tasks, keyword);
            TaskList foundList = Parser.storageToTaskList(found);
            ui.print("Here are the matching tasks in your list: \n" + foundList.toString());

        } else {
            throw new DukeException("\u2639 OOPS!!!"
                    + " I'm sorry, but I don't know what that means :-( \n");
        }        
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        TaskList taskList = new TaskList(new ArrayList<Task>());

        ui.print("Hello! I'm Duke \nWhat can I do for you?");
        Storage storage = new Storage();
        String tasks = storage.readFromFile();
        taskList = Parser.storageToTaskList(tasks);

        while (sc.hasNext()) {
            try {
                run(sc, taskList, tasks);
            } catch (DukeException ex) {
                ui.print(ex.getMessage());
            } catch (IOException e) {
                ui.print(e.getMessage());
            }
        }
        sc.close();
    }
}
