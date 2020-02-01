import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Storage {

    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public void createStorage() throws IOException {
        //relative path
        String relativePath = fileName;
        File file = new File(relativePath);
        if (file.createNewFile()) {
            System.out.println(relativePath + " Save File Created");
        } else System.out.println("File " + relativePath + " already exists");
    }

    public boolean checkFileExistence() {
        //relative path
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            return true;
        } else {
            return false;
        }
    }

    public String loadStorage() throws IOException {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        int content;
        String result = "";
        while ((content = fis.read()) != -1) {
            result = result + ((char) content);
        }
        return result;
    }

    public void storeData(String data) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(data.getBytes());
        fos.flush();
        fos.close();
    }
}
