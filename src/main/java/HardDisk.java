import java.io.*;

public class HardDisk {
    String path;

    public HardDisk(String path) throws IOException {
        this.path = path;
    }

    protected void addFileInputToTasks() throws IOException, InvalidTaskInputException {
        String inputLine;
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        while ((inputLine = br.readLine()) != null) {
            String[] input = inputLine.split("\\|", 3);
            String source = "file";
            String type = input[0].trim();
            String doneStatus = input[1].trim();
            String desc = input[2].trim();
            if (type.equalsIgnoreCase("T")) {
                Duke.addTodo(desc, doneStatus, source);
            } else if (type.equalsIgnoreCase("D")) {
                Duke.addDeadline(desc, doneStatus, source);
            } else if (type.equalsIgnoreCase("E")) {
                Duke.addEvent(desc, doneStatus, source);
            } else {
                throw new InvalidTaskInputException();
            }
        }
    }

    protected void addToHardDisk(Task task) throws IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String data = "";
        data += task.getType() + " | " + task.getStatusIcon() + " | " + task.getDescription();
        if (task instanceof Deadline || task instanceof Event) {
            data += " | " + task.getTime();
        }

        bw.write("\n" + data);
        bw.close();
        fw.close();
    }

    protected void changeToHardDisk(int index) throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String data = "";
        String line = null;
        int counter = 1;
        while ((line = br.readLine()) != null) {
            if (counter == index) {
                line = line.substring(0, 4) + "Y" + line.substring(5, line.length());
            }

            if (counter == 1) {
                data += line;
            } else {
                data += "\n" + line;
            }
            counter++;
        }

        FileOutputStream fileOutputStr = new FileOutputStream(path);
        fileOutputStr.write(data.getBytes());
        fileOutputStr.close();
    }
}
