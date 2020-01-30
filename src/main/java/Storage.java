import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class Storage {
        static List<Task> savedList = new ArrayList<>();
        private final static String ROOT_PATH = Paths.get("").toAbsolutePath().toString();
        private final static String NEWLINE = System.lineSeparator();
        private String absolutePath;
        private File file_path;

        public Storage(String filePath) {
            String path = Paths.get(ROOT_PATH + System.getProperty("file.separator") + filePath).toString();
            File file = new File(path);
            this.file_path = file;
            this.absolutePath = path;
        }

        public List<Task> load() throws FileNotFoundException {
                Scanner scan = new Scanner(file_path);
                while (scan.hasNextLine()) {
                    String command = scan.nextLine();
                    String[] arrOfCommands = command.split(" \\| ");

                    if ("T".equals(arrOfCommands[0])) {
                        if ("[Y]".equals(arrOfCommands[1])) {
                            ToDo toDo = new ToDo(arrOfCommands[2]);
                            toDo.markAsDone();
                            savedList.add(toDo);
                        } else if ("[N]".equals(arrOfCommands[1])) {
                            ToDo toDo = new ToDo(arrOfCommands[2]);
                            savedList.add(toDo);
                        }
                    } else if ("D".equals(arrOfCommands[0])) {
                        if (arrOfCommands[1].equals("[Y]")) {
                            Deadline deadline = new Deadline(arrOfCommands[2], LocalDate.parse(arrOfCommands[3].trim()));
                            deadline.markAsDone();
                            savedList.add(deadline);
                        } else if ("[N]".equals(arrOfCommands[1])) {
                            Deadline deadline = new Deadline(arrOfCommands[2], LocalDate.parse(arrOfCommands[3].trim()));
                            savedList.add(deadline);
                        }
                    } else if ("E".equals(arrOfCommands[0])) {
                        if (arrOfCommands[1].equals("[Y]")) {
                            Event event = new Event(arrOfCommands[2],
                                    LocalDate.parse(arrOfCommands[3].trim()));
                            event.markAsDone();
                            savedList.add(event);
                        } else if ("[N]".equals(arrOfCommands[1])) {
                            Event event = new Event(arrOfCommands[2], LocalDate.parse(arrOfCommands[3].trim()));
                            savedList.add(event);
                        }
                    }
                }
                return savedList;
        }

        public void save(TaskList taskList) throws IOException {
            File file = new File("data/duke.txt");
                PrintWriter out = new PrintWriter(file);
                for (Task task : taskList.getTaskList()) {
                    if (task instanceof ToDo) {
                        out.write("T | " + task.getStatusIcon() + " | " + task.getTaskName() + NEWLINE);
                    } else if (task instanceof Event) {
                        out.write("E | " + task.getStatusIcon() + " | "
                                + task.getTaskName() + " | " + ((Event) task).getDateAt() + NEWLINE);
                    } else if (task instanceof Deadline) {
                        out.write("D | " + task.getStatusIcon() + " | "
                                + task.getTaskName() + " | " + ((Deadline) task).getDateBy() + NEWLINE);
                    }
                }
                out.close();
            }
        }
