package storage;

import task.Task;
import taskList.TaskList;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
	protected String filePath;
	protected Path path;

	public Storage(String filePath) {
		path = Paths.get(filePath);
		// will need to deal with errorneous paths
	}

	public String getPath() {
		return path.toString();
	}

	public void save(TaskList tasks) {
//            List<String> encodedAddressBook = AddressBookEncoder.encodeAddressBook(addressBook);
		String s = "";
		for (Task t : tasks.getList()) {
			s = s + t.format() + "\n";
		}
		write(s, path);
//      Files.write(path, encodedAddressBook);
	}

	public TaskList load() {

		if (!Files.exists(path) || !Files.isRegularFile(path)) {
			return new TaskList();
		}
		TaskList tasks = new TaskList();
		try {
			List<String> loadedTasks = Files.readAllLines(path);
			for (String s : loadedTasks) {
				if (!s.equals("")) {
					tasks.add(Task.load(s));
				}
			}
		} catch (FileNotFoundException fnfe) {
			throw new AssertionError("A non-existent file scenario is already handled earlier.");
			// other errors
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return tasks;
	}

	public static void write(String s, Path path) {
		if (!Files.exists(path)) {
			try {
				Files.createDirectory(path);
			} catch (FileAlreadyExistsException e) {

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(s);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
