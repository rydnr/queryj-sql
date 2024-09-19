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
 * Filename: QueryResultSet.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Standard SQL query.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing Jetbrains annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Importing some JDK1.3 classes.
 */
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 * Represents standard SQL queries.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class QueryResultSet
    implements  ResultSet
{
    /**
     * The query reference.
     */
    private Query m__Query;

    /**
     * The wrapped result set.
     */
    private ResultSet m__ResultSet;

    /**
     * The temporary fetch direction (in case wrapped result set is null).
     */
    private int m__iTempFetchDirection = -1; 

    /**
     * The temporary fetch size (in case wrapped result set is null).
     */
    private int m__iTempFetchSize = -1; 

    /**
     * Builds a query result set with given references.
     * @param query the query.
     * @param resultSet the wrapped result set.
     */
    public QueryResultSet(@NotNull final Query query, @NotNull final ResultSet resultSet)
    {
        immutableSetQuery(query);
        immutableSetResultSet(resultSet);
    }

    /**
     * Specifies the query reference.
     * @param query the query.
     */
    protected void immutableSetQuery(@NotNull final Query query)
    {
        m__Query = query;
    }

    /**
     * Specifies the query reference.
     * @param query the query.
     */
    protected void setQuery(@NotNull final Query query)
    {
        immutableSetQuery(query);
    }

    /**
     * Retrieves the query.
     * @return such reference.
     */
    @NotNull
    protected Query getQuery()
    {
        return m__Query;
    }

    /**
     * Specifies the result set reference.
     * @param resultSet the result set.
     */
    protected final void immutableSetResultSet(@NotNull final ResultSet resultSet)
    {
        m__ResultSet = resultSet;
    }

    /**
     * Specifies the result set reference.
     * @param resultSet the result set.
     */
    protected void setResultSet(@NotNull final ResultSet resultSet)
    {
        immutableSetResultSet(resultSet);
    }

    /**
     * Retrieves the result set.
     * @return such reference.
     */
    @Nullable
    protected ResultSet getResultSet()
    {
        return m__ResultSet;
    }

    /**
     * Retrieves the result set.
     * @return such reference.
     * @throws SQLException if the {@link ResultSet} is {@code null}.
     */
    @NotNull
    protected ResultSet getResultSetOrDie()
        throws SQLException
    {
        if (m__ResultSet == null)
        {
            throw new SQLException("Invalid QueryResultSet instance");
        }

        return m__ResultSet;
    }

    // Implementation of java.sql.ResultSet

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public byte[] getBytes(final int index)
        throws  SQLException
    {
        return getBytes(index, getResultSetOrDie());
    }

    /**
     * See ResultSet.getBytes(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected byte[] getBytes(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getBytes(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public byte[] getBytes(final String columnName)
        throws  SQLException
    {
        return getBytes(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet.getBytes(String)
     * @see java.sql.ResultSet#getBytes(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected byte[] getBytes(
        @NotNull final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getBytes(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean next()
        throws SQLException
    {
        return next(getResultSetOrDie());
    }

    /**
     * See ResultSet#next()
     * @see java.sql.ResultSet#next()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if the
     * new current row is valid; false if there are no more rows.
     * @throws SQLException if an error occurs.
     */
    protected boolean next(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean previous()
        throws  SQLException
    {
        return previous(getResultSetOrDie());
    }

    /**
     * See ResultSet#previous()
     * @see java.sql.ResultSet#previous()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if the
     * cursor is on a valid row; false if it is off the result set.
     * @throws SQLException if an error occurs.
     */
    protected boolean previous(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.previous();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getBoolean(final int index)
        throws SQLException
    {
        return getBoolean(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getBoolean(int)
     * @see java.sql.ResultSet#getBoolean(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is <code>false</code>.
     * @throws SQLException if an error occurs.
     */
    protected boolean getBoolean(final int index, @NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getBoolean(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getBoolean(@NotNull final String columnName)
        throws  SQLException
    {
        return getBoolean(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getBoolean(String)
     * @see java.sql.ResultSet#getBoolean(String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is <code>false</code>.
     * @exception SQLException if an error occurs.
     */
    protected boolean getBoolean(
        @NotNull final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getBoolean(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getType()
        throws  SQLException
    {
        return getType(getResultSetOrDie());
    }

    /**
     * See ResultSet#getType()
     * @see java.sql.ResultSet#getType()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @throws SQLException if an error occurs.
     */
    protected int getType(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLong(final int index)
        throws  SQLException
    {
        return getLong(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getLong(int)
     * @see java.sql.ResultSet#getLong(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @throws SQLException if an error occurs.
     */
    protected long getLong(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getLong(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLong(@NotNull final String columnName)
        throws  SQLException
    {
        return getLong(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getLong(String)
     * @see java.sql.ResultSet#getLong(String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @throws SQLException if an error occurs.
     */
    protected long getLong(@NotNull final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getLong(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Object getObject(final int index)
        throws  SQLException
    {
        return getObject(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getObject(int)
     * @see java.sql.ResultSet#getObject(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected Object getObject(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getObject(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Object getObject(@NotNull final String columnName)
        throws  SQLException
    {
        return getObject(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getObject(String)
     * @see java.sql.ResultSet#getObject(String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the object.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Object getObject(
        @NotNull final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getObject(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Object getObject(final int index, @NotNull final Map<String,Class<?>> map)
        throws  SQLException
    {
        return getObject(index, map, getResultSetOrDie());
    }

    /**
     * See ResultSet#getObject(int,Map)
     * @see java.sql.ResultSet#getObject(int,java.util.Map)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param map (Taken from Sun Javadoc) a java.util.Map object that
     * contains the mapping from SQL type names to classes in the Java
     * programming language.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected Object getObject(
        final int index, @NotNull final Map<String,Class<?>> map, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getObject(index, map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Object getObject(@NotNull final String columnName, final Map<String, Class<?>> map)
        throws  SQLException
    {
        return getObject(columnName, map, getResultSetOrDie());
    }

    /**
     * See ResultSet#getObject(String,Map)
     * @see java.sql.ResultSet#getObject(java.lang.String,java.util.Map)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param map (Taken from Sun Javadoc) a java.util.Map object that
     * contains the mapping from SQL type names to classes in the Java
     * programming language.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    protected Object getObject(
        @NotNull final String columnName,
        @NotNull final Map<String, Class<?>> map,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getObject(columnName, map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close()
        throws  SQLException
    {
        close(getResultSetOrDie());
    }

    /**
     * See ResultSet#close()
     * @see java.sql.ResultSet#close()
     * @param resultSet the result set.
     * @throws SQLException if an error occurs.
     */
    protected void close(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        resultSet.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Ref getRef(final int index)
        throws  SQLException
    {
        return getRef(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getRef(int)
     * @see java.sql.ResultSet#getRef(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected Ref getRef(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getRef(index);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Ref getRef(final String columnName)
        throws  SQLException
    {
        return getRef(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getRef(String)
     * @see java.sql.ResultSet#getRef(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Ref getRef(final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getRef(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Time getTime(final int index)
        throws  SQLException
    {
        return getTime(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getTime(int)
     * @see java.sql.ResultSet#getTime(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected Time getTime(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getTime(index);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    public Time getTime(@NotNull final String columnName)
        throws  SQLException
    {
        return getTime(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getTime(String)
     * @see java.sql.ResultSet#getTime(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected Time getTime(@NotNull final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getTime(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Time getTime(final int index, @NotNull final Calendar calendar)
        throws  SQLException
    {
        return getTime(index, calendar, getResultSetOrDie());
    }

    /**
     * See ResultSet#getTime(int,Calendar)
     * @see java.sql.ResultSet#getTime(int,java.util.Calendar)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected Time getTime(
        final int index, @NotNull final Calendar calendar, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getTime(index, calendar);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Time getTime(@NotNull final String columnName, @NotNull final Calendar calendar)
        throws  SQLException
    {
        return getTime(columnName, calendar, getResultSetOrDie());
    }

    /**
     * See ResultSet#getTime(String,Calendar)
     * @see java.sql.ResultSet#getTime(java.lang.String,java.util.Calendar)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected Time getTime(
        @NotNull final String columnName,
        @NotNull final Calendar calendar,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getTime(columnName, calendar);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Date getDate(final int index)
        throws  SQLException
    {
        return getDate(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getDate(int)
     * @see java.sql.ResultSet#getDate(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected Date getDate(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getDate(index);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Date getDate(@NotNull final String columnName)
        throws  SQLException
    {
        return getDate(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getDate(String)
     * @see java.sql.ResultSet#getDate(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the date.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected Date getDate(@NotNull final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getDate(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    public Date getDate(final int index, @NotNull final Calendar calendar)
        throws  SQLException
    {
        return getDate(index, calendar, getResultSetOrDie());
    }

    /**
     * See ResultSet#getDate(int,Calendar)
     * @see java.sql.ResultSet#getDate(int,java.util.Calendar)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @throws SQLException if an error occurs.
     */
    @Nullable
    protected Date getDate(
        final int index, @NotNull final Calendar calendar, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getDate(index, calendar);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Date getDate(@NotNull final String columnName, @NotNull final Calendar calendar)
        throws  SQLException
    {
        return getDate(columnName, calendar, getResultSetOrDie());
    }

    /**
     * See ResultSet#getDate(String,Calendar)
     * @see java.sql.ResultSet#getDate(java.lang.String,java.util.Calendar)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Date getDate(
        @NotNull final String columnName,
        @NotNull final Calendar calendar,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getDate(columnName, calendar);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean first()
        throws  SQLException
    {
        return first(getResultSetOrDie());
    }

    /**
     * See ResultSet#first()
     * @see java.sql.ResultSet#first()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) 
     * @throws SQLException if an error occurs.
     */
    protected boolean first(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.first();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte getByte(final int index)
        throws  SQLException
    {
        return getByte(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getByte(int)
     * @see java.sql.ResultSet#getByte(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @throws SQLException if an error occurs.
     */
    protected byte getByte(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getByte(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte getByte(@NotNull final String columnName)
        throws  SQLException
    {
        return getByte(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getByte(String)
     * @see java.sql.ResultSet#getByte(String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the byte.
     * @throws SQLException if an error occurs.
     */
    protected byte getByte(@NotNull final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getByte(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getShort(final int index)
        throws  SQLException
    {
        return getShort(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getShort(int)
     * @see java.sql.ResultSet#getShort(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @throws SQLException if an error occurs.
     */
    protected short getShort(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getShort(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getShort(@NotNull final String columnName)
        throws  SQLException
    {
        return getShort(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getShort(String)
     * @see java.sql.ResultSet#getShort(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the short value.
     * @throws SQLException if an error occurs.
     */
    protected short getShort(
        @NotNull final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getShort(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInt(final int index)
        throws  SQLException
    {
        return getInt(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getInt(int)
     * @see java.sql.ResultSet#getInt(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @throws SQLException if an error occurs.
     */
    protected int getInt(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getInt(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInt(@NotNull final String columnName)
        throws  SQLException
    {
        return getInt(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getInt(String)
     * @see java.sql.ResultSet#getInt(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the int value.
     * @throws SQLException if an error occurs.
     */
    protected int getInt(final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getInt(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getFloat(final int index)
        throws  SQLException
    {
        return getFloat(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getFloat(int)
     * @see java.sql.ResultSet#getFloat(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @throws SQLException if an error occurs.
     */
    protected float getFloat(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getFloat(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getFloat(@NotNull final String columnName)
        throws  SQLException
    {
        return getFloat(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getFloat(String)
     * @see java.sql.ResultSet#getFloat(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the float value.
     * @throws SQLException if an error occurs.
     */
    protected float getFloat(@NotNull final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getFloat(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDouble(final int index)
        throws  SQLException
    {
        return getDouble(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getDouble(int)
     * @see java.sql.ResultSet#getDouble(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @throws SQLException if an error occurs.
     */
    protected double getDouble(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getDouble(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDouble(final String columnName)
        throws  SQLException
    {
        return getDouble(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getDouble(String)
     * @see java.sql.ResultSet#getDouble(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @exception SQLException if an error occurs.
     */
    protected double getDouble(
        final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getDouble(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSetMetaData getMetaData()
        throws  SQLException
    {
        return getMetaData(getResultSetOrDie());
    }

    /**
     * See ResultSet#getMetaData()
     * @see java.sql.ResultSet#getMetaData()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the description of a ResultSet
     * object's columns or null if the driver cannot return a
     * ResultSetMetaData object.
     * @exception SQLException if an error occurs.
     */
    protected ResultSetMetaData getMetaData(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getMetaData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLWarning getWarnings()
        throws  SQLException
    {
        return getWarnings(getResultSetOrDie());
    }

    /**
     * See ResultSet#getWarnings()
     * @see java.sql.ResultSet#getWarnings()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the first SQLWarning object
     * reported or null if there are none.
     * @exception SQLException if an error occurs.
     */
    protected SQLWarning getWarnings(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getWarnings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearWarnings()
        throws SQLException
    {
        clearWarnings(getResultSetOrDie());
    }

    /**
     * See ResultSet#clearWarnings()
     * @see java.sql.ResultSet#clearWarnings()
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void clearWarnings(@NotNull final ResultSet resultSet)
        throws SQLException
    {
        resultSet.clearWarnings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    /**
     * See ResultSet#setFetchDirection(int)
     * @see java.sql.ResultSet#setFetchDirection(int)
     * @param direction (Taken from Sun Javadoc) an integer specifying the
     * suggested fetch direction; one of ResultSet.FETCH_FORWARD,
     * ResultSet.FETCH_REVERSE, or ResultSet.FETCH_UNKNOWN.
     * @exception SQLException if an error occurs.
     */
    public void setFetchDirection(final int direction)
        throws  SQLException
    {
        setFetchDirection(direction, getResultSetOrDie());
    }

    /**
     * See ResultSet#setFetchDirection(int)
     * @see java.sql.ResultSet#setFetchDirection(int)
     * @param direction (Taken from Sun Javadoc) an integer specifying the
     * suggested fetch direction; one of ResultSet.FETCH_FORWARD,
     * ResultSet.FETCH_REVERSE, or ResultSet.FETCH_UNKNOWN.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void setFetchDirection(
        final int direction, @Nullable final ResultSet resultSet)
      throws  SQLException
    {
        if  (resultSet != null) 
        {
            resultSet.setFetchDirection(direction);
        }
        else
        {
            m__iTempFetchDirection = direction;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFetchDirection()
        throws  SQLException
    {
        return getFetchDirection(getResultSetOrDie());
    }

    /**
     * See ResultSet#getFetchDirection()
     * @see java.sql.ResultSet#getFetchDirection()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the current fetch direction for
     * this ResultSet object.
     * @exception SQLException if an error occurs.
     */
    protected int getFetchDirection(@Nullable final ResultSet resultSet)
        throws  SQLException
    {
        int result = m__iTempFetchDirection;

        if  (resultSet != null) 
        {
            result = resultSet.getFetchDirection();
        }
        
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    /**
     * See ResultSet#setFetchSize(int)
     * @see java.sql.ResultSet#setFetchSize(int)
     * @param size (Taken from Sun Javadoc) the number of rows to fetch.
     * @exception SQLException if an error occurs.
     */
    public void setFetchSize(final int size)
        throws  SQLException
    {
        setFetchSize(size, getResultSetOrDie());
    }

    /**
     * See ResultSet#setFetchSize(int)
     * @see java.sql.ResultSet#setFetchSize(int)
     * @param size (Taken from Sun Javadoc) the number of rows to fetch.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void setFetchSize(final int size, @Nullable final ResultSet resultSet)
        throws  SQLException
    {
        if  (resultSet != null) 
        {
            resultSet.setFetchSize(size);
        }
        else
        {
            m__iTempFetchSize = size;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    /**
     * See ResultSet#getFetchSize()
     * @see java.sql.ResultSet#getFetchSize()
     * @return (Taken from Sun Javadoc) the current fetch size for this
     * ResultSet object.
     * @exception SQLException if an error occurs.
     */
    public int getFetchSize()
        throws  SQLException
    {
        return getFetchSize(getResultSetOrDie());
    }

    /**
     * See ResultSet#getFetchSize()
     * @see java.sql.ResultSet#getFetchSize()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the current fetch size for this
     * ResultSet object.
     * @exception SQLException if an error occurs.
     */
    protected int getFetchSize(@Nullable final ResultSet resultSet)
        throws  SQLException
    {
        int result = m__iTempFetchSize;

        if  (resultSet != null) 
        {
            result = resultSet.getFetchSize();
        }
        
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getString(final int index)
        throws  SQLException
    {
        return getString(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getString(int)
     * @see java.sql.ResultSet#getString(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected String getString(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getString(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getString(@NotNull final String columnName)
        throws  SQLException
    {
        return getString(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getString(String)
     * @see java.sql.ResultSet#getString(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the value.
     * @exception SQLException if an error occurs.
     */
    protected String getString(
        @NotNull final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getString(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Array getArray(final int index)
        throws  SQLException
    {
        return getArray(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getArray(int)
     * @see java.sql.ResultSet#getArray(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Array getArray(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getArray(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Array getArray(@NotNull final String columnName)
        throws  SQLException
    {
        return getArray(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getArray(String)
     * @see java.sql.ResultSet#getArray(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the array.
     * @exception SQLException if an error occurs.
     */
    protected Array getArray(
        @NotNull final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getArray(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getAsciiStream(final int index)
        throws  SQLException
    {
        return getAsciiStream(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getAsciiStream(int)
     * @see java.sql.ResultSet#getAsciiStream(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected InputStream getAsciiStream(
        final int index, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getAsciiStream(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getAsciiStream(@NotNull final String columnName)
        throws  SQLException
    {
        return getAsciiStream(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getAsciiStream(String)
     * @see java.sql.ResultSet#getAsciiStream(String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the stream.
     * @exception SQLException if an error occurs.
     */
    protected InputStream getAsciiStream(
        final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getAsciiStream(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("deprecated")
    @Override
    public BigDecimal getBigDecimal(final int index, final int scale)
        throws  SQLException
    {
        return getBigDecimal(index, scale, getResultSetOrDie());
    }

    /**
     * See ResultSet#getBigDecimal(int,int)
     * @see java.sql.ResultSet#getBigDecimal(int,int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param scale (Taken from Sun Javadoc) the number of digits to the
     * right of the decimal point.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     * @deprecated since it's deprecated in JDK 1.4 as well.
     */
    protected BigDecimal getBigDecimal(
        final int index, final int scale, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getBigDecimal(index, scale);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("deprecated")
    @Override
    public BigDecimal getBigDecimal(@NotNull final String columnName, final int scale)
        throws  SQLException
    {
        return getBigDecimal(columnName, scale, getResultSetOrDie());
    }

    /**
     * See ResultSet#getBigDecimal(String,int)
     * @see java.sql.ResultSet#getBigDecimal(java.lang.String,int)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param scale (Taken from Sun Javadoc) the number of digits to the
     * right of the decimal point.
     * @param resultSet the result set.
     * @return the big decimal.
     * @exception SQLException if an error occurs.
     * @deprecated since it's deprecated in JDK 1.4 as well.
     */
    protected BigDecimal getBigDecimal(
        @NotNull final String columnName, final int scale, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getBigDecimal(columnName, scale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getBigDecimal(final int index)
        throws  SQLException
    {
        return getBigDecimal(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getBigDecimal(int)
     * @see java.sql.ResultSet#getBigDecimal(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected BigDecimal getBigDecimal(
        final int index, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getBigDecimal(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getBigDecimal(@NotNull final String columnName)
        throws  SQLException
    {
        return getBigDecimal(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getBigDecimal(String)
     * @see java.sql.ResultSet#getBigDecimal(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the big decimal.
     * @exception SQLException if an error occurs.
     */
    protected BigDecimal getBigDecimal(
        @NotNull final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getBigDecimal(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getBinaryStream(final int index)
        throws  SQLException
    {
        return getBinaryStream(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getBinaryStream(int)
     * @see java.sql.ResultSet#getBinaryStream(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected InputStream getBinaryStream(
        final int index, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getBinaryStream(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getBinaryStream(@NotNull final String columnName)
        throws  SQLException
    {
        return getBinaryStream(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getBinaryStream(String)
     * @see java.sql.ResultSet#getBinaryStream(String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the stream.
     * @exception SQLException if an error occurs.
     */
    protected InputStream getBinaryStream(
        @NotNull final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getBinaryStream(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blob getBlob(final int index)
        throws  SQLException
    {
        return getBlob(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getBlob(int)
     * @see java.sql.ResultSet#getBlob(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Blob getBlob(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getBlob(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blob getBlob(final String columnName)
        throws  SQLException
    {
        return getBlob(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getBlob(String)
     * @see java.sql.ResultSet#getBlob(String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Blob getBlob(final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getBlob(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clob getClob(final int index)
        throws  SQLException
    {
        return getClob(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getClob(int)
     * @see java.sql.ResultSet#getClob(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Clob getClob(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getClob(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clob getClob(final String columnName)
        throws  SQLException
    {
        return getClob(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getClob(String)
     * @see java.sql.ResultSet#getClob(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Clob getClob(final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getClob(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp getTimestamp(final int index)
        throws  SQLException
    {
        return getTimestamp(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getTimestamp(int)
     * @see java.sql.ResultSet#getTimestamp(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Timestamp getTimestamp(
        final int index, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getTimestamp(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp getTimestamp(final String columnName)
        throws  SQLException
    {
        return getTimestamp(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getTimestamp(String)
     * @see java.sql.ResultSet#getTimestamp(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Timestamp getTimestamp(
        final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getTimestamp(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp getTimestamp(final int index, final Calendar calendar)
        throws  SQLException
    {
        return getTimestamp(index, calendar, getResultSetOrDie());
    }

    /**
     * See ResultSet#getTimestamp(int,Calendar)
     * @see java.sql.ResultSet#getTimestamp(int,java.util.Calendar)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Timestamp getTimestamp(
        final int index, final Calendar calendar, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getTimestamp(index, calendar);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp getTimestamp(final String columnName, final Calendar calendar)
        throws  SQLException
    {
        return getTimestamp(columnName, calendar, getResultSetOrDie());
    }

    /**
     * See ResultSet#getTimestamp(String,Calendar)
     * @see java.sql.ResultSet#getTimestamp(java.lang.String,java.util.Calendar)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Timestamp getTimestamp(
        final String columnName,
        final Calendar calendar,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getTimestamp(columnName, calendar);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("deprecated")
    @Override
    public InputStream getUnicodeStream(final int index)
        throws  SQLException
    {
        return getUnicodeStream(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getUnicodeStream(int)
     * @see java.sql.ResultSet#getUnicodeStream(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     * @deprecated since it's deprecated in JDK 1.4 as well.
     */
    protected InputStream getUnicodeStream(
        final int index, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getUnicodeStream(index);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("deprecated")
    @Override
    public InputStream getUnicodeStream(final String columnName)
        throws  SQLException
    {
        return getUnicodeStream(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getUnicodeStream(String)
     * @see java.sql.ResultSet#getUnicodeStream(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return the stream.
     * @exception SQLException if an error occurs.
     * @deprecated since it's deprecated in JDK 1.4 as well.
     */
    @NotNull
    protected InputStream getUnicodeStream(
        @NotNull final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getUnicodeStream(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean wasNull()
        throws  SQLException
    {
        return wasNull(getResultSetOrDie());
    }

    /**
     * See ResultSet#wasNull()
     * @see java.sql.ResultSet#wasNull()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if the last
     * column value read was SQL NULL and <code>false</code> otherwise.
     * @exception SQLException if an error occurs.
     */
    protected boolean wasNull(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.wasNull();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reader getCharacterStream(final int index)
        throws  SQLException
    {
        return getCharacterStream(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#getCharacterStream(int)
     * @see java.sql.ResultSet#getCharacterStream(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Reader getCharacterStream(
        final int index, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getCharacterStream(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reader getCharacterStream(final String columnName)
        throws  SQLException
    {
        return getCharacterStream(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getCharacterStream(String)
     * @see java.sql.ResultSet#getCharacterStream(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Reader getCharacterStream(
        final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getCharacterStream(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean absolute(final int index)
        throws  SQLException
    {
        return absolute(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#absolute(int)
     * @see java.sql.ResultSet#absolute(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if the cursor
     * is on the result set; <code>false</code> otherwise.
     * @exception SQLException if an error occurs.
     */
    protected boolean absolute(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.absolute(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterLast()
        throws  SQLException
    {
        afterLast(getResultSetOrDie());
    }

    /**
     * See ResultSet#afterLast()
     * @param resultSet the result set.
     * @see java.sql.ResultSet#afterLast()
     * @exception SQLException if an error occurs.
     */
    protected void afterLast(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        resultSet.afterLast();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeFirst()
        throws  SQLException
    {
        beforeFirst(getResultSetOrDie());
    }

    /**
     * See ResultSet#beforeFirst()
     * @see java.sql.ResultSet#beforeFirst()
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void beforeFirst(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        resultSet.beforeFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelRowUpdates()
        throws  SQLException
    {
        cancelRowUpdates(getResultSetOrDie());
    }

    /**
     * See ResultSet#cancelRowUpdates()
     * @see java.sql.ResultSet#cancelRowUpdates()
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void cancelRowUpdates(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        resultSet.cancelRowUpdates();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteRow()
        throws  SQLException
    {
        deleteRow(getResultSetOrDie());
    }

    /**
     * See ResultSet#deleteRow()
     * @see java.sql.ResultSet#deleteRow()
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void deleteRow(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        resultSet.deleteRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int findColumn(final String columnName)
        throws  SQLException
    {
        return findColumn(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#findColumn(String)
     * @see java.sql.ResultSet#findColumn(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column index of the
     * given column name.
     * @exception SQLException if an error occurs.
     */
    protected int findColumn(final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.findColumn(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getConcurrency()
        throws  SQLException
    {
        return getConcurrency(getResultSetOrDie());
    }

    /**
     * See ResultSet#getConcurrency()
     * @see java.sql.ResultSet#getConcurrency()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the concurrency type, either
     * ResultSet.CONCUR_READ_ONLY or ResultSet.CONCUR_UPDATABLE.
     * @exception SQLException if an error occurs.
     */
    protected int getConcurrency(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getConcurrency();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCursorName()
        throws  SQLException
    {
        return getCursorName(getResultSetOrDie());
    }

    /**
     * See ResultSet#getCursorName()
     * @see java.sql.ResultSet#getCursorName()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the SQL name for this ResultSet
     * object's cursor.
     * @exception SQLException if an error occurs.
     */
    protected String getCursorName(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getCursorName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRow()
        throws  SQLException
    {
        return getRow(getResultSetOrDie());
    }

    /**
     * See ResultSet#getRow()
     * @see java.sql.ResultSet#getRow()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the current row number;
     * 0 if there is no current row.
     * @exception SQLException if an error occurs.
     */
    protected int getRow(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Statement getStatement()
        throws SQLException
    {
        return getStatement(getQuery(), getResultSetOrDie());
    }

    /**
     * See ResultSet#getStatement()
     * @see java.sql.ResultSet#getStatement()
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the Statement object that produced
     * this ResultSet object or null if the result set was produced some
     * other way.
     * @exception SQLException if an error occurs.
     */
    public Statement getStatement(
        final Statement query, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        Statement result = query;

        if  (result == null) 
        {
            result = resultSet.getStatement();
        }
        
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertRow()
        throws  SQLException
    {
        insertRow(getResultSetOrDie());
    }

    /**
     * See ResultSet#insertRow()
     * @see java.sql.ResultSet#insertRow()
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void insertRow(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        resultSet.insertRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAfterLast()
        throws  SQLException
    {
        return isAfterLast(getResultSetOrDie());
    }

    /**
     * See ResultSet#isAfterLast()
     * @see java.sql.ResultSet#isAfterLast()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if the cursor is
     * after the last row; <code>false</code> if the cursor is at any other
     * position or the result set contains no rows.
     * @exception SQLException if an error occurs.
     */
    protected boolean isAfterLast(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.isAfterLast();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBeforeFirst()
        throws  SQLException
    {
        return isBeforeFirst(getResultSetOrDie());
    }

    /**
     * See ResultSet#isBeforeFirst()
     * @see java.sql.ResultSet#isBeforeFirst()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if the cursor is
     * before the first row; <code>false</code> if the cursor is at any other
     * position or the result set contains no rows.
     * @exception SQLException if an error occurs.
     */
    protected boolean isBeforeFirst(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.isBeforeFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFirst()
        throws  SQLException
    {
        return isFirst(getResultSetOrDie());
    }

    /**
     * See ResultSet#isFirst()
     * @see java.sql.ResultSet#isFirst()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if the cursor is
     * on the first row; <code>false</code> otherwise.
     * @exception SQLException if an error occurs.
     */
    protected boolean isFirst(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.isFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLast()
        throws  SQLException
    {
        return isLast(getResultSetOrDie());
    }

    /**
     * See ResultSet#isLast()
     * @see java.sql.ResultSet#isLast()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if the cursor is
     * on the last row; <code>false</code> otherwise.
     * @exception SQLException if an error occurs.
     */
    protected boolean isLast(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.isLast();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean last()
        throws  SQLException
    {
        return last(getResultSetOrDie());
    }

    /**
     * See ResultSet#last()
     * @see java.sql.ResultSet#last()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if the cursor is
     * on a valid row; <code>false</code> if there are no rows in the result
     * set.
     * @exception SQLException if an error occurs.
     */
    protected boolean last(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.last();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveToCurrentRow()
        throws  SQLException
    {
        moveToCurrentRow(getResultSetOrDie());
    }

    /**
     * See ResultSet#moveToCurrentRow()
     * @see java.sql.ResultSet#moveToCurrentRow()
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void moveToCurrentRow(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        resultSet.moveToCurrentRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveToInsertRow()
        throws  SQLException
    {
        moveToInsertRow(getResultSetOrDie());
    }

    /**
     * See ResultSet#moveToInsertRow()
     * @see java.sql.ResultSet#moveToInsertRow()
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void moveToInsertRow(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        resultSet.moveToInsertRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refreshRow()
        throws  SQLException
    {
        refreshRow(getResultSetOrDie());
    }

    /**
     * See ResultSet#refreshRow()
     * @see java.sql.ResultSet#refreshRow()
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void refreshRow(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        resultSet.refreshRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean relative(final int index)
        throws  SQLException
    {
        return relative(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#relative(int)
     * @see java.sql.ResultSet#relative(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if the cursor is
     * on a row; <code>false</code> otherwise.
     * @exception SQLException if an error occurs.
     */
    protected boolean relative(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.relative(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean rowDeleted()
        throws  SQLException
    {
        return rowDeleted(getResultSetOrDie());
    }

    /**
     * See ResultSet#rowDeleted()
     * @see java.sql.ResultSet#rowDeleted()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if a row was
     * deleted and deletions are detected; <code>false</code> otherwise.
     * @exception SQLException if an error occurs.
     */
    protected boolean rowDeleted(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.rowDeleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean rowInserted()
        throws  SQLException
    {
        return rowInserted(getResultSetOrDie());
    }

    /**
     * See ResultSet#rowInserted()
     * @see java.sql.ResultSet#rowInserted()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if a row has had
     * an insertion and insertions are detected; <code>false</code> otherwise.
     * @exception SQLException if an error occurs.
     */
    protected boolean rowInserted(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.rowInserted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean rowUpdated()
        throws  SQLException
    {
        return rowUpdated(getResultSetOrDie());
    }

    /**
     * See ResultSet#rowUpdated()
     * @see java.sql.ResultSet#rowUpdated()
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) <code>true</code> if both (1) the
     * row has been visibly updated by the owner or another and (2) updates
     * are detected.
     * @exception SQLException if an error occurs.
     */
    protected boolean rowUpdated(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.rowUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAsciiStream(
        final int index,
        final InputStream value,
        final int length)
      throws  SQLException
    {
        updateAsciiStream(index, value, length, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateAsciiStream(int,InputStream,int)
     * @see java.sql.ResultSet#updateAsciiStream(int,java.io.InputStream,int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param length (Taken from Sun Javadoc) the length of the stream.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateAsciiStream(
        final int index,
        final InputStream value,
        final int length,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateAsciiStream(index, value, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAsciiStream(
        final String columnName,
        final InputStream value,
        final int length)
      throws  SQLException
    {
        updateAsciiStream(columnName, value, length, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateAsciiStream(String,InputStream,int)
     * @see java.sql.ResultSet#updateAsciiStream(java.lang.String,java.io.InputStream,int)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param length (Taken from Sun Javadoc) the length of the stream.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateAsciiStream(
        final String columnName,
        final InputStream value,
        final int length,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateAsciiStream(columnName, value, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBigDecimal(final int index, final BigDecimal value)
        throws  SQLException
    {
        updateBigDecimal(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateBigDecimal(int,BigDecimal)
     * @see java.sql.ResultSet#updateBigDecimal(int,java.math.BigDecimal)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value a <code>BigDecimal</code> value
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateBigDecimal(
        final int index, final BigDecimal value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateBigDecimal(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBigDecimal(final String columnName, final BigDecimal value)
        throws  SQLException
    {
        updateBigDecimal(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateBigDecimal(String,BigDecimal)
     * @see java.sql.ResultSet#updateBigDecimal(java.lang.String,java.math.BigDecimal)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value a <code>BigDecimal</code> value
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateBigDecimal(
        final String columnName,
        final BigDecimal value,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateBigDecimal(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBinaryStream(
        final int index,
        final InputStream value,
        final int length)
      throws  SQLException
    {
        updateBinaryStream(index, value, length, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateBinaryStream(int,InputStream,int)
     * @see java.sql.ResultSet#updateBinaryStream(int,java.io.InputStream,int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param length (Taken from Sun Javadoc) the length of the stream.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateBinaryStream(
        final int index,
        final InputStream value,
        final int length,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateBinaryStream(index, value, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBinaryStream(
        final String columnName,
        final InputStream value,
        final int length)
      throws  SQLException
    {
        updateBinaryStream(columnName, value, length, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateBinaryStream(String,InputStream,int)
     * @see java.sql.ResultSet#updateBinaryStream(java.lang.String,java.io.InputStream,int)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param length (Taken from Sun Javadoc) the length of the stream.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateBinaryStream(
        final String columnName,
        final InputStream value,
        final int length,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateBinaryStream(columnName, value, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBoolean(final int index, final boolean value)
        throws  SQLException
    {
        updateBoolean(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateBoolean(int,boolean)
     * @see java.sql.ResultSet#updateBoolean(int,boolean)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateBoolean(
        final int index, final boolean value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateBoolean(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBoolean(final String columnName, final boolean value)
        throws  SQLException
    {
        updateBoolean(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateBoolean(String,boolean)
     * @see java.sql.ResultSet#updateBoolean(java.lang.String,boolean)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateBoolean(
        final String columnName,
        final boolean value,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateBoolean(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateByte(final int index, final byte value)
        throws  SQLException
    {
        updateByte(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateByte(int,byte)
     * @see java.sql.ResultSet#updateByte(int,byte)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateByte(
        final int index, final byte value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateByte(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateByte(final String columnName, final byte value)
        throws  SQLException
    {
        updateByte(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateByte(String,byte)
     * @see java.sql.ResultSet#updateByte(java.lang.String,byte)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateByte(
        final String columnName, final byte value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateByte(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBytes(final int index, final byte[] value)
        throws  SQLException
    {
        updateBytes(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateBytes(int,byte[])
     * @see java.sql.ResultSet#updateBytes(int,byte[])
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateBytes(
        final int index, final byte[] value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateBytes(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBytes(final String columnName, final byte[] value)
        throws  SQLException
    {
        updateBytes(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateBytes(String,byte[])
     * @see java.sql.ResultSet#updateBytes(java.lang.String,byte[])
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateBytes(
        final String columnName, final byte[] value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateBytes(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCharacterStream(final int index, Reader value, final int length)
        throws  SQLException
    {
        updateCharacterStream(index, value, length, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateCharacterStream(int,Reader,int)
     * @see java.sql.ResultSet#updateCharacterStream(int,java.io.Reader,int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param length (Taken from Sun Javadoc) the length of the stream.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateCharacterStream(
        final int index, Reader value, final int length, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateCharacterStream(index, value, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCharacterStream(
        final String columnName,
        final Reader value,
        final int length)
      throws  SQLException
    {
        updateCharacterStream(columnName, value, length, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateCharacterStream(String,Reader,int)
     * @see java.sql.ResultSet#updateCharacterStream(java.lang.String,java.io.Reader,int)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param length (Taken from Sun Javadoc) the length of the stream.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateCharacterStream(
        final String columnName,
        final Reader value,
        final int length,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateCharacterStream(columnName, value, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDate(final int index, final Date value)
        throws  SQLException
    {
        updateDate(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateDate(int,Date)
     * @see java.sql.ResultSet#updateDate(int,java.sql.Date)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateDate(
        final int index, final Date value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateDate(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDate(final String columnName, final Date value)
        throws  SQLException
    {
        updateDate(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateDate(String,Date)
     * @see java.sql.ResultSet#updateDate(java.lang.String,java.sql.Date)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateDate(
        final String columnName, final Date value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateDate(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDouble(final int index, final double value)
        throws  SQLException
    {
        updateDouble(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateDouble(int,double)
     * @see java.sql.ResultSet#updateDouble(int,double)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateDouble(
        final int index, final double value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateDouble(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDouble(final String columnName, final double value)
        throws  SQLException
    {
        updateDouble(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateDouble(String,double)
     * @see java.sql.ResultSet#updateDouble(String,double)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateDouble(
        final String columnName, final double value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateDouble(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFloat(final int index, final float value)
        throws  SQLException
    {
        updateFloat(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateFloat(int,float)
     * @see java.sql.ResultSet#updateFloat(int,float)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateFloat(
        final int index, final float value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateFloat(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFloat(final String columnName, final float value)
        throws  SQLException
    {
        updateFloat(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateFloat(String,float)
     * @see java.sql.ResultSet#updateFloat(String,float)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs
     */
    protected void updateFloat(
        final String columnName, final float value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateFloat(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInt(final int index, final int value)
        throws  SQLException
    {
        updateInt(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateInt(int,int)
     * @see java.sql.ResultSet#updateInt(int,int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateInt(
        final int index, final int value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateInt(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInt(final String columnName, final int value)
        throws  SQLException
    {
        updateInt(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateInt(String,int)
     * @see java.sql.ResultSet#updateInt(java.lang.String,int)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateInt(
        final String columnName, final int value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateInt(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLong(final int index, final long value)
        throws  SQLException
    {
        updateLong(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateLong(int,long)
     * @see java.sql.ResultSet#updateLong(int,long)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateLong(
        final int index, final long value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateLong(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLong(final String columnName, final long value)
        throws  SQLException
    {
        updateLong(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateLong(String,long)
     * @see java.sql.ResultSet#updateLong(java.lang.String,long)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs
     */
    protected void updateLong(
        final String columnName, final long value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateLong(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNull(final int index)
        throws  SQLException
    {
        updateNull(index, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateNull(int)
     * @see java.sql.ResultSet#updateNull(int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateNull(final int index, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        resultSet.updateNull(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNull(final String columnName)
        throws  SQLException
    {
        updateNull(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateNull(String)
     * @see java.sql.ResultSet#updateNull(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateNull(
        final String columnName, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateNull(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateObject(
        final int index, final Object value, final int scale)
      throws  SQLException
    {
        updateObject(index, value, scale, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateObject(int,Object,int)
     * @see java.sql.ResultSet#updateObject(int,java.lang.Object,int)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param scale (Taken from Sun Javadoc) for java.sql.Types.DECIMA
     * or java.sql.Types.NUMERIC types, this is the number of digits after
     * the decimal point. For all other types this value will be ignored.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateObject(
        final int index,
        final Object value,
        final int scale,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateObject(index, value, scale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateObject(final int index, final Object value)
        throws  SQLException
    {
        updateObject(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateObject(int,Object)
     * @see java.sql.ResultSet#updateObject(int,java.lang.Object)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateObject(
        final int index, final Object value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateObject(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateObject(
        final String columnName, final Object value, final int scale)
      throws  SQLException
    {
        updateObject(columnName, value, scale, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateObject(String,Object,int)
     * @see java.sql.ResultSet#updateObject(java.lang.String,java.lang.Object,int)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param scale (Taken from Sun Javadoc) for java.sql.Types.DECIMA
     * or java.sql.Types.NUMERIC types, this is the number of digits after
     * the decimal point. For all other types this value will be ignored.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateObject(
        final String columnName,
        final Object value,
        final int scale,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateObject(columnName, value, scale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateObject(final String columnName, final Object value)
        throws  SQLException
    {
        updateObject(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateObject(String,Object)
     * @see java.sql.ResultSet#updateObject(java.lang.String,java.lang.Object)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateObject(
        final String columnName, final Object value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateObject(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRow()
        throws  SQLException
    {
        updateRow(getResultSetOrDie());
    }

    /**
     * See ResultSet#updateRow()
     * @param resultSet the result set.
     * @see java.sql.ResultSet#updateRow()
     * @exception SQLException if an error occurs.
     */
    protected void updateRow(@NotNull final ResultSet resultSet)
        throws  SQLException
    {
        resultSet.updateRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateShort(final int index, final short value)
        throws  SQLException
    {
        updateShort(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateShort(int,short)
     * @see java.sql.ResultSet#updateShort(int,short)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateShort(
        final int index, final short value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateShort(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateShort(final String columnName, final short value)
        throws  SQLException
    {
        updateShort(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateShort(String,short)
     * @see java.sql.ResultSet#updateShort(java.lang.String,short)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateShort(
        final String columnName, final short value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateShort(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateString(final int index, final String value)
        throws  SQLException
    {
        updateString(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateString(int,String)
     * @see java.sql.ResultSet#updateString(int,java.lang.String)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateString(
        final int index, final String value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateString(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateString(final String columnName, final String value)
        throws  SQLException
    {
        updateString(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateString(String,String)
     * @see java.sql.ResultSet#updateString(java.lang.String,java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateString(
        final String columnName, final String value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateString(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTime(final int index, final Time value)
        throws  SQLException
    {
        updateTime(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateTime(int,Time)
     * @see java.sql.ResultSet#updateTime(int,java.sql.Time)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateTime(
        final int index, final Time value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateTime(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTime(final String columnName, final Time value)
        throws  SQLException
    {
        updateTime(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateTime(String,Time)
     * @see java.sql.ResultSet#updateTime(java.lang.String,java.sql.Time)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateTime(
        final String columnName, final Time value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateTime(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTimestamp(final int index, final Timestamp value)
        throws  SQLException
    {
        updateTimestamp(index, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateTimestamp(int,Timestamp)
     * @see java.sql.ResultSet#updateTimestamp(int,java.sql.Timestamp)
     * @param index (Taken from Sun Javadoc) the first column
     * is 1, the second is 2, ...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateTimestamp(
        final int index, final Timestamp value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateTimestamp(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTimestamp(@NotNull final String columnName, @NotNull final Timestamp value)
        throws  SQLException
    {
        updateTimestamp(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateTimestamp(String,Timestamp)
     * @see java.sql.ResultSet#updateTimestamp(java.lang.String,java.sql.Timestamp)
     * @param columnName (Taken from Sun Javadoc) the SQL name of
     * the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateTimestamp(
        final String columnName, final Timestamp value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateTimestamp(columnName, value);
    }

    // New methods from JDK 1.4 //

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getURL(final int columnIndex)
        throws  SQLException
    {
        return getURL(columnIndex, getResultSetOrDie());
    }

    /**
     * See ResultSet#getURL(int)
     * @see java.sql.ResultSet#getURL(int)
     * @param columnIndex (Taken from Sun Javadoc) the index of the column
     * 1 is the first, 2 is the second,...
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value as a java.net.URL
     * object; if the value is SQL NULL, the value returned is null in the
     * Java programming language.
     * @exception SQLException if an error occurs.
     */
    protected URL getURL(final int columnIndex, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getURL(columnIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getURL(final String columnName)
        throws  SQLException
    {
        return getURL(columnName, getResultSetOrDie());
    }

    /**
     * See ResultSet#getURL(String)
     * @see java.sql.ResultSet#getURL(java.lang.String)
     * @param columnName (Taken from Sun Javadoc) the SQL name of the column.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value as a java.net.URL
     * object; if the value is SQL NULL, the value returned is null in the
     * Java programming language.
     * @exception SQLException if an error occurs.
     */
    protected URL getURL(final String columnName, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getURL(columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRef(final int columnIndex, final Ref value)
        throws  SQLException
    {
        updateRef(columnIndex, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateRef(int,Ref)
     * @see java.sql.ResultSet#updateRef(int,java.sql.Ref)
     * @param columnIndex (Taken from Sun Javadoc) the index of the column
     * 1 is the first, 2 is the second,...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateRef(
        final int columnIndex, @NotNull final Ref value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateRef(columnIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRef(@NotNull final String columnName, @NotNull final Ref value)
        throws  SQLException
    {
        updateRef(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateRef(String,Ref)
     * @see java.sql.ResultSet#updateRef(java.lang.String,java.sql.Ref)
     * @param columnName (Taken from Sun Javadoc) the SQL name of the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateRef(
        @NotNull final String columnName, @NotNull final Ref value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateRef(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBlob(final int columnIndex, @NotNull final Blob value)
        throws  SQLException
    {
        updateBlob(columnIndex, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateBlob(int,Blob)
     * @see java.sql.ResultSet#updateBlob(int,java.sql.Blob)
     * @param columnIndex (Taken from Sun Javadoc) the index of the column
     * 1 is the first, 2 is the second,...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateBlob(
        final int columnIndex, @NotNull final Blob value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateBlob(columnIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBlob(@NotNull final String columnName, @NotNull final Blob value)
        throws  SQLException
    {
        updateBlob(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateBlob(String,Blob)
     * @see java.sql.ResultSet#updateBlob(java.lang.String,java.sql.Blob)
     * @param columnName (Taken from Sun Javadoc) the SQL name of the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateBlob(
        @NotNull final String columnName, @NotNull final Blob value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateBlob(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClob(final int columnIndex, @NotNull final Clob value)
        throws  SQLException
    {
        updateClob(columnIndex, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateClob(int,Clob)
     * @see java.sql.ResultSet#updateClob(int,java.sql.Clob)
     * @param columnIndex (Taken from Sun Javadoc) the index of the column
     * 1 is the first, 2 is the second,...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateClob(
        final int columnIndex, @NotNull final Clob value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateClob(columnIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClob(@NotNull final String columnName, @NotNull final Clob value)
        throws  SQLException
    {
        updateClob(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateClob(String,Clob)
     * @see java.sql.ResultSet#updateClob(java.lang.String,java.sql.Clob)
     * @param columnName (Taken from Sun Javadoc) the SQL name of the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateClob(
        @NotNull final String columnName, @NotNull final Clob value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateClob(columnName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateArray(final int columnIndex, @NotNull final Array value)
        throws  SQLException
    {
        updateArray(columnIndex, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateArray(int,Array)
     * @see java.sql.ResultSet#updateArray(int,java.sql.Array)
     * @param columnIndex (Taken from Sun Javadoc) the index of the column
     * 1 is the first, 2 is the second,...
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateArray(
        @NotNull final int columnIndex, @NotNull final Array value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateArray(columnIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateArray(@NotNull final String columnName, @NotNull final Array value)
        throws  SQLException
    {
        updateArray(columnName, value, getResultSetOrDie());
    }

    /**
     * See ResultSet#updateArray(String,Array)
     * @see java.sql.ResultSet#updateArray(java.lang.String,java.sql.Array)
     * @param columnName (Taken from Sun Javadoc) the SQL name of the column.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param resultSet the result set.
     * @exception SQLException if an error occurs.
     */
    protected void updateArray(
        @NotNull final String columnName, @NotNull final Array value, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        resultSet.updateArray(columnName, value);
    }

    // Helper ResultSet methods //

    /**
     * Retrieves a byte array value using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    @SuppressWarnings("unused")
    public byte[] getBytes(@NotNull final Field field)
        throws  SQLException
    {
        return getBytes(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a byte array value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected byte[] getBytes(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getBytes(query.getFieldIndex(field));
    }

    /**
     * Retrieves a boolean value using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is <code>false</code>.
     * @exception SQLException if an error occurs.
     */
    public boolean getBoolean(@NotNull final Field field)
        throws  SQLException
    {
        return getBoolean(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a boolean value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is <code>false</code>.
     * @exception SQLException if an error occurs.
     */
    protected boolean getBoolean(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getBoolean(query.getFieldIndex(field));
    }

    /**
     * Retrieves a boolean value using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @exception SQLException if an error occurs.
     */
    public long getLong(@NotNull final Field field)
        throws  SQLException
    {
        return getLong(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a boolean value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @exception SQLException if an error occurs.
     */
    protected long getLong(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getLong(query.getFieldIndex(field));
    }

    /**
     * Retrieves an object value using the field reference.
     * @param field the field.
     * @return such object.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    @SuppressWarnings("unused")
    public Object getObject(@NotNull final Field field)
        throws  SQLException
    {
        return getObject(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves an object value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return the object.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Object getObject(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getObject(query.getFieldIndex(field));
    }

    /**
     * Retrieves an object value using the field reference.
     * @param field the field.
     * @param map (Taken from Sun Javadoc) a java.util.Map object that
     * contains the mapping from SQL type names to classes in the Java
     * programming language.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    @Nullable
    public Object getObject(@NotNull final Field field, @NotNull final Map<String, Class<?>> map)
        throws  SQLException
    {
        return getObject(field, map, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves an object value using the field reference.
     * @param field the field.
     * @param map (Taken from Sun Javadoc) a java.util.Map object that
     * contains the mapping from SQL type names to classes in the Java
     * programming language.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Object getObject(
        @NotNull final Field field,
        @NotNull final Map<String, Class<?>> map,
        @NotNull final Query query,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getObject(query.getFieldIndex(field), map);
    }

    /**
     * Retrieves a Ref value using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    public Ref getRef(@NotNull final Field field)
        throws  SQLException
    {
        return getRef(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a Ref value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Ref getRef(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getRef(query.getFieldIndex(field));
    }

    /**
     * Retrieves a Time value using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    public Time getTime(@NotNull final Field field)
        throws  SQLException
    {
        return getTime(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a Time value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Time getTime(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getTime(query.getFieldIndex(field));
    }

    /**
     * Retrieves a Time value using the field reference.
     * @param field the field.
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    public Time getTime(@NotNull final Field field, @NotNull final Calendar calendar)
        throws  SQLException
    {
        return getTime(field, calendar, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a Time value using the field reference.
     * @param field the field.
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Time getTime(
        @NotNull final Field field,
        @NotNull final Calendar calendar,
        @NotNull final Query query,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getTime(query.getFieldIndex(field), calendar);
    }

    /**
     * Retrieves a Date value using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    @SuppressWarnings("unused")
    public Date getDate(@NotNull final Field field)
        throws  SQLException
    {
        return getDate(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a Date value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Date getDate(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getDate(query.getFieldIndex(field));
    }

    /**
     * Retrieves a Date value using the field reference.
     * @param field the field.
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    @SuppressWarnings("unused")
    public Date getDate(@NotNull final Field field, @NotNull final Calendar calendar)
        throws  SQLException
    {
        return getDate(field, calendar, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a Date value using the field reference.
     * @param field the field.
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Date getDate(
        @NotNull final Field field,
        @NotNull final Calendar calendar,
        @NotNull final Query query,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getDate(query.getFieldIndex(field), calendar);
    }

    /**
     * Retrieves a byte value using the field reference.
     * @param field the field.
     * @return see getByte(int).
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public byte getByte(@NotNull final Field field)
        throws  SQLException
    {
        return getByte(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a byte value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return see getByte(int).
     * @exception SQLException if an error occurs.
     */
    protected byte getByte(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getByte(query.getFieldIndex(field));
    }

    /**
     * Retrieves a short value using the field reference.
     * @param field the field.
     * @return see getShort(int).
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public short getShort(@NotNull final Field field)
        throws  SQLException
    {
        return getShort(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a short value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return see getShort(int).
     * @exception SQLException if an error occurs.
     */
    protected short getShort(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getShort(query.getFieldIndex(field));
    }

    /**
     * Retrieves an integer value using the field reference.
     * @param field the field.
     * @return see getInt(int)
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public int getInt(@NotNull final Field field)
        throws  SQLException
    {
        return getInt(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves an integer value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return see getInt(int)
     * @exception SQLException if an error occurs.
     */
    protected int getInt(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getInt(query.getFieldIndex(field));
    }

    /**
     * Retrieves a float value using the field reference.
     * @param field the field.
     * @return see getFloat(int)
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public float getFloat(@NotNull final Field field)
        throws  SQLException
    {
        return getFloat(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a float value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return see getFloat(int)
     * @exception SQLException if an error occurs.
     */
    protected float getFloat(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getFloat(query.getFieldIndex(field));
    }

    /**
     * Retrieves a double value using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public double getDouble(@NotNull final Field field)
        throws  SQLException
    {
        return getDouble(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a double value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is 0.
     * @exception SQLException if an error occurs.
     */
    protected double getDouble(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getDouble(query.getFieldIndex(field));
    }

    /**
     * Retrieves a text value using the field reference.
     * @param field the field.
     * @return see getString(int)
     * @exception SQLException if an error occurs.
     */
    @Nullable
    public String getString(@NotNull final ClobField field)
        throws  SQLException
    {
        return
            getString(
                field, getQuery(), getResultSetOrDie(), QueryUtils.getInstance());
    }

    /**
     * Retrieves a text value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @param queryUtils the <code>QueryUtils</code> instance.
     * @return see getString(int)
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected String getString(
        @NotNull final ClobField field,
        @NotNull final Query query,
        @NotNull final ResultSet resultSet,
        @NotNull final QueryUtils queryUtils)
      throws  SQLException
    {
        return
            queryUtils.clobToString(
                resultSet.getClob(query.getFieldIndex(field)));
    }

    /**
     * Retrieves a text value using the field reference.
     * @param field the field.
     * @return see getString(int)
     * @exception SQLException if an error occurs.
     */
    @Nullable
    public String getString(@NotNull final Field field)
        throws  SQLException
    {
        return getString(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a text value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return see getString(int)
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected String getString(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getString(query.getFieldIndex(field));
    }

    /**
     * Retrieves an array value using the field reference.
     * @param field the field.
     * @return see getArray(int)
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    @Nullable
    public Array getArray(@NotNull final Field field)
        throws  SQLException
    {
        return getArray(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves an array value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return see getArray(int)
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Array getArray(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getArray(query.getFieldIndex(field));
    }

    /**
     * Retrieves an ASCII stream using the field reference.
     * @param field the field.
     * @return see getAsciiStream(int)
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    @Nullable
    public InputStream getAsciiStream(@NotNull final Field field)
        throws  SQLException
    {
        return getAsciiStream(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves an ASCII stream using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return see getAsciiStream(int)
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected InputStream getAsciiStream(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getAsciiStream(query.getFieldIndex(field));
    }

    /**
     * Retrieves a BigDecimal value using the field reference.
     * @see #getBigDecimal(int, int)
     * @param field the field.
     * @param scale (Taken from Sun Javadoc) the number of digits to the
     * right of the decimal point.
     * @return see getBigDecimal(int, int)
     * @exception SQLException if an error occurs.
     * @deprecated since it's deprecated in JDK 1.4 as well.
     */
    @SuppressWarnings("unused")
    @Nullable
    public BigDecimal getBigDecimal(@NotNull final Field field, final int scale)
        throws  SQLException
    {
        return getBigDecimal(field, scale, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a BigDecimal value using the field reference.
     * @see #getBigDecimal(int, int)
     * @param field the field.
     * @param scale (Taken from Sun Javadoc) the number of digits to the
     * right of the decimal point.
     * @param query the query.
     * @param resultSet the result set.
     * @return the value.
     * @exception SQLException if an error occurs.
     * @deprecated since it's deprecated in JDK 1.4 as well.
     */
    @Nullable
    protected BigDecimal getBigDecimal(
        @NotNull final Field field,
        final int scale,
        @NotNull final Query query,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getBigDecimal(query.getFieldIndex(field), scale);
    }

    /**
     * Retrieves a BigDecimal value using the field reference.
     * @param field the field.
     * @return see getBigDecimal(field)
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    @Nullable
    public BigDecimal getBigDecimal(@NotNull final Field field)
        throws  SQLException
    {
        return getBigDecimal(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a BigDecimal value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return see getBigDecimal(field)
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected BigDecimal getBigDecimal(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getBigDecimal(query.getFieldIndex(field));
    }

    /**
     * Retrieves a binary stream using the field reference.
     * @param field the field.
     * @return see getBinaryStream(int)
     * @exception SQLException if an error occurs.
     */
    @Nullable
    @SuppressWarnings("unused")
    public InputStream getBinaryStream(@NotNull final Field field)
        throws  SQLException
    {
        return getBinaryStream(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a binary stream using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return see getBinaryStream(int)
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected InputStream getBinaryStream(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getBinaryStream(query.getFieldIndex(field));
    }

    /**
     * Retrieves a blob using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    @Nullable
    public Blob getBlob(@NotNull final Field field)
        throws  SQLException
    {
        return getBlob(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a blob using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Blob getBlob(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getBlob(query.getFieldIndex(field));
    }

    /**
     * Retrieves a clob value using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    public Clob getClob(final Field field)
        throws  SQLException
    {
        return getClob(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a clob value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Clob getClob(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getClob(query.getFieldIndex(field));
    }

    /**
     * Retrieves a timestamp value using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    public Timestamp getTimestamp(@NotNull final Field field)
        throws  SQLException
    {
        return getTimestamp(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a timestamp value using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Timestamp getTimestamp(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
        throws  SQLException
    {
        return resultSet.getTimestamp(query.getFieldIndex(field));
    }

    /**
     * Retrieves a timestamp value using the field reference.
     * @param field the field.
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    public Timestamp getTimestamp(@NotNull final Field field, @NotNull final Calendar calendar)
        throws  SQLException
    {
        return getTimestamp(field, calendar, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a timestamp value using the field reference.
     * @param field the field.
     * @param calendar (Taken from Sun Javadoc) the java.util.Calendar
     * object to use in constructing the time.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    protected Timestamp getTimestamp(
        @NotNull final Field field,
        @NotNull final Calendar calendar,
        @NotNull final Query query,
        @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getTimestamp(query.getFieldIndex(field), calendar);
    }

    /**
     * Retrieves an Unicode stream using the field reference.
     * @param field the field.
     * @return see getUnicodeStream(int)
     * @exception SQLException if an error occurs.
     * @deprecated since it's deprecated in JDK 1.4 as well.
     */
    @Nullable
    public InputStream getUnicodeStream(@NotNull final Field field)
        throws  SQLException
    {
        return getUnicodeStream(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves an Unicode stream using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return see getUnicodeStream(int)
     * @exception SQLException if an error occurs.
     * @deprecated since it's deprecated in JDK 1.4 as well.
     */
    @Nullable
    protected InputStream getUnicodeStream(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getUnicodeStream(query.getFieldIndex(field));
    }

    /**
     * Retrieves a character stream using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    @Nullable
    public Reader getCharacterStream(@NotNull final Field field)
        throws  SQLException
    {
        return getCharacterStream(field, getQuery(), getResultSetOrDie());
    }

    /**
     * Retrieves a character stream using the field reference.
     * @param field the field.
     * @param query the query.
     * @param resultSet the result set.
     * @return (Taken from Sun Javadoc) the column value; if
     * the value is SQL NULL, the value returned is null.
     * @exception SQLException if an error occurs.
     */
    protected Reader getCharacterStream(
        @NotNull final Field field, @NotNull final Query query, @NotNull final ResultSet resultSet)
      throws  SQLException
    {
        return resultSet.getCharacterStream(query.getFieldIndex(field));
    }

    // New methods from JDK 1.4 //

    /**
     * Retrieves an URL using the field reference.
     * @param field the field.
     * @return (Taken from Sun Javadoc) the column value as a java.net.URL
     * object; if the value is SQL NULL, the value returned is null in the
     * Java programming language.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    @Nullable
    public URL getURL(@NotNull final Field field)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        return getResultSetOrDie().getURL(t_iFieldIndex);
    }

    /**
     * Retrieves the column index associated to given field.
     * @param field the field.
     * @return the column index of the given field.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public int findColumn(@NotNull final Field field)
        throws  SQLException
    {
        return getQuery().getFieldIndex(field);
    }

    /**
     * Updates an ASCII stream column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param length (Taken from Sun Javadoc) the length of the stream.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateAsciiStream(
        @NotNull final Field field,
        @NotNull final InputStream value,
        final int length)
      throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateAsciiStream(t_iFieldIndex, value, length);
    }

    /**
     * See ResultSet#updateBigDecimal(String,BigDecimal)
     * @see java.sql.ResultSet#updateBigDecimal(java.lang.String,java.math.BigDecimal)
     * @param field the field.
     * @param value a <code>BigDecimal</code> value
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateBigDecimal(@NotNull final Field field, @NotNull final BigDecimal value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateBigDecimal(t_iFieldIndex, value);
    }

    /**
     * Updates a binary stream column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param length (Taken from Sun Javadoc) the length of the stream.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateBinaryStream(
        @NotNull final Field field,
        @NotNull final InputStream value,
        final int length)
      throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateBinaryStream(t_iFieldIndex, value, length);
    }

    /**
     * Updates a boolean column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateBoolean(final Field field, final boolean value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateBoolean(t_iFieldIndex, value);
    }

    /**
     * Updates a byte column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateByte(@NotNull final Field field, final byte value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateByte(t_iFieldIndex, value);
    }

    /**
     * Updates a byte array column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateBytes(final Field field, final byte[] value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateBytes(t_iFieldIndex, value);
    }

    /**
     * Updates a character stream column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param length (Taken from Sun Javadoc) the length of the stream.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateCharacterStream(
        @NotNull final Field field,
        @NotNull final Reader value,
        final int length)
      throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateCharacterStream(t_iFieldIndex, value, length);
    }

    /**
     * Updates a date column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateDate(@NotNull final Field field, @NotNull final Date value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateDate(t_iFieldIndex, value);
    }

    /**
     * Updates a double column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateDouble(@NotNull final Field field, final double value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateDouble(t_iFieldIndex, value);
    }

    /**
     * Updates a float column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs
     */
    @SuppressWarnings("unused")
    public void updateFloat(@NotNull final Field field, final float value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateFloat(t_iFieldIndex, value);
    }

    /**
     * Updates an integer column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateInt(@NotNull final Field field, final int value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateInt(t_iFieldIndex, value);
    }

    /**
     * Updates a long column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs
     */
    @SuppressWarnings("unused")
    public void updateLong(@NotNull final Field field, final long value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateLong(t_iFieldIndex, value);
    }

    /**
     * Sets a column to null using the field reference.
     * @param field the field.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateNull(@NotNull final Field field)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateNull(t_iFieldIndex);
    }

    /**
     * Updates an object column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @param scale (Taken from Sun Javadoc) for java.sql.Types.DECIMA
     * or java.sql.Types.NUMERIC types, this is the number of digits after
     * the decimal point. For all other types this value will be ignored.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateObject(
        @NotNull final Field field, @NotNull final Object value, final int scale)
      throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateObject(t_iFieldIndex, value, scale);
    }

    /**
     * Updates an object column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateObject(@NotNull final Field field, @NotNull final Object value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateObject(t_iFieldIndex, value);
    }

    /**
     * Updates a short column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateShort(@NotNull final Field field, final short value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateShort(t_iFieldIndex, value);
    }

    /**
     * Updates a text column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateString(@NotNull final Field field, @NotNull final String value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateString(t_iFieldIndex, value);
    }

    /**
     * Updates a time column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateTime(@NotNull final Field field, @NotNull final Time value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateTime(t_iFieldIndex, value);
    }

    /**
     * Updates a timestamp column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateTimestamp(@NotNull final Field field, @NotNull final Timestamp value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateTimestamp(t_iFieldIndex, value);
    }

    // Methods from JDK 1.4 //

    /**
     * Updates a Ref column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateRef(@NotNull final Field field, @NotNull final Ref value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateRef(t_iFieldIndex, value);
    }

    /**
     * Updates a Blob column using the field bloberence.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateBlob(@NotNull final Field field, @NotNull final Blob value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateBlob(t_iFieldIndex, value);
    }

    /**
     * Updates a Clob column using the field reference.
     * @param field the field.
     * @param value (Taken from Sun Javadoc) the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateClob(@NotNull final Field field, @NotNull final Clob value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateClob(t_iFieldIndex, value);
    }

    /**
     * Updates a Array column using the field reference.
     * @param field the field.
     * @param value the new column value.
     * @exception SQLException if an error occurs.
     */
    @SuppressWarnings("unused")
    public void updateArray(@NotNull final Field field, @NotNull final Array value)
        throws  SQLException
    {
        final int t_iFieldIndex = getQuery().getFieldIndex(field);

        getResultSetOrDie().updateArray(t_iFieldIndex, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNClob(@NotNull final String name, @NotNull final Reader reader)
        throws SQLException
    {
        updateNClob(name, reader, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNClob(String, java.io.Reader)
     * @param name the parameter name.
     * @param reader the Clob reader.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNClob(
        @NotNull final String name, @NotNull final Reader reader, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNClob(name, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNClob(@NotNull final String name, @NotNull final Reader reader, final long length)
        throws SQLException
    {
        updateNClob(name, reader, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNClob(String, java.io.Reader, long)
     * @param name the parameter name.
     * @param reader the Clob reader.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNClob(
        @NotNull final String name,
        @NotNull final Reader reader,
        final long length,
        @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNClob(name, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNClob(final int index, @NotNull final Reader reader)
        throws SQLException
    {
        updateNClob(index, reader, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNClob(int, java.io.Reader)
     * @param index the parameter index.
     * @param reader the Clob reader.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNClob(
        final int index,
        @NotNull final Reader reader,
        @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNClob(index, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNClob(final int index, @NotNull final Reader reader, final long length)
        throws SQLException
    {
        updateNClob(index, reader, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNClob(int, java.io.Reader, long)
     * @param index the parameter index.
     * @param reader the Clob reader.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNClob(
        final int index, @NotNull final Reader reader, final long length, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNClob(index, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClob(@NotNull final String name, @NotNull final Reader reader)
        throws SQLException
    {
        updateClob(name, reader, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateClob(String, java.io.Reader)
     * @param name the parameter name.
     * @param reader the Clob reader.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateClob(
        final String name, @NotNull final Reader reader, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateClob(name, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClob(@NotNull final String name, @NotNull final Reader reader, final long length)
        throws SQLException
    {
        updateClob(name, reader, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateClob(String, java.io.Reader, long)
     * @param name the parameter name.
     * @param reader the Clob reader.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateClob(
        @NotNull final String name, @NotNull final Reader reader, final long length, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateClob(name, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClob(final int index, @NotNull final Reader reader)
        throws SQLException
    {
        updateClob(index, reader, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateClob(int, java.io.Reader)
     * @param index the parameter index.
     * @param reader the Clob reader.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateClob(
        final int index, @NotNull final Reader reader, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateClob(index, reader);
    }

    /**
     * {@inheritDoc}
     */
    public void updateClob(final int index, @NotNull final Reader reader, final long length)
        throws SQLException
    {
        updateClob(index, reader, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateClob(int, java.io.Reader, long)
     * @param index the parameter index.
     * @param reader the Clob reader.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateClob(
        final int index, @NotNull final Reader reader, final long length, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateClob(index, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBlob(@NotNull final String name, @NotNull final InputStream stream)
        throws SQLException
    {
        updateBlob(name, stream, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateBlob(String, java.io.InputStream)
     * @param name the parameter name.
     * @param stream the Blob stream.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateBlob(
        @NotNull final String name, @NotNull final InputStream stream, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateBlob(name, stream);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBlob(@NotNull final String name, @NotNull final InputStream stream, final long length)
        throws SQLException
    {
        updateBlob(name, stream, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateBlob(String, java.io.InputStream, long)
     * @param name the parameter name.
     * @param stream the Blob stream.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateBlob(
        @NotNull final String name, @NotNull final InputStream stream, final long length, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateBlob(name, stream, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBlob(final int index, final InputStream stream)
        throws SQLException
    {
        updateBlob(index, stream, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateBlob(int, java.io.InputStream)
     * @param index the parameter index.
     * @param stream the Blob stream.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateBlob(
        final int index, @NotNull final InputStream stream, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateBlob(index, stream);
    }

    /**
     * {@inheritDoc}
     */
    public void updateBlob(final int index, @NotNull final InputStream stream, final long length)
        throws SQLException
    {
        updateBlob(index, stream, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateBlob(int, java.io.InputStream, long)
     * @param index the parameter index.
     * @param stream the Blob stream.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateBlob(
        final int index, @NotNull final InputStream stream, final long length, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateBlob(index, stream, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCharacterStream(@NotNull final String name, @NotNull final Reader reader)
        throws SQLException
    {
        updateCharacterStream(name, reader, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateCharacterStream(String, java.io.Reader)
     * @param name the parameter name.
     * @param reader the CharacterStream reader.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateCharacterStream(
        @NotNull final String name, @NotNull final Reader reader, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateCharacterStream(name, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCharacterStream(@NotNull final String name, @NotNull final Reader reader, final long length)
        throws SQLException
    {
        updateCharacterStream(name, reader, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateCharacterStream(String, java.io.Reader, long)
     * @param name the parameter name.
     * @param reader the CharacterStream reader.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateCharacterStream(
        @NotNull final String name, @NotNull final Reader reader, final long length, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateCharacterStream(name, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCharacterStream(final int index, @NotNull final Reader reader)
        throws SQLException
    {
        updateCharacterStream(index, reader, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateCharacterStream(int, java.io.Reader)
     * @param index the parameter index.
     * @param reader the CharacterStream reader.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateCharacterStream(
        final int index, @NotNull final Reader reader, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateCharacterStream(index, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCharacterStream(final int index, @NotNull final Reader reader, final long length)
        throws SQLException
    {
        updateCharacterStream(index, reader, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateCharacterStream(int, java.io.Reader, long)
     * @param index the parameter index.
     * @param reader the CharacterStream reader.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateCharacterStream(
        final int index, @NotNull final Reader reader, final long length, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateCharacterStream(index, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNCharacterStream(@NotNull final String name, @NotNull final Reader reader)
        throws SQLException
    {
        updateNCharacterStream(name, reader, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNCharacterStream(String, java.io.Reader)
     * @param name the parameter name.
     * @param reader the NCharacterStream reader.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNCharacterStream(
        @NotNull final String name, @NotNull final Reader reader, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNCharacterStream(name, reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNCharacterStream(@NotNull final String name, @NotNull final Reader reader, final long length)
        throws SQLException
    {
        updateNCharacterStream(name, reader, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNCharacterStream(String, java.io.Reader, long)
     * @param name the parameter name.
     * @param reader the NCharacterStream reader.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNCharacterStream(
        @NotNull final String name, @NotNull final Reader reader, final long length, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNCharacterStream(name, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNCharacterStream(final int index, @NotNull final Reader reader)
        throws SQLException
    {
        updateNCharacterStream(index, reader, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNCharacterStream(int, java.io.Reader)
     * @param index the parameter index.
     * @param reader the NCharacterStream reader.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNCharacterStream(
        final int index, @NotNull final Reader reader, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNCharacterStream(index, reader);
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public void updateNCharacterStream(final int index, @NotNull final Reader reader, final long length)
        throws SQLException
    {
        updateNCharacterStream(index, reader, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNCharacterStream(int, java.io.Reader, long)
     * @param index the parameter index.
     * @param reader the NCharacterStream reader.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNCharacterStream(
        final int index, @NotNull final Reader reader, final long length, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNCharacterStream(index, reader, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBinaryStream(@NotNull final String name, @NotNull final InputStream stream)
        throws SQLException
    {
        updateBinaryStream(name, stream, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateBinaryStream(String, java.io.InputStream)
     * @param name the parameter name.
     * @param stream the BinaryStream stream.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateBinaryStream(
        @NotNull final String name, @NotNull final InputStream stream, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateBinaryStream(name, stream);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBinaryStream(@NotNull final String name, @NotNull final InputStream stream, final long length)
        throws SQLException
    {
        updateBinaryStream(name, stream, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateBinaryStream(String, java.io.InputStream, long)
     * @param name the parameter name.
     * @param stream the BinaryStream stream.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateBinaryStream(
        @NotNull final String name,
        @NotNull final InputStream stream,
        final long length,
        @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateBinaryStream(name, stream, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBinaryStream(final int index, @NotNull final InputStream stream)
        throws SQLException
    {
        updateBinaryStream(index, stream, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateBinaryStream(int, java.io.InputStream)
     * @param index the parameter index.
     * @param stream the BinaryStream stream.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateBinaryStream(
        final int index, @NotNull final InputStream stream, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateBinaryStream(index, stream);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBinaryStream(final int index, @NotNull final InputStream stream, final long length)
        throws SQLException
    {
        updateBinaryStream(index, stream, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateBinaryStream(int, java.io.InputStream, long)
     * @param index the parameter index.
     * @param stream the BinaryStream stream.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateBinaryStream(
        final int index, @NotNull final InputStream stream, final long length, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateBinaryStream(index, stream, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAsciiStream(@NotNull final String name, @NotNull final InputStream stream)
        throws SQLException
    {
        updateAsciiStream(name, stream, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateAsciiStream(String, java.io.InputStream)
     * @param name the parameter name.
     * @param stream the AsciiStream stream.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateAsciiStream(
        @NotNull final String name, @NotNull final InputStream stream, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateAsciiStream(name, stream);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAsciiStream(
        @NotNull final String name, @NotNull final InputStream stream, final long length)
        throws SQLException
    {
        updateAsciiStream(name, stream, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateAsciiStream(String, java.io.InputStream, long)
     * @param name the parameter name.
     * @param stream the AsciiStream stream.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateAsciiStream(
        @NotNull final String name,
        @NotNull final InputStream stream,
        final long length,
        @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateAsciiStream(name, stream, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAsciiStream(final int index, @NotNull final InputStream stream)
        throws SQLException
    {
        updateAsciiStream(index, stream, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateAsciiStream(int, java.io.InputStream)
     * @param index the parameter index.
     * @param stream the AsciiStream stream.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateAsciiStream(
        final int index,
        @NotNull final InputStream stream,
        @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateAsciiStream(index, stream);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAsciiStream(
        final int index,
        @NotNull final InputStream stream,
        final long length)
      throws SQLException
    {
        updateAsciiStream(index, stream, length, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateAsciiStream(int, java.io.InputStream, long)
     * @param index the parameter index.
     * @param stream the AsciiStream stream.
     * @param length the length.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateAsciiStream(
        final int index,
        @NotNull final InputStream stream,
        final long length,
        @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateAsciiStream(index, stream, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Reader getNCharacterStream(@NotNull final String name)
        throws SQLException
    {
        return getNCharacterStream(name, getResultSetOrDie());
    }

    /**
     * @see java.sql.ResultSet#getNCharacterStream(String)
     * @param name the parameter name.
     * @param resultSet the result set.
     * @return the value.
     * @throws SQLException if the operation fails.
     */
    @Nullable
    protected Reader getNCharacterStream(@NotNull final String name, @NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getNCharacterStream(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Reader getNCharacterStream(final int index)
        throws SQLException
    {
        return getNCharacterStream(index, getResultSetOrDie());
    }

    /**
     * @see java.sql.ResultSet#getNCharacterStream(int)
     * @param index the parameter index.
     * @param resultSet the result set.
     * @return the value.
     * @throws SQLException if the operation fails.
     */
    @Nullable
    protected Reader getNCharacterStream(final int index, @NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getNCharacterStream(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public String getNString(@NotNull final String name)
        throws SQLException
    {
        return getNString(name, getResultSetOrDie());
    }

    /**
     * See java.sql.ResultSet#getNString(String).
     * @param name the parameter name.
     * @param resultSet the result set.
     * @return the value.
     * @throws SQLException if the operation fails.
     */
    @Nullable
    protected String getNString(@NotNull final String name, @NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getNString(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public String getNString(final int index)
        throws SQLException
    {
        return getNString(index, getResultSetOrDie());
    }

    /**
     * See java.sql.ResultSet#getNString(int).
     * @param index the parameter index.
     * @param resultSet the result set.
     * @return the value.
     * @throws SQLException if the operation fails.
     */
    @Nullable
    protected String getNString(final int index, @NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getNString(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateSQLXML(@NotNull final String name, @NotNull final SQLXML sqlXml)
        throws SQLException
    {
        updateSQLXML(name, sqlXml, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateSQLXML(String, java.sql.SQLXML)
     * @param name the parameter name.
     * @param sqlXml the SQLXML value.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateSQLXML(
        @NotNull final String name, @NotNull final SQLXML sqlXml , @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateSQLXML(name, sqlXml);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateSQLXML(final int index, @NotNull final SQLXML sqlXml)
        throws SQLException
    {
        updateSQLXML(index, sqlXml, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateSQLXML(int, java.sql.SQLXML)
     * @param index the parameter index.
     * @param sqlXml the SQLXML value.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateSQLXML(
        final int index,
        @NotNull final SQLXML sqlXml,
        @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateSQLXML(index, sqlXml);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public SQLXML getSQLXML(@NotNull final String name)
        throws SQLException
    {
        return getSQLXML(name, getResultSetOrDie());
    }

    /**
     * @see java.sql.ResultSet#getSQLXML(String)
     * @param name the parameter name.
     * @param resultSet the result set.
     * @return the value.
     * @throws SQLException if the operation fails.
     */
    @Nullable
    protected SQLXML getSQLXML(@NotNull final String name, @NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getSQLXML(name);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    public SQLXML getSQLXML(final int index)
        throws SQLException
    {
        return getSQLXML(index, getResultSetOrDie());
    }

    /**
     * @see java.sql.ResultSet#getSQLXML(int)
     * @param index the parameter index.
     * @param resultSet the result set.
     * @return the value.
     * @throws SQLException if the operation fails.
     */
    @Nullable
    protected SQLXML getSQLXML(final int index, @NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getSQLXML(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public NClob getNClob(@NotNull final String name)
        throws SQLException
    {
        return getNClob(name, getResultSetOrDie());
    }

    /**
     * @see java.sql.ResultSet#getNClob(String)
     * @param name the parameter name.
     * @param resultSet the result set.
     * @return the value.
     * @throws SQLException if the operation fails.
     */
    @Nullable
    protected NClob getNClob(@NotNull final String name, @NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getNClob(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public NClob getNClob(final int index)
        throws SQLException
    {
        return getNClob(index, getResultSetOrDie());
    }

    /**
     * @see java.sql.ResultSet#getNClob(int)
     * @param index the parameter index.
     * @param resultSet the result set.
     * @return the value.
     * @throws SQLException if the operation fails.
     */
    @Nullable
    protected NClob getNClob(final int index, @NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getNClob(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNClob(@NotNull final String name, @NotNull final NClob nclob)
        throws SQLException
    {
        updateNClob(name, nclob, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNClob(String, java.sql.NClob)
     * @param name the parameter name.
     * @param nclob the NClob value.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNClob(
        @NotNull final String name, @NotNull final NClob nclob , @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNClob(name, nclob);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNClob(final int index, @NotNull final NClob nclob)
        throws SQLException
    {
        updateNClob(index, nclob, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNClob(int, java.sql.NClob)
     * @param index the parameter index.
     * @param nclob the NClob value.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNClob(
        final int index,
        @NotNull final NClob nclob,
        @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNClob(index, nclob);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNString(@NotNull final String name, @NotNull final String nstring)
        throws SQLException
    {
        updateNString(name, nstring, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNString(String, String)
     * @param name the parameter name.
     * @param nstring the String value.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNString(
        @NotNull final String name,
        @NotNull final String nstring,
        @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNString(name, nstring);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNString(final int index, @NotNull final String nstring)
        throws SQLException
    {
        updateNString(index, nstring, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateNString(int, String)
     * @param index the parameter index.
     * @param nstring the String value.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateNString(
        final int index,
        @NotNull final String nstring,
        @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateNString(index, nstring);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public RowId getRowId(@NotNull final String name)
        throws SQLException
    {
        return getRowId(name, getResultSetOrDie());
    }

    /**
     * @see java.sql.ResultSet#getRowId(String)
     * @param name the parameter name.
     * @param resultSet the result set.
     * @return the value.
     * @throws SQLException if the operation fails.
     */
    @Nullable
    protected RowId getRowId(@NotNull final String name, @NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getRowId(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public RowId getRowId(final int index)
        throws SQLException
    {
        return getRowId(index, getResultSetOrDie());
    }

    /**
     * @see java.sql.ResultSet#getRowId(int)
     * @param index the parameter index.
     * @param resultSet the result set.
     * @return the value.
     * @throws SQLException if the operation fails.
     */
    @Nullable
    protected RowId getRowId(final int index, @NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getRowId(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRowId(@NotNull final String name, @NotNull final RowId rowId)
        throws SQLException
    {
        updateRowId(name, rowId, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateRowId(String, java.sql.RowId)
     * @param name the parameter name.
     * @param rowId the row id.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateRowId(
        @NotNull final String name, @NotNull final RowId rowId, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateRowId(name, rowId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRowId(final int index, @NotNull final RowId rowId)
        throws SQLException
    {
        updateRowId(index, rowId, getResultSetOrDie());
    }

    /**
     * See java.sql.PreparedStatement#updateRowId(int, java.sql.RowId)
     * @param index the parameter index.
     * @param rowId the row id.
     * @param resultSet the actual result set.
     * @throws SQLException if the operation fails.
     */
    protected void updateRowId(
        final int index, @NotNull final RowId rowId, @NotNull final ResultSet resultSet)
      throws SQLException
    {
        resultSet.updateRowId(index, rowId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClosed()
        throws SQLException
    {
        return isClosed(getResultSetOrDie());
    }

    /**
     * @see java.sql.ResultSet#isClosed()
     * @param resultSet the result set.
     * @return such information.
     * @throws SQLException if the operation fails.
     */
    protected boolean isClosed(@NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.isClosed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHoldability()
        throws SQLException
    {
        return getHoldability(getResultSetOrDie());
    }

    /**
     * @see java.sql.ResultSet#getHoldability()
     * @param resultSet the result set.
     * @return such information.
     * @throws SQLException if the operation fails.
     */
    protected int getHoldability(@NotNull final ResultSet resultSet)
        throws SQLException
    {
        return resultSet.getHoldability();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWrapperFor(final Class<?> wrapperClass)
    {
        return isWrapperFor(wrapperClass, getResultSet());
    }

    /**
     * Checks whether the wrapped result set is compatible with given class.
     * @param wrapperClass the wrapper class.
     * @param wrappedResultSet the wrapped result set.
     * @return <code>true</code> if the wrapped statement is compatible with given class.
     */
    protected boolean isWrapperFor(@NotNull final Class<?> wrapperClass, @Nullable final ResultSet wrappedResultSet)
    {
        return
            (   (wrappedResultSet != null)
             && (wrappedResultSet.getClass().isAssignableFrom(wrapperClass)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public <T> T unwrap(final Class<T> wrapperClass)
    {
        return unwrap(wrapperClass, getResultSet());
    }

    /**
     * Unwraps the wrapped result set if it's compatible with given class.
     * @param wrapperClass the wrapper class.
     * @param wrappedResultSet the wrapped result set.
     * @param <T> the type.
     * @return the wrapped statement if it's compatible.
     */
    @Nullable
    protected <T> T unwrap(final Class<T> wrapperClass, final ResultSet wrappedResultSet)
    {
        @Nullable T result = null;

        if  (isWrapperFor(wrapperClass, wrappedResultSet))
        {
            result = (T) wrappedResultSet;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getObject(final String name, final Class<T> clazz)
	throws SQLException
    {
	    return getResultSetOrDie().getObject(name, clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getObject(final int index, final Class<T> clazz)
	throws SQLException
    {
	    return getResultSetOrDie().getObject(index, clazz);
    }

    @Override
    public String toString()
    {
        return "QueryResultSet{" +
               "m__iTempFetchDirection=" + m__iTempFetchDirection +
               ", m__Query=" + m__Query +
               ", m__ResultSet=" + m__ResultSet +
               ", m__iTempFetchSize=" + m__iTempFetchSize +
               '}';
    }
}
