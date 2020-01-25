import java.util.Scanner;
import java.util.StringTokenizer;

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
                } else {
                    StringTokenizer st = new StringTokenizer(str);
                    String first = st.nextToken(" ");
                    switch (first) {
                        //decide which action to be done by the first token
                        case "done":
                            int num = Integer.parseInt(str.substring(5));
                            list[num - 1].markAsDone();
                            typeSetting("    \uD83D\uDC4D Nice! I've marked this task as done: " + num
                                    + "\n" + "      " + list[num - 1]);
                            break;

                        case "todo":
                            Todo td = new Todo(st.nextToken("").substring(1));
                            list[index] = td;
                            index++;
                            typeSetting("    Got it. I've added this task: \n      " +
                                        td + "\n" +
                                        "    Now you have " + index + " tasks in the list.");
                            break;

                        case "deadline":
                            String temp = st.nextToken("/").substring(1);
                            String des1 = temp.substring(0, temp.length() - 1);
                            String by = st.nextToken("/").substring(3);
                            Deadline ddl = new Deadline(des1, by);
                            list[index] = ddl;
                            index++;
                            typeSetting("    Got it. I've added this task: \n      " +
                                    ddl + "\n" +
                                    "    Now you have " + index + " tasks in the list.");
                            break;

                        case "event":
                            String temp2 = st.nextToken("/").substring(1);
                            String des2 = temp2.substring(0, temp2.length() - 1);
                            String at = st.nextToken("/").substring(3);
                            Event ev = new Event(des2, at);
                            list[index] = ev;
                            index++;
                            typeSetting("    Got it. I've added this task: \n      " +
                                    ev + "\n" +
                                    "    Now you have " + index + " tasks in the list.");
                            break;
                    }
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
