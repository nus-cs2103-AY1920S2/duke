package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    public TaskList(){

    }
    /**
     * This Method is print out the items in the list parameter
     * @param list This is the ArrayList of Object
     */
    public static void printList(ArrayList<Task> list){

        if(list.isEmpty()) {
            System.out.println("Sorry, Master. The list is empty");
        }
        else{
            for(int i = 0 ; i< list.size(); i++ ){
                System.out.println( i+1 +"." + list.get(i).toString());
            }
        }
    }

    /**
     * This Method is create "to-do", "deadline" and "event" Task Object
     * and add it to list parameter
     * @param input This is the commandline input that user keyed in
     * @param list This is the ArrayList of Object
     */
    public static void createObject(String input, ArrayList<Task> list){
        String filePath = "C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt";
        int numOfWords = 0;
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

                }
                catch(ArrayIndexOutOfBoundsException | IOException e){
                    System.out.println("Exception thrown  :" + e);
                }
            } else
                System.out.println("OOPS!!! The description of todo cannot be empty.");
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
                }
                catch(ArrayIndexOutOfBoundsException | IOException e){
                    System.out.println("Exception thrown  :" + e);
                }
            } else {
                System.out.println("OOPS!!! The description of deadline cannot be empty.");
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
                }
                catch(ArrayIndexOutOfBoundsException | IOException e){
                    System.out.println("Exception thrown  :" + e);
                }
            } else {
                System.out.println("OOPS!!! The description of deadline cannot be empty.");
            }
        }
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
    public static void markDone(ArrayList<Task> list, String input) throws IOException {
        String filePath = "C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt";
        int itemPos = Integer.parseInt(input.replaceAll("[^0-9]" , ""));
        itemPos = itemPos - 1;
        list.get(itemPos).markDone();
        System.out.println(list.get(itemPos).getStatus());
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + list.get(itemPos).getType() + "][" + list.get(itemPos).getStatus() + "] " + list.get(itemPos).getDescription());
        Storage.updateFile(list, filePath);
    }

    /**
     * This method is to delete a task and update the list.
     * @param input This is the commandline input that user keyed in
     * @param list This is the ArrayList of Task object
     */
    public static void deleteTask(ArrayList<Task> list, String input) throws IOException {
        String filePath = "C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt";
        System.out.println("As you wish, Master. I've removed this task: ");
        int itemPos = Integer.parseInt(input.replaceAll("[^0-9]" , ""));
        itemPos-- ;
        System.out.println("[" + list.get(itemPos).getType() + "][" + list.get(itemPos).getStatus() + "] " +
                list.get(itemPos).getDescription() + list.get(itemPos).getWhen());

        list.remove(itemPos);
        System.out.println("Now you have "+ list.size() + " tasks in the list.");
        Storage.updateFile(list, filePath);
    }
    /**
     * This method is to search a task occuring on the specific date
     * @param input This is the commandline input that user keyed in
     * @param list This is the ArrayList of Task object
     */
    public static void searchByDate(ArrayList<Task> list, String input) throws IOException {
        String date = input.replaceAll("search ", "");
        LocalDate referenceDate = LocalDate.parse(date);
        boolean flag = false;
        System.out.println("These are the task(s) occurring at " + date + " : ");
        for(int i = 0 ; i < list.size();i++){
            if(list.get(i).getWhen().equals(referenceDate)){
                flag = true;
                System.out.println(list.get(i).toString());
            }
        }

        if (!flag){
            System.out.println("Opps, there is no such task occurring for this date. ");
        }
    }

    public static void searchByName(ArrayList<Task> list, String ...values) throws IOException {
        String keyword = values[0].replaceAll("find ", "");
        boolean hasMatch = false;
        int [] matchFound = new int[100];
        int count = 0;
        for(int i = 0 ; i < list.size();i++){
            if(list.get(i).getDescription().contains(keyword)){
                hasMatch = true;
                matchFound[i] = i;
                count = count + 1;
            }
        }

        if (!hasMatch){
            System.out.println("Sorry Master, there is no such task that has the keyword: " + keyword);
        }
        else {
            System.out.println("Here are the matching task(s) in your list: ");
            for(int i = 0 ; i < count + 1;i++){
                System.out.println( i+1 + ".[" + list.get(matchFound[i]).getType() + "]" +  list.get(matchFound[i]).toString());
            }
        }
    }
}
