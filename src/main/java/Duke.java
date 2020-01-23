import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
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
            } else { //TODO: change from using contains to 1st word

//            } else {
                    String taskType = in.split(" ", 2)[0];
                    if (taskType.equals("done")) {
//                        try {
                            Task.printDone(in);
//                            int num = Integer.parseInt(in.substring(5));
//                            Task.printDone(num);
//                        } catch (IndexOutOfBoundsException e){ // when no int arg provided
//                            System.err.println("OOPS!!! Done must take a valid number in the range of the task list.");
//                        } catch (NumberFormatException e) { // when non-int arg provided
//                            System.err.println("OOPS!!! Done must take a valid integer in the range of the task list.");
//                        }
                    } else if (taskType.equals("delete")){
                        Task.deleteTask(in);
                    } else if (Task.isValidTask(taskType)){
                        Task.addTask(in);
                    } else if (in.isEmpty() || in == null){
                        System.err.println("     ☹ OOPS!!! Please type something here.");
                    } else {
                        System.err.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
            }
            in = sc.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
    }
}
