import java.io.*;
import java.util.Formatter;
import java.lang.*;

public class createFile {

    private Formatter x;

    public void openFile(){
        try{
            x = new Formatter("C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskList");

        }
        catch(Exception e){
            System.out.println("You have an error");
        }
    }
    public void addRecords(){
        x.format("%s%s%s" , " help me ", "save me ", "please me");
    }
    public void closeFile(){
        x.close();
    }
}
