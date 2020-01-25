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
        if (!input.equals("bye") && !input.equals("list")) {
            System.out.println("added: " + input);
            arr[listCounter] = new Task(input);
            listCounter++;
        } else if (input.equals("list")) {
            for(int i = 0; i < arr.length; i++) {
                System.out.println(i + 1 + ". " + arr[i].getDescription());
            }
        } else if (input.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
