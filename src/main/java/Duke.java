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
            if (!input.equals("list") && !input.contains("done")) {
                char[] inputArr = input.toCharArray();
                if(input.contains("todo")) {
                    String todoInput = "";
                    for(int i = 5; i < inputArr.length; i++) {
                        todoInput += inputArr[i];
                    }
                    Todo task = new Todo(todoInput);
                    arr[listCounter] = task;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.toString());
                } else if (input.contains("event")) {
                    int marker = 0;
                    String date = "";
                    String desc = "";
                    for(int i = inputArr.length - 1; (inputArr[i] != '/'); i--) {
                        marker = i;
                    }
                    for(int i = marker + 3; i < inputArr.length; i++) {
                        date += inputArr[i];
                    }
                    System.out.println(date);
                    for (int i = 6; i < marker - 2; i++) {
                        desc += inputArr[i];
                    }
                    System.out.println(desc);
                    Event task = new Event(desc, date);
                    arr[listCounter] = task;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.toString());
                } else if (input.contains("deadline")) {
                    int marker = 0;
                    String by = "";
                    String desc = "";
                    for(int i = inputArr.length - 1; (inputArr[i] != '/'); i--) {
                        marker = i;
                    }
                    for(int i = marker + 3; i < inputArr.length; i++) {
                        by += inputArr[i];
                    }
                    System.out.println(by);
                    for (int i = 9; i < marker - 2; i++) {
                        desc += inputArr[i];
                    }
                    System.out.println(desc);
                    Deadline task = new Deadline(desc, by);
                    arr[listCounter] = task;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.toString());
                }
                listCounter++;
                System.out.println("Now you have " + listCounter + " tasks in the list.");
                input = sc.nextLine();
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listCounter; i++) {
                    System.out.println(i + 1 + "." + arr[i].toString());
                }
                input = sc.nextLine();
            } else {
                char charArr[] = input.toCharArray();
                String taskNum = "";
                for (int i = 5; i < charArr.length; i++) {
                    taskNum += charArr[i];
                }
                int taskInt = Integer.parseInt(taskNum);
                taskInt -= 1;
                arr[taskInt].isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + arr[taskInt].getStatusIcon() + "] " + arr[taskInt].getDescription());
                input = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
