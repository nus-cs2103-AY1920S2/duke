import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> tasks = new ArrayList<>();
    private final String LINE = "\t__________________________________________________________";
    private void response(String s){
        System.out.println(LINE);
        System.out.println("\t" + s);
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
        String input;
        mainLoop: while (true){
            input = sc.nextLine().trim();
            switch(input){
                case "bye":
                    response("Goodbye! Hope to hear from you soon :)");
                    break mainLoop;
                case "list":
                    listTasks();
                    break;
                default:
                    tasks.add(new Task(input));
                    response("added: " + input);
            }
        }
    }

    private void listTasks() {
        System.out.println(LINE);
        for (int i=0; i<tasks.size(); i++){
            System.out.println("\t" + (i+1) + ": " + tasks.get(i));
        }
        System.out.println(LINE);
    }
}
