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
 * Filename: DataSourceTransactionToken.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: JDBC-related Transaction Token using DataSource class.
 */
package org.acmsl.queryj.sql.dao;

/*
 * Importing Spring classes.
 */
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.TransactionStatus;

/*
 * Importing some JDK1.3 classes.
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/*
 * Importing some extension classes.
 */
import javax.sql.DataSource;

/*
 * Importing NotNull annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * JDBC-related Transaction Token using DataSource class.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class DataSourceTransactionToken
    extends     DefaultTransactionStatus
    implements  TransactionToken
{
    /**
     * DataSource reference.
     */
    private _DataSourceWrapper m__DataSource;

    /**
     * The wrapped transaction status.
     */
    private TransactionStatus m__TransactionStatus;

    /**
     * Creates a new <code>DataSourceTransactionToken</code> instance.
     * @param transactionStatus the original transaction status.
     * @param dataSource the data source.
     */
    public DataSourceTransactionToken(
        @NotNull final DefaultTransactionStatus transactionStatus,
        @NotNull final DataSource dataSource)
    {
        super(
            transactionStatus.getTransaction(),
            transactionStatus.isNewTransaction(),
            transactionStatus.isNewSynchronization(),
            transactionStatus.isReadOnly(),
            transactionStatus.isDebug(),
            transactionStatus.getSuspendedResources());
        immutableSetTransactionStatus(transactionStatus);
        immutableSetDataSource(dataSource);
    }

    /**
     * Specifies the transaction status.
     * @param status such status.
     */
    protected final void immutableSetTransactionStatus(@NotNull final TransactionStatus status)
    {
        m__TransactionStatus = status;
    }

    /**
     * Specifies the transaction status.
     * @param status such status.
     */
    @SuppressWarnings("unused")
    protected void setTransactionStatus(@NotNull final TransactionStatus status)
    {
        immutableSetTransactionStatus(status);
    }

    /**
     * Retrieves the transaction status.
     * @return such information.
     */
    @SuppressWarnings("unused")
    @Nullable
    protected TransactionStatus getTransactionStatus()
    {
        return m__TransactionStatus;
    }

    /**
     * Specifies the data source.
     * @param dataSource the connection to use inside the whole transaction.
     */
    protected final void immutableSetDataSource(@NotNull final DataSource dataSource)
    {
        final DataSource t_DataSource = immutableGetDataSource();

        if  (t_DataSource != dataSource)
        {
            m__DataSource = new _DataSourceWrapper(dataSource);
        }
    }

    /**
     * Specifies the data source.
     * @param dataSource the connection to use inside the whole transaction.
     */
    protected void setDataSource(@NotNull final DataSource dataSource)
    {
        immutableSetDataSource(dataSource);
    }
        
    /**
     * Retrieves the data source.
     * @return the data source used inside the whole transaction.
     */
    @NotNull
    protected final DataSource immutableGetDataSource()
    {
        return m__DataSource;
    }

    /**
     * Retrieves the data source.
     * @return the data source used inside the whole transaction.
     */
    @NotNull
    public DataSource getDataSource()
    {
        return immutableGetDataSource();
    }

    /**
     * Checks if this object is logically equal to given one.
     * @param object the object to compare to.
     * @return <code>true</code> if both objects are equal logically.
     *
    public boolean equals(final Object object)
    {
        return equals(object, getTransactionStatus(), getDataSource());
    }

    /**
     * Checks if this object is logically equal to given one.
     * @param object the object to compare to.
     * @param transactionStatus the transaction status.
     * @param dataSource the wrapped data source.
     * @return <code>true</code> if both objects are equal logically.
     *
    protected boolean equals(
        final Object object,
        final TransactionStatus transactionStatus,
        final DataSource dataSource)
    {
        boolean result =
            (   (transactionStatus != null) 
             && (transactionStatus.equals(object)));

        if  (!result)
        {
            result =
                (   (dataSource != null) 
                 && (dataSource.equals(object)));
        }

        if  (!result) 
        {
            result = super.equals(object);
        }

        return result;
    }

    /**
     * Retrieves the hash code.
     * @return such information.
     *
    public int hashCode()
    {
        return hashCode(getDataSource());
    }

    /**
     * Retrieves the hash code.
     * @param dataSource the data source.
     * @return such information.
     *
    protected int hashCode(final DataSource dataSource)
    {
        return dataSource.hashCode();
    }
    */

    /**
     * Private <code>DataSource</code> wrapper.
     * @author <a href="mailto:chous@acm-sl.org"
     *         >Jose San Leandro Armendariz</a>
     */
    private static class _DataSourceWrapper
        implements  DataSource
    {
        /**
        * Concrete wrapped data source.
        */
        private DataSource m__DataSource;

        /**
        * The connection used in the transaction.
        */
        private Connection m__Connection;

        /**
        * Creates a _DataSourceWrapper using given data source.
        * @param dataSource the data source to wrap.
        */
        public _DataSourceWrapper(@NotNull final DataSource dataSource)
        {
            immutableSetDataSource(dataSource);
        }

        /**
         * Specifies the data source.
         * @param dataSource the data source.
         */
        protected final void immutableSetDataSource(@NotNull final DataSource dataSource)
        {
            m__DataSource = dataSource;
        }

        /**
         * Specifies the data source.
         * @param dataSource the data source.
         */
        protected void setDataSource(@NotNull final DataSource dataSource)
        {
            immutableSetDataSource(dataSource);
        }

        /**
         * Retrieves the data source.
         * @return the data source.
         */
        @NotNull
        public DataSource getDataSource()
        {
            return m__DataSource;
        }

        /**
         * Specifies the JDBC connection.
         * @param connection the connection.
         */
        protected final void immutableSetConnection(@NotNull final Connection connection)
        {
            m__Connection = connection;
        }

        /**
         * Specifies the JDBC connection.
         * @param connection the connection.
         */
        protected void setConnection(@NotNull final Connection connection)
        {
            immutableSetConnection(connection);
        }

        /**
         * Retrieves the JDBC connection.
         * @return such connection.
         */
        @Nullable
        protected Connection getInternalConnection()
        {
            return m__Connection;
        }

        /**
         * Attempts to establish a database connection.
         * @return the connection.
         */
        @Override
        @NotNull
        public Connection getConnection()
            throws  SQLException
        {
            return
                getConnection(
                    getInternalConnection(),
                    getDataSource());
        }

        /**
         * Attempts to establish a database connection.
         * @param internalConnection the internal connection.
         * @param dataSource the data source.
         * @return the connection.
         */
        @NotNull
        protected Connection getConnection(
            @Nullable final Connection internalConnection,
            @NotNull final DataSource dataSource)
          throws  SQLException
        {
            Connection result = internalConnection;

            if  (result == null)
            {
                result = dataSource.getConnection();
                setConnection(result);
                result.setAutoCommit(false);
            }

            return m__Connection;
        }

        /**
         * Attempts to establish a database connection.
         * @param userName the user name connection settings.
         * @param password the user password connection settings.
         * @return the connection.
         */
        @Override
        @NotNull
        public Connection getConnection(
            @NotNull final String userName, @NotNull final String password)
            throws  SQLException
        {
            return
                getConnection(
                    userName, password, getConnection(), getDataSource());
        }

        /**
         * Attempts to establish a database connection.
         * @param userName the user name connection settings.
         * @param password the user password connection settings.
         * @param connection the connection.
         * @param dataSource the data source.
         * @return the connection.
         */
        @NotNull
        public Connection getConnection(
            @NotNull final String userName,
            @NotNull final String password,
            @Nullable final Connection connection,
            @NotNull final DataSource dataSource)
          throws  SQLException
        {
            Connection result = connection;

            if  (result == null)
            {
                result = dataSource.getConnection(userName, password);
                setConnection(result);
                result.setAutoCommit(false);
            }

            return result;
        }

        /**
         * Retrieves the maximum time in seconds that this data source can
         * wait while attempting to connect to a database.
         * @return such timeout.
         */
        @Override
        public int getLoginTimeout()
            throws  SQLException
        {
            return getLoginTimeout(getDataSource());
        }

        /**
         * Retrieves the maximum time in seconds that this data source can
         * wait while attempting to connect to a database.
         * @param dataSource the data source.
         * @return such timeout.
         */
        protected int getLoginTimeout(@Nullable final DataSource dataSource)
            throws  SQLException
        {
            int result = 0;

            if  (dataSource != null) 
            {
                result = dataSource.getLoginTimeout();
            }
            
            return result;
        }

        /**
         * Retrieves the log writer for this data source.
         * @return such log writer.
         */
        @Override
        @Nullable
        public PrintWriter getLogWriter()
            throws  SQLException
        {
            return getLogWriter(getDataSource());
        }

        /**
         * Retrieves the log writer for this data source.
         * @return such log writer.
         */
        @Nullable
        protected PrintWriter getLogWriter(@Nullable final DataSource dataSource)
            throws  SQLException
        {
            @Nullable PrintWriter result = null;

            if  (dataSource != null) 
            {
                result = dataSource.getLogWriter();
            }

            return result;
        }

        /**
         * Specifies the maximum time in seconds that this data source will
         * wait while attempting to connect to a database.
         * @param seconds the timeout.
         */
        @Override
        public void setLoginTimeout(final int seconds)
            throws  SQLException
        {
            setLoginTimeout(seconds, getDataSource());
        }

        /**
         * Specifies the maximum time in seconds that this data source will
         * wait while attempting to connect to a database.
         * @param seconds the timeout.
         * @param dataSource the data source.
         */
        protected void setLoginTimeout(
            final int seconds, @Nullable final DataSource dataSource)
          throws  SQLException
        {
            if  (dataSource != null) 
            {
                dataSource.setLoginTimeout(seconds);
            }
        }

        /**
         * Specifies the log writer for this data source.
         * @param out the log writer.
         */
        @Override
        public void setLogWriter(@NotNull final PrintWriter out)
            throws  SQLException
        {
            setLogWriter(out, getDataSource());
        }

        /**
         * Specifies the log writer for this data source.
         * @param out the log writer.
         * @param dataSource the data source.
         */
        protected void setLogWriter(
            @NotNull final PrintWriter out, @NotNull final DataSource dataSource)
          throws  SQLException
        {
            dataSource.setLogWriter(out);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isWrapperFor(@NotNull final Class<?> wrapperClass)
        {
            return isWrapperFor(wrapperClass, getDataSource());
        }

        /**
         * Checks whether the wrapped data source is compatible with given class.
         * @param wrapperClass the wrapper class.
         * @param wrappedDataSource the wrapped data source.
         * @return <code>true</code> if the wrapped data source is compatible with given class.
         */
        protected boolean isWrapperFor(final Class<?> wrapperClass, @Nullable final Object wrappedDataSource)
        {
            return
                (   (wrappedDataSource != null)
                 && (wrappedDataSource.getClass().isAssignableFrom(wrapperClass)));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @Nullable
        public <T> T unwrap(final Class<T> wrapperClass)
        {
            return unwrap(wrapperClass, getDataSource());
        }

        /**
         * Unwraps the wrapped data source if it's compatible with given class.
         * @param wrapperClass the wrapper class.
         * @param wrappedDataSource the wrapped data source.
         * @return the wrapped data source if it's compatible.
         */
        @SuppressWarnings("unchecked")
        @Nullable
        protected <T> T unwrap(final Class<T> wrapperClass, final Object wrappedDataSource)
        {
            @Nullable final T result;

            if  (isWrapperFor(wrapperClass, wrappedDataSource))
            {
                result = (T) wrappedDataSource;
            }
            else
            {
                result = null;
            }

            return result;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Logger getParentLogger()
	        throws SQLFeatureNotSupportedException
        {
            return getDataSource().getParentLogger();
        }

        /**
         * Checks if the data source is equal logically to given object.
         * @param object the object to compare to.
         * @return true if both data sources represents the same entity.
         */
        @Override
        public boolean equals(@Nullable final Object object)
        {
            return equals(object, getDataSource());
        }

        /**
         * Checks if the data source is equal logically to given object.
         * @param object the object to compare to.
         * @param dataSource the data source.
         * @return true if both data sources represents the same entity.
         */
        protected boolean equals(
            @Nullable final Object object, @NotNull final DataSource dataSource)
        {
            boolean result = false;

            if  (object instanceof DataSource)
            {
                @NotNull final DataSource t_GivenDataSource = (DataSource) object;

                result =
                    (   (t_GivenDataSource == dataSource)
                     || (t_GivenDataSource.equals(dataSource)));
            }

            return result;
        }

        /**
         * Retrieves the hash code.
         * @return such value.
         */
        @Override
        public int hashCode()
        {
            return hashCode(getDataSource());
        }

        /**
         * Retrieves the hash code.
         * @param dataSource the data source.
         * @return such value.
         */
        protected int hashCode(@Nullable final DataSource dataSource)
        {
            final int result;

            if  (dataSource != null)
            {
                result = dataSource.hashCode();
            }
            else
            {
                result = super.hashCode();
            }

            return result;
        }

        /**
         * Retrieves the textual version of this instance.
         * @return such information.
         */
        @Override
        @NotNull
        public String toString()
        {
            return toString(getDataSource());
        }

        /**
         * Retrieves the textual version of this instance.
         * @param dataSource the wrapped data source.
         * @return such information.
         */
        @NotNull
        protected String toString(@Nullable final DataSource dataSource)
        {
            @Nullable final String result;

            if  (dataSource == null)
            {
                result = super.toString();
            }
            else
            {
                result = dataSource.toString();
            }

            return result;
        }
    }
}
