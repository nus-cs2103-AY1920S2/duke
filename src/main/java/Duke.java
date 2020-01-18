import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        while(true) {
            String command = sc.next();
            boolean wantToQuit = false;
            switch (command) {
                case "bye":
                    wantToQuit = true;
                    System.out.println("Goodbye!! See you some time soon.");

            }

            if(wantToQuit) {
                break;
            }

            System.out.println((command));
        }




    }
}
