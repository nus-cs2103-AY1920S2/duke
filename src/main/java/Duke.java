import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList= new ArrayList<Task>();
        //String[] taskDes = new String[100];
        int x = 1;
        int y = 0;
        while(true) {
            String in = sc.nextLine();
            Task T = new Task(in);

            if(in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else if(in.equals("list")) {
                System.out.println("Here are the items in your list:");
                x = 1;
                for(Task A: taskList) {
                    System.out.printf("%d.[%s] %s\n", x, A.getStatusIcon(),A.getDescription());
                    x++;
                }
            }

            else if(in.substring(0,Math.min(in.length(), 4)).equals("done")) {
                int index = Integer.parseInt(in.substring(5,in.length()));
                taskList.get(index - 1).doTask();
                System.out.println("Nice, I've marked this task as done:");
                System.out.printf("[%s] %s\n", taskList.get(index - 1).getStatusIcon(), taskList.get(index - 1).getDescription());

            }

            else {
                taskList.add(T);
                System.out.printf("added: %s\n", T.getDescription());
            }
        }

    }
}
