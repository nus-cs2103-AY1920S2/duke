import java.util.ArrayList;
import java.util.List;
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
        List<String> list = new ArrayList<>();
        String instruction;
        while (!(instruction = sc.nextLine()).equals("bye")) {
            if (instruction.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else {
                list.add(instruction);
                System.out.println("added: "+ instruction);
            }
        }

        System.out.println("Goodbye. See you next time!");
    }
}
