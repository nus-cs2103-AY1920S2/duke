import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DukeList duke = new DukeList();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you today?");

        while(true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;

            } else if (input.equals("list")) {
                if (duke.getSize() == 0) {
                    System.out.println("Yay! There are no tasks in your list!");
                } else {
                    System.out.println("Here are your tasks in your list:");
                    System.out.print(duke);
                }

            } else if (input.contains("done ") && input.substring(0, 4).equals("done")) {
                int taskID = (Integer.parseInt(input.substring(5)));
                System.out.println(duke.markDone(taskID));

            } else if (input.contains("todo ") && input.substring(0, 4).equals("todo")) {
                String newTaskName = input.substring(5);
                char taskType = 'T';
                System.out.println(duke.newTodo(taskType, newTaskName));

            } else if (input.contains("deadline ") && input.substring(0, 8).equals("deadline")) {
                String[] taskDetails = input.substring(9).split("/by");
                String newTaskName = taskDetails[0];
                String newTaskDeadline = "(by:" + taskDetails[1] + ")";
                char taskType = 'D';
                System.out.println(duke.newDeadline(taskType, newTaskName, newTaskDeadline));

            } else if (input.contains("event ") && input.substring(0, 5).equals("event")) {
                String[] taskDetails = input.substring(6).split("/at");
                String newTaskName = taskDetails[0];
                String newTaskDeadline = "(at:" + taskDetails[1] + ")";
                char taskType = 'E';
                System.out.println(duke.newEvent(taskType, newTaskName, newTaskDeadline));

            }

            else {
                System.out.println("Oops! Unknown command!");
            }

            System.out.println("");
        }

        System.out.println("Thank you for using Duke.\nHave a nice day!\n");
        scanner.close();
    }
}
