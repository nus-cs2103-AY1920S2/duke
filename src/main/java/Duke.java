
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) {
        int tasks = 0;
        String directory = System.getProperty("user.dir");
        String fileName = "filedata.txt";
        Path path = Paths.get(directory, fileName);
        ArrayList<Task> lst = new ArrayList<Task>();
        try {
            List<String> input = Files.readAllLines(path);
            for (int i = 0; i < input.size(); i++) {
                tasks++;
                String[] temp = input.get(i).split("\\|");
                System.out.println(Arrays.toString(temp));
                //is there a way to add different child class instances without 3 diff if-else statement
                if (temp[0].equals("T")) {
                    Todo todo = new Todo(temp[2]);
                    if (todo.getStatusIcon().equals("Y")) {
                        todo.doAct();
                        tasks--;
                    } 
                    lst.add(todo);
                } else if (temp[0].equals("D")){
                    String[] temp1 = temp[3].split(" ");
                    String[] temp2 = temp1[1].split("/");
                    int year = Integer.parseInt(temp2[2]);
                    int month = Integer.parseInt(temp2[1]);
                    int day = Integer.parseInt(temp2[0]);
                    int time = Integer.parseInt(temp1[2]);
                    LocalDate localdate = LocalDate.of(year, month, day);
                    Deadline deadline = new Deadline(temp[2],localdate);
                    if (deadline.getStatusIcon().equals("Y")) {
                        deadline.doAct();
                        tasks--;
                    }
                    deadline.setTime(temp[3]);
                    lst.add(deadline);   
                } else {
                    String[] temp1 = temp[3].split(" ");
                    String[] temp2 = temp1[1].split("/");
                    int year = Integer.parseInt(temp2[2]);
                    int month = Integer.parseInt(temp2[1]);
                    int day = Integer.parseInt(temp2[0]);
                    int time = Integer.parseInt(temp1[2]);
                    LocalDate localdate = LocalDate.of(year, month, day);
                    Event event = new Event(temp[2],localdate);
                    if (event.getStatusIcon().equals("Y")) {
                        event.doAct();
                        tasks--;
                    }
                    event.setTime(temp[3]);
                    lst.add(event); 
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //input file stuff into database
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Hey! I'm Duke!");
        System.out.println("What can I help you with?");
        boolean done = false;
        while (done != true) {
            String output = sc.nextLine();
            if (validationInput(output)) {
                if (output.equals("bye")) {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                        for (Task task:lst) {
                            if (task instanceof Todo) {
                                writer.write("T" + "|" + task.getStatusIcon() + "|" + task.getD());
                                writer.newLine();
                            } else {
                                String initials = "";
                                if (task instanceof Deadline) {
                                    initials = "D";
                                } else {
                                    initials = "E";
                                }
                                writer.write(initials + "|" + task.getStatusIcon() + "|" + task.getD() + "|" + task.getTime());
                                writer.newLine();
                            }
                            
                        }     
                        writer.close();   
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Bye! See you around:)");
                    done = true;
                } else if (output.equals("list")) {
                    int counter = 1;
                    System.out.println("    ____________________________________________________________");
                    for (int i = 0; i < lst.size(); i++) {
                        if (!(lst.get(i) instanceof Todo)) {
                            System.out.println("     " + lst.get(i));
                        } else {
                            System.out.println("     " + lst.get(i));
                        }
                        counter++;
                    }
                    System.out.println("    ____________________________________________________________");
                } else if (output.split(" ")[0].equals("done")) {
                    int space = Integer.parseInt(output.split(" ")[1]) -1;
                    lst.get(space).doAct();
                    tasks--;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("      Nice! I've marked this task as done:");
                    System.out.println("     " + lst.get(space));
                    System.out.println("      Now you have " + tasks + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                } else if (output.split(" ")[0].equals("delete")) {
                    int i = Integer.parseInt(output.split(" ")[1]) - 1;
                    Task task = lst.get(i);
                    lst.remove(i);
                    if (!task.getStatus()) {
                        tasks--;
                    }
                    if (task instanceof Todo) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      Noted.I've removed this task:");
                        System.out.println("     " + task);
                        System.out.println("      Now you have " + tasks + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      Noted.I've removed this task:");
                        System.out.println("     " + task);
                        System.out.println("      Now you have " + tasks + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    }
                } else {
                    tasks++;
                    String typeD = output.split(" ")[0];
                    if (!(typeD.equals("todo"))) {
                        int slash = output.indexOf("/");
                        String[] datetime = output.substring(slash + 4,output.length()).split(" "); //string containing date and time on
                        String ddate = datetime[0];
                        String time = datetime[1];
                        String name = output.substring(0,slash).split(" ")[1];
                        //System.out.println(temp[1])
                        LocalDate date = setDate(ddate);
                        if (typeD.equals("deadline")) {
                            Deadline task = new Deadline(name,date);
                            task.setTime(time);
                            lst.add(task);
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    ____________________________________________________________");
                            System.out.println("      Got it. I added this task: ");
                            System.out.println("     " + task);
                            System.out.println("      Now you have " + tasks + " tasks in the list.");
                            System.out.println("    ____________________________________________________________");
                        } else {
                            Event task = new Event(name,date);
                            task.setTime(time);
                            lst.add(task);
                            System.out.println("    ____________________________________________________________");
                            System.out.println("      Got it. I added this task: ");
                            System.out.println("     " + task);
                            System.out.println("      Now you have " + tasks + " tasks in the list.");
                            System.out.println("    ____________________________________________________________");
                        }
                    } else {
                        String name = convertN(output);
                        Todo task = new Todo(name);
                        lst.add(task);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      Got it. I added this task: ");
                        System.out.println("     " + task);
                        System.out.println("      Now you have " + tasks + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    }
                }
                  
            }
        }
        sc.close();
    }

    public static String convertN(String name) {
        String[] temp = name.split(" ");
        String result = "";
        for (int i = 1; i < temp.length; i++) {
            result += (temp[i] + " ");
        }
        return result;
    }

    public static String getInitials(String type) {
        if (type.equals("todo")) {
            return "T";
        } else if (type.equals("deadline")) {
            return "D";
        } else {
            return "E";
        }
    }

    //OBSOLETE
    public static String breakDate(String time) {
        String[] temp = time.split(" ");
        String result = temp[0] + ": ";
        for (int i = 1; i < temp.length; i++) {
            if (i == temp.length - 1) {
                result += (temp[i]);
            } else {
                result += (temp[i] + " ");
            }
        }
        return result;
    }

    public static LocalDate setDate(String date1) {
        System.out.println(date1);
        String[] date = date1.split("/");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);
        LocalDate localdate = LocalDate.of(year, month, day);
        return localdate;
    }

    public static boolean validationInput(String input) {
        String[] temp = input.split(" ");
        if (input.length() == 0) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     ☹ OOPS!!! The input cannot be empty. Please enter something!");
            System.out.println("    ____________________________________________________________");
            return false;
        } else if (temp.length == 1) {
            if (input.equals("bye") || input.equals("list") || input.equals("delete") || input.equals("done")) {
                return true;
            }  else {
                if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     ☹ OOPS!!! The description of a " + input + " cannot be empty.");
                    System.out.println("    ____________________________________________________________");
                } else {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("    ____________________________________________________________");
                }
                return false;
            }
        }
        return true;
    }
}

