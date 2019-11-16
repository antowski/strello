package strello.dao;

import strello.model.Task;

import java.util.List;

public interface TaskDao {

    List<Task> getTasks();

    List<String> getUniqueAssignees();
}
