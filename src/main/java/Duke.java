import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today homie?\n");
        Scanner io = new Scanner(System.in);
        String command = io.nextLine();
        while(!command.equals("bye")) {
            System.out.println("   -----");
            if (command.equals("list")) {
                for (int i = 1; i <= Task.tasks.size(); i++) {
                    System.out.println("   " + i + ". " + Task.tasks.get(i - 1));
                }
            } else if (command.length() > 4 && command.substring(0, 5).equals("done ")) {
                int index = Integer.parseInt(command.substring(5)) - 1;
                if (Task.tasks.size() <= index) {
                    System.out.println("   There is no task number " + (index + 1));
                    System.out.println("   -----");
                    command = io.nextLine();
                    continue;
                }
                System.out.println("   Nice! I've marked this task as done:");
                Task.tasks.get(index).done();
                System.out.println("   " + Task.tasks.get(index));
            } else {
                System.out.println("   added " + command);
                Task.tasks.add(new Task(command));
            }
            System.out.println("   -----");
            command = io.nextLine();
        }
        System.out.println("   -----");
        System.out.println("   Bye bye friend!");
        System.out.println("   -----");
    }
}
