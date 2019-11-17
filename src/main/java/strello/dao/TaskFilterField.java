package strello.dao;

/*
 * Перечень полей, по которым можно установить фильтр для отбора задач
 */
public enum TaskFilterField {

    ASSIGNEE("assignee", java.sql.Types.VARCHAR, " = "),
    START_DATE("start_date", java.sql.Types.DATE, " >= "),
    END_DATE("end_date", java.sql.Types.DATE, " <= ");

    private final String columnName;
    private final int sqlType;
    private final String typeOfComparison;

    TaskFilterField(String columnName, int sqlType, String typeOfComparison) {
        this.columnName = columnName;
        this.sqlType = sqlType;
        this.typeOfComparison = typeOfComparison;
    }

    public String getColumnName() {
        return columnName;
    }

    public int getSqlType() {
        return sqlType;
    }

    public String getTypeOfComparison() {
        return typeOfComparison;
    }

}
