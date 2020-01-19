import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Todo todo = new Todo();

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
                System.out.println("Here are your tasks in your list:");
                System.out.print(todo);
            } else if (input.substring(0, 4).equals("done")) {
                System.out.println(todo.markDone(Integer.parseInt(input.substring(5))));
            } else {
                System.out.println(todo.add(input));
            }
        }

        System.out.println("Thank you for using Duke.\nHave a nice day!\n");
        scanner.close();
    }
}
