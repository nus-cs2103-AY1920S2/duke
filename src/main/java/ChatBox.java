package packagedirectory.test;

import packagedirectory.test.Folder;
import packagedirectory.test.ToDos;
import packagedirectory.test.Message;
import packagedirectory.test.Deadlines;
import packagedirectory.test.Events;
import packagedirectory.test.DukeException;
import packagedirectory.test.Tasks;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChatBox {
    private Folder folder;
    private boolean hasClosed = false;
    private String location;

    public ChatBox(String location) {
        this.folder = new Folder();
        this.location = location;
    }

    public void load() throws DukeException {
        try {
            File file = new File(location);
            file.getParentFile().mkdirs();
            file.createNewFile();
            Scanner data = new Scanner(file);
            while (data.hasNextLine()) {
                String[] retrieveMsg = data.nextLine().split("=");
                String key = retrieveMsg[0];
                String status = retrieveMsg[1];
                String info = retrieveMsg[2];
                Tasks tasks = categories(key, status, info);
                folder.add(tasks);
            }
        } catch (FileNotFoundException errorMsg) {
            //File file = new File("data/duke.txt");
            //file.getParentFile().mkdirs();
            //file.createNewFile();
            //String er = "Creating new file directory.";
            throw new DukeException(errorMsg.getMessage());
        } catch (IllegalArgumentException e) {
            String errorMsg = "OOPS!!! Dont understand what you are saying...";
            throw new DukeException(errorMsg);
        } catch (IOException e) {
            String errorMsg = "Could not create file...";
            throw new DukeException(errorMsg);
        }
    }

    public void save() throws IOException {
        FileWriter fw = new FileWriter(location);
        fw.write(folder.getText());
        fw.close();
    }

    public String reply(Message input) {
        String replyMsg = "";
        String info = input.getMsg();
        String[] msg = info.split(" ");
        String key = msg[0];
        try {
            switch (key) {
            case "bye":
                hasClosed = true;
                replyMsg = Message.end();
                save();
                break;
            case "list":
                replyMsg = folder.show();
                break;
            case "done":
                replyMsg = finishTasks(msg);
                save();
                break;
            case "delete":
                replyMsg = deleteTasks(msg);
                save();
                break;
            case "find":
                replyMsg = folder.find(msg[1]);
                break;
            default:
                Tasks tasks;
                tasks = categories(key, "", info);
                folder.add(tasks);
                replyMsg = tasks.added();
                save();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            String errorMsg = "OOPS!! The description of a " + key + " cannot be empty";
            return errorMsg;
            // System.out.println(new DukeException(errorMsg));
        } catch (IllegalArgumentException e) {
            String errorMsg = "OOPS!!! Dont understand what you are saying...";
            return errorMsg;
            //System.out.println(new DukeException(er));
        } catch (IOException e) {
            String errorMsg = "OOPS!!! No such directory to save the file...";
            return errorMsg;
            //System.out.println(new DukeException(er));
        } catch (NoSuchElementException e) {
            String errorMsg = "You have entered a number out of range...";
            return errorMsg;
        }
        return replyMsg;
    }

    public Tasks categories(String key, String status, String input) throws IllegalArgumentException {
        Tasks tasks = new Tasks();
        Message message = new Message(input);
        String data = Arrays.stream(input.split(key))
                .dropWhile(x -> x.equals("") || x.equals(" "))
                .findFirst()
                .get();

        if (key.equals("todo")) {
            tasks = new ToDos(new Message(data));
        }

        if (key.equals("deadline")) {
            String detail = data.split("/by")[0];
            String date = data.split("/by")[1];
            tasks = new Deadlines(new Message(detail + "(by: " + date + ")"));
        }

        if (key.equals("event")) {
            String detail = data.split("/at")[0];
            String date = data.split("/at")[1];
            tasks = new Events(new Message(detail + "(at: " + date + ")"));
        }

        if (key.equals("[T]")) {
            tasks = new ToDos(message, status);
        }

        if (key.equals("[E]")) {
            tasks = new Events(message, status);
        }

        if (key.equals("[D]")) {
            tasks = new Deadlines(message, status);
        }

        if (!tasks.isEmpty()) {
           return tasks;
        } else {
            throw new IllegalArgumentException("wrong liao");
        }

    }

    public boolean isClose() {
        return this.hasClosed;
    }

    public String initialise() {
        try {
            String output;
            output = Message.welcome();
            load();
            return output;
        } catch (DukeException e) {
            String errorMsg = "OOPS!! History is not loaded correctly, check the file location...";
            return errorMsg;
        }
    }

    private String deleteTasks(String ... msg) {
        String output = "";
        int i = 0;

        if (msg[1].equals("all")) {
            output = folder.deleteAll();
            return output;
        }

        for (String x : msg) {
            boolean isNumber;
            isNumber = x.matches("\\d+(\\.\\d+)?");
            if (isNumber) {
                int number = Integer.parseInt(x);
                output = output + folder.deleteTasks(number - i) + "\n";
                i++;
            }
        }

        return output.equals("") ? "Delete Nth" : output;
    }

    private String finishTasks(String ... msg) {
        String output = "";
        int i = 0;

        if (msg[1].equals("all")) {
            output = folder.finishAll();
            return output;
        }

        for (String x : msg) {
            boolean isNumber;
            isNumber = x.matches("\\d+(\\.\\d+)?");
            if (isNumber) {
                int number = Integer.parseInt(x);
                output = output + folder.finishTasks(number) + "\n";
                i++;
            }
        }

        return output.equals("") ? "Finish Nth" : output;
    }
}