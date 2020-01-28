package core;

import dukexception.DukeException;
import dukexception.StorageException;

import java.io.*;


public class Storage {

    private static final String fileName= "duke.txt";
    private File file;


    public Storage(){
        file=new File(fileName);
    }


    public void save(StateHolder stateHolder) throws DukeException {
        writeTo(file,stateHolder);
    }


    public StateHolder load() throws DukeException{
        return readFrom(file);
    }


    public void clearData() throws DukeException{
        try(FileOutputStream fo=new FileOutputStream(file)){
            ObjectOutputStream oo=new ObjectOutputStream(fo);
            oo.close();
        }catch(IOException ex){
            throw new DukeException("Encounter error in resetting data");
        }

    }


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
