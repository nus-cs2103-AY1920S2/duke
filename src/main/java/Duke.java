import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void print(String toPrint) {
        String line = "\n______________________________________ \n";
        System.out.println(line + toPrint + line);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList(new ArrayList<Task>());

        print("Hello! I'm Duke \nWhat can I do for you?");

        while (scanner.hasNext()) {
            String[] input = scanner.nextLine().split("\\s+");
            String command = input[0];

            if (command.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            
            } else if (command.equals("list")) {
                print(taskList.toString());
            
            } else if (command.equals("done")) {
                int index = Integer.parseInt(input[1]) - 1;
                Task done = taskList.list.get(index);
                done.markAsDone();
                print("Nice! I've marked this task as done: \n" + done);
                
            } else {
                String output = String.join(" ", input);
                taskList.list.add(new Task(output));
                print(" > added: " + output);
            }
        }
        scanner.close();
    }
}
