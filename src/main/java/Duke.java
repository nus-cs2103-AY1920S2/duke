import java.util.Scanner;
import java.util.ArrayList;
public class Duke  {
    private ArrayList<Task> taskStorage;
    public static final String path = "/Users/weicheng/Dropbox/Duke.txt";

    private Duke(ArrayList<Task> newtaskStorage){
        this.taskStorage = newtaskStorage;
    }
    public static Duke initiateBot() {
        Archive archive = new Archive(Duke.path);
        return new Duke(archive.loadFromFile());
    }
    public void initiateGreetings() {
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
    }
    public void initiateChat() {
        Scanner sc = new Scanner(System.in);
        String[] userInput = sc.nextLine().split(" ",2);
        String action = userInput[0];
        Archive archive = new Archive(Duke.path);
        while (!action.equals("bye")) {
            try {
                ExceptionGenerator.checkInputLength(userInput);
                ExceptionGenerator.checkInputAction(userInput);
                switch (action) {
                    case "done":
                        int taskNo = Integer.parseInt(userInput[1]);
                        this.taskStorage.get(taskNo - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n  "
                                + this.taskStorage.get(taskNo - 1).toString());
                        break;
                    case "delete":
                        taskNo = Integer.parseInt(userInput[1]) - 1;
                        Task removedTask = this.taskStorage.get(taskNo);
                        this.taskStorage.remove(taskNo);
                        System.out.println("Noted. I've removed this task:\n"
                                + removedTask.toString());
                        break;
                    case "list":
                        this.printText();
                        break;
                    case "todo":
                        String description = userInput[1];
                        System.out.println("Got it. I've added this task:");
                        this.taskStorage.add(new Task(description));
                        break;
                    case "deadline":
                        String[] tokens = userInput[1].split("/by");
                        System.out.println("Got it. I've added this task:");
                        this.taskStorage.add(new Deadlines(tokens[0], tokens[1]));
                        break;
                    case "event":
                        tokens = userInput[1].split("/at");
                        System.out.println("Got it. I've added this task:");
                        this.taskStorage.add(new Events(tokens[0], tokens[1]));
                        break;
                }
                int numbOfTask = this.taskStorage.size();
                if(numbOfTask > 0 && !action.equals("list") && !action.equals("delete") && !action.equals("done")) {
                    System.out.println(this.taskStorage.get(numbOfTask - 1).toString());
                }
                    System.out.println("Now you have " + numbOfTask + " tasks in the list.");
            } catch (DukeException ex){
                //System.err.println(ex);
                ex.printStackTrace();
            }
            userInput = sc.nextLine().split(" ", 2);
            action = userInput[0];
            archive.saveToDisk(this.taskStorage);
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
        public static String arrayToString(String[] source){
            StringBuilder sb = new StringBuilder();
            for(int i = 1; i < source.length-1; i++){
                sb.append(source[i]);
                sb.append(" ");
            }
            sb.append(source[source.length - 1]);
            return sb.toString();
    }

    public void printText() {
        int counter = 1;
        for(Task task : this.taskStorage){
            System.out.println(counter + "." + task.toString());
            counter++;
        }
    }
}