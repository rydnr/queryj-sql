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
 * Filename: InsertQuery.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents standard SQL insert queries.
 */
package org.acmsl.queryj.sql;

/*
 * Importing Jetbrains annotations.
 */
import org.jetbrains.annotations.NotNull;

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
 * Represents standard SQL insert queries.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class InsertQuery
    extends  WriteQuery
{
    /**
     * Constructs a query.
     */
    public InsertQuery()
    {
        super();
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final StringField field, @NotNull final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     */
    public void value(
        @NotNull final StringField field, @NotNull final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final IntField field, final int value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final IntField field, @NotNull final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     */
    public void value(
        @NotNull final IntField field, @NotNull final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final LongField field, final long value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final LongField field, @NotNull final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     */
    public void value(
        @NotNull final LongField field, @NotNull final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final DoubleField field, final double value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final DoubleField field, @NotNull final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     */
    public void value(
        @NotNull final DoubleField field, @NotNull final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final CalendarField field, @NotNull final Calendar value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final CalendarField field, @NotNull final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     */
    public void value(
        @NotNull final CalendarField field, @NotNull final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final DateField field, @NotNull final Date value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final DateField field, @NotNull final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     */
    public void value(
        @NotNull final DateField field, @NotNull final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final BigDecimalField field, @NotNull final BigDecimal value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final BigDecimalField field, @NotNull final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     */
    public void value(
        @NotNull final BigDecimalField field, @NotNull final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    public void value(@NotNull final Field field, @NotNull final Object value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     */
    public void value(
        @NotNull final Field field, @NotNull final Object value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     */
    public void value(@NotNull final Field field)
    {
        addField(field);
        addVariableCondition(field.equals());
    }

    /**
     * Indicates which table the insert applies to.
     * @param table the table.
     */
    public void insertInto(@NotNull final Table table)
    {
        setTable(table);
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
                getTable(),
                getFields(),
                QueryUtils.getInstance());
    }

    /**
     * Outputs a text version of the query, in SQL format.
     * @param table the table.
     * @param fields the fields.
     * @param queryUtils the <code>QueryUtils</code> instance.
     * @return the SQL query.
     */
    @NotNull
    protected String toString(
        @NotNull final Table table,
        @NotNull final List<Field> fields,
        @NotNull final QueryUtils queryUtils)
    {
        @NotNull final StringBuilder t_sbResult = new StringBuilder();

        t_sbResult.append("INSERT INTO ");

        t_sbResult.append(table);

        t_sbResult.append(" ( ");

        @NotNull final List<String> t_alBriefFields = new ArrayList<String>();
        @NotNull final List<String> t_alValues = new ArrayList<String>();

        final Iterator<Field> t_FieldIterator = fields.iterator();

        while  (t_FieldIterator.hasNext())
        {
            @NotNull final Field t_Field = t_FieldIterator.next();

            if  (t_Field != null)
            {
                t_alBriefFields.add(t_Field.toSimplifiedString());

                @NotNull String t_strValue;

                final Object t_Value = getValue(t_Field);

                if  (t_Value == null)
                {
                    t_strValue = "?";
                }
                else 
                {
                    t_strValue = "" + t_Value;

                    if  (shouldBeEscaped(t_Value))
                    {
                        t_strValue = "'" + t_strValue + "'";
                    }
                }

                t_alValues.add(t_strValue);
            }
        }

        t_sbResult.append(
            queryUtils.concatenate(t_alBriefFields, ", "));

        t_sbResult.append(" ) VALUES ( ");

        t_sbResult.append(
            queryUtils.concatenate(t_alValues, ", "));

        t_sbResult.append(" )");

        return t_sbResult.toString();
    }
}
