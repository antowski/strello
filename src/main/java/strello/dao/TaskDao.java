package strello.dao;

import strello.model.Task;

import java.util.List;

public interface TaskDao {

    List<Task> getAllTasks();

    List<String> getUniqueAssignees();

    List<Task> getFilteredTasks(TaskFilter filter);

    void addTask(Task task);
}
