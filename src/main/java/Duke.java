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
            String input = null;
            String[] taskDescriptionArr = null;
            String date = null;
            Parser parser = new Parser();
            TaskManagement manager = new TaskManagement("../../../DataFile.txt");
            ErrorHandler handler = new ErrorHandler();

            manager.loadFile();

            while (true) {

                input = br.readLine();
                taskDescriptionArr = parser.parseUserInput(input);

                if(taskDescriptionArr[1].equals("EmptyDescription") || taskDescriptionArr[1].equals("EmptyDate")
                        || taskDescriptionArr[0].equals("MissingTaskNumber")) {
                    System.out.print(header);

                    if(taskDescriptionArr[0].equals("MissingTaskNumber")) {

                        System.out.println(handler.handleError(taskDescriptionArr[0], null));
                        System.out.println(footer);
                        continue;
                    }

                    if(taskDescriptionArr[0].equals("todo")) {

                        System.out.println(handler.handleError(taskDescriptionArr[1], Task.Types.ToDo));
                        System.out.println(footer);
                        continue;

                    } else if (taskDescriptionArr[0].equals("deadline")) {

                        System.out.println(handler.handleError(taskDescriptionArr[1], Task.Types.Deadline));
                        System.out.println(footer);
                        continue;

                    } else if(taskDescriptionArr[0].equals("event")) {

                        System.out.println(handler.handleError(taskDescriptionArr[1], Task.Types.Event));
                        System.out.println(footer);
                        continue;

                    }

                }


                if (taskDescriptionArr[0].equals("bye")) {

                    System.out.println(header);
                    System.out.println("Bye. I hope you liked the service and hope to see you soon !");
                    System.out.println(footer);
                    break;

                } else if (taskDescriptionArr[0].equals("list")) {

                    System.out.print(header);
                    manager.list();
                    System.out.print(footer + "\n");

                } else if (taskDescriptionArr[0].equals("done")) {

                    Task t = manager.getTask(Integer.parseInt(taskDescriptionArr[1]));
                    System.out.println(header);
                    System.out.println(manager.markDone(t));
                    System.out.println(footer);

                } else if (taskDescriptionArr[0].equals("delete")) {

                    System.out.println(header);
                    System.out.println(manager.deleteTask(Integer.parseInt(taskDescriptionArr[1])));
                    System.out.println(manager.reportTotal());
                    System.out.println(footer);

                } else {

                    if (taskDescriptionArr[0].equals("todo")) {

                        System.out.println(header);
                        System.out.println(manager.addTask(taskDescriptionArr[1], taskDescriptionArr[2], Task.Types.ToDo));
                        System.out.println(manager.reportTotal());
                        System.out.println(footer);

                    } else if (taskDescriptionArr[0].equals("deadline")) {

                        System.out.println(header);
                        System.out.println(manager.addTask(taskDescriptionArr[1], taskDescriptionArr[2], Task.Types.Deadline));
                        System.out.println(manager.reportTotal());
                        System.out.println(footer);

                    } else if (taskDescriptionArr[0].equals("event")) {

                        System.out.println(header);
                        System.out.println(manager.addTask(taskDescriptionArr[1], taskDescriptionArr[2], Task.Types.Event));
                        System.out.println(manager.reportTotal());
                        System.out.println(footer);

                    } else {

                        System.out.println(header);
                        System.out.println(handler.handleError("InvalidInput", null));
                        System.out.println(footer);
                        continue;

                    }

                }
            }



        } catch (IOException e) {

            System.out.print(e);
        }

    }
}
