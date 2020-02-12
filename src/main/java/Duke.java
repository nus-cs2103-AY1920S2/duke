
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Duke program implements an application to interact with a user's tasks
 * Functionalities include:
 * 1) Able to store and mark as done for three different types of tasks (to-do, deadline, events)
 * 2) Able to  list down all the tasks in sequence
 * 3) Able to delete the tasks
 * 4) Able to find the task by it's name
 */

public class Duke{


    static Scanner sc = new Scanner(System.in);

    /**
     * This method reads contents from taskList.txt and add them to tempTaskList arrayList
     */
    private static void readFileContent(String filePath, ArrayList<String> tempTaskList ) throws FileNotFoundException{
        File f = new File(filePath); // create a file for the given file path
        Scanner s = new Scanner(f);// create a scanner using the File as the source
        while(s.hasNext()){
            tempTaskList.add(s.nextLine());
        }
    }

    /**
     * This method is to append a string to tastList.txt
     */
    static void appendToFile(String filePath, String textToAdd) throws IOException{
        FileWriter fw = new FileWriter(filePath , true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * This method is to greet the user when Duke program runs
     */
    static void printGreetings() {
        System.out.print("____________________________________________________________ \n" + "Hello! I'm Duke \n"
                + "What can I do for you?\n" + "____________________________________________________________ \n"
        );
    }


    static void overWriteFile(String filePath, String textToAdd) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void editFileContent (String filePath, String taskDescription , String taskType, String extraInfo) throws FileNotFoundException{

        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> tempTaskList = new ArrayList<String>();

        String taskLine;
        while(s.hasNext()){
            taskLine = s.next();
            if(taskLine.contains(taskDescription)){
                taskLine = taskType + " ** 1 ** " + taskDescription;

                if (!extraInfo.equals("")){
                    taskLine += " ** " + extraInfo;
                }
            }
            tempTaskList.add(taskLine);
        }

        /**
         * Try to empty the taskList.txt
         */
        try{
            overWriteFile("C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt" ,"");
        }
        catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }

        /**
         * Re-insert all the tasks from list array to taskList.txt by
         * appending each tasks again
         */
        for(int i = 0 ; i < tempTaskList.size();i++){
            try{
                appendToFile("C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt" , tempTaskList.get(i)  );
            }
            catch (IOException e){
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

    }




    public static void main(String[] args) {

        /**
         * list arraylist - to store the Task objects
         * type arraylist - to store the Task type (E - Event , T - to-do, D - Deadline)
         * tempTaskList - a temporary arraylist to store each line of taskList.txt
         */
        ArrayList<Task> list = new ArrayList<Task>();
        ArrayList<String> type = new ArrayList<String>();
        ArrayList<String> tempTaskList = new ArrayList<String>();

        /**
         * This method reads from taskList.txt and transfer each line to  arraylist tempTaskList
         */
        try{
            readFileContent("C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt", tempTaskList);
            for(int i = 0 ; i <tempTaskList.size();i++){


                String tempTask ;
                tempTask = tempTaskList.get(i);
                System.out.println(tempTaskList.get(i) );
                String [] strArray = tempTask.split(" -");

                switch(strArray[0]){
                    case "T":   type.add("T");
                                Todo t = new Todo(strArray[2]);
                                list.add(t);
                                if(strArray[1].contains("1")){
                                    list.get(i).markDone();
                                }
                                break;

                    case "D":   type.add("D");
                                Deadline s = new Deadline(strArray[2], strArray[3]);
                                list.add(s);
                                if(strArray[1].contains("1")){
                                    list.get(i).markDone();
                                }
                                break;
                    case "E":   type.add("E");
                                Event e = new Event(strArray[2], strArray[3]);
                                list.add(e);
                                if(strArray[1].contains("1")){
                                    list.get(i).markDone();
                                }
                                break;
                }


            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }

        String filePath = "C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt";


        printGreetings();
        String input = sc.nextLine();
        int count = 1;
        Iterator itr = list.iterator();



        while (!input.equals("bye")) {


            /**
             * Adding item to list
             */
            if(!input.equals("list") && !input.contains("done")){

                String findPrint = "Here are the matching tasks in your list: \n";
                /**
                 * Find task from the list
                 */
                if(input.contains("find")){
                    int numOfWords = 0;
                    for (String inputDisect : input.split(" ")) {
                        numOfWords++;
                    }
                    /**
                     * integer count to keep track of the number of similar task of the same name
                     */
                    int counter = 1;

                    String taskDescription = input.replaceAll("find ", "");

                    for(int i = 0 ; i < list.size();i++){

                        /**
                         * to check if list array contains the taskDescription
                         */
                        if((list.get(i).getDescription()).contains(taskDescription)){
                            findPrint = findPrint + counter + "." + "[" + type.get(i) + "][" +
                                    list.get(i).getStatus() + "] " + list.get(i).getDescription();

                            if(type.get(i).equals("E") ||type.get(i).equals("D") ){
                                findPrint = findPrint + " " + list.get(i).getWhen();
                            }
                            findPrint = findPrint + "\n";
                            counter++;
                        }
                    }

                    if(counter==1){
                        findPrint = "Sorry there is no match for it. ):";
                    }
                    System.out.println(findPrint);

                }

                /**
                 * Adding to-do task to the list
                 */
                if(input.contains("todo")) {
                    int numOfWords = 0;
                    for (String inputDisect : input.split(" ")) {
                        numOfWords++;
                    }
                    if (numOfWords > 1) {
                        String taskDescription = input.replaceAll("todo ", "");
                        Todo t = new Todo(taskDescription);
                        list.add(t);
                        type.add("T");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[T][X] " + taskDescription);
                        System.out.println("Now you have " + (list.size()) + " tasks in the list.");

                        /**
                         * Writing data to taskList.txt
                         */
                        try{
                            appendToFile(filePath, System.lineSeparator() +"T -0 -" + taskDescription);
                        }
                        catch(IOException e){
                            System.out.println("Something went wrong: " + e.getMessage());
                        }

                    }
                    /**
                     * Eror handling when to-do doesnt have descriptions
                     */

                    else
                        System.out.println("OOPS!!! The description of todo cannot be empty.");
                }
                /**
                 * Adding deadline task to the list
                 */
                if(input.contains("deadline")) {

                    int numOfWords = 0;
                    for (String inputDisect : input.split(" ")) {
                        numOfWords++;
                    }
                    if (numOfWords > 1) {
                        String taskDescription = input.replaceAll("deadline ", "");
                        String[] strArray = taskDescription.split("/by ");

                        Deadline t = new Deadline(strArray[0], strArray[1]);
                        list.add(t);
                        type.add("D");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t.toString());
                        System.out.println("Now you have " + (list.size()) + " tasks in the list.");

                        /**
                         * Writing to the tasklist.txt
                         */
                        try{
                            appendToFile(filePath, System.lineSeparator() +"D -0 -" + strArray[0] + " -" + strArray[1] );
                        }
                        catch(IOException e){
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                    }

                    /**
                     * Error handling when there is no description input for Deadline task
                     */
                    else
                        System.out.println("OOPS!!! The description of deadline cannot be empty.");
                }
                /**
                 * Adding Event task to the list
                 */
                if(input.contains("event")) {
                    int numOfWords = 0;
                    for (String inputDisect : input.split(" ")) {
                        numOfWords++;
                    }
                    if (numOfWords > 1) {
                        String taskDescription = input.replaceAll("event ", "");
                        String[] strArray = taskDescription.split("/at ");

                        Event t = new Event(strArray[0], strArray[1]);
                        list.add(t);
                        type.add("E");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t.toString());
                        System.out.println("Now you have " + (list.size()) + " tasks in the list.");

                        /**
                         * To write data to taskList.txt
                         */
                        try{
                            appendToFile(filePath, System.lineSeparator() + "D -0 -" + strArray[0] + " -" + strArray[1] );
                        }
                        catch(IOException e){
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                    }

                    /**
                     * Error handling when there is no description input for Event task
                     */
                    else
                        System.out.println("OOPS!!! The description of deadline cannot be empty.");
                }


            }
            //Error handling when user type words that are not the intended instructions
            if(!input.contains("done") && !input.contains("list") && !input.contains("todo") &&  !input.contains("deadline")
                    && !input.contains("event") && !input.contains("delete") && !input.contains("find")){
                System.out.println("OPPS!!! I'm sorry, but I don't know what that means :-( ");
            }
            if(input.contains("done")){
                System.out.println("Nice! I've marked this task as done:");
                int itemPos = Integer.parseInt(input.replaceAll("[^0-9]" , ""));
                itemPos-- ;
                list.get(itemPos).markDone();
                System.out.println("[" + list.get(itemPos).getStatus() + "]" + list.get(itemPos).getDescription());

                //overwrite the file with new list whenever there is a task is done
                try{
                    overWriteFile(filePath, "");
                    for(int k = 0 ; k < list.size();k++) {
                        String statusToNumber = "0";
                        if (list.get(k).getStatus().equals("Tick")) {
                            statusToNumber = "1";
                        }
                        if(type.get(k).equals("T")){
                            appendToFile(filePath,   type.get(k) + " -" + statusToNumber + " -" +
                                    list.get(k).getDescription() + System.lineSeparator());
                        }
                        if(type.get(k).equals("D") || type.get(k).equals("E")) {
                            appendToFile(filePath, type.get(k) + " -" + statusToNumber + " -" +
                                    list.get(k).getDescription() + " -" + list.get(k).extraInfo() + System.lineSeparator());
                        }
                    }
                }
                catch(IOException e){
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
            if(input.contains("delete")){
                System.out.println("Noted. I've removed this task: ");
                int itemPos = Integer.parseInt(input.replaceAll("[^0-9]" , ""));
                itemPos-- ;


                System.out.println("[" + type.get(itemPos) + "][" + list.get(itemPos).getStatus() + "] " +
                        list.get(itemPos).getDescription() + list.get(itemPos).getWhen());

                list.remove(itemPos);
                type.remove(itemPos);
                System.out.println("Now you have "+ list.size() + " tasks in the list.");

                //overwrite the file with new list whenever there is a task is done
                try{
                    overWriteFile(filePath, "");
                    for(int k = 0 ; k < list.size();k++) {
                        String statusToNumber = "0";
                        if (list.get(k).getStatus().equals("Tick")) {
                            statusToNumber = "1";
                        }
                        if(type.get(k).equals("T")){
                            appendToFile(filePath,   type.get(k) + " -" + statusToNumber + " -" +
                                    list.get(k).getDescription() + System.lineSeparator());
                        }
                        if(type.get(k).equals("D") || type.get(k).equals("E")) {
                            appendToFile(filePath, type.get(k) + " -" + statusToNumber + " -" +
                                    list.get(k).getDescription() + " -" + list.get(k).extraInfo() + System.lineSeparator());
                        }
                    }
                }
                catch(IOException e){
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
            if(input.equals("list")){
                count = 1;
                System.out.println("____________________________________________________________\n");


                for(int i = 0 ; i < list.size();i++){
                    String icon = list.get(i).getStatus();
                    String addInfo = "";
                    if(type.get(i).equals("E")){
                        addInfo = (list.get(i)).getWhen();
                    }

                    if(type.get(i).equals("D")){
                        addInfo = (list.get(i)).getWhen();
                    }
                    System.out.println(count + ".[" + type.get(i) + "][" + icon + "] "
                            + list.get(i).getDescription() + addInfo);
                    count++;

                }

                System.out.println("____________________________________________________________\n");


            }
            input = sc.nextLine();

        }
        System.out.println("____________________________________________________________\n" + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");

    }
}