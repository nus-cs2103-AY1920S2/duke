import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class Storage {
        private static List<Task> savedList = new ArrayList<>();
        private final static String ROOT_PATH = Paths.get("").toAbsolutePath().toString();
        public final static String NEWLINE = System.lineSeparator();
        private String absolutePath;
        protected File file_path;

        public Storage(String filePath) {
            Path path = Paths.get(ROOT_PATH + System.getProperty("file.separator") + filePath);
            String pathString = path.toString();
            File file = new File(pathString);
            this.absolutePath = pathString;
            this.file_path = file;
        }

        public List<Task> load() throws FileNotFoundException {
            Scanner scan = new Scanner(file_path);

            while (scan.hasNextLine()) {
                String command = scan.nextLine();
                String[] arrOfCommands = command.split("\\| ");
                System.out.println("TASK NAME FOR TODO " + arrOfCommands[1]);
                if (arrOfCommands[0].equals("T")) {
                    if ("[Y]".equals(arrOfCommands[1])) {
                        ToDo toDo = new ToDo(arrOfCommands[2]);
                        toDo.markAsDone();
                        savedList.add(toDo);
                    } else if ("[N]".equals(arrOfCommands[1])) {
                        ToDo toDo = new ToDo(arrOfCommands[2]);
                        savedList.add(toDo);
                    }
                } else if (arrOfCommands[0].equals("D")) {
                    if (arrOfCommands[1].equals("[Y]")) {
                        Deadline deadline = new Deadline(arrOfCommands[2], arrOfCommands[3]);
                        deadline.markAsDone();
                        savedList.add(deadline);
                    } else if ("[N]".equals(arrOfCommands[1])) {
                        Deadline deadline = new Deadline(arrOfCommands[2], arrOfCommands[3]);
                        savedList.add(deadline);
                    }
                } else if (arrOfCommands[0].equals("E")) {
                    if (arrOfCommands[1].equals("[Y]")) {
                        Event event = new Event(arrOfCommands[2], arrOfCommands[3]);
                        event.markAsDone();
                        savedList.add(event);
                    } else if ("[N]".equals(arrOfCommands[1])) {
                        Event event = new Event(arrOfCommands[2], arrOfCommands[3]);
                        savedList.add(event);
                    }
                }
            }
            return savedList;
        }

        public void save(List<Task> list) throws IOException {
            File file = new File("data/duke.txt");
            try {
                PrintWriter out = new PrintWriter(file);
                for (Task task : list) {
                    if (task instanceof ToDo) {
                        out.write("T | " + task.getStatusIcon() + " | " + task.getTaskName() + NEWLINE);
                    } else if (task instanceof Event) {
                        out.write("E | " + task.getStatusIcon() + " | "
                                +  task.getTaskName() + " | " + ((Event)task).getAt() + NEWLINE);
                    } else if (task instanceof Deadline) {
                        out.write("D | " + task.getStatusIcon() + " | "
                                + task.getTaskName() + "|" + ((Deadline)task).getBy() + NEWLINE);
                    }
                }
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
}
