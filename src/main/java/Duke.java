import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    enum TaskList {
        TODO, DEADLINE, EVENT
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "dukeStorage";
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        ArrayList<Task> todo = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            todo = (ArrayList)in.readObject();
            in.close();
            file.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                    " is caught");
        }
        TaskList taskList;
        try {
            while (true) {
                String readtext = scanner.nextLine();
                if (readtext.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (readtext.equals("list")) {
                    if(todo.size() == 0) {
                        throw new EmptyListException();
                    } else{
                        System.out.println("Here are the task in your list:");
                        for (int i = 0; i < todo.size(); i++) {
                            System.out.println((i + 1) + ". " + todo.get(i).toString());
                        }
                    }
                } else {
                    if (readtext.contains("done")) {
                        String num = readtext.split(" ")[1];
                        System.out.println("Nice! I've marked this task as done:\n");
                        todo.get(Integer.parseInt(num) - 1).markAsDone();
                        System.out.println(num + ". " + "[" + todo.get(Integer.parseInt(num) - 1).getStatusIcon() + "] " + todo.get(Integer.parseInt(num) - 1).getDescription());
                    } else if(readtext.contains("delete")) {
                        String num = readtext.split(" ")[1];
                        System.out.println("Noted! I've removed this task:\n");
                        System.out.println(num + ". " + "[" + todo.get(Integer.parseInt(num) - 1).getStatusIcon() + "] " + todo.get(Integer.parseInt(num) - 1).getDescription());
                        todo.remove(Integer.parseInt(num) - 1);
                        try {
                            FileOutputStream file = new FileOutputStream(fileName);
                            ObjectOutputStream out = new ObjectOutputStream(file);
                            out.writeObject(todo);
                            out.close();
                            out.flush();
                        } catch (IOException e) {

                        }
                        System.out.println("Now you have " + todo.size() + " tasks in the list");
                    } else {
                        Task t;
                        String spli;
                        String des;
                        String fin;
                        String comm = readtext.split(" ")[0];

                        System.out.println(comm);
                        taskList = TaskList.valueOf(comm.toUpperCase());
                        switch (taskList) {
                            case TODO:
                                spli = readtext.split("todo")[1];
                                t = new Todo(spli);
                                todo.add(t);
                                try {
                                    //saving file as object
                                    FileOutputStream file = new FileOutputStream(fileName);
                                    ObjectOutputStream out = new ObjectOutputStream(file);
                                    out.writeObject(todo);
                                    out.close();

                                } catch (IOException e) {
                                    System.out.println(e);
                                }
                                System.out.println("Got it. I've added this task");
                                System.out.println(t.toString());
                                System.out.println("Now you have " + todo.size() + " tasks in the list");
                                break;
                            case DEADLINE:
                                spli = readtext.split("deadline")[1];
                                des = spli.split("/by")[0];
                                fin = spli.split("/by")[1];
                                t = new Deadline(des, fin);
                                todo.add(t);
                                try {
                                    //saving file as object
                                    FileOutputStream file = new FileOutputStream(fileName);
                                    ObjectOutputStream out = new ObjectOutputStream(file);
                                    out.writeObject(todo);
                                    out.close();

                                } catch (IOException e) {
                                    System.out.println(e);
                                }
                                System.out.println("Got it. I've added this task");
                                System.out.println(t.toString());
                                System.out.println("Now you have " + todo.size() + " tasks in the list");
                                break;
                            case EVENT:
                                spli = readtext.split("event")[1];
                                des = spli.split("/at")[0];
                                fin = spli.split("/at")[1];
                                t = new Event(des, fin);
                                todo.add(t);
                                try {
                                    //saving file as object
                                    FileOutputStream file = new FileOutputStream(fileName);
                                    ObjectOutputStream out = new ObjectOutputStream(file);
                                    out.writeObject(todo);
                                    out.close();

                                } catch (IOException e) {
                                    System.out.println(e);
                                }
                                System.out.println("Got it. I've added this task");
                                System.out.println(t.toString());
                                System.out.println("Now you have " + todo.size() + " tasks in the list");
                                break;
                            default:
                                throw new UnknownCommandException();
                            }
                        }
                    }
                }
        }catch (DukeException ex){
            System.out.print(ex);
        }
    }
}
