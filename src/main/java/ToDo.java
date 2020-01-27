import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.*;
public class ToDo extends Task implements java.io.Serializable{

    protected ToDo(String desciption){
        super(desciption);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
