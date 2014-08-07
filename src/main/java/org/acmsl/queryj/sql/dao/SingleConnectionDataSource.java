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
    Lesser General Public License for more details.

    You should have received a copy of the GNU General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

    Thanks to ACM S.L. for distributing this library under the GPL license.
    Contact info: jose.sanleandro@acm-sl.com

 ******************************************************************************
 *
 * Filename: SingleConnectionDataSource.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Wraps a given connection to appear as a valid data source.
 *
 */
package org.acmsl.queryj.sql.dao;

/*
 * Importing NotNull annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Importing some JDK1.3 classes.
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/*
 * Importing some extension classes.
 */
import javax.sql.DataSource;

/**
 * DataSource proxy to wrap the original connections with connection proxies.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class   SingleConnectionDataSource
    implements DataSource
{
    /**
     * Wrapped connection.
     */
    private Connection m__Connection;

    /**
     * The log writer.
     */
    private PrintWriter m__pwLogWriter;

    /**
     * The login timeout.
     */
    private int m__iLoginTimeout;

    /**
     * Creates a <code>SingleConnectionDataSource</code> to wrap given connection.
     * @param connection the connection to wrap.
     */
    @SuppressWarnings("unused")
    public SingleConnectionDataSource(@NotNull final Connection connection)
    {
        immutableSetWrappedConnection(connection);
    }

    /**
     * Specifies the connection.
     * @param connection the connection to wrap.
     */
    protected final void immutableSetWrappedConnection(@NotNull final Connection connection)
    {
        m__Connection = connection;
    }

    /**
     * Specifies the connection.
     * @param connection the connection to wrap.
     */
    @SuppressWarnings("unused")
    protected void setWrappedConnection(@NotNull final Connection connection)
    {
        immutableSetWrappedConnection(connection);
    }

    /**
     * Retrieves the connection.
     * @return such instance.
     */
    @NotNull
    public Connection getWrappedConnection()
    {
        return m__Connection;
    }

    /**
     * Attempts to establish a database connection.
     * @return a Connection to the database
     * @throws SQLException if a database-access error occurs.
     */
    @Override
    @NotNull
    public Connection getConnection()
        throws SQLException
    {
        return getWrappedConnection();
    }

    /**
     * Attempts to establish a database connection.
     * @param username the database user on whose behalf the Connection
     * is being made.
     * @param password the user's password.
     * @return a Connection to the database.
     * @throws SQLException if a database-access error occurs.
     */
    @NotNull
    public Connection getConnection(
        @NotNull final String username, @NotNull final String password)
      throws SQLException
    {
        return
            getConnection(
                username,
                password,
                getWrappedConnection());
    }

    /**
     * Attempts to establish a database connection.
     * @param username the database user on whose behalf the Connection
     * is being made.
     * @param password the user's password.
     * @param connection the connection.
     * @return a Connection to the database.
     * @throws SQLException if a database-access error occurs.
     */
    @SuppressWarnings("unused")
    @NotNull
    protected Connection getConnection(
        @NotNull final String username,
        @NotNull final String password,
        @NotNull final Connection connection)
      throws SQLException
    {
        @Nullable SQLException exceptionToThrow = null;

        @Nullable DatabaseMetaData databaseMetaData = null;

        try
        {
            databaseMetaData = connection.getMetaData();
        }
        catch  (@NotNull final SQLException sqlException)
        {
            exceptionToThrow = sqlException;
        }

        try
        {
            if  (   (databaseMetaData != null)
                 && (!username.equals(databaseMetaData.getUserName())))
            {
                exceptionToThrow =
                    new SQLException(
                        "Cannot use different username/password settings.");
            }
        }
        catch  (@NotNull final SQLException sqlException)
        {
            exceptionToThrow = sqlException;
        }

        if  (exceptionToThrow != null)
        {
            throw exceptionToThrow;
        }

        return connection;
    }

    /**
     * Specifies the log writer.
     * @param out such stream.
     */
    private void immutableSetLogWriter(final PrintWriter out)
    {
        m__pwLogWriter = out;
    }

    /**
     * Sets the log writer for this data source.
     * The log writer is a character output stream to which all logging and
     * tracing messages for this data source object instance will be printed.
     * This includes messages printed by the methods of this object, messages
     * printed by methods of other objects manufactured by this object, and
     * so on.
     * Messages printed to a data source specific log writer are not printed
     * to the log writer associated with the java.sql.Drivermanager class.
     * When a DataSource object is created the log writer is initially null,
     * in other words, logging is disabled.
     * @param out the new log writer; to disable, set to null
     * @throws SQLException if a database-access error occurs.
     */
    public void setLogWriter(final PrintWriter out)
        throws SQLException
    {
        immutableSetLogWriter(out);
    }

    /**
     * Get the log writer for this data source.
     * The log writer is a character output stream to which all logging
     * and tracing messages for this data source object instance will be
     * printed.
     * This includes messages printed by the methods of this object,
     * messages printed by methods of other objects manufactured by this
     * object, and so on.
     * Messages printed to a data source specific log writer are not printed
     * to the log writer associated with the java.sql.Drivermanager class.
     * When a DataSource object is created the log writer is initially null,
     * in other words, logging is disabled.
     * @return the log writer for this data source, null if disabled.
     * @throws SQLException if a database-access error occurs.
     */
    public PrintWriter getLogWriter()
        throws SQLException
    {
        return m__pwLogWriter;
    }

    /**
     * Specifies the login timeout.
     * @param seconds such timeout.
     */
    private void immutableSetLoginTimeout(final int seconds)
    {
        m__iLoginTimeout = seconds;
    }

    /**
     * Sets the maximum time in seconds that this data source will wait
     * while attempting to connect to a database. A value of zero specifies
     * that the timeout is the default system timeout if there is one;
     * otherwise it specifies that there is no timeout. When a DataSource
     * object is created the login timeout is initially zero.
     * @param seconds the data source login time limit.
     * @throws SQLException if a database access error occurs.
     */
    public void setLoginTimeout(final int seconds)
        throws SQLException
    {
        immutableSetLoginTimeout(seconds);
    }

    /**
     * Gets the maximum time in seconds that this data source can wait while
     * attempting to connect to a database. A value of zero means that the
     * timeout is the default system timeout if there is one; otherwise it
     * means that there is no timeout. When a DataSource object is created
     * the login timeout is initially zero.
     * @return the data source login time limit.
     * @throws SQLException if a database access error occurs.
     */
    public int getLoginTimeout()
        throws SQLException
    {
        return m__iLoginTimeout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getParentLogger()
    {
        return Logger.getLogger(SingleConnectionDataSource.class.getName());
    }

    /**
     * Checks whether given object is logically equal to this instance.
     * @param object the object to check.
     * @return <code>true</code> in such case.
     */
    public boolean equals(final Object object)
    {
        return equals(object, getWrappedConnection());
    }

    /**
     * Checks whether given object is logically equal to this instance.
     * @param object the object to check.
     * @return <code>true</code> in such case.
     */
    protected boolean equals(@Nullable final Object object, @Nullable final Connection connection)
    {
        boolean result = false;

        if  (   (object != null)
             && (object instanceof Connection)
             && (connection != null))
        {
            result = connection.equals(object);
        }

        return result;
    }

    /**
     * Retrieves the hashcode of wrapped instance.
     * @return such hashcode.
     */
    public int hashCode()
    {
        return hashCode(getWrappedConnection());
    }

    /**
     * Retrieves the hashcode of wrapped instance.
     * @param object the object.
     * @return such hashcode.
     */
    protected int hashCode(@NotNull final Object object)
    {
        return object.hashCode();
    }

    /**
     * Executes wrapped instance's toString(), with a
     * "datasource wrapped: " prefix.
     * @return such text.
     */
    @Override
    @NotNull
    public String toString()
    {
        return toString(getWrappedConnection());
    }

    /**
     * Executes wrapped instance's toString(), with a
     * "datasource wrapped: " prefix.
     * @param object the object.
     * @return such text.
     */
    @NotNull
    protected String toString(@NotNull final Object object)
    {
        return "connection wrapped: " + object;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWrapperFor(@NotNull final Class<?> wrapperClass)
    {
        return isWrapperFor(wrapperClass, getWrappedConnection());
    }

    /**
     * Checks whether the wrapped connection is compatible with given class.
     * @param wrapperClass the wrapper class.
     * @param wrappedConnection the wrapped connection.
     * @return <code>true</code> if the wrapped connection is compatible with given class.
     */
    protected boolean isWrapperFor(@NotNull final Class<?> wrapperClass, @Nullable final Object wrappedConnection)
    {
        return
            (   (wrappedConnection != null)
             && (wrappedConnection.getClass().isAssignableFrom(wrapperClass)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public <T> T unwrap(@NotNull final Class<T> wrapperClass)
    {
        return unwrap(wrapperClass, getWrappedConnection());
    }

    /**
     * Unwraps the wrapped connection if it's compatible with given class.
     * @param wrapperClass the wrapper class.
     * @param wrappedConnection the wrapped connection.
     * @return the wrapped connection if it's compatible.
     */
    @SuppressWarnings("unchecked")
    @Nullable
    protected <T> T unwrap(@NotNull final Class<T> wrapperClass, @NotNull final Object wrappedConnection)
    {
        @Nullable final T result;

        if  (isWrapperFor(wrapperClass, wrappedConnection))
        {
            result = (T) wrappedConnection;
        }
        else
        {
            result = null;
        }

        return result;
    }
}
