import java.io.*;

public class HardDisk {
    String path;
    FileReader fr;
    FileWriter fw;
    BufferedReader br;
    BufferedWriter bw;

    public HardDisk(String path) throws IOException {
        this.path = path;
        File file = new File(path);
        this.fr = new FileReader(file);
        this.fw = new FileWriter(file, true);
        this.br = new BufferedReader(fr);
        this.bw = new BufferedWriter(fw);
    }

    protected void addFileInputToTasks() throws IOException, InvalidTaskInputException {
        String inputLine;
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

    protected void addToHardDisk(Task task) throws IOException, InvalidTaskInputException {
        String data = "";
        data += task.getType() + " | " + task.getStatusIcon() + " | " + task.getDescription();
        if (task instanceof Deadline || task instanceof Event) {
            data += " | " + task.getTime();
        }

        bw.write("\n" + data);
        bw.close();
        fw.close();
    }

    protected void changeToHardDisk(int index) {

    }
}
