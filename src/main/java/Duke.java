import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    final String header = "____________________________________________________________\n";
    final String footer = "____________________________________________________________\n";

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.frontDesk();
    }

    public void frontDesk() {
        try {
            String greetings = header
                    + "\nHello! I'm Chu Chu \n"
                    + "What can I do for you ? \n"
                    + footer;
            System.out.println(greetings);

            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(rd);
            String command = null;
            String taskDescription = null;
            String[] taskDescriptionArr = null;
            String date = null;
            Parser parser = new Parser();
            TaskManagement manager = new TaskManagement();

            while (true) {

                command = br.readLine();
                taskDescription = parser.parseCommand(command);
                date = parser.parseDate();

                if (command.startsWith("bye")) {

                    break;

                } else if (command.startsWith("list")) {

                    System.out.print(header);
                    manager.list();
                    System.out.print(footer + "\n");

                } else if (command.startsWith("done")) {

                    taskDescriptionArr = taskDescription.split(" ");
                    Task t = manager.getTask(Integer.parseInt(taskDescriptionArr[1]));
                    System.out.println(header);
                    manager.markDone(t);
                    System.out.println(footer);

                } else {

                    if (command.startsWith("todo")) {

                        System.out.println(header);
                        System.out.println(manager.addTask(taskDescription, date, Task.Types.ToDo));
                        System.out.println(manager.reportTotal());
                        System.out.println(footer);

                    } else if (command.startsWith("deadline")) {

                        System.out.println(header);
                        System.out.println(manager.addTask(taskDescription, date, Task.Types.Deadline));
                        System.out.println(manager.reportTotal());
                        System.out.println(footer);

                    } else if (command.startsWith("event")) {

                        System.out.println(header);
                        System.out.println(manager.addTask(taskDescription, date, Task.Types.Event));
                        System.out.println(manager.reportTotal());
                        System.out.println(footer);

                    } else {
                        System.out.println("invalid input!");
                    }

                }
            }

            System.out.println(header);
            System.out.println("Bye. I hope you liked the service and hope to see you soon !");
            System.out.println(footer);

        } catch (IOException e) {

            System.out.print(e);
        }

    }
}
