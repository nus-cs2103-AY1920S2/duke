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
        chatbot.chat();
    }
    public void greet() {
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
    }
    public void chat() {
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
                String description = sc.nextLine();
                System.out.println("added: " + action + " " + description);
                this.taskStorage.add(new Task(action + " " + description));
            }
            action = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printText() {
        int counter = 1;
        for(Task task : this.taskStorage){
            System.out.println(counter + ". " + task.toString());
            counter++;
    }
    }

}