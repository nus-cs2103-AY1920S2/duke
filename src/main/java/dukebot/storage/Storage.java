package dukebot.storage;

import dukebot.command.CommandList;
import dukebot.contactlist.ContactDetail;
import dukebot.contactlist.ContactList;
import dukebot.exception.DukeException;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Storage {
    private boolean saveAlreadyFailed = false;
    private String storageDirectory;
    private static final String TASK_LIST_FILEPATH = "/task-list.data";
    private static final String CONTACT_LIST_FILEPATH = "/contact-list.data";
    private static final String ALIAS_FILEPATH = "/alias.data";

    /**
     * Generates the Storage with default directory.
     */
    public Storage() {
        this.storageDirectory = "./user-data";
    }

    /**
     * Generates the Storage with custom directory.
     *
     * @param storageDirectory Path to save file.
     */
    public Storage(String storageDirectory) {
        this.storageDirectory = storageDirectory;
    }

    /**
     * Ensure that storageDirectory exists.
     *
     * @return Whether storageDirectory exists.
     */
    private boolean mkDataDir() {
        File directory = new File(storageDirectory);
        if (!directory.exists()) {
            return directory.mkdir();
        }
        return true;
    }

    /**
     * Deletes all data files.
     */
    public void clearStorage() {
        if (mkDataDir()) {
            String[] fileNames = new String[]{TASK_LIST_FILEPATH, ALIAS_FILEPATH};
            for (String fileName : fileNames) {
                File file = new File(storageDirectory + fileName);
                // Nobody cares if delete fails as file might not exist in the first place.
                file.delete();
            }
        }
    }

    /**
     * Saves data to drive.
     *
     * @param tasks The TaskList to save.
     * @throws DukeException If save fails for the first time.
     */
    public void saveTaskList(TaskList tasks) throws DukeException {
        ArrayList<Task> taskList = tasks.getTaskList();
        if (!mkDataDir() && !saveAlreadyFailed) {
            saveAlreadyFailed = true;
            throw new DukeException(LineName.SAVE_FAIL);
        }
        try {
            File file = new File(storageDirectory + TASK_LIST_FILEPATH);
            FileOutputStream writeData = new FileOutputStream(file);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            Task[] taskArr = taskList.toArray(new Task[0]);
            writeStream.writeObject(taskArr);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            // e.printStackTrace();
            if (!saveAlreadyFailed) {
                saveAlreadyFailed = true;
                throw new DukeException(LineName.SAVE_FAIL);
            }
        }
    }

    /**
     * Loads data from drive.
     *
     * @return The saved TaskList.
     * @throws DukeException If no data is found.
     */
    public ArrayList<Task> loadTaskArrayList() throws DukeException {
        File file = new File(storageDirectory + TASK_LIST_FILEPATH);
        if (file.isFile()) {
            try {
                FileInputStream readData = new FileInputStream(file);
                ObjectInputStream readStream = new ObjectInputStream(readData);
                Object obj = readStream.readObject();
                if (obj instanceof Task[]) {
                    return new ArrayList<>(Arrays.asList((Task[]) obj));
                }
            } catch (IOException | ClassNotFoundException e) {
                // e.printStackTrace();
            }
        }
        throw new DukeException(LineName.LOAD_FAIL);
    }

    /**
     * Saves contacts to drive.
     *
     * @param contactList The ContactList to save.
     * @throws DukeException If save fails for the first time.
     */
    public void saveContactList(ContactList contactList) throws DukeException {
        ArrayList<ContactDetail> contactDetails = contactList.getContactList();
        if (!mkDataDir() && !saveAlreadyFailed) {
            saveAlreadyFailed = true;
            throw new DukeException(LineName.SAVE_FAIL);
        }
        try {
            File file = new File(storageDirectory + CONTACT_LIST_FILEPATH);
            FileOutputStream writeData = new FileOutputStream(file);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            ContactDetail[] contactArr = contactDetails.toArray(new ContactDetail[0]);
            writeStream.writeObject(contactArr);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            // e.printStackTrace();
            if (!saveAlreadyFailed) {
                saveAlreadyFailed = true;
                throw new DukeException(LineName.SAVE_FAIL);
            }
        }
    }

    /**
     * Loads contacts from drive.
     *
     * @return The saved ContactArrayList.
     * @throws DukeException If no data is found.
     */
    public ArrayList<ContactDetail> loadContactArrayList() throws DukeException {
        File file = new File(storageDirectory + CONTACT_LIST_FILEPATH);
        if (!file.isFile()) {
            throw new DukeException(LineName.LOAD_FAIL);
        }

        try {
            FileInputStream readData = new FileInputStream(file);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            Object obj = readStream.readObject();
            if (obj instanceof ContactDetail[]) {
                return new ArrayList<>(Arrays.asList((ContactDetail[]) obj));
            }
        } catch (IOException | ClassNotFoundException e) {
            // e.printStackTrace();
        }
        throw new DukeException(LineName.LOAD_FAIL);
    }

    /**
     * Saves data to drive.
     *
     * @param aliasMap The aliasMap to save.
     * @throws DukeException If save fails for the first time.
     */
    public void saveAlias(HashMap<String, CommandList> aliasMap) throws DukeException {
        try {
            mkDataDir();
            File file = new File(storageDirectory + ALIAS_FILEPATH);
            FileOutputStream writeData = new FileOutputStream(file);
            OutputStreamWriter writeStream = new OutputStreamWriter(writeData);
            for (String alias : aliasMap.keySet()) {
                String command = aliasMap.get(alias).getDefaultCommand();
                writeStream.write(command + " " + alias + "\n");
            }
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            // e.printStackTrace();
            if (!saveAlreadyFailed) {
                saveAlreadyFailed = true;
                throw new DukeException(LineName.SAVE_FAIL);
            }
        }
    }

    /**
     * Loads aliasMap from drive.
     *
     * @return The saved aliasMap with key default command and value alias.
     * @throws DukeException If no data is found.
     */
    public HashMap<String, String> loadAlias() throws DukeException {
        File file = new File(storageDirectory + ALIAS_FILEPATH);
        if (file.isFile()) {
            try {
                FileReader fr = new FileReader(file);   //reads the file
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
                String line = br.readLine();

                HashMap<String, String> aliasMap = new HashMap<>();
                while (line != null) {
                    String[] lineArr = line.split(" ");
                    if (lineArr.length >= 2) {
                        aliasMap.put(lineArr[0], lineArr[1]);
                    }
                    line = br.readLine();
                }
                fr.close();    //closes the stream and release the resources
                return aliasMap;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new DukeException(LineName.LOAD_FAIL);
    }
}