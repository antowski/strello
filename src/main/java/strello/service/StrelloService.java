package strello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import strello.dao.TaskDao;
import strello.dao.TaskFilter;
import strello.dao.TaskFilterField;
import strello.model.Task;

import java.util.List;

@Service
public class StrelloService {

    private TaskDao taskDao;

    @Autowired
    public StrelloService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    private boolean isAssigneeSelected(String assignee) {
        return !(assignee == null || assignee.isEmpty() || assignee.equals("all"));
    }

    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public List<Task> getFilteredTasks(String assignee) {

        TaskFilter filter = new TaskFilter();

        if (isAssigneeSelected(assignee)) {
            filter.addCondition(TaskFilterField.ASSIGNEE, assignee);
        }

        return taskDao.getFilteredTasks(filter);
    }

    public List<String> getUniqueAssignees() {
        return taskDao.getUniqueAssignees();
    }

}
