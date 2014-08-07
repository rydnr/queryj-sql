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
 * Filename: UpdateQuery.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents standard SQL update queries.
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Represents standard SQL update queries.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class UpdateQuery
    extends  WriteQuery
{
    /**
     * Constructs a query.
     */
    public UpdateQuery()
    {
        super();
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void set(final StringField field, final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void set(final IntField field, final int value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void set(final LongField field, final long value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void set(final DoubleField field, final double value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void set(final CalendarField field, final Calendar value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void set(final DateField field, final Date value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void set(final BigDecimalField field, final BigDecimal value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void set(final Field field, final Object value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     */
    public void set(@NotNull final Field field)
    {
        addField(field);
        addVariableCondition(field.equals());
    }

    /**
     * Indicates which table the update applies to.
     * @param table the table.
     */
    public void update(@NotNull final Table table)
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
     * Outputs a text version of the query, in SQL format.
     * @return the SQL query.
     */
    @NotNull
    @Override
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
    @NotNull
    protected String toString(
        @NotNull final Table table,
        @NotNull final List<Field> fields,
        @Nullable final List<Condition> conditions,
        @NotNull final QueryUtils queryUtils)
    {
        @NotNull final StringBuilder t_sbResult = new StringBuilder();

        t_sbResult.append("UPDATE ");

        t_sbResult.append(table);

        t_sbResult.append(" SET ");

        @NotNull final List<String> t_alValues = new ArrayList<String>();

        @NotNull final Iterator<Field> t_FieldIterator = fields.iterator();

        while  (t_FieldIterator.hasNext())
        {
            @NotNull final Field t_Field = t_FieldIterator.next();

            @NotNull String t_strValue;

            @Nullable final Object t_Value = getValue(t_Field);

            if  (t_Value == null)
            {
                t_strValue = "?";
            }
            else
            {
                t_strValue = "" + t_Value;

                if  (queryUtils.shouldBeEscaped(t_Value))
                {
                    t_strValue = "'" + t_strValue + "'";
                }
            }

            t_alValues.add(
                t_Field.toSimplifiedString() + " = " + t_strValue);
        }

        t_sbResult.append(
            queryUtils.concatenate(t_alValues, ", "));

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
