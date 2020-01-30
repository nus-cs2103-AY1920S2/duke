import java.io.*;

public class Storage {

    private String FILEPATH;

    public Storage (String FILEPATH) {
        this.FILEPATH = FILEPATH;
    }

    public File load(){
        File f = new File(FILEPATH);
        return f;
    }

    public void saveFile(String text){
        try {
            File f = new File(FILEPATH);

            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
