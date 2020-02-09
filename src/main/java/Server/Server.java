package Server;

import task.Task;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(59090);
            HashMap<String, Timer> taskTimerMapping = new HashMap<String, Timer>();

            while (true) {
                try {
                    Socket s = server.accept();
                    ObjectInputStream is = new ObjectInputStream(s.getInputStream());
                    Message msg = (Message)is.readObject();
                    if (msg.message.equals("add")) {
                        Task task = (Task) msg.object;
                        task.setUpTimer(taskTimerMapping);
                    } else if (msg.message.equals("remove")) {
                        Task task = (Task) msg.object;
                        taskTimerMapping.remove(task.getDescription());
                    }
                } catch(Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
