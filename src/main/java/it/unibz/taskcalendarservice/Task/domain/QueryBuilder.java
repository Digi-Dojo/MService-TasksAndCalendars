package it.unibz.taskcalendarservice.Task.domain;

import java.util.*;

/**
 * QueryBuilder class to build SQL queries
 */
public class QueryBuilder {
    private String tableName;
    private List<String> joinTableName = new ArrayList<>();
    private List<String> joinOnClause = new ArrayList<>();
    private List<String> joinType = new ArrayList<>();
    private List<String> setClause = new ArrayList<>();
    private List<String> selectColumns = new ArrayList<>();
    private List<String> insertValues = new ArrayList<>();
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
     * @param joinType Optional join type, defaults to LEFT
     * @return this QueryBuilder
     */
    public QueryBuilder join(String tableName, String onClause, Optional<String> joinType) {
        String joinTypeString = joinType.orElse("LEFT");
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

    /**
     * @param orderByColumn column to order by
     * @return this QueryBuilder
     */
    public QueryBuilder orderBy(String orderByColumn) {
        this.orderByColumn = orderByColumn;
        return this;
    }

    /**
     * @param tableName table name to update
     * @return this QueryBuilder
     */
    public QueryBuilder update(String tableName) {
        this.tableName = tableName;
        return this;
    }

    /**
     * @param setClause set clause to add
     * @return this QueryBuilder
     */
    public QueryBuilder set(String setClause) {
        this.setClause.add(setClause);
        return this;
    }

    public QueryBuilder delete(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder insert(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder value(String column, String value) {
        this.selectColumns.add(column);
        this.insertValues.add(value);
        return this;
    }

    /**
     * Builds the Insert query
     *
     * @return the built query
     */
    public String buildInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(tableName);
        sb.append(" (").append(String.join(", ", selectColumns)).append(")");
        sb.append(" VALUES (").append(String.join(", ", Collections.nCopies(insertValues.size(), "?"))).append(")");
        return sb.toString();
    }

    /**
     * Builds the Select query
     *
     * @return the built query
     */
    public String buildSelect() {
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
                sb.append(" ").append(joinType.get(i)).append(" JOIN ").append(joinTableName.get(i));
                sb.append(" ON ").append(joinOnClause.get(i));
            }
        }
        if (!whereClauses.isEmpty()) {
            sb.append(" WHERE ");
            sb.append(String.join(" AND ", whereClauses));
        }
        else {
            sb.append(" WHERE 1");
        }
        if (orderByColumn != null) {
            sb.append(" ORDER BY ").append(orderByColumn);
        }
        return sb.toString();
    }

    /**
     * Builds the Update query
     *
     * @return the built query
     */
    public String buildUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(tableName);
        if (!setClause.isEmpty()) {
            sb.append(" SET ").append(String.join(", ", setClause));
        }
        if (!joinTableName.isEmpty()) {
            for (int i = 0; i < joinTableName.size(); i++) {
                sb.append(" ").append(joinType.get(i)).append(" JOIN ").append(joinTableName.get(i));
                sb.append(" ON ").append(joinOnClause.get(i));
            }
        }
        if (!whereClauses.isEmpty()) {
            sb.append(" WHERE ");
            sb.append(String.join(" AND ", whereClauses));
        }
        else {
            sb.append(" WHERE 1");
        }
        return sb.toString();
    }

    public String buildDelete() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").append(tableName);
        if (!whereClauses.isEmpty()) {
            sb.append(" WHERE ");
            sb.append(String.join(" AND ", whereClauses));
        }
        else {
            sb.append(" WHERE 1");
        }
        return sb.toString();
    }
}
