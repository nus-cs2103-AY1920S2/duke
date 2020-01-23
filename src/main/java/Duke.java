import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> userInputList = new ArrayList<>();
        String logo = "  ____\n" + " (.__.)\n" + "<|>\n" + " /\\" + "\n_  _";
        Scanner sc = new Scanner(System.in);
        System.out.println(logo);
        System.out.println("☛ dude, what do you want? \n☛ give me a command!");
        System.out.print("dude, ");
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
                if (userInput.equals("list")) { // to list given tasks
                    System.out.println("☛ you asked to:");
                    for (int i = 0; i < userInputList.size(); i++) {
                        System.out.println("\t" + (i + 1) + ". " + userInputList.get(i));
                    }
                } else if (userInput.startsWith("done")) { // to mark tasks as done
                    System.out.println("☛ dude, you're fast!");
                    Integer taskNumber = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                    userInputList.get(taskNumber).markAsDone();
                }
                else { // to add different tasks: todos, deadlines, events
                    Task task = new Task();
                    String[] split = userInput.split(" ", 2);
                    String taskType = split[0];
                    String taskLine = split[1];
                    if (taskType.equals("todo")) { // to add todos (tasks with no date/time attached)
                        task = new ToDo(taskLine);
                    } else if (userInput.startsWith("deadline")) { // to add deadlines (tasks that must be done by specific date/time)
                        task = new Deadline(taskLine);
                    } else if (userInput.startsWith("event")) { // to add event (tasks that start at a specific time and ends at a specific time)
                        task = new Event(taskLine);
                    }
                    userInputList.add(task);
                    System.out.println("☛ fine, I will take note of: " + task);
                    System.out.println("☛ you made me remember " + userInputList.size() + " task(s)");
                }
            System.out.print("dude, ");
            userInput = sc.nextLine();
        }

        System.out.println("☛ dude, stop wasting my time! shoo!"); // 'bye' message
    }
}

