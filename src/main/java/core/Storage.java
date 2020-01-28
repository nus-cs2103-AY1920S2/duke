package core;

import dukexception.DukeException;
import dukexception.StorageException;

import java.io.*;

/**
 * Platform to connect to external data storage.
 */
public class Storage {

    private static final String fileName= "duke.txt";
    private File file;

    /**
     * Constructor to initialize the path of the external storage.
     */
    public Storage(){
        file=new File(fileName);
    }

    /**
     * Saves the states of the system to external file.
     * @param stateHolder is the states of the system.
     * @throws DukeException when writing to file is unsuccessful.
     */
    public void save(StateHolder stateHolder) throws DukeException {
        writeTo(file,stateHolder);
    }

    /**
     * Loads the states from the external file to the system.
     * @return the states of the system.
     * @throws DukeException when reading from the file is unsuccessful.
     */
    public StateHolder load() throws DukeException{
        return readFrom(file);
    }

    /**
     * Clears all the data in the external file.
     * @throws DukeException when reading from the file is unsuccessful.
     */
    public void clearData() throws DukeException{
        try(FileOutputStream fo=new FileOutputStream(file)){
            ObjectOutputStream oo=new ObjectOutputStream(fo);
            oo.close();
        }catch(IOException ex){
            throw new DukeException("Encounter error in resetting data");
        }

    }

    /**
     * Obtains the data from the external file.
     * @param thisFile indicates the file to be read from.
     * @return the states of the system.
     * @throws DukeException when reading the file is unsuccessful.
     */
    private StateHolder readFrom(File thisFile) throws DukeException{
        StateHolder stateHolder;
        try(FileInputStream fi=new FileInputStream(thisFile)) {
            ObjectInputStream oi = new ObjectInputStream(fi);
            stateHolder=(StateHolder)oi.readObject();
            oi.close();
        } catch (FileNotFoundException e) {
            throw new StorageException("The stated file is not found.");
        }catch(EOFException e){
            //empty file
            return null;
        }catch (IOException e) {
            e.printStackTrace();
            throw new StorageException("Encountered error in reading from the file.");
        } catch (ClassNotFoundException e) {
            throw new StorageException("The class stated in the file is not found.");
        }
        return stateHolder;
    }

    /**
     * Saves the date to the external file.
     * @param thisFile indicates the file to be written to.
     * @param stateHolder the states of the system to be save.
     * @throws DukeException when the writing to the file is unsuccessful.
     */
    private void writeTo(File thisFile,StateHolder stateHolder) throws DukeException{
        try(FileOutputStream fo=new FileOutputStream(thisFile)){
            ObjectOutputStream oo=new ObjectOutputStream(fo);
            oo.writeObject(stateHolder);
            oo.close();
        }catch(IOException ex){
            throw new DukeException("Encountered error in writing into the file");
        }
    }


}
