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
 * Filename: TransactionManager.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents a transaction runtime environment. The one that
 *              created it manages the transaction.
 */
package org.acmsl.queryj.sql.dao;

/*
 * Importing Spring classes.
 */
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/*
 * Importing some ACM-SL Commons classes.
 */
import org.acmsl.commons.patterns.Manager;
import org.acmsl.commons.patterns.Singleton;

/*
 * Importing some JDK classes.
 */
import java.sql.Connection;

/*
 * Importing Java extension classes.
 */
import javax.sql.DataSource;

/*
 * Importing NotNull annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Simplifies transaction management.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class TransactionManager
    implements Manager,
               Singleton
{
    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class TransactionManagerSingletonContainer
    {
        /**
         * The actual singleton.
         */
        public static final TransactionManager SINGLETON =
            new TransactionManager();
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected TransactionManager() {};

    /**
     * Retrieves a <code>TransactionManager</code> instance.
     * @return such instance.
     */
    @NotNull
    public static TransactionManager getInstance()
    {
        return TransactionManagerSingletonContainer.SINGLETON;
    }

    /**
     * Starts a transaction for given connection.
     * @param connection the connection.
     * @return the transaction token.
     * @throws TransactionException if the transaction cannot be started for
     * some reason.
     */
    @NotNull
    public TransactionToken begin(@NotNull final Connection connection)
        throws  TransactionException
    {
        return
            begin(
                new SingleConnectionDataSource(connection, true));
    }

    /**
     * Starts a transaction for given data source.
     * @param connection the connection.
     * @param transactionDefinition the transaction definition.
     * @return the transaction token.
     * @throws TransactionException if the transaction cannot be started for
     * some reason.
     */
    @NotNull
    public TransactionToken begin(
        @NotNull final Connection connection,
        @NotNull final TransactionDefinition transactionDefinition)
      throws  TransactionException
    {
        return
            begin(
                new SingleConnectionDataSource(connection, true),
                transactionDefinition);
    }

    /**
     * Starts a transaction for given connection.
     * @param dataSource the data source.
     * @return the transaction token.
     * @throws TransactionException if the transaction cannot be started for
     * some reason.
     */
    @NotNull
    public TransactionToken begin(@NotNull final DataSource dataSource)
        throws  TransactionException
    {
        return begin(dataSource, new DefaultTransactionDefinition());
    }

    /**
     * Starts a transaction for given data source.
     * @param dataSource the data source.
     * @param transactionDefinition the transaction definition.
     * @return the transaction token.
     * @throws TransactionException if the transaction cannot be started for
     * some reason.
     */
    @NotNull
    public TransactionToken begin(
        @NotNull final DataSource dataSource,
        @NotNull final TransactionDefinition transactionDefinition)
      throws  TransactionException
    {
        return
            begin(
                dataSource,
                transactionDefinition,
                TransactionTokenFactory.getInstance());
    }

    /**
     * Starts a transaction for given data source.
     * @param dataSource the data source.
     * @param transactionDefinition the transaction definition.
     * @param transactionTokenFactory the <code>TransactionTokenFactory</code>
     * instance.
     * @return the transaction token.
     * @throws TransactionException if the transaction cannot be started for
     * some reason.
     */
    @NotNull
    protected TransactionToken begin(
        @NotNull final DataSource dataSource,
        @NotNull final TransactionDefinition transactionDefinition,
        @NotNull final TransactionTokenFactory transactionTokenFactory)
      throws  TransactionException
    {
        @NotNull final TransactionToken result;

        DataSource t_DataSource = dataSource;

        if  (!(t_DataSource instanceof ThreadAwareDataSourceWrapper))
        {
            t_DataSource =
                new ThreadAwareDataSourceWrapper(t_DataSource);
        }

        @Nullable final PlatformTransactionManager t_TransactionManager =
            createTransactionManager(t_DataSource, true);

        // Performs an implicit transaction start.
        @Nullable final TransactionStatus t_TransactionStatus =
            t_TransactionManager.getTransaction(transactionDefinition);

        if  (   (t_TransactionStatus != null)
             && (t_TransactionStatus instanceof DefaultTransactionStatus))
        {
            result =
                transactionTokenFactory.createTransactionToken(
                    (DefaultTransactionStatus) t_TransactionStatus,
                    t_DataSource);
        }
        else
        {
            throw
                new TransactionManagerException(
                    "Cannot begin transaction. Unknown transaction status: " + t_TransactionStatus);
        }

        return result;
    }

    /**
     * Creates a transaction manager correctly initialized.
     * @param connection the connection.
     * @return a <code>PlatformTransactionManager</code> instance.
     */
    @SuppressWarnings("unused")
    @Nullable
    protected PlatformTransactionManager createTransactionManager(
        final Connection connection)
    {
        return
            createTransactionManager(
                new SingleConnectionDataSource(connection, true),
                true);
    }

    /**
     * Creates a transaction manager correctly initialized.
     * @param dataSource the data source.
     * @return a <code>PlatformTransactionManager</code> instance.
     */
    @SuppressWarnings("unused")
    @Nullable
    protected PlatformTransactionManager createTransactionManager(
        final DataSource dataSource)
    {
        return createTransactionManager(dataSource, false);
    }

    /**
     * Creates a transaction manager correctly initialized.
     * @param dataSource the data source.
     * @param initialize whether to initialize the transaction manager or not.
     * @return a <code>PlatformTransactionManager</code> instance.
     */
    @NotNull
    protected PlatformTransactionManager createTransactionManager(
        @NotNull final DataSource dataSource, final boolean initialize)
    {
        @NotNull final PlatformTransactionManager result;

        if  (initialize)
        {
            // Enforcing connection is registered.
            DataSourceUtils.getConnection(dataSource);
        }

        result = new DataSourceTransactionManager(dataSource);

        return result;
    }

    /**
     * Commits a transaction identified by given transaction token.
     * @param transactionToken the transaction token.
     * @throws TransactionException if the transaction could not be committed
     * anyway.
     */
    public void commit(@NotNull final TransactionStatus transactionToken)
      throws  TransactionException
    {
        if   (transactionToken instanceof DataSourceTransactionToken)
        {
            commitTransaction((DataSourceTransactionToken) transactionToken);
        }
        else
        {
            throw
                new TransactionManagerException(
                    "Invalid transaction token: " + transactionToken);
        }
    }

    /**
     * Commits a transaction identified by given transaction token.
     * @param transactionToken the transaction token.
     * @throws TransactionException if the transaction could not be committed
     * anyway.
     */
    protected void commitTransaction(
        @NotNull final DataSourceTransactionToken transactionToken)
      throws  TransactionException
    {
        commitTransaction(
            transactionToken,
            createTransactionManager(
                transactionToken.getDataSource(), false));
    }

    /**
     * Commits a transaction identified by given transaction token.
     * @param transactionToken the transaction token.
     * @param transactionManager the <code>PlatformTransactionManager</code>
     * instance.
     * @throws TransactionException if the transaction could not be committed
     * anyway.
     */
    protected void commitTransaction(
        @NotNull final TransactionToken transactionToken,
        @NotNull final PlatformTransactionManager transactionManager)
      throws  TransactionException
    {
        transactionManager.commit(transactionToken);
    }

    /**
     * Rolls back a transaction identified by given transaction token.
     * @param transactionToken the transaction token.
     * @throws TransactionException if the transaction could not be rolled back
     * anyway.
     */
    @SuppressWarnings("unused")
    public void rollback(@NotNull final TransactionStatus transactionToken)
      throws  TransactionException
    {
        if   (transactionToken instanceof DataSourceTransactionToken)
        {
            rollbackTransaction((DataSourceTransactionToken) transactionToken);
        }
        else
        {
            throw
                new TransactionManagerException(
                    "Invalid transaction token: " + transactionToken);
        }
    }

    /**
     * Rolls back a transaction identified by given transaction token.
     * @param transactionToken the transaction token.
     * @throws TransactionException if the transaction could not be rolled back
     * anyway.
     */
    protected void rollbackTransaction(
        @NotNull final DataSourceTransactionToken transactionToken)
      throws  TransactionException
    {
        rollbackTransaction(
            transactionToken,
            createTransactionManager(transactionToken.getDataSource(), false));
    }

    /**
     * Rolls back a transaction identified by given transaction token.
     * @param transactionToken the transaction token.
     * @param transactionManager the <code>PlatformTransactionManager</code>
     * instance.
     * @throws TransactionException if the transaction could not be rolled back
     * anyway.
     */
    protected void rollbackTransaction(
        @NotNull final TransactionToken transactionToken,
        @NotNull final PlatformTransactionManager transactionManager)
      throws  TransactionException
    {
        transactionManager.rollback(transactionToken);
    }

    /**
     * Inner transaction exception shortcut.
     * @author <a href="mailto:chous@acm-sl.org"
     *         >Jose San Leandro Armendariz</a>
     */
    public static class TransactionManagerException
        extends  TransactionException
    {
        /**
         * Creates a <code>TransactionManagerException</code>
         * with given message.
         * @param message the message.
         */
        public TransactionManagerException(@NotNull final String message)
        {
            super(message);
        }
    }
}
