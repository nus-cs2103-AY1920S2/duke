import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<>();
        System.out.println("Hi! I am Duke! What would you like to tell me today?:)");

        String command = sc.nextLine();
        String exit = "bye";
        int index = 1;
        while (!command.equals(exit)) {
            if (command.contains("done")) {
                String[] strArr = command.split(" ");
                Task currTask = arr.get(Integer.parseInt(strArr[1]) - 1);
                currTask.setDone();
                System.out.println("Okay noted! You have completed the below task: ");
                System.out.println("[" + currTask.getDoneSymbol() + "] " + currTask.getCommand());
            } else if (command.equals("list")) {
                System.out.println("The below is what you have told me so far. Have you completed them? ");

                for (Task task: arr) {
                    System.out.println(task.getID() + ".[" + task.getDoneSymbol() + "]" + task.getCommand());
                }
            } else {
                Task newTask = new Task(command, index);
                arr.add(newTask);
                System.out.println("I have taken note: " + newTask.getCommand());
                index++;
            }
            command = sc.nextLine();
        }
        System.out.println("Okay then! Goodbye!");
    }
}
