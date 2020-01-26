import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] arr = new Task[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    Hello! I'm Duke\n" + "  What can I do for you?");
        String input = sc.nextLine();
        int listCounter = 0;
        while(!input.equals("bye")) {
            if (!input.equals("list") && !input.contains("done")){
                System.out.println("added: " + input);
                arr[listCounter] = new Task(input);
                listCounter++;
                input = sc.nextLine();
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listCounter; i++) {
                    System.out.println(i + 1 + ".[" + arr[i].getStatusIcon() + "] " + arr[i].getDescription());
                }
                input = sc.nextLine();
            } else {
                char charArr[] = input.toCharArray();
                String taskNum = "";
                for (int i = 5; i < charArr.length; i++) {
                    taskNum += i;
                }
                int taskInt = Integer.parseInt(taskNum);
                taskInt -= 1;
                arr[taskInt].isDone = true;
                input = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
