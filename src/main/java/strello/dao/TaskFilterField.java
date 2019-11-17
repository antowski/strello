package strello.dao;

/*
 * Перечень полей, по которым можно установить фильтр для отбора задач
 */
public enum TaskFilterField {

    ASSIGNEE("assignee", java.sql.Types.VARCHAR);

    private final String columnName;
    private final int sqlType;

    TaskFilterField(String columnName, int sqlType) {
        this.columnName = columnName;
        this.sqlType = (int) sqlType;
    }

    public String getColumnName() {
        return columnName;
    }

    public int getSqlType() {
        return sqlType;
    }
}
