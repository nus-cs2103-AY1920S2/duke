import java.io.*;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;

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
              // System.out.println("Storage loaded: " + line);
                line = line.replaceAll("[^\\x00-\\x7F]", ""); // I HAVE NO IDEA HOW I GOT A WEIRD 2 CHARACTER ERROR HERE IS A QUICK FIX TO REMOVE NON ASCII
                lst.add(parser.parse(line));
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
//                String parsed = parser.parse(task);
//                System.out.println("parser parsed: " + parsed);
                bw.write(parser.parse(task));
                bw.newLine();
                bw.flush();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
