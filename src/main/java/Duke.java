import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Task[] arr = new Task[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    Hello! I'm Duke\n" + "  What can I do for you?");
        Task.loadSavedData(); //check if there is previous list avail and load if avail
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] inputs = input.split(" ", 2);
                String command = inputs[0];
                char[] inputArr = input.toCharArray();
                if (command.equals("todo")) { //create todo
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    ;
                    String info = Todo.generateTodoDesc(inputArr);
                    Todo task = new Todo(info);
                    Task.addTask(task);
                } else if (command.equals("event")) { //create event
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    ;
                    String date, desc;
                    date = Event.getEventDate(inputs[1]);
                    desc = Event.getEventDesc(inputArr);
                    Event task = new Event(desc, date);
                    Task.addTask(task);
                } else if (command.equals("deadline")) { //create deadline
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    ;
                    String by, desc;
                    by = Deadline.getDate(inputs[1]);
                    desc = Deadline.getDesc(inputArr);
                    Deadline task = new Deadline(desc, by);
                    Task.addTask(task);
                } else if (command.equals("list")) { //list command
                    Task.showTasks();
                } else if (command.equals("done")) { //done command
                    Task.taskDone(input);
                } else if (command.equals("bye")) { //bye command
                    Task.saveToFile();
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("delete")) { //delete command
                    Task.deleteTask(inputs[1]);
                } else if (command.equals("find")) {
                    Task.find(inputs[1]);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}