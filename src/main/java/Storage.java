import java.io.*;
import java.util.ArrayList;

public class Storage {
    String path;
    Parser parser;

    Storage(String path) {
        this.path = path;
        this.parser = new Parser();
    }

    public ArrayList<Task> load() {
        ArrayList<Task> lst = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[^\\x00-\\x7F]", "");
                lst.add(parser.parseFile(line));
            }


        }
        catch (IOException e) {
            System.err.println(e);
        }
        return lst;
    }

    public void update(ArrayList<Task> lst) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(path);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);

            for (Task task : lst) {
                bw.write(parser.parseTask(task));
                bw.newLine();
                bw.flush();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
