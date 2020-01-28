import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
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

        // Instantiate variables
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);

        // Read the data\duke.txt file
        String homeDir = System.getProperty("user.home");
        String fileName = "duke.txt";
        Path path = Paths.get(homeDir, "duke", "data", fileName);

        try {
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                String curLine = lines.get(i);
                String[] components = curLine.split(" , ");

                if (components.length < 3) {
                    continue;
                }
                if (components[0].equals("T")) {
                    Task tTask = new ToDo(" " + components[2]);
                    tTask.setStatus(components[1]);
                    tasks.add(tTask);
                } else if (components[0].equals("D")) {

                    Task dTask = new Deadline(" " + components[2], LocalDate.parse(components[3]));
                    dTask.setStatus(components[1]);
                    tasks.add(dTask);
                } else if (components[0].equals("E")) {
                    Task eTask = new Event(" " + components[2], LocalDate.parse(components[3]));
                    eTask.setStatus(components[1]);
                    tasks.add(eTask);
                } else {
                    System.err.print("Wrong File Structure");
                }
            }
        } catch (IOException e) {
            System.err.print("IOException occured.");
        }

        while (sc.hasNextLine()) {
            try {
                String line = sc.nextLine();

                if (line.equals("bye")) {
                    System.out.print(exit);

                    // Write current tasks to data\duke.txt
                    String output = "";
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        output += t.writeToFile() + "\n";
                    }

                    try {
                        BufferedWriter writer = Files.newBufferedWriter(path);
                        writer.write(output);
                        writer.flush();
                    } catch (IOException e) {
                        System.err.print("IOException occured.");
                    }
                    System.exit(0);


                } else if (line.equals("list")) {
                    System.out.println(borderDesign + "     Here are the tasks in your list:");
                    for (int i = 1; i <= tasks.size(); i++) {
                        String item = "     " + i + "." + tasks.get(i - 1);
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
                        if (index > tasks.size() - 1) {
                            throw new DEIndex("done");
                        }

                        tasks.get(index).markDone();
                        String done = borderDesign
                                + "     Nice! I've marked this task as done: \n"
                                + "       " + tasks.get(index) + "\n"
                                + borderDesign;
                        System.out.println(done);

                    } else if (comArs[0].equals("delete")) {
                        if (comArs.length == 1) {
                            throw new DEIndex("delete");
                        }

                        int index = Integer.parseInt(comArs[1]) - 1;
                        if (index > tasks.size() - 1) {
                            throw new DEIndex("delete");
                        }

                        Task t = tasks.get(index);
                        tasks.remove(index);
                        String del = borderDesign
                                + "     Noted. I've removed this task: \n"
                                + "       " + t + "\n"
                                + "     Now you have " + tasks.size() + " tasks in the list.\n"
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
                            LocalDate date = LocalDate.parse(msgDate[1]);
                            t = new Event(msgDate[0], date);

                        } else if (comArs[0].equals("deadline")){
                            String details = line.substring(8, line.length());
                            if (details.isBlank()) {
                                throw new DEDescription("deadline");
                            }

                            String[] msgDate = details.split(" /by ", 2);
                            if (msgDate.length == 1) {
                                throw new DEDate("deadline");
                            }
                            LocalDate date = LocalDate.parse(msgDate[1]);
                            t = new Deadline(msgDate[0], date);

                        } else { // invalid command
                            DukeException err = new DECommand();
                            throw err;
                        }

                        tasks.add(t);

                        String tnew = borderDesign
                                + "     Got it. I've added this task:\n"
                                + "       " + t + "\n"
                                + "     Now you have " + tasks.size() + " tasks in the list.\n"
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
