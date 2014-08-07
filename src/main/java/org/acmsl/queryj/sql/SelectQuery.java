/*
                        QueryJ

    Copyright (C) 2002-today  Jose San Leandro Armendariz
                              chous@acm-sl.org

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the License, or any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    General Public License for more details.

    You should have received a copy of the GNU General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

    Thanks to ACM S.L. for distributing this library under the GPL license.
    Contact info: jose.sanleandro@acm-sl.com

 ******************************************************************************
 *
 * Filename: SelectQuery.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Standard SQL select query.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing NotNull annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Importing some JDK classes.
 */
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents standard SQL select queries.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class SelectQuery
    extends  Query
{
    /**
     * The ordering fields.
     */
    private List<Field> m__lOrderingFields;

    /**
     * The grouping fields.
     */
    private List<Field> m__lGroupingFields;

    /**
     * Constructs a query.
     */
    public SelectQuery()
    {
        super();
        immutableSetOrderingFields(new ArrayList<Field>());
        immutableSetGroupingFields(new ArrayList<Field>());
    }

    /**
     * Specifies new ordering field collection.
     * @param list the new list.
     */
    private void immutableSetOrderingFields(@NotNull final List<Field> list)
    {
        m__lOrderingFields = list;
    }

    /**
     * Specifies new ordering field collection.
     * @param list the new list.
     */
    @SuppressWarnings("unused")
    protected void setOrderingFields(@NotNull final List<Field> list)
    {
        immutableSetOrderingFields(list);
    }

    /**
     * Retrieves the ordering field collection.
     * @return such list.
     */
    @NotNull
    protected List<Field> getOrderingFields()
    {
        return m__lOrderingFields;
    }

    /**
     * Adds a new ordering field.
     * @param orderingField the ordering field to add.
     */
    protected void addOrderingField(@NotNull final Field orderingField)
    {
        addOrderingField(orderingField, getOrderingFields());
    }

    /**
     * Adds a new ordering field to given collection.
     * @param orderingField the ordering field to add.
     * @param orderingFields the ordering fields.
     */
    protected void addOrderingField(
        @NotNull final Field orderingField, @NotNull final List<Field> orderingFields)
    {
        orderingFields.add(orderingField);
    }

    /**
     * Retrieves the position of given ordering field on the query.
     * @param field the field to find.
     * @return its position, or -1 if such field doesn't belong to
     * this query.
     */
    @SuppressWarnings("unused")
    protected int getOrderingFieldIndex(@NotNull final Field field)
    {
        return getIndex(getOrderingFields(), field);
    }

    /**
     * Specifies new grouping field collection.
     * @param list the new list.
     */
    protected final void immutableSetGroupingFields(@NotNull final List<Field> list)
    {
        m__lGroupingFields = list;
    }

    /**
     * Specifies new grouping field collection.
     * @param list the new list.
     */
    @SuppressWarnings("unused")
    protected void setGroupingFields(@NotNull final List<Field> list)
    {
        immutableSetGroupingFields(list);
    }

    /**
     * Retrieves the grouping field collection.
     * @return such list.
     */
    @NotNull
    protected List<Field> getGroupingFields()
    {
        return m__lGroupingFields;
    }

    /**
     * Adds a new grouping field.
     * @param groupingField the grouping field to add.
     */
    protected void addGroupingField(@NotNull final Field groupingField)
    {
        addGroupingField(groupingField, getGroupingFields());
    }

    /**
     * Adds a new grouping field.
     * @param groupingField the grouping field to add.
     * @param groupingFields the grouping fields.
     */
    protected void addGroupingField(
        @NotNull final Field groupingField, @NotNull final List<Field> groupingFields)
    {
        groupingFields.add(groupingField);
    }

    /**
     * Retrieves the position of given grouping field on the query.
     * @param field the field to find.
     * @return its position, or -1 if such field doesn't belong to
     * this query.
     */
    @SuppressWarnings("unused")
    protected int getGroupingFieldIndex(@NotNull final Field field)
    {
        return getIndex(getGroupingFields(), field);
    }

    /**
     * Selects a field.
     * @param field the field to select.
     */
    public void select(@NotNull final Field field)
    {
        addField(field);
    }

    /**
     * Selects the fields (typically, via <code>Table.getAll</code>
     * meaning <i>select </i>.
     * @param fields the fields to select.
     */
    public void select(@NotNull final Field[] fields)
    {
        final int t_iCount = fields.length;

        Field t_Field;

        for  (int t_iIndex = 0; t_iIndex < t_iCount; t_iIndex++)
        {
            t_Field = fields[t_iIndex];

            if (t_Field != null)
            {
                addField(t_Field);
            }
        }
    }

    /**
     * Indicates which table participates in the query.
     * @param table the table.
     */
    public void from(@NotNull final Table table)
    {
        addTable(table);
    }

    /**
     * Indicates a query condition.
     * @param condition such condition.
     */
    public void where(@NotNull final Condition condition)
    {
        addCondition(condition);
    }

    /**
     * Indicates a query variable condition.
     * @param variableCondition such variable condition.
     */
    public void where(@NotNull final VariableCondition variableCondition)
    {
        addCondition(variableCondition, false);
        addVariableCondition(variableCondition);
    }

    /**
     * Indicates a field to be used to group the results.
     * @param groupingField such field.
     */
    @SuppressWarnings("unused")
    public void groupBy(@NotNull final Field groupingField)
    {
        addGroupingField(groupingField);
    }

    /**
     * Indicates a field to be used to order the results.
     * @param orderingField such field.
     */
    @SuppressWarnings("unused")
    public void orderBy(@NotNull final Field orderingField)
    {
        addOrderingField(orderingField);
    }

    /**
     * Looks for the records that match the filter.
     * @return the result set.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    @Nullable
    public QueryResultSet retrieveMatchingResults()
        throws  SQLException
    {
        @Nullable QueryResultSet result = null;

        @Nullable final Statement t_Statement = getPreparedStatement();

        if  (t_Statement != null)
        {
            result =
                new QueryResultSet(
                    this,
                    t_Statement.executeQuery(toString()));
        }

        return result;
    }

    /**
     * Executes given update operation using field references.
     * @param sql (Taken from Sun Javadoc) must be an SQL INSERT, UPDATE
     * or DELETE statement or an SQL statement that returns nothing.
     * @param fields the fields.
     * @return (Taken from Sun Javadoc) either the row count for INSERT,
     * UPDATE or DELETE statements, or 0 for SQL statements that return
     * nothing.
     * @throws SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public int executeUpdate(@NotNull final String sql, @NotNull final Field[] fields)
        throws  SQLException
    {
        return
            executeUpdate(
                sql, fields, getPreparedStatement());
    }

    /**
     * Executes given update operation using field references.
     * @param sql (Taken from Sun Javadoc) must be an SQL INSERT, UPDATE
     * or DELETE statement or an SQL statement that returns nothing.
     * @param fields the fields.
     * @param preparedStatement the {@link PreparedStatement}.
     * @return (Taken from Sun Javadoc) either the row count for INSERT,
     * UPDATE or DELETE statements, or 0 for SQL statements that return
     * nothing.
     * @throws SQLException if an error occurs.
     */
    protected int executeUpdate(
        @NotNull final String sql,
        @NotNull final Field[] fields,
        @Nullable final PreparedStatement preparedStatement)
      throws  SQLException
    {
        final int result;

        if  (preparedStatement == null) 
        {
            throw new SQLException("No statement created yet!");
        }
        else
        {
            @NotNull final int[] t_aiIndexes = new int[fields.length];

            for  (int t_iIndex = 0; t_iIndex < fields.length; t_iIndex++) 
            {
                t_aiIndexes[t_iIndex] = getFieldIndex(fields[t_iIndex]);
            }

            result = preparedStatement.executeUpdate(sql, t_aiIndexes);
        }

        return result;
    }

    /**
     * Executes given update operation using field references.
     * @param sql (Taken from Sun Javadoc) must be an SQL INSERT, UPDATE
     * or DELETE statement or an SQL statement that returns nothing.
     * @param fields the fields.
     * @return (Taken from Sun Javadoc) true if the first result is a
     * ResultSet object; false if it is an update count or there are no
     * results.
     * @throws SQLException if an error occurs.
     */
    public boolean execute(@NotNull final String sql, @NotNull final Field[] fields)
        throws  SQLException
    {
        return execute(sql, fields, getPreparedStatement());
    }

    /**
     * Executes given update operation using field references.
     * @param sql (Taken from Sun Javadoc) must be an SQL INSERT, UPDATE
     * or DELETE statement or an SQL statement that returns nothing.
     * @param fields the fields.
     * @param preparedStatement the {@link PreparedStatement}.
     * @return (Taken from Sun Javadoc) true if the first result is a
     * ResultSet object; false if it is an update count or there are no
     * results.
     * @throws SQLException if an error occurs.
     */
    protected boolean execute(
        @NotNull final String sql,
        @NotNull final Field[] fields,
        @Nullable final PreparedStatement preparedStatement)
      throws  SQLException
    {
        final boolean result;

        if  (preparedStatement == null) 
        {
            throw new SQLException("No statement created yet!");
        }
        else
        {
            @NotNull final int[] t_aiIndexes = new int[fields.length];

            for  (int t_iIndex = 0; t_iIndex < fields.length; t_iIndex++) 
            {
                t_aiIndexes[t_iIndex] = getFieldIndex(fields[t_iIndex]);
            }

            result = preparedStatement.execute(sql, t_aiIndexes);
        }

        return result;
    }

    /**
     * Processes given tables to appear in "FROM" clauses.
     * @param tables the tables to process.
     * @param separator the separator.
     * @return the processed tables.
     */
    @NotNull
    protected List<String> processTables(@NotNull final List<Table> tables, @NotNull final String separator)
    {
        @NotNull final List<String> result = new ArrayList<String>();

        @NotNull final Iterator<Table> t_itTables = tables.iterator();

        @Nullable Table t_CurrentTable;

        @Nullable TableAlias t_CurrentTableAlias;

        @Nullable StringBuilder t_sbCurrentTable;

        while  (t_itTables.hasNext())
        {
            t_CurrentTable = t_itTables.next();

            if  (t_CurrentTable != null)
            {
                t_sbCurrentTable = new StringBuilder();

                t_sbCurrentTable.append(t_CurrentTable.getName());

                t_CurrentTableAlias = t_CurrentTable.getTableAlias();

                if  (t_CurrentTableAlias != null)
                {
                    t_sbCurrentTable.append(separator);
                    t_sbCurrentTable.append(t_CurrentTableAlias);
                }

                result.add(t_sbCurrentTable.toString());
            }
        }
        
        return result;
    }

    // Serialization methods //

    /**
     * Outputs a text version of the query, in SQL format.
     * @return the SQL query.
     */
    @Override
    @NotNull
    public String toString()
    {
        return
            toString(
                getTables(),
                getFields(),
                getConditions(),
                getGroupingFields(),
                getOrderingFields(),
                QueryUtils.getInstance());
    }

    /**
     * Outputs a text version of the query, in SQL format.
     * @param tables the tables.
     * @param fields the fields.
     * @param conditions the conditions.
     * @param groupingFields the <i>group by</i> fields.
     * @param orderingFields the <i>order by</i> fields.
     * @param queryUtils the {@link QueryUtils} instance.
     * @return the SQL query.
     */
    @NotNull
    protected String toString(
        @NotNull final List<Table> tables,
        @NotNull final List<Field> fields,
        @NotNull final List<Condition> conditions,
        @NotNull final List<Field> groupingFields,
        @NotNull final List<Field> orderingFields,
        @NotNull final QueryUtils queryUtils)
    {
        @NotNull final StringBuilder t_sbResult = new StringBuilder();

        t_sbResult.append("SELECT ");

        t_sbResult.append(
            queryUtils.concatenate(fields, ", "));

        t_sbResult.append(" FROM ");

        t_sbResult.append(
            queryUtils.concatenate(processTables(tables, " "), ", "));

        if  (conditions.size() > 0)
        {
            t_sbResult.append(" WHERE ");

            t_sbResult.append(
                queryUtils.concatenate(conditions, " AND "));
        }

        if  (groupingFields.size() > 0)
        {
            t_sbResult.append(" GROUP BY ");

            t_sbResult.append(
                queryUtils.concatenate(groupingFields, ", "));
        }
            
        if  (orderingFields.size() > 0)
        {
            t_sbResult.append(" ORDER BY ");

            t_sbResult.append(
                queryUtils.concatenate(orderingFields, ", "));
        }

        return t_sbResult.toString();
    }
}
