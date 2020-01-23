import java.util.*;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> lst = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hey! I'm Duke!");
        System.out.println("What can I help you with?");
        boolean done = false;
        while (done != true) {
            String output = sc.nextLine();
            if (output.equals("bye")) {
                System.out.println("Bye! See you around:)");
                done = true;
            } else if (output.equals("list")) {
                int counter = 1;
                for (int i = 0; i < lst.size(); i++) {
                    if (!lst.get(i).getType().equals("todo")) {
                        System.out.println(counter + ".[" + initialsT(lst.get(i).getType()) + "]" +"[" + lst.get(i).getStatusIcon() + "] " +lst.get(i).getD() + "(" + breakDate(lst.get(i).getTime() +")") +")");
                    } else {
                        System.out.println(counter + ".[" + initialsT(lst.get(i).getType()) + "]" +"[" + lst.get(i).getStatusIcon() + "] " +lst.get(i).getD() );
                    }
                    counter++;
                }
            } else if (output.split(" ")[0].equals("done")) {
                int space = Integer.parseInt(output.split(" ")[1]) -1;
                lst.get(space).doAct();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + initialsT(lst.get(space).getType()) + "] " + lst.get(space).getD());
            } else {
                String typeD = output.split(" ")[0];
                if (!typeD.equals("todo")) {
                    String[] temp = output.split("/");
                    String name = convertN(temp[0]);
                    String time = temp[1];
                    Task task = new Task(name,typeD);
                    task.setTime(time);
                    lst.add(task);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I added this task: ");
                    System.out.println("  [" + initialsT(typeD) + "][" + task.getStatusIcon() + "] "+ name + "(" + breakDate(time) +")");
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    String name = convertN(output);
                    Task task = new Task(name,typeD);
                    lst.add(task);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I added this task: ");
                    System.out.println("[" + initialsT(typeD) + "][" + task.getStatusIcon() + "] " + name);
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
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
}

