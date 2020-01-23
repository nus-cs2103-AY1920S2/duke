import java.util.Scanner;
import java.util.ArrayList;
public class Duke  {
    private ArrayList<Task> taskStorage;

    private Duke(ArrayList<Task> newtaskStorage){
        this.taskStorage = newtaskStorage;
    }
    public static void main(String[] args) {
        Duke chatbot = new Duke(new ArrayList<>());
        chatbot.initiateGreetings();
        chatbot.initiateChat();
    }
    public void initiateGreetings() {
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
    }
    public void initiateChat() {
        Scanner sc = new Scanner(System.in);
        String[] userInput = sc.nextLine().split(" ");
        String action = userInput[0];
        while (!action.equals("bye")) {
                if (action.equals("done")) {
                    int taskNo = Integer.parseInt(userInput[1]);
                    this.taskStorage.get(taskNo - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + this.taskStorage.get(taskNo - 1).toString());
                }
                else if (action.equals("delete")){
                    int taskNo = Integer.parseInt(userInput[1]) - 1;
                    Task removedTask = this.taskStorage.get(taskNo);
                    this.taskStorage.remove(taskNo);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removedTask.toString() + "\nNow you have " + this.taskStorage.size() + " tasks in the list");
                }
                else if (action.equals("list")) {
                    this.printText();
                } else {
                    String description = Duke.arrayToString(userInput);
                    if (action.equals("todo")) {
                           if (userInput.length == 1) {
                               throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                           }
                            System.out.println("Got it. I've added this task:");
                            this.taskStorage.add(new Task(description));
                    }
                    else if (action.equals("deadline")) {
                        if (userInput.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] tokens = description.split("/by");
                        System.out.println("Got it. I've added this task:");
                        this.taskStorage.add(new Deadlines(tokens[0], tokens[1]));
                    } else if (action.equals("event")) {
                        if (userInput.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        String[] tokens = description.split("/at");
                        System.out.println("Got it. I've added this task:");
                        this.taskStorage.add(new Events(tokens[0], tokens[1]));
                    } else {
                        throw new DukeException("  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    int numbOfTask = this.taskStorage.size();
                    System.out.println(this.taskStorage.get(numbOfTask - 1).toString());
                    System.out.println("Now you have " + numbOfTask + " tasks in the list.");
                }
                userInput = sc.nextLine().split(" ");
                action = userInput[0];
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