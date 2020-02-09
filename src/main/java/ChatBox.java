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
            throw new DukeException(errorMsg.getMessage());
        } catch (IllegalArgumentException e) {
            String er = "OOPS!!! Dont understand what you are saying...";
            throw new DukeException(er);
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
                int i = Integer.parseInt(msg[1]);
                assert i >= 0 : "Index out of bounds";
                replyMsg = folder.finishTasks(i);
                save();
                break;
            case "delete":
                int b = Integer.parseInt(msg[1]);
                assert b >= 0 : "Index out of bounds!";
                replyMsg = folder.deleteTasks(b);
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
            String er = "OOPS!! The description of a " + key + " cannot be empty";
            System.out.println(new DukeException(er));
        } catch (IllegalArgumentException e) {
            String er = "OOPS!!! Dont understand what you are saying...";
            System.out.println(new DukeException(er));
        } catch (IOException e) {
            String er = "OOPS!!! No such directory to save the file...";
            System.out.println(new DukeException(er));
        }
        return replyMsg;
    }

    public Tasks categories(String key, String status, String input) throws IllegalArgumentException {
        Tasks tasks = new Tasks();
        Message message = new Message(input);

        if (key.equals("todo")) {
            String data = input.split("todo ")[1];
            tasks = new ToDos(new Message(data));
        }

        if (key.equals("deadline")) {
            String s1 = input.split("deadline ")[1];
            String s2 = s1.split("/by")[0];
            String s3 = s1.split("/by")[1];
            tasks = new Deadlines(new Message(s2 + "(by: " + s3 + ")"));
        }

        if (key.equals("event")) {
            String s1 = input.split("event ")[1];
            String s2 = s1.split("/at")[0];
            String s3 = s1.split("/at")[1];
            tasks = new Events(new Message(s2 + "(at: " + s3 + ")"));
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
        } else throw new IllegalArgumentException("wrong liao");

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
        } catch (DukeException errorMsg) {
            String error = "OOPS!! History is not loaded correctly, check the file location...";
            return error;
        }
    }
}