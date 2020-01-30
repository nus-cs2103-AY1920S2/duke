import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DukeFile {
    private List<Task> lst = new ArrayList<>();
    public static final String defaultPath = "./data/duke.txt";
    File originFl;

    public DukeFile() {
        this(defaultPath);
    }

    public DukeFile(String path) {
        this.originFl = new File(path);
        try {
            if (this.originFl.isFile()) {
                load();
            } else {
                //mkdir requires directory
                this.originFl.getParentFile().mkdir();
                this.originFl.createNewFile();
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    boolean add(Task t) {
        return this.lst.add(t);
    }

    Task get(int idx) {
        return this.lst.get(idx);
    }

    Task remove(int idx) {
        return this.lst.remove(idx);
    }

    int count() {
        return this.lst.size();
    }

    void load() {
        assert this.originFl != null && this.originFl.isFile() : "path not file";
        try {
            FileReader flr = new FileReader(this.originFl);
            BufferedReader bfr = new BufferedReader(flr);
            String line = "";
            Task q;
            while ((line = bfr.readLine()) != null) {
                q = Task.parseFromCSV(line);
                lst.add(q);
            }
            bfr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void save(File fl) {
        try (PrintWriter pw = new PrintWriter(fl)) {
            for (Task q : this.lst) {
                pw.println(q.toCSV().printString());
            }
        } catch (FileNotFoundException fne) {
            System.out.println(fne);
        }
        this.originFl = fl;
    }

    void save() {
        assert this.originFl != null : "fuck";
        save(this.originFl);
    }

    List<Task> getTaskList() {
        return this.lst;
    }
}
