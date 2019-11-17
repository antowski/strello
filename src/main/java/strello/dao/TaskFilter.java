package strello.dao;

import java.util.LinkedHashMap;
import java.util.Map;

class TaskFilter {

    private LinkedHashMap<TaskFilterField, Object> conditions = new LinkedHashMap<>();

    void addCondition(TaskFilterField field, Object value) {
        conditions.put(field, value);
    }

    String getWhereClause() {

        StringBuilder whereClause = new StringBuilder(" WHERE TRUE");

        conditions.entrySet().forEach(entry -> {
            whereClause
                    .append(" AND ")
                    .append(entry.getKey().getColumnName())
                    .append(" = ?");
        });

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
