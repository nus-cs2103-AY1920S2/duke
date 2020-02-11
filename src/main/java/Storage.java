import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file;

    public Storage() {
        this.file = new File("Duke.txt");
    }

    public void updateTxtFile (ArrayList<task> ls) throws IOException {
        PrintWriter pw = new PrintWriter(this.file);
        pw.write("");
        pw.close();

        PrintWriter toWrite = new PrintWriter(this.file);
        String finalToWrite = "";

        for (int i = 0; i < ls.size(); i++) {
            finalToWrite = finalToWrite + ls.get(i).toSave() + "\n";
        }

        toWrite.write(finalToWrite);
        toWrite.close();
    }

    public ArrayList<task> readTxtFile () throws IOException {
        boolean check = this.file.exists();
        this.file.createNewFile();
        Scanner fsc = new Scanner(this.file);
        ArrayList<task> mylist = new ArrayList<>();

        if (check == false) {

        } else {
            while (fsc.hasNextLine()) {
                String currline = fsc.nextLine();
                String[] temparr = currline.split(" /n ");

                if (temparr[0].equals("T")) {
                    ToDo td = new ToDo(temparr[2]);

                    if (temparr[1].equals("1")) {
                        td.markDone();
                    } else {

                    }

                    mylist.add(td);
                } else  if (temparr[0].equals("D")) {
                    DeadLine d = new DeadLine(temparr[2], temparr[3]);

                    if (temparr[1].equals("1")) {
                        d.markDone();
                    } else {

                    }

                    mylist.add(d);
                } else if (temparr[0].equals("E")) {
                    Event e = new Event(temparr[2], temparr[3]);

                    if (temparr[1].equals("1")) {
                        e.markDone();
                    } else {

                    }

                    mylist.add(e);
                } else {

                }
            }
            System.out.println("You currently have " + mylist.size() + " items from the previous session\n");

        }
        return mylist;
    }
}
