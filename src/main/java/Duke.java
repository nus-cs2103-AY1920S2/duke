import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TasksList tasksList = new TasksList();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String userInput = sc.nextLine();
        String[] inputs = userInput.split(" ");

        while (!userInput.equals("bye")) {
            switch(inputs[0]) {
                case "list":
                    tasksList.list();
                    break;
                case "todo":
                    tasksList.addTodo(userInput);
                    break;
                case "deadline":
                    tasksList.addDeadline(userInput);
                    break;
                case "event":
                    tasksList.addEvent(userInput);
                    break;
                case "done":
                    tasksList.markDone(Integer.valueOf(inputs[1]));
                    break;
            }
            userInput = sc.nextLine();
            inputs = userInput.split(" ");
        }

        System.out.print("Bye. Hope to see you again soon!");
    }
}