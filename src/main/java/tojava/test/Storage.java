package tojava.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    ArrayList<Task> arrList = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load date from file and store in an ArrayList
     * @return task list
     * @throws FileNotFoundException if the file does not exist
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        Task t;
        boolean done;

        while (s.hasNext()) {
            String c = s.next();
            if (c.equals("T")) {
                done = (s.nextInt())==1;
                t = new Todo(s.nextLine());
                arrList.add(t);
            }
            else if (c.equals("E")) {
                done = (s.nextInt())==1;
                t = new Event(s.nextLine());
                arrList.add(t);
            }
            else {
                done = (s.nextInt())==1;
                t = new Deadline(s.nextLine());
                arrList.add(t);
            }
            if (done) {
                t.markAsDone();
            }
        }

        return arrList;
    }

    /**
     * Write to file the new task list
     * @param newList new task list created
     * @throws IOException if there is an error with the inputs from newList or output when writing to file
     */
    public void appendToFile(ArrayList<Task> newList) throws IOException {
        String s = "";
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < newList.size(); i++) {
            if ((newList.get(i).getType()).equals("T")) {
                s += newList.get(i).getType() + " " + newList.get(i).getDone() + newList.get(i).getDescription() + newList.get(i).getWord() + " " + newList.get(i).getDate();
            }
            else {
                s += newList.get(i).getType() + " " + newList.get(i).getDone() + newList.get(i).getDescription() + "/" + newList.get(i).getWord() + " " + newList.get(i).getDate()+ " " +newList.get(i).getTime();
            }
            if (i != newList.size()-1) {
                s = s + System.lineSeparator();
            }
        }
        fw.write(s);
        fw.close();
    }
}
