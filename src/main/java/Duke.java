import java.util.Scanner;
import java.util.ArrayList;
public class Duke  {
    private ArrayList<Task> taskStorage;


    private Duke(ArrayList<Task> newtaskStorage){
        this.taskStorage = newtaskStorage;
    }
    public static void main(String[] args) {
        Duke chatbot = new Duke(new ArrayList<>());
        chatbot.greet();
        chatbot.initiateChat();
    }
    public void greet() {
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
    }
    public void initiateChat() {
        Scanner sc = new Scanner(System.in);
        String action = sc.next();
        while(!action.equals("bye")) {
            if(action.equals("done")){
                int taskNo = sc.nextInt();
                this.taskStorage.get(taskNo - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + this.taskStorage.get(taskNo - 1).toString());
            }
            else if(action.equals("list")){
                this.printText();
            }
            else {
                System.out.println("Got it. I've added this task:");
                String description = sc.nextLine();
                if (action.equals("todo")) {
                    this.taskStorage.add(new Task(description));
                }
                 else if (action.equals("deadline")) {
                    String[] tokens = description.split("/by");
                    this.taskStorage.add(new Deadlines(tokens[0],tokens[1]));
                } else { // this is for events
                    String[] tokens = description.split("/at");
                    this.taskStorage.add(new Events(tokens[0],tokens[1]));
                }
                int numbOfTask = this.taskStorage.size();
                System.out.println(this.taskStorage.get(numbOfTask - 1).toString());
                System.out.println("Now you have " + numbOfTask + " tasks in the list.");
            }
                action = sc.next();
            }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printText() {
        int counter = 1;
        for(Task task : this.taskStorage){
            System.out.println(counter + "." + task.toString());
            counter++;
        }
    }
}