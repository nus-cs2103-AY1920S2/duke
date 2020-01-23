import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String separator = "________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you? :)\n");

        Scanner scanner = new Scanner(System.in);

        //store user task
        Task[] task = new Task[100];
        int index = 0;

        String input = "";
        input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(separator);
                for (int i = 0; i < index; i++) {
                    System.out.println((i+1) + ". " + task[i]);
                }
                System.out.println(separator);

            } else if (input.split(" ")[0].equals("done")) {
                System.out.println("Nice! I've marked this task as done: ");
                int num = Integer.parseInt(input.split(" ")[1]) - 1;
                task[num].markDone();
                System.out.println("  " + task[num]);
            } else {
                task[index] = new Task(input);

                //display reply
                System.out.println(separator);
                System.out.println("added: " + task[index].getDescription());
                System.out.println(separator);

                index++;
            }
            //next input
            input = scanner.nextLine();
        }

        // exit the app
        System.out.println(separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);

    }
}
