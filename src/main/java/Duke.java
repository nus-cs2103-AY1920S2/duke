import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

//        System.out.println("Hello from\n" + logo);

        printFormattedOutput("Hello! I'm Duke\n    What can I do for you?");

        // Chat logic
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] list = new String[100];
        int sizeOfList = 0;

        while(!input.equals("bye")) {
            if(input.equals("list")) {
                printList(list, sizeOfList);
            } else {
                list[sizeOfList] = (sizeOfList + 1) + ". " + input;
                sizeOfList++;
                printFormattedOutput("added: " + input);
            }
            input = sc.nextLine();
        }

        printFormattedOutput("Bye. Hope to see you again soon!");

    }

    public static void printFormattedOutput(String output) {
        String bar = "    *****************************************************\n";

        System.out.println(bar + "    " + output + "\n" + bar);
    }

    public static void printList(String[] list, int size) {
        String bar = "    *****************************************************\n";
        System.out.print(bar);
        for(int i = 0; i < size; i++) {
            System.out.println("    " + list[i]);
        }
        System.out.println(bar);
    }
}
