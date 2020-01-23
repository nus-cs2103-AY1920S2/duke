import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while (!in.equals("bye")){
            if (in.equals("list")){
                Task.printList();
            } else if (in.contains("done")) {
                int num = Integer.parseInt(in.substring(5));
                Task.markDone(num);
            } else {
                String out = Task.addTask(in);
                System.out.println(out);
            }
            in = sc.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
    }
}
