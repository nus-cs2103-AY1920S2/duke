import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //setting up
        Scanner sc = new Scanner(System.in);
        StringBuilder horizontalLine = new StringBuilder("   ");
        horizontalLine.append("\u02DC".repeat(80));
        horizontalLine.append("\n");
        boolean exiting = false;
        String[] list = new String[100];
        int index = 0;

        //welcome message
        System.out.println(horizontalLine +
                            "    Hello, I'm Bob.\n    What can I do for you? \uD83D\uDE03\n\n"
                            + horizontalLine);

        //talking to bob
        String str = sc.nextLine();
        while (!exiting) {//check if the user want to exit
            while (!str.equals("bye")) {
                if (str.equals("list")) {
                    //print out the whole list
                    StringBuilder li = new StringBuilder(horizontalLine.toString());
                    for (int i = 0; i < index; i++) {
                        li.append("    ").append(i + 1).append(": ").append(list[i]).append("\n");
                    }
                    System.out.println(li + "\n" + horizontalLine);
                } else {
                    //add the message into the list
                    list[index] = str;
                    System.out.println(horizontalLine +
                                        "    added: " + str + "\n\n" +
                                        horizontalLine);
                    index++;
                }
                str = sc.nextLine();
            }

            //confirmation
            System.out.println(horizontalLine +
                                "    Are you sure you want to leave me alone?\uD83E\uDD7A (y/n)\n\n" +
                                horizontalLine);
            if (sc.nextLine().equals("y")) {
                //confirm to leave and leaving message
                exiting = true;
                System.out.println(horizontalLine +
                        "    Bye. Hope to see you again soon!\uD83D\uDE1E\n\n" +
                        horizontalLine);
            } else {
                //not leaving
                System.out.println(horizontalLine +
                                    "    I know you are the best!\uD83D\uDE06\n    Then, what's next?\n\n" +
                                    horizontalLine);
                str = sc.nextLine();
            }
        }
    }
}
