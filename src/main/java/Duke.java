import java.util.*;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> lst = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hey! I'm Duke!");
        System.out.println("What can I help you with?");
        boolean done = false;
        int tasks = 0;
        while (done != true) {
            String output = sc.nextLine();
            if (validationInput(output)) {
                if (output.equals("bye")) {
                    System.out.println("Bye! See you around:)");
                    done = true;
                } else if (output.equals("list")) {
                    int counter = 1;
                    System.out.println("    ____________________________________________________________");
                    for (int i = 0; i < lst.size(); i++) {
                        if (!lst.get(i).getType().equals("todo")) {
                            System.out.println("      " + counter + ".[" + initialsT(lst.get(i).getType()) + "]" +"[" + lst.get(i).getStatusIcon() + "] " +lst.get(i).getD() + "(" + breakDate(lst.get(i).getTime() +")") +")");
                        } else {
                            System.out.println("      " + counter + ".[" + initialsT(lst.get(i).getType()) + "]" +"[" + lst.get(i).getStatusIcon() + "] " +lst.get(i).getD() );
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
                    System.out.println("        [" + initialsT(lst.get(space).getType()) + "] " + lst.get(space).getD());
                    System.out.println("      Now you have " + tasks + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                } else if (output.split(" ")[0].equals("delete")) {
                    int i = Integer.parseInt(output.split(" ")[1]) - 1;
                    Task task = lst.get(i);
                    lst.remove(i);
                    tasks--;
                    if (task.getType().equals("todo")) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      Noted.I've removed this task:");
                        System.out.println("        [" + initialsT(task.getType()) + "][" + task.getStatusIcon() + "] "+ task.getD());
                        System.out.println("      Now you have " + tasks + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      Noted.I've removed this task:");
                        System.out.println("        [" + initialsT(task.getType()) + "][" + task.getStatusIcon() + "] "+ task.getD() + "(" + breakDate(task.getTime()) +")");
                        System.out.println("      Now you have " + tasks + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    }
                } else {
                    tasks++;
                    String typeD = output.split(" ")[0];
                    if (!typeD.equals("todo")) {
                        String[] temp = output.split("/");
                        String name = convertN(temp[0]);
                        String time = temp[1];
                        Task task = new Task(name,typeD);
                        task.setTime(time);
                        lst.add(task);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      Got it. I added this task: ");
                        System.out.println("        [" + initialsT(typeD) + "][" + task.getStatusIcon() + "] "+ name + "(" + breakDate(time) +")");
                        System.out.println("      Now you have " + tasks + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        String name = convertN(output);
                        Task task = new Task(name,typeD);
                        lst.add(task);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("      Got it. I added this task: ");
                        System.out.println("        [" + initialsT(typeD) + "][" + task.getStatusIcon() + "] " + name);
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

    public static String initialsT(String type) {
        if (type.equals("todo")) {
            return "T";
        } else if (type.equals("deadline")) {
            return "D";
        } else {
            return "E";
        }
    }

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

