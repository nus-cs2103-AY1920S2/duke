import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();

        System.out.println("Hello from\n" + logo);
        UserInput input;
        do {
            input = new UserInput(sc.nextLine());
            try {
                String message = input.execute(tasks);
                System.out.println(message);
            } catch (DukeNoCommandException e) {
                continue;
            } catch (DukeException e) {
                System.out.println(e);
            }
        } while (!input.getCommand().equals("bye") && sc.hasNextLine());
        sc.close();
        System.exit(0);
    }
}
