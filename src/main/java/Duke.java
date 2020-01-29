import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Duke{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String mesInput = "";
        Task [] tasks = new Task[100];
        ArrayList<Task> arrTask = new ArrayList<Task>();
        int index = 0; // to store inside array

        while(!mesInput.equalsIgnoreCase("bye")){

            System.out.println("  -----------");
            mesInput = sc.nextLine();
            String [] s = new String[2];

            // store task
            if(mesInput.contains("todo") && !mesInput.equalsIgnoreCase("todo")){
                s = mesInput.split("todo ");
                arrTask.add(new Task(s[1]));
                System.out.println("   Got it. I've added this task:");
                System.out.println("   "+arrTask.get(index).toString());
                index = index + 1;
                System.out.println("     Now you have "+index+" tasks in the list");
                createFile("todo",s[1]);
            }else if(mesInput.contains("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + arrTask.get(i).toString());
                }
            }else if (mesInput.contains("deadline") && !mesInput.equalsIgnoreCase("deadline")) {
                s = mesInput.split("deadline ");
                String [] temp = s[1].split("/");
                String taskName = temp[0];
                String date = temp[1];
                System.out.println("   Got it. I've added this task:");
                arrTask.add(new Deadline(taskName,date));
                System.out.println("     "+arrTask.get(index).toString());
                index = index + 1;
                System.out.println("     Now you have "+index+" tasks in the list.");
                createFile("deadline",taskName+date);
            }else if (mesInput.contains("event") && !mesInput.equalsIgnoreCase("event")) {
                s = mesInput.split("event ");
                String[] temp = s[1].split("/");
                String taskName = temp[0];
                String date = temp[1];
                System.out.println("   Got it. I've added this task:");
                arrTask.add(new Event(taskName, date));
                System.out.println("     " + arrTask.get(index).toString());
                index = index + 1;
                System.out.println("     Now you have " + index + " tasks in the list");
                createFile("event",taskName+date);
            }else if (mesInput.contains("done") && !mesInput.equalsIgnoreCase("done")){
                String [] temp = mesInput.split(" ");
                int arrPos = Integer.parseInt(temp[1]);
                arrTask.get(arrPos-1).setDone();
                System.out.println("  -------------");
                System.out.println("    Nice! I've marked this task as done: ");
                System.out.println("    ["+arrTask.get(arrPos-1).getStatusIcon()+"] "+arrTask.get(arrPos-1).getDescription());
                System.out.println("  -------------");
            }else if(mesInput.contains("delete") && !mesInput.equalsIgnoreCase("delete")){
                String [] temp = mesInput.split(" ");
                int arrPos = Integer.parseInt(temp[1]);
                System.out.println("  -------------");
                System.out.println("    Noted. I've removed this task: ");
                System.out.println("    "+arrTask.get(arrPos-1).toString());
                arrTask.remove(arrPos-1);
                index = index - 1 ;
                System.out.println("     Now you have " + index + " tasks in the list");
            }else if (mesInput.equalsIgnoreCase("todo")){
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

    public static void createFile(String command,String task) throws IOException{
        String filePath = "duke.txt";
        File f = new File(filePath);
        FileWriter fw = new FileWriter(filePath);
        fw.write(command+" || "+task);
        fw.close();
        try {
            boolean result = f.createNewFile();
            Scanner s = new Scanner(f);
            System.out.println("Load data from file ++++ ");
            while(s.hasNext()){
                System.out.println(s.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void printFileContents(String filePath) throws FileNotFoundException {
//
//    }
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
