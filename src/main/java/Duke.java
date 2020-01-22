import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TasksList tasksList = new TasksList();

        System.out.println("____________________________________________________________\n"
                + "Hello! I'm Duke\nWhat can I do for you?\n"
                + "____________________________________________________________");

        String userInput = sc.nextLine();
        String[] inputs = userInput.split(" ", 2);

        while (!userInput.equals("bye")) {
            try {
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
                        tasksList.markDone(userInput);
                        break;
                    default:
                        throw new DukeUnknownInputException();
                }
            } catch (DukeMissingDescriptionException mE) {
                System.out.println("OOPS!!! The description cannot be empty.");
            } catch (DukeUnknownInputException uE) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :<");
            }
            userInput = sc.nextLine();
            inputs = userInput.split(" ", 2);
        }

        System.out.print("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________");
    }
}