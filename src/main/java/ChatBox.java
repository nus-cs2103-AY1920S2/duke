package packagedirectory.test;

import packagedirectory.test.DukeException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class ChatBox {
    private Folder folder;
    private boolean toClose;
    private String location;

    public ChatBox(String location) {
        this.folder = new Folder();
        this.toClose = true;
        this.location = location;
    }

    public void load() throws FileNotFoundException {
        File f = new File(location);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String[] ms = s.nextLine().split("=");
            String key = ms[0];
            String status = ms[1];
            Message message = new Message(ms[2]);
            if(key.equals("[T]")) {
                folder.add(new ToDos(message, status));
            } else if (key.equals("[E]")) {
                folder.add(new Events(message, status));
            } else if (key.equals("[D]")) {
                folder.add(new Deadlines(message, status));
            }
        }
    }

    public void save() throws IOException {
        FileWriter fw = new FileWriter(location);
        fw.write(folder.getText());
        fw.close();
    }

    public void reply(Message input) {
        String[] msg = input.getMsg().split(" ");
        String key = msg[0];
        try {
            switch (key) {
                case "bye":
                    Message.end();
                    toClose = false;
                    break;
                case "list":
                    folder.show();
                    break;
                case "done":
                    int i = Integer.parseInt(msg[1]);
                    folder.finishTasks(i);
                    save();
                    break;
                case "delete":
                    int b = Integer.parseInt(msg[1]);
                    folder.deleteTasks(b);
                    save();
                    break;
                default:
                    Tasks tasks;
                    if (key.equals("todo")) {
                        String s = input.getMsg().split("todo ")[1];
                        tasks = new ToDos(new Message(s));
                    } else if (key.equals("deadline")) {
                        String s1 = input.getMsg().split("deadline ")[1];
                        String s2 = s1.split("/by")[0];
                        String s3 = s1.split("/by")[1];
                        tasks = new Deadlines(new Message(s2 + "(by: " + s3 + ")"));
                    } else if (key.equals("event")) {
                        String s1 = input.getMsg().split("event ")[1];
                        String s2 = s1.split("/at")[0];
                        String s3 = s1.split("/at")[1];
                        tasks = new Deadlines(new Message(s2 + "(by: " + s3 + ")"));
                    } else {
                        throw new IllegalArgumentException("wrong liao");
                    }
                    folder.add(tasks);
                    tasks.added();
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
    }

    public void initialise() {
        try {
            Message.welcome();
            load();
            Scanner scan = new Scanner(System.in);
            while (toClose && scan.hasNextLine()) {
                Message input = new Message();
                String msg = scan.nextLine();
                input.add(msg);
                reply(input);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            String er = "OOPS!! History is not loaded correctly, check the file location...";
            System.out.println(new DukeException(er));
        }
    }
}