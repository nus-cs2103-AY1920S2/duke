import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * The UI class for Duke
 **/
public class Squirtle {
    protected ArrayList<Task> userInputList;
    protected String logoPath = "logo.txt";
    protected String lineBreak = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    protected String waterDroplets = "\uD83D\uDCA6";
    protected boolean isActivated = false;
    protected Storage storage;


    Squirtle() {
        userInputList = new ArrayList<>();
    }

    public void start() {
        isActivated = true;
        try (BufferedReader br = new BufferedReader(new FileReader(logoPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // print logo
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        this.printSquirtleCall();
        this.printPrompt();

    }

    public void setStorage(Storage storage) {
        this.storage = storage;
        userInputList = storage.load();
    }

    public void end() {
        isActivated = false;
        this.printSquirtleCall("Squirtle says bye bye!!!");
    }

    public boolean isRunning() {
        return isActivated;
    }

    public void passInput(String input) {
        if (input.equals("bye")) {
            this.end();
        }
        else if (input.equals("list")) {
            this.listTasks();
        }
        else if (input.startsWith("done")) {
            doneTask(input);
            storage.update(userInputList);
        }
        else if (input.startsWith("delete")) {
            deleteTask(input);
            storage.update(userInputList);
        }
        else {
            createTask(input);
            storage.update(userInputList);
        }
        this.printLineBreak();
        this.printPrompt();
    }



    public void listTasks() {
        this.printSquirtleCall("your tasks!! ");
        for (int i = 0; i < userInputList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + userInputList.get(i));
        }
    }

    public void doneTask(String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
            userInputList.get(taskNumber).markAsDone();
            this.printSquirtleCall("Squirtle finished!!");
            System.out.println("\t" + userInputList.get(taskNumber));
        }
        catch(IndexOutOfBoundsException e) { // catch exception - trying to access number higher than number of tasks
            System.out.println(new DukeException(DukeError.NUMBER));
        }
    }

    public void deleteTask(String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
            Task task = userInputList.get(taskNumber);
            this.printSquirtleCall("Squirtle is deleting~ \n\t" + task.toString());
            userInputList.remove(task);
        }
        catch(IndexOutOfBoundsException e) { // catch exception - trying to access number higher than number of tasks
            System.out.println(new DukeException(DukeError.COMMAND));
        }

    }

    public void printLineBreak() {
        System.out.println(lineBreak);
    }

    public void printPrompt() {
        System.out.print("What should Squirtle do?? ☛ ");
    }

    public void printSquirtleCall() {
        System.out.println(waterDroplets + " SQUIRTLE!! I'M HERE TO HELP!! SQUIRTLE!!");
    }

    public void printSquirtleCall(String message) {
        System.out.println(waterDroplets + " SQUIRTLE!!");
        System.out.println("\t" + message);
    }

    public void createTask(String userInput) { // TODO found new error: when passing deadline blah/event blah
            userInputList.add(task);
            this.printSquirtleCall(task.toString());
            this.printSquirtleCall("Squirtle now knows " + userInputList.size() + " task(s)!");
        }
        catch(DukeException e) {
            System.out.println(e);
        }
    }

}
