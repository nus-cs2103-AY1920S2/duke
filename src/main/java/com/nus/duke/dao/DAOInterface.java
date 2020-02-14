package com.nus.duke.dao;

import static com.nus.duke.tasks.Tasks.TASK_STATUS;
import static com.nus.duke.tasks.Tasks.TASK_CATEGORY;
import com.nus.duke.tasks.Tasks;
import java.util.List;

public interface DAOInterface{
    public boolean add(Tasks task);
    public boolean set(Tasks task, TASK_STATUS status);
    public boolean set(Tasks task, TASK_CATEGORY category);
    public boolean delete(Tasks task);
    public Tasks search(String name);
    public List<Tasks> getAll();
    public List<Tasks> filter(String condition);
}
