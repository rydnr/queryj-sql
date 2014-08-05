//;-*- mode: java -*-
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
 * Filename: DeleteQuery.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents standard SQL delete queries.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.queryj.sql.BigDecimalField;
import org.acmsl.queryj.sql.CalendarField;
import org.acmsl.queryj.sql.Condition;
import org.acmsl.queryj.sql.DoubleField;
import org.acmsl.queryj.sql.Field;
import org.acmsl.queryj.sql.IntField;
import org.acmsl.queryj.sql.LongField;
import org.acmsl.queryj.sql.Query;
import org.acmsl.queryj.sql.Table;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Importing some JDK classes.
 */
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Represents standard SQL delete queries.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class DeleteQuery
    extends  WriteQuery
{
    /**
     * Constructs a query.
     */
    public DeleteQuery()
    {
        super();
    }

    /**
     * Indicates which table the delete applies to.
     * @param table the table.
     */
    public void deleteFrom(@NotNull final Table table)
    {
        setTable(table);
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
        addCondition(variableCondition);
        addVariableCondition(variableCondition);
    }

    // Serialization methods //

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public String toString()
    {
        return
            toString(
                getTable(),
                getFields(),
                getConditions(),
                QueryUtils.getInstance());
    }

    /**
     * Outputs a text version of the query, in SQL format.
     * @param table the table.
     * @param fields the fields.
     * @param conditions the conditions.
     * @param queryUtils the <code>QueryUtils</code> instance.
     * @return the SQL query.
     */
    protected String toString(
        @NotNull final Table table,
        @SuppressWarnings("unused") @NotNull final List fields,
        @Nullable final List conditions,
        @NotNull final QueryUtils queryUtils)
    {
        @NotNull final StringBuilder t_sbResult = new StringBuilder();

        t_sbResult.append("DELETE FROM ");

        t_sbResult.append(table);

        if  (   (conditions != null)
             && (conditions.size() > 0))
        {
            t_sbResult.append(" WHERE ");

            t_sbResult.append(
                queryUtils.concatenate(conditions, " AND ", true));
        }

        return t_sbResult.toString();
    }
}
