import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int x = 0;
        int y = 0;
        while(true) {
            String in = sc.nextLine();
            if (in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else if(in.equals("list")) {
                for(y = 0; y < x; y++) {
                    System.out.printf("%d. %s\n", y+1, tasks[y]);
                }
            }

            else {
                tasks[x] = in;
                System.out.printf("added: %s\n", tasks[x]);
                x++;
            }
        }

    }
}
