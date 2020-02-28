package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.lang.*;


public class TaskList {

    public TaskList(){

    }
    /**
     * This Method is print out the items in the list parameter
     * @param list This is the ArrayList of Object
     */
    public static String printList(ArrayList<Task> list){

        String output = "";
        int headerNum = 0;
        if(list.isEmpty()) {
            System.out.println("Sorry, Master. The list is empty");

            output = "Sorry, Master. The list is empty";
        }
        else{
            for(int i = 0 ; i< list.size(); i++ ){
                System.out.println( ++headerNum +"." + list.get(i).toString());
                output = output +  headerNum +"." + list.get(i).toString() + "\n" ;
            }
        }
        return output;
    }

    /**
     * This Method is create "to-do", "deadline" and "event" Task Object
     * and add it to list parameter
     * @param input This is the commandline input that user keyed in
     * @param list This is the ArrayList of Object
     */
    public static String createObject(String input, ArrayList<Task> list){
        String filePath = "C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt";
        int numOfWords = 0;
        String output = "";
        for (String inputDisect : input.split(" ")) {
            numOfWords++;
        }

        if (input.contains("todo")) {
            if (numOfWords > 1) {

                try {
                    String taskDescription = input.replaceAll("todo ", "");
                    list = createTodo(taskDescription, list, "0");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("[T][X] " + taskDescription);
                    System.out.println("Now you have " + (list.size()) + " tasks in the list.");
                    Storage.appendToFile(filePath, list.get(list.size()-1));

                    output = "Got it. I've added this task:" + "\n" + "[T][X] " + taskDescription + "\n" +
                            "Now you have " + (list.size()) + " tasks in the list.";
                }
                catch(ArrayIndexOutOfBoundsException | IOException e){
                    System.out.println("Exception thrown  :" + e);

                    output = "Exception thrown  :" + e ;
                }
            } else {
                System.out.println("OOPS!!! The description of todo cannot be empty.");
                output = "OOPS!!! The description of todo cannot be empty.";
            }
        } else if (input.contains("deadline")) {

            if (numOfWords > 1)  {
                try {
                    String taskDescription = input.replaceAll("deadline ", "");

                    String[] strArray = taskDescription.split("/by ");

                    list =  createDeadline(strArray[0], strArray[1], list, "0" );
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size()-1).toString());
                    System.out.println("Now you have " + (list.size()) + " tasks in the list.");
                    Storage.appendToFile(filePath, list.get(list.size()-1));

                    output = "Got it. I've added this task:" + "\n" + list.get(list.size()-1).toString() + "\n" +
                            "Now you have " + (list.size()) + " tasks in the list.";

                }
                catch(ArrayIndexOutOfBoundsException | IOException e){
                    System.out.println("Exception thrown  :" + e);
                    output = "Exception thrown  :" + e;
                }
            } else {
                System.out.println("OOPS!!! The description of deadline cannot be empty.");
                output = "OOPS!!! The description of deadline cannot be empty.";
            }
        } else {
            if (numOfWords > 1) {
                try {
                    String taskDescription = input.replaceAll("event ", "");
                    String[] strArray = taskDescription.split("/at ");
                    list = createEvent(strArray[0], strArray[1], list, "0");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size()-1).toString());
                    System.out.println("Now you have " + (list.size()) + " tasks in the list.");
                    Storage.appendToFile(filePath, list.get(list.size()-1));

                    output = "Got it. I've added this task:" + "\n" + list.get(list.size()-1).toString() + "\n"
                            + "Now you have " + (list.size()) + " tasks in the list.";
                }
                catch(ArrayIndexOutOfBoundsException | IOException e){
                    System.out.println("Exception thrown  :" + e);
                    output = "Exception thrown  :" + e;
                }
            } else {
                System.out.println("OOPS!!! The description of deadline cannot be empty.");
                output = "OOPS!!! The description of deadline cannot be empty.";
            }
        }
        return output;
    }


    public static ArrayList createTodo(String taskDescription, ArrayList<Task> list, String ...c){

        Todo t = new Todo(taskDescription);

        if (c[0].contains("1")){
            t.markDone();
        }
        t.setType("T");
        list.add(t);

        return list;
    }

    public static ArrayList<Task> createDeadline(String taskDescription, String extraInfo, ArrayList<Task> list, String c){

        Deadline t = new Deadline(taskDescription, extraInfo);
        t.setType("D");

        if (c.contains("1")){
            t.markDone();
        }
        list.add(t);


        return list;
    }
    public static ArrayList<Task> createEvent(String taskDescription, String extraInfo, ArrayList<Task> list, String c){
        Event t = new Event(taskDescription, extraInfo);
        t.setType("E");
        if (c.contains("1")){
            t.markDone();
        }
        list.add(t);

        return list;
    }

    /**
     * This method is to mark a task done and update the list.
     * @param input This is the commandline input that user keyed in
     * @param list This is the ArrayList of Object
     */
    public static String markDone(ArrayList<Task> list, String input) throws IOException {
        String filePath = "C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt";
        int itemPos = Integer.parseInt(input.replaceAll("[^0-9]" , ""));
        String output ="";
        itemPos = itemPos - 1;
        list.get(itemPos).markDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + list.get(itemPos).getType() + "][" + list.get(itemPos).getStatus() + "] " + list.get(itemPos).getDescription());
        output ="Nice! I've marked this task as done:";
        output = output + "\n" + "[" + list.get(itemPos).getType() + "][" + list.get(itemPos).getStatus() + "] " + list.get(itemPos).getDescription();
        Storage.updateFile(list, filePath);
        return output;
    }

    /**
     * This method is to delete a task and update the list.
     * @param input This is the commandline input that user keyed in
     * @param list This is the ArrayList of Task object
     */
    public static String deleteTask(ArrayList<Task> list, String input) throws IOException {
        String filePath = "C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt";
        String output = "";

        System.out.println("As you wish, Master. I've removed this task: ");
        output = output + "As you wish, Master. I've removed this task: ";
        int itemPos = Integer.parseInt(input.replaceAll("[^0-9]" , ""));
        itemPos-- ;
        System.out.println("[" + list.get(itemPos).getType() + "][" + list.get(itemPos).getStatus() + "] " +
                list.get(itemPos).getDescription() + list.get(itemPos).getWhen());
        output = output + "\n" + "[" + list.get(itemPos).getType() + "][" + list.get(itemPos).getStatus() + "] " +
                list.get(itemPos).getDescription() + list.get(itemPos).getWhen();

        list.remove(itemPos);
        System.out.println("Now you have "+ list.size() + " tasks in the list.");

        output = output + "\n" + "Now you have "+ list.size() + " tasks in the list.";
        Storage.updateFile(list, filePath);

        return output;
    }
    /**
     * This method is to search a task occuring on the specific date
     * @param input This is the commandline input that user keyed in
     * @param list This is the ArrayList of Task object
     */
    public static String searchByDate(ArrayList<Task> list, String input) throws IOException {
        String date = input.replaceAll("search ", "");
        LocalDate referenceDate = LocalDate.parse(date);
        boolean flag = false;
        String output = "";

        System.out.println("These are the task(s) occurring at " + date + " : ");
        output = output + "These are the task(s) occurring at " + date + " : ";

        for(int i = 0 ; i < list.size();i++){
            if(list.get(i).getWhen().equals(referenceDate)){
                flag = true;
                System.out.println(list.get(i).toString());
                output = output + "\n" + list.get(i).toString();
            }
        }

        if (!flag){
            System.out.println("No, no, no, there is no such task occurring for this date. ");
            output = "These are the task(s) occurring at " + date + " : " + "\n"
                    + "No, no, no..., no such task occurring for this date. ";
        }
        return output;
    }

    public static String searchByName(ArrayList<Task> list, String input) throws IOException {
        String keyword = input.replaceAll("find ", "");
        boolean hasMatch = false;
        String output = "";
        int [] matchFound = new int[100];
        int count = 1;
        for(int i = 0 ; i < list.size();i++){
            if(list.get(i).getDescription().contains(keyword)){
                hasMatch = true;
                matchFound[i] = i;
            }
        }

        if (!hasMatch){
            System.out.println("Sorry Master, there is no such task that has the keyword: " + keyword);
            output = "Sorry Master, there is no such task that has the keyword: " + keyword;
        }
        else {
            System.out.println("Here are the matching task(s) in your list: ");
            output = "Here are the matching task(s) in your list: ";

            for(int i = 0 ; i < list.size();i++){
                if(list.get(i).getDescription().contains(keyword)) {
                    System.out.println(count++ + list.get(i).toString());
                    output = output +"\n" + (count-1) + list.get(i).toString();
                }
            }
        }
        return output;
    }
    public static void clearList(ArrayList<Task>list){
        list.clear();
        try {
            Storage.clearContent( "C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
