package it.unibz.taskcalendarservice;

import java.util.*;

/**
 * QueryBuilder class to build SQL queries
 */
public class QueryBuilder {
    private String tableName;
    private List<String> joinTableName;
    private List<String> joinOnClause;
    private List<String> joinType;
    private List<String> selectColumns = new ArrayList<>();
    private List<String> whereClauses = new ArrayList<>();
    private String orderByColumn;

    /**
     * @param columns columns to select
     * @return this QueryBuilder
     */
    public QueryBuilder select(String... columns) {
        this.selectColumns.addAll(Arrays.asList(columns));
        return this;
    }

    /**
     * @param tableName table name to select from
     * @return this QueryBuilder
     */
    public QueryBuilder from(String tableName) {
        this.tableName = tableName;
        return this;
    }

    /**
     * @param tableName table name to join
     * @param onClause Clause to join on
     * @param joinType Optional join type, defaults to INNER
     * @return this QueryBuilder
     */
    public QueryBuilder join(String tableName, String onClause, Optional<String> joinType) {
        String joinTypeString = joinType.orElse("INNER");
        this.joinTableName.add(tableName);
        this.joinOnClause.add(onClause);
        this.joinType.add(joinTypeString);
        return this;
    }

    /**
     * @param whereClause where clause to add
     * @return this QueryBuilder
     */
    public QueryBuilder where(String whereClause) {
        this.whereClauses.add(whereClause);
        return this;
    }

        public QueryBuilder orderBy(String orderByColumn) {
        this.orderByColumn = orderByColumn;
        return this;
    }

    /**
     * @return the built query
     */
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        if (selectColumns.isEmpty()) {
            sb.append("*");
        } else {
            sb.append(String.join(", ", selectColumns));
        }
        sb.append(" FROM ").append(tableName);
        if (!joinTableName.isEmpty()) {
            for (int i = 0; i < joinTableName.size(); i++) {
                sb.append(" ").append(joinType.get(i)).append(" JOIN ").append(joinTableName.get(i)).append(" ON ").append(joinOnClause.get(i));
            }
        }
        if (!whereClauses.isEmpty()) {
            sb.append(" WHERE ");
            sb.append(String.join(" AND ", whereClauses));
        }
        if (orderByColumn != null) {
            sb.append(" ORDER BY ").append(orderByColumn);
        }
        return sb.toString();
    }

}
