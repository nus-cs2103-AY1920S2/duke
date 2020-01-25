import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
        String command, input;
        mainLoop: while (true){
            input = sc.nextLine().trim();
            inputSc = new Scanner(input);
            command = inputSc.next();
            switch(command){
                case "bye":
                    response("Goodbye! Hope to hear from you soon :)");
                    break mainLoop;
                case "list":
                    listTasks();
                    break;
                case "done":
                    Task t = tasks.get(inputSc.nextInt() - 1);
                    t.markAsDone();
                    response("Awesome! I've marked this task as done:", t.toString());
                    break;
                case "todo":
                    Todo todo = new Todo(input.substring(command.length()).trim());
                    addTask(todo);
                    break;
                case "deadline":
                    int byIndex = input.indexOf("/by");
                    Deadline dl = new Deadline(
                            input.substring(command.length(), byIndex).trim(),
                            input.substring(byIndex + 3).trim()
                    );
                    addTask(dl);
                    break;
                case "event":
                    int atIndex = input.indexOf("/at");
                    Event e = new Event(
                            input.substring(command.length(), atIndex).trim(),
                            input.substring(atIndex + 3).trim()
                    );
                    addTask(e);
                    break;
                default:
                    tasks.add(new Task(input));
                    response("added: " + input);
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
