import java.util.*;


public class Duke{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String mesInput = "";
        Task [] tasks = new Task[100];
        int index = 0; // to store inside array

        while(!mesInput.equalsIgnoreCase("bye")){
            System.out.println("  -----------");
            mesInput = sc.nextLine();
            String [] s = new String[2];

            // store task
            if(mesInput.contains("todo") && !mesInput.equalsIgnoreCase("todo")){
                s = mesInput.split("todo ");
                tasks[index] = new Task(s[1]);
                System.out.println("   Got it. I've added this task:");
                System.out.println("   "+tasks[index].toString());
                index = index + 1;
                System.out.println("     Now you have "+index+" tasks in the list");
            }else if(mesInput.contains("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + tasks[i].toString());
                }
            }else if (mesInput.contains("deadline") && !mesInput.equalsIgnoreCase("deadline")) {
                s = mesInput.split("deadline ");
                String [] temp = s[1].split("/");
                String taskName = temp[0];
                String date = temp[1];
                System.out.println("   Got it. I've added this task:");
                tasks[index] = new Deadline(taskName,date);
                System.out.println("     "+tasks[index].toString());
                index = index + 1;
                System.out.println("     Now you have "+index+" tasks in the list.");
            }else if (mesInput.contains("event") && !mesInput.equalsIgnoreCase("event")) {
                s = mesInput.split("event ");
                String[] temp = s[1].split("/");
                String taskName = temp[0];
                String date = temp[1];
                System.out.println("   Got it. I've added this task:");
                tasks[index] = new Event(taskName, date);
                System.out.println("     " + tasks[index].toString());
                index = index + 1;
                System.out.println("     Now you have " + index + " tasks in the list");
            }else if (mesInput.contains("done") && !mesInput.equalsIgnoreCase("done")){
                String [] temp = mesInput.split(" ");
                int arrPos = Integer.parseInt(temp[1]);
                tasks[arrPos-1].setDone();
                System.out.println("  -------------");
                System.out.println("    Nice! I've marked this task as done: ");
                System.out.println("    ["+tasks[arrPos-1].getStatusIcon()+"] "+tasks[arrPos-1].getDescription());
                System.out.println("  -------------");
            }else if(mesInput.equalsIgnoreCase("todo")){
                try {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            }else if(mesInput.equalsIgnoreCase("deadline")){
                try {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a deadlines cannot be empty.");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            }else if(mesInput.equalsIgnoreCase("event")){
                try {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a event cannot be empty.");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    throw new IncorrectInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ");
                } catch (IncorrectInputException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("  --------------");
        System.out.println("     Bye. Hope to see you again");
        System.out.println("  --------------");
    }
}


class Deadline extends Task {
    private String by ;
    public Deadline(String description, String by){
        super(description);
        this.by= by;
    }
    @Override
    public String toString(){
        return "[D][" +super.getStatusIcon()+ "] "+ super.getDescription() + "(" + by.substring(0,2) + ": " + by.substring(3,by.length())+")" ;
    }
}

class Event extends Task {
    private String at ;
    public Event(String description, String at){
        super(description);
        this.at= at;
    }
    @Override
    public String toString(){
        return "[E][" + super.getStatusIcon()+ "] "+ super.getDescription() + "(" + at.substring(0,2) + ": " + at.substring(3,at.length())+ ")";
    }
}
