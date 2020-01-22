import java.util.*;

public class Duke {

    static ArrayList<String> tasks = new ArrayList<String>();
    static Scanner sc = new Scanner(System.in);

    static void printIntro() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Hello! I'm Duke\n" +
                        "What can I do for you?\n" +
                        "____________________________________________________________\n\n");
    }

    static void printReply(String input) {
        System.out.print(
                "____________________________________________________________\n" +
                "added: " + input +
                "\n____________________________________________________________\n");
    }

    static void printGoodbye() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!" +
                        "\n____________________________________________________________\n");
    }

    static void printList(ArrayList<String> tasks){
        System.out.print("____________________________________________________________\n");
        for (int i =  0; i < tasks.size(); i++){
            System.out.println(Integer.toString(i + 1) + ": " + tasks.get(i));
        }
        System.out.print("____________________________________________________________\n");
    }

    public static void main(String[] args) {
        printIntro();
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")){
            if (input.toLowerCase().equals("list")){
                printList(tasks);
            } else {
                tasks.add(input);
                printReply(input);
            }
            input = sc.nextLine();
        }
        printGoodbye();
    }
    
}
