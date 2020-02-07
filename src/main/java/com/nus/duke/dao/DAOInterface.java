package com.nus.duke.dao;

import static com.nus.duke.tasks.Tasks.TASK_STATUS;
import com.nus.duke.tasks.Tasks;
import java.util.ArrayList;

public interface DAOInterface{
    public Boolean add(Tasks task);
    public Boolean set(Tasks task, TASK_STATUS status);
    public Boolean delete(Tasks task);
    public ArrayList<Tasks> getAll();
    public Tasks search(String name);
}
