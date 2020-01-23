import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String logo = "DUKE!\n";
        System.out.println("Hello from " + logo);
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while (!in.equals("bye")){
            if (in.equals("list")){
                Task.printList();
            } else if (in.contains("done")) {
                int num = Integer.parseInt(in.substring(5));
                Task.printDone(num);
            } else {
                Task.addTask(in);
            }
            in = sc.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
    }
}
