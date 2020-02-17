package database;
import commands.CommandInvoker;
import commands.LoadCommand;
import duke.UI;
import exceptions.DukeException;
import resources.DateTimeHandler;
import resources.Task;
import database.MyList;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * * The class Database.Storage handles the memory segment of the Bot
 */
public class Storage {
    private static File file;

    /**
     * Initialises memory
     */
    public static void memoryInit() {
        try {
            file = new File("DataFile.txt");
            if (file.createNewFile()) {
                System.out.println("Init new memory");
            } else {
                System.out.println("\nPrevious data found,"
                        + " Loading memory...\n");
                loadMemory();
            }
        } catch (IOException e) {
            System.out.println("Failed to init memory..");
        }
    }

    private static void overwrite(String line) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            bw.write(line);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void append(String line) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
            bw.write(line);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the memory on close.
     *
     * @throws DukeException the duke exception
     */
    public static void saveMemory() throws DukeException {
        ArrayList<Task> mylist = MyList.getList();
        boolean first = true;

        if (mylist.size() == 0) {
            overwrite("Wow..much emptiness");
        }
        for (Task t: mylist) {
            String[] splitted = t.toString().split(" ", 3);
            String type = parseType(splitted[0]);
            String isDone = parseDone(splitted[1]);
            String desc = parseDesc(splitted[2]);
            String doneDate = "1/1/1";
            if (isDone.equals("true")) {
                doneDate = parseDate(t.getDoneDate());
            }

            String buildLine = type + "~" + isDone + "~" + desc + "~" + doneDate;
            if (first) {
                first = false;
                overwrite(buildLine);
                continue;
            }
            append(buildLine);
        }
    }


    private static String parseDate(LocalDate d) {
        String date = "";
        int year = d.getYear();
        int month = d.getMonthValue();
        int day = d.getDayOfMonth();
        date = day + "/" + month + "/" + year;
        return date;
    }
    private static String parseType(String s) throws DukeException {
        if (s.equals("[D]")) {
            return "deadline";
        } else if (s.equals("[E]")) {
            return "event";
        } else if (s.equals("[T]")) {
            return "todo";
        } else {
            throw new DukeException(UI.getReply("memParsedError"));
        }
    }
    private static String parseDone(String s) {
        if (s.equals("[\u2713]")) {
            return "true";
        } else {
            return "false";
        }
    }

    private static String parseDesc(String str) {
        if (!str.contains("(")) {
            return str;
        }
        String typeSpecific = str.substring(str.indexOf("(") + 1, str.indexOf(":"));
        String desc = str.substring(0, str.indexOf("(") - 1);
        String datedesc = str.substring(str.indexOf(":") + 2, str.indexOf(")"));
        return  desc + "/" + typeSpecific + " " + DateTimeHandler.revertDateTime(datedesc);

    }

    private static void loadMemory() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
               if (line.equals("Wow..much emptiness")) {
                   return;
               }
                boolean markdone;
                String[] splitted = line.split("~");
                if (splitted[1].equals("false")) {
                    markdone = false;
                } else {
                    markdone = true;
                }
                CommandInvoker.invoke(new LoadCommand(splitted[0], markdone, splitted[2]));

                if (splitted[1].equals("true")) {
                    Task t = MyList.getRecentAddedTask();
                    String[] dates = splitted[3].split("/");
                    int year = Integer.parseInt(dates[2]);
                    int month = Integer.parseInt(dates[1]);
                    int day = Integer.parseInt(dates[0]);

                    LocalDate d = LocalDate.of(year, month, day);
                    t.setDone(d);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            e.printStackTrace();
        }

    }
}
