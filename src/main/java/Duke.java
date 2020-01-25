import java.util.Scanner;

public class Duke {
    //set a horizontal line
    public static StringBuilder horizontalLine = new StringBuilder("   ").append("\u02DC".repeat(80)).append("\n");

    //to add horizontal line around the message and print it out
    public static void typeSetting(String s) {
        System.out.println(horizontalLine + s + "\n" + horizontalLine);
    }

    public static void main(String[] args) {
        //setting up
        Scanner sc = new Scanner(System.in);
        boolean exiting = false;
        Task[] list = new Task[100];
        int index = 0;

        //welcome message
        typeSetting("    Hello, I'm Bob.\n    What can I do for you? \uD83D\uDE03\n");

        //talking to Bob
        String str = sc.nextLine();
        while (!exiting) {//check if the user want to exit
            while (!str.equals("bye")) {
                if (str.equals("list")) {
                    //print out the whole list
                    StringBuilder li = new StringBuilder("    \uD83D\uDCDC Here are the tasks in your list:\n");
                    for (int i = 0; i < index; i++) {
                        li.append("         ").append(i + 1).append(": ").append(list[i]).append("\n");
                    }
                    typeSetting(li.toString());
                } else if (str.length() > 5 && str.substring(0, 5).equals("Done ")) {
                    //to mark certain task as done
                    int num = Integer.parseInt(str.substring(5));
                    list[num - 1].markAsDone();
                    typeSetting("    \uD83D\uDC4D Nice! I've marked this task as done: " + num
                                + "\n" + "      " + list[num - 1]);
                } else {
                    //add the message into the list
                    list[index] = new Task(str);
                    typeSetting("    added: " + str + "\n");
                    index++;
                }
                str = sc.nextLine();
            }

            //exit confirmation
            typeSetting("    Are you sure you want to leave me alone?\uD83E\uDD7A (y/n)\n");

            if (sc.nextLine().equals("y")) {
                //confirm to leave and leaving message
                exiting = true;
                typeSetting("    Bye. Hope to see you again soon!\uD83D\uDE1E\n");
            } else {
                //not leaving and continue to interact with Bob
                typeSetting("    I know you are the best!\uD83D\uDE06\n    Then, what's next?\n");
                str = sc.nextLine();
            }
        }
    }
}
