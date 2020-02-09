package service;

import collection.TaskCollection;
import task.Task;
import Server.Message;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ReminderObserver implements PropertyChangeListener {
    public ReminderObserver(TaskCollection taskCollection) {
        taskCollection.addChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("tasks updated")) {
            ArrayList<Task> newTasks = (ArrayList<Task>) event.getNewValue();
            ArrayList<Task> oldTasks = (ArrayList<Task>) event.getOldValue();
            if (newTasks.size() > oldTasks.size()) {
                for(Task task : newTasks) {
                    if (oldTasks.contains(task) == false) {
                        sendMessage(task, true);
                    }
                }
            } else if (newTasks.size() < oldTasks.size()) {
                for(Task task: oldTasks) {
                    if (newTasks.contains(task) == false) {
                        sendMessage(task, false);
                    }
                }
            }
        }
    }

    public void sendMessage(Task task, boolean isNewTask) {
        try {
            Socket socket = new Socket("127.0.0.1", 59090);
            Message msg;
            if (isNewTask) {
                msg = new Message("add", task);
            } else {
                msg = new Message("remove", task);
            }
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(msg);
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
