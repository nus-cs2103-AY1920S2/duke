
package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the file and saving tasks
 * in the file.
 */

public class Storage {

    String filePath = "";
    public Storage(String filePath){
        this.filePath = filePath;
    }
    public void load(ArrayList<Task> list){

        try {
            readFileContent(filePath, list);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads contents from taskList.txt and add them to list arrayList
     * @param filePath This is the file directory address
     * @param list This is the ArrayList of Task object
     */
    public void readFileContent(String filePath, ArrayList<Task> list ) throws FileNotFoundException, Exception {
        File f = new File(filePath); // create a file for the given file path
        Scanner s = new Scanner(f);// create a scanner using the File as the source
        while(s.hasNext()){

            String input = s.nextLine();
            String [] strArray = input.split(" -");

            switch (strArray[0]) {
                case "T":
                    TaskList.createTodo(strArray[2], list, strArray[1]);
                    break;

                case "D":
                    TaskList.createDeadline(strArray[2], strArray[3], list, strArray[1]);
                    break;

                case "E":
                    TaskList.createEvent(strArray[2], strArray[3], list, strArray[1]);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + strArray[0]);
            }
        }
    }

    /**
     * This method is to append the task info to tastList.txt
     * @param filePath This parameter is the taskList.txt file directory
     * @param t This parameter is the Task Object
     */
    public static void appendToFile(String filePath, Task t) throws IOException {
        FileWriter fw = new FileWriter(filePath , true);
        int typeCount = 0 ;
        if (t.getStatus().equals("Y")){
            typeCount = 1;
        }
        String extraInfo = "";
        if(t.getType().equals("E") || t.getType().equals("D")){
            extraInfo = t.getStringDate();
        }
        String textToAdd =  t.getType() + " -" + typeCount + " -" + t.getDescription() + " -" + extraInfo + "\n" ;

        fw.write(textToAdd);
        fw.close();
    }

    /**
     * This method is to update file by clearing and adding each task from arrayList list to tastList.txt
     * @param filePath This parameter is the taskList.txt file directory
     * @param list This parameter is the arrayList list
     */
    public static void updateFile(ArrayList<Task> list, String filePath) throws IOException{

        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();

        for(int i = 0 ; i <list.size(); i++){
            appendToFile(filePath, list.get(i));
        }
    }
}
