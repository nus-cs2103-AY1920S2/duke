import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Storage {
    private final String fileDir = "./appData";
    private final String fileName = "duke.txt";
    private final String filePath = fileDir + "/" + fileName;

    private void createNewFile() {
        try {
            File dir = new File(this.fileDir);
            dir.mkdirs();
            File file = new File(this.filePath);
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeToDisk(String data) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void markAsDone(int lineIdx) {
        try {
            ArrayList<String> lines = this.readFromDisk();
            FileWriter fileWriter = new FileWriter(this.filePath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int currIdx = 0;
            for (String line : lines) {
                if (currIdx == lineIdx) {
                    bufferedWriter.write(line.replaceFirst("0", "1"));
                } else {
                    bufferedWriter.write(line);
                }
                bufferedWriter.newLine();
                currIdx++;
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeTask(int lineIdx) {
        try {
            ArrayList<String> lines = this.readFromDisk();
            FileWriter fileWriter = new FileWriter(this.filePath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int currIdx = 0;
            for (String line : lines) {
                if (currIdx != lineIdx) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                currIdx++;
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<String> readFromDisk() {
        ArrayList<String> lines = new ArrayList<>(); 
        try {
            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            // file not found yet, create the file
            this.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }
}