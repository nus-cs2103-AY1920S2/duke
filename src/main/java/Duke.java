import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileOutputStream;

public class Duke {
    static String space = "     ";
    static String line = space + "____________________________________________________________";
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = line + "\n" + space + " Hello! I'm Duke\n" + space + " What can I do for you?\n" + line + "\n";
        System.out.print(greeting);
        Scanner sc = new Scanner(System.in);
        processlist(sc);
    }

    private static void processlist(Scanner sc) throws IllegalInstructionException, NumberFormatException, IOException {
        List list = new List();
        loadTxt(list);
        while (!sc.hasNext("bye")) {
            try {
                String temp = sc.nextLine();
                String[] tmp = temp.split(" ");
                String task = "";
                for (int i = 1; i < tmp.length; i++) {
                    task += tmp[i];
                    if (i != tmp.length - 1) {
                        task += " ";
                    }
                }
                if (temp.equals("list")) {
                    System.out.println(line + "\n" + list);
                } else if (tmp[0].equals("done") || tmp[0].equals("delete")) {
                    if (tmp.length < 2) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The index of a task cannot be empty.\n"
                                + line);
                    } else if (Integer.parseInt(tmp[1]) > list.items.size()) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The index of a task is out of range.\n"
                                + line);
                    }
                    int index = Integer.parseInt(tmp[1]);
                    if (tmp[0].equals("done")) {
                        list.items.get(index - 1).markDone();
                        updateTxt(list.items.get(index - 1).replace(), list.items.get(index - 1).toString());
                    } else {
                        updateTxt(list.items.get(index - 1).toString(), "");
                        list.delete(index - 1);
                    }
                } else if (tmp[0].equals("todo")) {
                    if (task.equals("")) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                                + line);
                    }
                    Todo todo = new Todo(task);
                    list.addItem(todo);
                    addTxt(todo.toString());
                } else if (tmp[0].equals("event")) {
                    if (task.equals("")) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The description of a event cannot be empty.\n"
                                + line);
                    }
                    String[] e = task.split(" /at ");
                    if (e.length < 2) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The time of a event cannot be empty.\n"
                                + line);
                    }
                    Event event = new Event(e[0], e[1]);
                    list.addItem(event);
                    addTxt(event.toString());
                } else if (tmp[0].equals("deadline")) {
                    if (task.equals("")) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The description of a deadline cannot be empty.\n"
                                + line);
                    }
                    String[] d = task.split(" /by ");
                    if (d.length < 2) {
                        throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! The time of a deadline cannot be empty.\n"
                                + line);
                    }
                    Deadline ddl = new Deadline(d[0], d[1]);
                    list.addItem(ddl);
                    addTxt(ddl.toString());
                }  else {
                    throw new IllegalInstructionException(line + "\n" + space + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                            + line);
                }
            } catch (IllegalInstructionException e) {
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println(line + "\n" + space + "☹ OOPS!!! The format of index is wrong.\n"
                        + line);
            } catch (IOException e) {
                System.err.println("Incorrect IO format");
            }
        }
        String bye = line + "\n" + space + " Bye. Hope to see you again soon!\n" + line;
        System.out.print(bye);
        return;
    }

    private static void updateTxt(String prev, String now) throws IOException {
        try {
            BufferedReader file = new BufferedReader(new FileReader("data/output.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();
            inputStr = inputStr.replace(prev, now);
            FileOutputStream fileOut = new FileOutputStream("data/output.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (IOException e) {
            System.err.println(space + "Incorrect IO format");
        }
    }

    public static void addTxt(String s) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("data/output.txt", true);
            fileWriter.append(s);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(space + "Incorrect IO format");
        }
    }

    public static void loadTxt(List list) throws IOException {
        try {
            BufferedReader file = new BufferedReader(new FileReader("data/output.txt"));
            String line;
            while ((line = file.readLine()) != null) {
                line = line.trim();
                boolean done;
                String[] splitted;
                String[] tmp;
                splitted = line.split(" ", 2);
                tmp = splitted[1].split(" ", 2);
                if (line.charAt(4) == '✓') {
                    done = true;
                } else {
                    done = false;
                }
                if (line.charAt(1) == 'E') {
                    tmp[1] = tmp[1].replaceAll("\\(at: ","");
                    tmp[1] = tmp[1].replaceAll("\\)","");
                    list.addItem(new Event(tmp[0], tmp[1]));
                } else if (line.charAt(1) == 'D') {
                    tmp[1] = tmp[1].replaceAll("\\(by: ","");
                    tmp[1] = tmp[1].replaceAll("\\)","");
                    list.addItem(new Deadline(tmp[0], tmp[1]));
                } else {
                    list.addItem(new Todo(splitted[1], done));
                }
            }
            file.close();
        } catch (IOException e) {
            System.err.println(space + "Incorrect IO format.");
        } catch (Exception e) {
            System.err.println(space + "Unable to load past data.");
        }
    }
}
