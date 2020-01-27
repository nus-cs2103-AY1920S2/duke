import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dukeList = new String[100];
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
                System.out.println("I have added: " + nxt + " to your list.");
                dukeList[counter] = nxt;
                counter++;
                nxt = sc.nextLine();
            } else {
                for (int i = 0; i < counter; i++) {
                    System.out.println((i+1) + ". " + dukeList[i]);
                }
                nxt = sc.nextLine();
            }
        }


        System.out.println("Bye. Hope to see you again soon!");

    }
}
