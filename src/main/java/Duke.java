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

        while(sn.hasNext()){
            String input = sn.nextLine();

            if(input.equals("bye")){
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println(input);
                System.out.println(line);
            }


        }

    }
}
