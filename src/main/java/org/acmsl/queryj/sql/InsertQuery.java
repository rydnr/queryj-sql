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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
     * @precondition field != null
     */
    public void value(final StringField field, final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     * @precondition field != null
     */
    public void value(
        final StringField field, final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final IntField field, final int value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final IntField field, final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     * @precondition field != null
     */
    public void value(
        final IntField field, final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final LongField field, final long value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final LongField field, final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     * @precondition field != null
     */
    public void value(
        final LongField field, final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final DoubleField field, final double value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final DoubleField field, final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     * @precondition field != null
     */
    public void value(
        final DoubleField field, final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final CalendarField field, final Calendar value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final CalendarField field, final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     * @precondition field != null
     */
    public void value(
        final CalendarField field, final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final DateField field, final Date value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final DateField field, final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     * @precondition field != null
     */
    public void value(
        final DateField field, final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final BigDecimalField field, final BigDecimal value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final BigDecimalField field, final String value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     * @precondition field != null
     */
    public void value(
        final BigDecimalField field, final String value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @precondition field != null
     */
    public void value(final Field field, final Object value)
    {
        addValue(field, value);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to forcing value escaping or not.
     * @precondition field != null
     */
    public void value(
        final Field field, final Object value, final boolean escape)
    {
        addValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @precondition field != null
     */
    public void value(@NotNull final Field field)
    {
        addField(field);
        addVariableCondition(field.equals());
    }

    /**
     * Indicates which table the insert applies to.
     * @param table the table.
     * @precondition table != null
     */
    public void insertInto(final Table table)
    {
        setTable(table);
    }

    // Serialization methods //

    /**
     * Outputs a text version of the query, in SQL format.
     * @return the SQL query.
     */
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
     * @precondition table != null
     * @precondition fields != null
     * @precondition queryUtils != null
     */
    protected String toString(
        final Table table,
        @NotNull final List fields,
        @NotNull final QueryUtils queryUtils)
    {
        @NotNull StringBuffer t_sbResult = new StringBuffer();

        t_sbResult.append("INSERT INTO ");

        t_sbResult.append(table);

        t_sbResult.append(" ( ");

        @NotNull List t_alBriefFields = new ArrayList();
        @NotNull List t_alValues = new ArrayList();

        Iterator t_FieldIterator = fields.iterator();

        while  (   (t_FieldIterator != null)
                && (t_FieldIterator.hasNext()))
        {
            @NotNull Field t_Field = (Field) t_FieldIterator.next();

            if  (t_Field != null)
            {
                t_alBriefFields.add(t_Field.toSimplifiedString());

                @NotNull String t_strValue = "";

                Object t_Value = getValue(t_Field);

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
