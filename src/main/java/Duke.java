import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        Ui.printHello();
        String filePath = "data/duke.txt";
        Storage storage = new Storage(filePath);
        TaskList taskList;
        try {
            taskList = storage.getTaskList(); // initialise from text file
            Parser.scan(taskList, storage);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
