import java.util.ArrayList;
import java.util.Scanner;

public class Duke {


    public static class DukeException extends Exception {
        public DukeException(String msg){
            super(msg);
        }
    }


    private ArrayList<Task> tasks = new ArrayList<>();
    private final String LINE = "\t__________________________________________________________";
    private void response(String... strs){
        System.out.println(LINE);
        for(String s: strs){
            System.out.println("\t" + s);
        }
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        new Duke().start();
    }
    private void start(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        Scanner inputSc;
        String command, input, name, datetime, rest;
        mainLoop: while (true){
            input = sc.nextLine().trim();
            inputSc = new Scanner(input);
            command = inputSc.next();
            try {
                switch (command) {
                    case "bye":
                        response("Goodbye! Hope to hear from you soon :)");
                        break mainLoop;
                    case "list":
                        listTasks();
                        break;
                    case "done":
                        if (tasks.size() == 0) {
                            throw new DukeException("There are no tasks in the list :(");
                        }
                        if (!inputSc.hasNextInt()) {
                            throw new DukeException("The index of the task is missing :/");
                        }
                        int index = inputSc.nextInt() - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new DukeException("Please enter a valid index, from 1 to " + tasks.size());
                        }
                        Task t = tasks.get(index);
                        t.markAsDone();
                        response("Awesome! I've marked this task as done:", t.toString());
                        break;
                    case "delete":
                        if (tasks.size() == 0) {
                            throw new DukeException("There are no tasks in the list :(");
                        }
                        if (!inputSc.hasNextInt()) {
                            throw new DukeException("The index of the task is missing :/");
                        }
                        int i = inputSc.nextInt() - 1;
                        if (i < 0 || i >= tasks.size()) {
                            throw new DukeException("Please enter a valid index, from 1 to " + tasks.size());
                        }
                        Task task = tasks.get(i);
                        tasks.remove(i);
                        response("Noted. I've removed this task: ", task.toString());
                        break;
                    case "todo":
                        name = input.substring(command.length()).trim();
                        if (name.length() == 0) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        Todo todo = new Todo(name);
                        addTask(todo);
                        break;
                    case "deadline":
                        rest = input.substring(command.length()).trim();
                        int byIndex = rest.indexOf("/by");
                        if (byIndex == -1) {
                            throw new DukeException("Please include the deadline datetime after the \"/by\" keyword");
                        }
                        name = rest.substring(0, byIndex).trim();
                        if (name.length() == 0) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        datetime = rest.substring(byIndex + 3).trim();
                        if (datetime.length() == 0) {
                            throw new DukeException("The deadline datetime cannot be empty.");
                        }
                        Deadline dl = new Deadline(name, datetime);
                        addTask(dl);
                        break;
                    case "event":
                        rest = input.substring(command.length()).trim();
                        int atIndex = rest.indexOf("/at");
                        if (atIndex == -1) {
                            throw new DukeException("Please include the event datetime after the \"/at\" keyword");
                        }
                        name = rest.substring(0, atIndex).trim();
                        if (name.length() == 0) {
                            throw new DukeException("The description of a event cannot be empty.");
                        }
                        datetime = rest.substring(atIndex + 3).trim();
                        if (datetime.length() == 0) {
                            throw new DukeException("The event datetime cannot be empty.");
                        }
                        Event e = new Event(name, datetime);
                        addTask(e);
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e){
                response("☹ OOPS!!! " + e.getMessage());
            }
        }
    }

    private void addTask(Task t){
        tasks.add(t);
        response("Got it, I've added this task:", t.toString(), "Now you have " + tasks.size() + " task(s) in the list.");
    }
    private void listTasks() {
        System.out.println(LINE);
        for (int i=0; i<tasks.size(); i++){
            System.out.println("\t" + (i+1) + ": " + tasks.get(i));
        }
        System.out.println(LINE);
    }
}
