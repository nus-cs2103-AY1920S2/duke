import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        String borderDesign = "    ____________________________________________________________\n";
        String greet = borderDesign + logo
                + "\n     Hello! I'm Duke\n     What can I do for you?\n"
                + borderDesign;
        System.out.println(greet);

        String exit = borderDesign
                + "     Bye. Hope to see you again soon!\n"
                + borderDesign;

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<Task>();
        boolean first = true;

        while (sc.hasNextLine()) {
            try {
                String line = sc.nextLine();

                if (line.equals("bye")) {
                    System.out.print(exit);
                    System.exit(0);

                } else if (line.equals("list")) {
                    System.out.println(borderDesign + "     Here are the tasks in your list:");
                    for (int i = 1; i <= lst.size(); i++) {
                        String item = "     " + i + "." + lst.get(i - 1);
                        System.out.println(item);
                    }
                    System.out.println(borderDesign);

                } else {
                    // Search for a command
                    String[] comArs = line.split("\\s", 2);
                    if (comArs[0].equals("done")) {
                        if (comArs.length == 1) {
                            throw new DEIndex("done");
                        }

                        int index = Integer.parseInt(comArs[1]) - 1;
                        if (index > lst.size() - 1) {
                            throw new DEIndex("done");
                        }

                        lst.get(index).markDone();
                        String done = borderDesign
                                + "     Nice! I've marked this task as done: \n"
                                + "       " + lst.get(index) + "\n"
                                + borderDesign;
                        System.out.println(done);

                    } else if (comArs[0].equals("delete")) {
                        if (comArs.length == 1) {
                            throw new DEIndex("delete");
                        }

                        int index = Integer.parseInt(comArs[1]) - 1;
                        if (index > lst.size() - 1) {
                            throw new DEIndex("delete");
                        }

                        Task t = lst.get(index);
                        lst.remove(index);
                        String del = borderDesign
                                + "     Noted. I've removed this task: \n"
                                + "       " + t + "\n"
                                + "     Now you have " + lst.size() + " tasks in the list.\n"
                                + borderDesign;
                        System.out.println(del);

                    } else { // add a task
                        Task t = new Task("");
                        if (comArs[0].equals("todo")) {
                            String details = line.substring(4, line.length());
                            if (details.isBlank()) {
                                throw new DEDescription("todo");
                            }
                            t = new ToDo(details);

                        } else if (comArs[0].equals("event")) {
                            String details = line.substring(5, line.length());
                            if (details.isBlank()) {
                                throw new DEDescription("event");
                            }

                            String[] msgDate = details.split(" /at ", 2);
                            if (msgDate.length == 1) {
                                throw new DEDate("event");
                            }

                            t = new Event(msgDate[0], msgDate[1]);

                        } else if (comArs[0].equals("deadline")){
                            String details = line.substring(8, line.length());
                            if (details.isBlank()) {
                                throw new DEDescription("deadline");
                            }

                            String[] msgDate = details.split(" /by ", 2);
                            if (msgDate.length == 1) {
                                throw new DEDate("deadline");
                            }
                            t = new Deadline(msgDate[0], msgDate[1]);

                        } else { // invalid command
                            DukeException err = new DECommand();
                            throw err;
                        }

                        lst.add(t);

                        String tnew = borderDesign
                                + "     Got it. I've added this task:\n"
                                + "       " + t + "\n"
                                + "     Now you have " + lst.size() + " tasks in the list.\n"
                                + borderDesign;
                        System.out.println(tnew);
                    }
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}
