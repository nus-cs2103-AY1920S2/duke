package duke.storage;

import java.io.*;

import java.util.List;
import java.util.ArrayList;

/**
 * Storage class represents a .csv file
 * loadCSVList() returns a list of .csv lines represented in CSV objects in the particular .csv file
 * which path was specified in argument of Storage constructor
 */
public class Storage {
    public static final String DEFAULT_PATH = "./data/duke.txt";
    File originFl;

    public Storage() {
        this(DEFAULT_PATH);
    }

    public Storage(String path) {
        this.originFl = new File(path);
    }

    public boolean fileExist() {
        return this.originFl.exists();
    }

    public boolean createFile() throws IOException  {
        return new File(this.originFl.getParent()).mkdirs() && this.originFl.createNewFile();
    }

    /**
     * Load csv objects from csv file
     *
     * @return list of csv object
     */
    public List<CSV> loadCSVList() {
        List<CSV> lst = new ArrayList<>();
        assert this.originFl != null && this.originFl.isFile() : "path not file";
        try {
            FileReader flr = new FileReader(this.originFl);
            BufferedReader bfr = new BufferedReader(flr);
            String line = "";
            while ((line = bfr.readLine()) != null) {
                lst.add(CSV.parseCSV(line));
            }
            bfr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    /**
     * Save all csv parsable object into local csv file specified by originFl
     *
     * @param lst = all objects to save
     */
    public void save(List<? extends CSVParsable> lst) {
        try (PrintWriter pw = new PrintWriter(this.originFl)) {
            for (CSVParsable q : lst) {
                pw.println(q.toCSV().printString());
            }
        } catch (FileNotFoundException fne) {
            System.out.println(fne);
        }
    }
}