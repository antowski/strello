package strello.dao;

/*
 * Перечень полей, по которым можно установить фильтр для отбора задач
 */
public enum TaskFilterField {

    ASSIGNEE("assignee");

    private final String columnName;

    TaskFilterField(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

}
