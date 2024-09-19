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
 * Filename: WriteQuery.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents SQL write operations.
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
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents standard SQL insert queries.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public abstract class WriteQuery
    extends  Query
{
    /**
     * The values.
     */
    private Map<Field, ?> m__mValues;

    /**
     * The table.
     */
    private Table m__Table;

    /**
     * The escaping flags.
     */
    private Map<String, Boolean> m__mEscapingFlags;

    /**
     * Constructs a query.
     */
    public WriteQuery()
    {
        super();

        immutableSetValues(new HashMap<Field, Object>());
        immutableSetEscapingFlags(new HashMap<String, Boolean>());
    }

    /**
     * Specifies the table.
     * @param table the table.
     */
    protected final void immutableSetTable(@NotNull final Table table)
    {
        m__Table = table;
    }

    /**
     * Specifies the table.
     * @param table the table.
     */
    protected void setTable(@NotNull final Table table)
    {
        immutableSetTable(table);
    }

    /**
     * Retrieves the table.
     * @return such table.
     */
    @NotNull
    protected Table getTable()
    {
        return m__Table;
    }

    /**
     * Specifies new value collection.
     * @param map the new map.
     */
    protected final void immutableSetValues(@NotNull final Map<Field, ?> map)
    {
        m__mValues = map;
    }

    /**
     * Specifies new value collection.
     * @param map the new map.
     */
    protected void setValues(@NotNull final Map<Field, ?> map)
    {
        immutableSetValues(map);
    }

    /**
     * Retrieves the value collection.
     * @return such map.
     * @param <F> the field type.
     */
    @SuppressWarnings("unchecked")
    @NotNull
    protected <F> Map<F, String> getValues()
    {
        return (Map<F, String>) m__mValues;
    }

    /**
     * Specifies the escaping flags.
     * @param map such map.
     */
    protected final void immutableSetEscapingFlags(final Map<String, Boolean> map)
    {
        m__mEscapingFlags = map;
    }

    /**
     * Specifies the escaping flags.
     * @param map such map.
     */
    @SuppressWarnings("unused")
    protected void setEscapingFlags(@NotNull final Map<String, Boolean> map)
    {
        immutableSetEscapingFlags(map);
    }

    /**
     * Retrieves the escaping flags.
     * @return such map.
     */
    @NotNull
    protected Map<String,Boolean> getEscapingFlags()
    {
        return m__mEscapingFlags;
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     */
    @SuppressWarnings("unused")
    protected void putValue(@NotNull final StringField field, @Nullable final String value)
    {
        putValue(field, value, true);
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    @SuppressWarnings("unchecked")
    protected void putValue(
        @NotNull final StringField field, @Nullable final String value, final boolean escape)
    {
        putValue(field, value, escape, getValues(), getEscapingFlags());
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     * @param values the values.
     * @param escapingFlags the escaping flags.
     * @param <F> the type.
     */
    protected <F> void putValue(
        @NotNull final F field,
        @Nullable final String value,
        final boolean escape,
        @NotNull final Map<F, String> values,
        @NotNull final Map<String, Boolean> escapingFlags)
    {
        @NotNull final String t_strValue;

        if  (value == null)
        {
            t_strValue = "null";
        }
        else
        {
            t_strValue = value;
        }

        if  (!escape)
        {
            escapingFlags.put(buildEscapingFlagKey(t_strValue), Boolean.FALSE);
        }

        values.put(field, t_strValue);
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     */
    @SuppressWarnings("unchecked")
    protected void putValue(@NotNull final IntField field, final int value)
    {
        putValue(field, "" + value);
    }

    /**
     * Puts a new keyword-based value.
     * @param field the field.
     * @param value the value.
     */
    protected void putValue(@NotNull final IntField field, @Nullable final String value)
    {
        putValue(field, value, true);
    }

    /**
     * Puts a new keyword-based value.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void putValue(
        @NotNull final IntField field, @Nullable final String value, final boolean escape)
    {
        putValue(field, value, escape, getValues(), getEscapingFlags());
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     */
    protected void putValue(@NotNull final LongField field, final long value)
    {
        putValue(field, "" + value);
    }

    /**
     * Puts a new keyword-based value.
     * @param field the field.
     * @param value the value.
     */
    protected void putValue(@NotNull final LongField field, @Nullable final String value)
    {
        putValue(field, value, true);
    }

    /**
     * Puts a new keyword-based value.
     * @param field the field.
     * @param value the value.
     * @param escape whether to escape the value or not.
     */
    protected void putValue(
        @NotNull final LongField field, @Nullable final String value, final boolean escape)
    {
        putValue(field, value, escape, getValues(), getEscapingFlags());
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     */
    protected void putValue(@NotNull final DoubleField field, final double value)
    {
        putValue(field, "" + value);
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void putValue(
        @NotNull final DoubleField field, @Nullable final String value, final boolean escape)
    {
        putValue(field, "" + value, escape);
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     */
    protected void putValue(@NotNull final CalendarField field, @NotNull final Calendar value)
    {
        putValue(field, DateFormat.getTimeInstance().format(value));
    }

    /**
     * Puts a new keyword.
     * @param field the field.
     * @param value the value.
     */
    protected void putValue(@NotNull final CalendarField field, @Nullable final String value)
    {
        putValue(field, value, true);
    }

    /**
     * Puts a new keyword.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void putValue(
        @NotNull final CalendarField field, @Nullable final String value, final boolean escape)
    {
        putValue(field, value, escape, getValues(), getEscapingFlags());
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     */
    protected void putValue(@NotNull final DateField field, @NotNull final Date value)
    {
        @NotNull final Calendar t_Calendar = Calendar.getInstance();
        t_Calendar.setTime(value);
        putValue(field, t_Calendar);
    }

    /**
     * Puts a new keyword.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void putValue(
        @NotNull final DateField field, @Nullable final String value, final boolean escape)
    {
        putValue(field, value, escape, getValues(), getEscapingFlags());
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     */
    protected void putValue(
        @NotNull final BigDecimalField field, final BigDecimal value)
    {
        putValue(field, "" + value);
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     */
    protected void putValue(@NotNull final Field field, @Nullable final Object value)
    {
        putValue(field, value, true);
    }

    /**
     * Puts a new value.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void putValue(
        @NotNull final Field field, final Object value, final boolean escape)
    {
        putValue(field, "" + value, escape, getValues(), getEscapingFlags());
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @return the value.
     */
    protected Object getValue(@NotNull final Field field)
    {
        return getValue(field, getValues());
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @param values the values.
     * @return the value.
     */
    protected Object getValue(@NotNull final Field field, @NotNull final Map values)
    {
        return values.get(field);
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @return the value.
     */
    @NotNull
    protected String getValue(@NotNull final org.acmsl.queryj.sql.StringField field)
    {
        return getValue(field, getValues());
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @param values the values.
     * @return the value.
     */
    @NotNull
    protected String getValue(@NotNull final org.acmsl.queryj.sql.StringField field, @NotNull final Map values)
    {
        @NotNull String result = "null";

        @Nullable final Object t_Result = values.get(field);

        if  (t_Result instanceof String)
        {
            result = (String) t_Result;
        }

        return result;
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @return the value.
     */
    protected int getValue(@NotNull final IntField field)
    {
        return getValue(field, getValues());
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @param values the values.
     * @return the value.
     */
    protected int getValue(@NotNull final IntField field, @NotNull final Map values)
    {
        int result = -1;

        @Nullable final Object t_Result = values.get(field);

        if  (t_Result instanceof Integer)
        {
            result = ((Integer) t_Result).intValue();
        }

        return result;
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @return the value.
     */
    protected long getValue(@NotNull final LongField field)
    {
        return getValue(field, getValues());
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @param values the values.
     * @return the value.
     */
    protected long getValue(@NotNull final LongField field, @NotNull final Map values)
    {
        long result = -1;

        @Nullable final Object t_Result = values.get(field);

        if  (t_Result instanceof Long)
        {
            result = ((Long) t_Result).longValue();
        }

        return result;
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @return the value.
     */
    protected double getValue(@NotNull final DoubleField field)
    {
        return getValue(field, getValues());
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @param values the values.
     * @return the value.
     */
    protected double getValue(@NotNull final DoubleField field, @NotNull final Map values)
    {
        double result = -1.0;

        @Nullable final Object t_Result = values.get(field);

        if  (t_Result instanceof Double)
        {
            result = ((Double) t_Result).doubleValue();
        }

        return result;
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @return the value.
     */
    @Nullable
    protected Calendar getValue(@NotNull final CalendarField field)
    {
        return getValue(field, getValues());
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @param values the values.
     * @return the value.
     */
    @Nullable
    protected Calendar getValue(@NotNull final CalendarField field, @NotNull final Map values)
    {
        @Nullable Calendar result = null;

        @Nullable final Object t_Result = values.get(field);

        if  (t_Result instanceof Calendar)
        {
            result = (Calendar) t_Result;
        }

        return result;
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @return the value.
     */
    @Nullable
    protected BigDecimal getValue(@NotNull final BigDecimalField field)
    {
        return getValue(field, getValues());
    }

    /**
     * Retrieves the value of given field.
     * @param field the field.
     * @param values the values.
     * @return the value.
     */
    @Nullable
    protected BigDecimal getValue(
        @NotNull final BigDecimalField field, @NotNull final Map values)
    {
        @Nullable BigDecimal result = null;

        @Nullable final Object t_Result = values.get(field);

        if  (t_Result instanceof BigDecimal)
        {
            result = (BigDecimal) t_Result;
        }

        return result;
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final StringField field, @Nullable final String value)
    {
        addValue(field, value, true);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void addValue(
        @NotNull final StringField field, @Nullable final String value, final boolean escape)
    {
        addField(field);
        putValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final IntField field, final int value)
    {
        addField(field);
        putValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final IntField field, @Nullable final String value)
    {
        addValue(field, value, true);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void addValue(
        @NotNull final IntField field, @Nullable final String value, final boolean escape)
    {
        addField(field);
        putValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final LongField field, final long value)
    {
        addField(field);
        putValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final LongField field, @Nullable final String value)
    {
        addValue(field, value, true);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void addValue(
        @NotNull final LongField field, @Nullable final String value, final boolean escape)
    {
        addField(field);
        putValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final DoubleField field, final double value)
    {
        addField(field);
        putValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final DoubleField field, @Nullable final String value)
    {
        addValue(field, value, true);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void addValue(
        @NotNull final DoubleField field, @Nullable final String value, final boolean escape)
    {
        addField(field);
        putValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final CalendarField field, final Calendar value)
    {
        addField(field);
        putValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final CalendarField field, @Nullable final String value)
    {
        addValue(field, value, true);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void addValue(
        @NotNull final CalendarField field, @Nullable final String value, final boolean escape)
    {
        addField(field);
        putValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final DateField field, @Nullable final Date value)
    {
        addField(field);
        if (value != null)
        {
            putValue(field, value);
        }
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final DateField field, @Nullable final String value)
    {
        addValue(field, value, true);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void addValue(
        @NotNull final DateField field, @Nullable final String value, final boolean escape)
    {
        addField(field);
        putValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(
        @NotNull final BigDecimalField field, @NotNull final BigDecimal value)
    {
        addField(field);
        putValue(field, value);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final BigDecimalField field, @Nullable final String value)
    {
        addValue(field, value, true);
    }

    /**
     * Specifies the keyword of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void addValue(
        @NotNull final BigDecimalField field, @Nullable final String value, final boolean escape)
    {
        addField(field);
        putValue(field, value, escape);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     */
    protected void addValue(@NotNull final Field field, @NotNull final Object value)
    {
        addValue(field, value, true);
    }

    /**
     * Specifies the value of a field.
     * @param field the field.
     * @param value the value.
     * @param escape to force value escaping.
     */
    protected void addValue(
        @NotNull final Field field, @NotNull final Object value, final boolean escape)
    {
        addField(field);
        putValue(field, value, escape);
    }

    /**
     * Checks whether given value should be escaped or not.
     * @param value the value to check.
     * @return <code>true</code> if given value should be escaped.
     */
    protected boolean shouldBeEscaped(@NotNull final Object value)
    {
        return
            shouldBeEscaped(
                value, getEscapingFlags(), QueryUtils.getInstance());
    }

    /**
     * Checks whether given value should be escaped or not.
     * @param value the value to check.
     * @param escapingFlags the flags.
     * @param queryUtils the <code>QueryUtils</code> instance.
     * @return <code>true</code> if given value should be escaped.
     */
    protected boolean shouldBeEscaped(
        @NotNull final Object value,
        @NotNull final Map<?, ?> escapingFlags,
        @NotNull final QueryUtils queryUtils)
    {
        final boolean result;

        @Nullable final Object t_Flag = escapingFlags.get(buildEscapingFlagKey(value));

        if  (t_Flag instanceof Boolean)
        {
            result = Boolean.TRUE.equals(t_Flag);
        }
        else
        {
            result = queryUtils.shouldBeEscaped(value);
        }

        return result;
    }

    /**
     * Builds the escaping flag key for given value.
     * @param value the value.
     * @return the escaping flag key.
     */
    @NotNull
    protected String buildEscapingFlagKey(@NotNull final Object value)
    {
        return "||queryj||escaping-flag||" + value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeOnCompletion()
    {
        // TODO
    }
}
