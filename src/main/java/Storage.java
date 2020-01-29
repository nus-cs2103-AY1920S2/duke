import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    public void writeToFile(String s) throws IOException {
        File file = new File("./myfile.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        writer.write(s);
        writer.newLine();

        writer.close();
    }

    public String readFromFile() throws IOException {
        File file = new File("./myfile.txt");
        StringBuilder content = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        reader.close();
        return content.toString();
    }

    public void removeLine(String tasks, int index) throws IOException {
        File file = new File("./myfile.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        String[] lines = tasks.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            if (i == index) {
                //do nth
            } else {
                writer.write(lines[i]);
                writer.newLine();
            }
        }
        writer.close();

    }

    public void changeToDone(String tasks, int index) throws IOException {
        File file = new File("./myfile.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        String[] lines = tasks.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            if (i == index) {
                writer.write(lines[i].replaceFirst("0", "1"));
            } else {
                writer.write(lines[i]);
            }
            writer.newLine();
        }
        writer.close();

    }
}