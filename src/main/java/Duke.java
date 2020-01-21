import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________");
        String command = sc.nextLine();
        String[] tasks = new String[100];
        int size = 0;
        while(!command.equals("bye")) {
            if(command.equals("list")){
                System.out.println("____________________________");
                for(int i = 0; i < size; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
                System.out.println("____________________________");
            } else {
                tasks[size] = command;
                size++;
                System.out.println("____________________________");
                System.out.println("added: " + command);
                System.out.println("____________________________");
            }
            command = sc.nextLine();
        }
        System.out.println("____________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________");
    }
}