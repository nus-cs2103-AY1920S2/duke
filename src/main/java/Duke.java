import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello, this is Duke. Please give me an instruction.");

        Scanner sc = new Scanner(System.in);
        String instruction;
        while (!(instruction = sc.nextLine()).equals("bye")) {
            System.out.println(instruction);
        }
        
        System.out.println("Goodbye. See you next time!");
    }
}
