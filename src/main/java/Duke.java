import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList= new ArrayList<Task>();
        //String[] taskDes = new String[100];
        int x = 1;
        int y = 0;

        try {
            loadData(taskList);
        }
        catch (FileNotFoundException e) {
            System.out.println("File could not be read!");
        }


        while(true) {
            String in = sc.nextLine();
            Task T = new Task(in);

            if(in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else if(in.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                x = 1;
                for(Task A: taskList) {
                    System.out.printf("%d.%s\n", x, A);
                    x++;
                }
            }

            else if(in.substring(0,Math.min(in.length(), 6)).equals("delete")) {
                int toDel = Integer.parseInt(in.substring(7));
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + taskList.get(toDel - 1));
                taskList.remove(toDel - 1);
                System.out.printf("Now you have %s tasks in the list\n", taskList.size());
                try {
                    saveData(taskList);
                }
                catch (IOException e){
                    System.out.println("An error occurred while saving, please try again!");
                }
            }

            else if(in.substring(0,Math.min(in.length(), 4)).equals("done")) {
                int index = Integer.parseInt(in.substring(5,in.length()));
                taskList.get(index - 1).doTask();
                System.out.println("Nice, I've marked this task as done:");
                //System.out.printf("[%s] %s\n", taskList.get(index - 1).getStatusIcon(), taskList.get(index - 1).getDescription());
                System.out.println("  " + taskList.get(index - 1));
                try {
                    saveData(taskList);
                }
                catch (IOException e) {
                    System.out.println("An error occurred while saving, please try again!");
                }

            }

            else {

                try {
                    String[] commands = in.split(" /");
                    String[] eventType = commands[0].split(" ");

                    if(!((eventType[0].equals("todo"))|| eventType[0].equals("deadline")|| eventType[0].equals("event"))) {
                        throw new DukeException("I'm sorry, but I do not know what that means :-(");
                    }

                    if(eventType.length == 1) {
                        throw new DukeException("The description of a " + commands[0] + " cannot be empty.");
                    }

                    if (eventType[0].equals("event")) {
                        if(commands.length == 1) {
                            throw new DukeException("It appears that no timing was provided for this event!");
                        }
                        String[] subS = commands[1].split(" ");
                        if(subS.length == 1) {
                            throw new DukeException("It appears that no timing was provided for this event!");
                        }

                        taskList.add(new Event(commands[0].substring(6, commands[0].length()), commands[1].substring(subS[0].length()+1)));
                    } else if (eventType[0].equals("deadline")) {
                        if(commands.length == 1) {
                            throw new DukeException("It appears that no due date provided for this deadline!");
                        }
                        String[] subS = commands[1].split(" ");
                        if(subS.length == 1) {
                            throw new DukeException("It appears that no due date was provided for this deadline!");
                        }
                        taskList.add(new Deadline(commands[0].substring(9, commands[0].length()), commands[1].substring(3)));
                    } else if (eventType[0].equals("todo")) {
                        taskList.add(new ToDo(commands[0].substring(5, commands[0].length())));
                    }

                    System.out.printf("Got it. I've added this task:\n");
                    System.out.println("  " +taskList.get(taskList.size() - 1));
                    if(taskList.size() < 2) {
                        System.out.println("Now you have 1 task in the list.");
                    }
                    else {
                        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                    }
                    try {
                        saveData(taskList);
                    }
                    catch (IOException e) {
                        System.out.println("An error occurred while saving, please try again!");
                    }

                }

                catch (DukeException e) {
                    System.out.println(e);
                    //break;
                }

            }
        }

    }

    public static void saveData(ArrayList<Task> taskList) throws IOException{
        FileWriter wr = new FileWriter("data/duke.txt");
        wr.write("");
        wr.close();
        if(taskList.size() < 1) {
            return;
        }
        FileWriter taskAdd = new FileWriter("data/duke.txt",true);
        int i = 0;
        for(i = 0; i < taskList.size(); i++) {
            taskAdd.write(taskList.get(i) + "\n");
        }
        taskAdd.close();

    }

    public static void loadData(ArrayList<Task> taskList) throws FileNotFoundException {
        File dataBank = new File("data/duke.txt");
        Scanner reader = new Scanner(dataBank);

        while(reader.hasNext()) {
            String task = reader.nextLine();
            char initial = task.charAt(1);
            char status = task.charAt(4);
            String desc = task.substring(7);

            if(initial == 'T') {
                taskList.add(new ToDo(desc));

            }

            else if(initial == 'E'){
                String[] subStringy = desc.split(" \\(at: ");
                taskList.add (new Event(subStringy[0], subStringy[1].substring(0, subStringy[1].length() - 1)));

            }

            else if(initial == 'D'){
                String[] subStringy = desc.split(" \\(by: ");
                taskList.add (new Deadline(subStringy[0], subStringy[1].substring(0, subStringy[1].length() - 1)));

            }

            if(status == 'Y') {
                taskList.get(taskList.size() - 1 ).doTask();
            }

        }
    }
}
