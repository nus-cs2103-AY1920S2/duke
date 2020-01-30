import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.time.LocalDate;


public class Duke {

    protected Ui ui;
    //protected Storage storage;
    protected Tasklist tasklist;

    public Duke(String file) {
        this.ui = new Ui();
        //this.storage = new Storage(file);
        this.tasklist = new Tasklist();
    }

    public void run() throws DukeException {
        this.ui.printIntro();
        Scanner myScanner = new Scanner(System.in);

        while (true) {
            String word = myScanner.nextLine();
            try {
                String[] parsed = TextParser.myFirstParser(word);
                String keyword = parsed[0];
                if (keyword.equals("bye")) {
                    // writeFile("./list.txt", list);
                    this.ui.printMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (keyword.equals("list")) {
                    this.ui.printMessage("Here are the task in your list");
                    this.tasklist.printList();
                } else if (keyword.equals("done")) {
                    int taskNumber = Integer.valueOf(parsed[1]);
                    this.tasklist.markDone(taskNumber);
                    this.ui.printMessage("Nice! I've marked this task as done:");
                    this.ui.printMessage("" + taskNumber + ". " + this.tasklist.getTask(taskNumber));
                } else if (keyword.equals("delete")) {
                    int taskNumber = Integer.valueOf(parsed[1]);
                    this.ui.printMessage("Noted. I've removed this task");
                    this.ui.printMessage("" + this.tasklist.getTask(taskNumber));
                    this.tasklist.removeTask(taskNumber);
                    this.ui.printMessage("Now you have " + this.tasklist.getSize() + " in the list.");
                } else if (keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event")) {
                    if (parsed.length <= 1) {
                        throw new DukeException("I think u need more arguments");
                    } else {
                        String word2 = parsed[1];
                        String[] parsed2 = TextParser.mySecondParser(word2);
                        if (keyword.equals("todo")) {
                            this.tasklist.addTask(new Todo(parsed2[0]));
                        } else if (keyword.equals("deadline")) {
                            this.tasklist.addTask(new Deadline(parsed2[0], LocalDate.parse(parsed2[1])));
                        } else if (keyword.equals("event")) {
                            this.tasklist.addTask(new Event(parsed2[0], LocalDate.parse(parsed2[1])));
                        }

                        this.ui.printMessage("Got it. I 've added this task:");
                        this.ui.printMessage("" + this.tasklist.getTask(this.tasklist.getSize()));
                        this.ui.printMessage("Now you have " + this.tasklist.getSize() + " in the list.");
                    }
                } else {
                    throw new DukeException("I DK how to process this -> " + word);
                }
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            } finally {
                this.ui.printLine();
            }
        }
    }



    public static void main(String[] args) throws DukeException {
        new Duke("./list.txt").run();
    }

    public static void readFile(String file, ArrayList<Task> taskList) {
        try {

            File f = new File(file);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String current = s.nextLine();
                String[] arrSplit = current.split("/");
                Task currentTask = null;

                switch (arrSplit[0]) {
                    case "T":
                        currentTask = new Todo(arrSplit[2]);
                        break;
                    case "D":
                        currentTask = new Deadline(arrSplit[2], LocalDate.parse(arrSplit[3]));
                        break;
                    case "E":
                        currentTask = new Event(arrSplit[2], LocalDate.parse(arrSplit[3]));
                        break;
                }

                if (arrSplit[1].equals("1")) { // 1 meaning done
                    currentTask.markAsDone();
                }
                taskList.add(currentTask);
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String file, ArrayList<Task> taskList) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            for (Task current : taskList) {
                if (current instanceof Todo) {
                    writer.write("T" + "/" + current.checkDone() + "/" + current.getTask() +
                            System.lineSeparator());
                } else if (current instanceof Deadline) {
                    writer.write("D" + "/" + current.checkDone() + "/" + current.getTask() + "/" +
                            ((Deadline) current).getDate() + System.lineSeparator());
                } else if (current instanceof Event){
                    writer.write("E" + "/" + current.checkDone() + "/" + current.getTask() + "/" +
                            ((Event) current).getDate() + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
