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
                int i = 1;
                for(Task task : Task.taskList){
                    System.out.println(i + ". " + task);
                    i++;
                }
            } else {
                String out = Task.addTask(in);
                System.out.println(out);
            }
            in = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
