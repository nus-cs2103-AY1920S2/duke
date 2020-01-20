import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    ArrayList<String> textArr = new ArrayList<String>();

    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.frontDesk();
    }

    public void frontDesk() {
        try {
            String greetings = "____________________________________________________________ \n" +
                    "Hello! I'm Chu Chu \n" +
                    "What can I do for you ? \n" +
                    "____________________________________________________________\n";
            System.out.println(greetings);

            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(rd);
            String command = null;

            while (!(command = br.readLine()).equals("bye")) {

                if (command.equals("list")) {
                    String header = "____________________________________________________________ ";
                    System.out.println(header);
                    String text = null;

                    for(int i = 0; i < textArr.size(); i++) {
                      text = (i + 1) + "." + " " + textArr.get(i);
                      System.out.println(text);
                    }

                    String foot = "____________________________________________________________ ";
                    System.out.println(foot);
                } else {
                    textArr.add(command);
                    String text = "____________________________________________________________ \n" +
                            "added: " + command + "\n" +
                            "____________________________________________________________ \n";
                    System.out.println(text);
                }
            }

            System.out.println("Bye. I hope you liked the service and hope to see you soon ! \n");

        } catch (IOException e) {
            System.out.print(e);
        }

    }
}
