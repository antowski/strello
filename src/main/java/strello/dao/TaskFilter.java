package strello.dao;

import java.util.LinkedHashMap;

public class TaskFilter {

    private final LinkedHashMap<TaskFilterField, Object> conditions = new LinkedHashMap<>();

    public void addCondition(TaskFilterField field, Object value) {
        conditions.put(field, value);
    }

    String getWhereClause() {

        StringBuilder whereClause = new StringBuilder(" WHERE TRUE");

        conditions.forEach((entry, value) -> whereClause
                .append(" AND ")
                .append(entry.getColumnName())
                .append(entry.getTypeOfComparison())
                .append("?"));

        return whereClause.toString();

    }

    Object[] getQueryArgs() {
        return conditions.values().toArray();
    }

    int[] getQueryArgTypes() {

        int[] argTypes = new int[conditions.size()];

        int i = 0;
        for (TaskFilterField field : conditions.keySet()) {
            argTypes[i] = field.getSqlType();
            i++;
        }

        return argTypes;
    }

}
