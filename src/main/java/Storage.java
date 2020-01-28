import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String filePath;
    private File file;
    private BufferedReader br;
    private FileWriter fw;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();
            this.file = new File(this.filePath);
        }
    }

    public TaskList getTaskList() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(file));
        ArrayList<String> stringArr = new ArrayList<>();
        String st;
        try {
            while ((st = br.readLine()) != null) {
                stringArr.add(st);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return new TaskList(stringArr);
    }

    public void writeList() throws IOException {
        fw = new FileWriter(file);
        for (Task t : TaskList.getTaskList()) {
//            System.out.println(t);
            fw.write(t.save() + "\n");
        }
        fw.flush();
    }
}