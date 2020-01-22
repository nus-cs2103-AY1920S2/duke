import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        Store lib = new Store();
        while(sn.hasNext()){
            String input = sn.nextLine();
            if(input.equals("bye")) {
                lib.bye();
            } else if (input.equals("list")) {
                lib.list();
            } else {
                lib.AddNewAction(input);
            }

        }

    }
}
