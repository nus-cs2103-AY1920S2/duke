import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] dukeList = new Task[100];
        int counter = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        String nxt;
        nxt = sc.nextLine();
        while (nxt.equals("bye") == false) {
            if (nxt.equals("list") == false) {
                StringTokenizer st = new StringTokenizer(nxt);
                String first = st.nextToken();
                if (first.equals("done") == false) {
                    Task t = new Task(nxt);
                    System.out.println("I have added: " + nxt + " to your list.");
                    dukeList[counter] = t;
                    counter++;
                    nxt = sc.nextLine();
                } else {
                    int num = Integer.parseInt(st.nextToken());
                    dukeList[num-1].markAsDone();
                    System.out.println("The following has been marked as done:");
                    System.out.println(dukeList[num-1]);
                    nxt = sc.nextLine();
                }
            } else {
                for (int i = 0; i < counter; i++) {
                    System.out.println((i+1) + "." + dukeList[i]);
                }
                nxt = sc.nextLine();
            }
        }


        System.out.println("Bye. Hope to see you again soon!");

    }
}
