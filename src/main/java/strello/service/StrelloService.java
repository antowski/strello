package strello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import strello.dao.TaskDao;
import strello.model.Task;

import java.util.List;

@Service
public class StrelloService {

    private TaskDao taskDao;

    @Autowired
    public StrelloService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> getAllTasks(){
        List<Task> tasks = taskDao.getTasks();
        return tasks;
    }

}
