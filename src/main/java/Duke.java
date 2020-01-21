import entity.Task;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________");
        String command = sc.nextLine();
        Task[] tasks = new Task[100];
        int size = 0;
        while(!command.equals("bye")) {
            String[] nextLine = command.split(" ");
            if(nextLine[0].equals("list")){
                System.out.println("____________________________");
                for(int i = 0; i < size; i++) {
                    System.out.println(i + 1 + ".[" +  (tasks[i].isDone() ? "\u2713" : "\u2718" ) + "] "+ tasks[i].getTaskName());
                }
                System.out.println("____________________________");
            } else if (nextLine[0].equals("done")) {
                int index = Integer.parseInt(nextLine[1]) - 1;
                tasks[index].markAsDone(true);
                System.out.println("____________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(index + 1 + ".[\u2713] "+ tasks[index].getTaskName());
                System.out.println("____________________________");
            } else {
                tasks[size] = new Task(command);
                size++;
                System.out.println("____________________________");
                System.out.println("added: " + command);
                System.out.println("____________________________");
            }
            command = sc.nextLine();
        }
        System.out.println("____________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________");
    }
}