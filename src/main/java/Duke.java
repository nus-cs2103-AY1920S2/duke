import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.frontDesk();
    }

    public void frontDesk() {
        try {
            String greetings = "____________________________________________________________ \n"
                    + "Hello! I'm Chu Chu \n"
                    + "What can I do for you ? \n"
                    + "____________________________________________________________\n";
            System.out.println(greetings);

            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(rd);
            String[] commandArr = null;
            String command = null;
            Parser parser = new Parser();

            while (true) {

                command = br.readLine();
                commandArr = parser.parse(command);

                if (commandArr[0].equals("bye")) {

                    break;

                } else if (commandArr[0].equals("list")) {

                    Task.list();

                } else if (commandArr[0].equals("done")) {

                    Task t = Task.getTask(Integer.parseInt(commandArr[1]));
                    t.markDone();

                } else {

                    Task.addTask(command);
                }
            }

            System.out.println("Bye. I hope you liked the service and hope to see you soon ! \n");

        } catch (IOException e) {

            System.out.print(e);
        }

    }
}
