package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

import java.util.List;
import java.util.ArrayList;

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