package strello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import strello.dao.TaskDao;
import strello.dao.TaskFilter;
import strello.dao.TaskFilterField;
import strello.model.Task;

import java.time.LocalDate;
import java.util.List;

@Service
public class StrelloService {

    private final TaskDao taskDao;

    @Autowired
    public StrelloService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    private boolean isAssigneeSelected(String assignee) {
        return !(assignee == null || assignee.isEmpty() || assignee.equals("all"));
    }

    private boolean isDateSelected(LocalDate date) {
        return !(date == null);
    }

    private TaskFilter buildFilter(String assignee, LocalDate startDate, LocalDate endDate) {

        TaskFilter filter = new TaskFilter();

        if (isAssigneeSelected(assignee)) {
            filter.addCondition(TaskFilterField.ASSIGNEE, assignee);
        }

        if (isDateSelected(startDate)) {
            filter.addCondition(TaskFilterField.START_DATE, startDate);
        }

        if (isDateSelected(endDate)) {
            filter.addCondition(TaskFilterField.END_DATE, endDate);
        }

        return filter;

    }

    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public List<Task> getFilteredTasks(String assignee, LocalDate startDate, LocalDate endDate) {
        return taskDao.getFilteredTasks(buildFilter(assignee, startDate, endDate));
    }

    public List<String> getUniqueAssignees() {
        return taskDao.getUniqueAssignees();
    }

}
