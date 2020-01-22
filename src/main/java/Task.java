import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

abstract class Task {

    // For whether its To-do(T), Deadline (D), Event (E)
    public enum Task_Codes {
        T,
        D,
        E
    }

    public enum Task_Type {
        Todo,
        Deadline,
        Event
    }

    private String description;
    private boolean isDone;
    private String space = "        ";
    private String lines = "        ____________________________________________________________";


    Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    String format_tasks() throws DukeException {
        return "";
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    // Get the description
    String getDescription() {
        return description;
    }

    // Set the task as complete
    void setDone(boolean done) {
        isDone = done;
    }

    void setDescription(String s) throws DukeException {
        this.description = s;
    }

    // Save the task into the hard disk
    //Need to get the task type through the string
    //HAHA
    void saveTask() {
        File file = new File("data/fruits.txt");
        try {
            writeToFile(file.getPath(),   "" + getTaskCodes() + " |  " + getStatusIcon()
                    + " | " + getDescription() +System.lineSeparator()
                   );
        } catch(IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    void got_it_line() {
        System.out.println(space + " Got it. I've added this task: ");
    }

    abstract Task_Codes getTaskCodes();


    private static void writeToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAppend);
        fw.close();
    }

    @Override
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + getDescription();
    }
}

