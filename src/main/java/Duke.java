import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(new File("data/duke.txt").getAbsolutePath());
            FileWriter fw = new FileWriter(file, true);
            String data;
            String line = null;
            FileReader fr = null;
            BufferedReader br = null;

            if (!file.createNewFile()) {
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                while ((line = br.readLine()) != null) {
                    String[] task = line.split("\\|");
                    String taskType = task[0].trim();
                    boolean isDone = task[1].trim().equals("1");
                    if (taskType.equals("T")) {
                        Task todo = new Todo(task[2].trim());
                        if (isDone) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                    } else if (taskType.equals("D")) {
                        Task deadline = new Deadline(task[2].trim(), LocalDate.parse(task[3].trim(), DateTimeFormatter.ofPattern("MMM d yyyy")));
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                    } else {
                        Task event = new Event(task[2].trim(), LocalDate.parse(task[3].trim(), DateTimeFormatter.ofPattern("MMM d yyyy")));
                        if (isDone) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                    }
                }
                fr.close();
                br.close();
            }
            String input = sc.nextLine();
            while (!input.equals("bye")) {
                String[] inputs = input.split(" ", 2);
                try {
                    if (inputs[0].equals("list")) {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + 1 + "." + tasks.get(i));
                        }
                    } else if (inputs[0].equals("done")) {
                        try {
                            int taskNumber = Integer.parseInt(inputs[1]) - 1;
                            if (tasks.get(taskNumber).isTaskDone()) {
                                System.out.println("Task is already done!");
                                input = sc.nextLine();
                                continue;
                            }
                            tasks.get(taskNumber).markAsDone();
                            List<String> lines = new ArrayList<>();
                            int i = 0;
                            fr = new FileReader(file);
                            br = new BufferedReader(fr);
                            while ((line = br.readLine()) != null) {
                                if (i == taskNumber) {
                                    line = line.substring(0, 4) + "1" + line.substring(5);
                                }
                                lines.add(line + "\n");
                                i++;
                            }
                            fr.close();
                            br.close();
                            FileWriter fw2 = new FileWriter(file);
                            BufferedWriter output = new BufferedWriter(fw2);
                            for (String s : lines) {
                                output.write(s);
                            }
                            output.flush();
                            output.close();
                            System.out.println("Nice! I've marked this task as done:\n    " + tasks.get(taskNumber));
                        } catch (Exception e) {
                            throw new DukeException("☹ OOPS!!! Please provide a task number within range.");
                        }
                    } else if (inputs[0].equals("todo")) {
                        try {
                            Task todo = new Todo(inputs[1]);
                            tasks.add(todo);
                            data = "T | 0 | " + inputs[1] + "\n";
                            fw.write(data);
                            fw.flush();
                            System.out.println("Got it. I've added this task:\n    " +
                                    todo + "\nNow you have " + tasks.size() + " tasks in the list.");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                    } else if (inputs[0].equals("deadline")) {
                        try {
                            String[] taskDetails = inputs[1].split("/by ");
                            try {
                                LocalDate date = LocalDate.parse(taskDetails[1]);
                                Task deadline = new Deadline(taskDetails[0].trim(), date);
                                tasks.add(deadline);
                                data = "D | 0 | " + taskDetails[0].trim() + " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n";
                                fw.write(data);
                                fw.flush();
                                System.out.println("Got it. I've added this task:\n    " +
                                        deadline + "\nNow you have " + tasks.size() + " tasks in the list.");
                            } catch (Exception e) {
                                throw new DukeException("☹ OOPS!!! Please provide a date using '/by ' with the format yyyy-mm-dd.");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                    } else if (inputs[0].equals("event")) {
                        try {
                            String[] taskDetails = inputs[1].split("/on ");
                            try {
                                LocalDate date = LocalDate.parse(taskDetails[1]);
                                Task event = new Event(taskDetails[0].trim(), date);
                                tasks.add(event);
                                data = "E | 0 | " + taskDetails[0].trim() + " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n";
                                fw.write(data);
                                fw.flush();
                                System.out.println("Got it. I've added this task:\n    " +
                                        event + "\nNow you have " + tasks.size() + " tasks in the list.");
                            } catch (Exception e) {
                                throw new DukeException("☹ OOPS!!! Please provide a date using '/on ' with the format yyyy-mm-dd..");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                    } else if (inputs[0].equals("delete")) {
                        try {
                            int taskNumber = Integer.parseInt(inputs[1]) - 1;
                            Task deletedTask = tasks.remove(taskNumber);
                            List<String> lines = new ArrayList<>();
                            int i = 0;
                            fr = new FileReader(file);
                            br = new BufferedReader(fr);
                            while ((line = br.readLine()) != null) {
                                if (i == taskNumber) {
                                    continue;
                                }
                                lines.add(line + "\n");
                                i++;
                            }
                            fr.close();
                            br.close();
                            FileWriter fw2 = new FileWriter(file);
                            BufferedWriter output = new BufferedWriter(fw2);
                            for (String s : lines) {
                                output.write(s);
                            }
                            output.flush();
                            output.close();
                            System.out.println("Noted. I've removed this task: \n    " + deletedTask);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        } catch (Exception e) {
                            throw new DukeException("☹ OOPS!!! Please provide a task number within range.");
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
                input = sc.nextLine();
            }
            fw.close();
            System.out.println("Bye. Hope to see you again soon!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
