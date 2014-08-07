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
 * Filename: TransactionTokenFactory.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Has the responsibility of creating transaction tokens.
 *              Right now, it follows the FactoryMethod pattern, but could be
 *              refactored as an abstract factory as new technologies are
 *              supported.
 *
 */
package org.acmsl.queryj.sql.dao;

/*
 * Importing Spring classes.
 */
import org.springframework.transaction.support.DefaultTransactionStatus;

/*
 * Importing some ACM-SL Commons classes.
 */
import org.acmsl.commons.patterns.Factory;
import org.acmsl.commons.patterns.Singleton;

/*
 * Importing some extension classes.
 */
import javax.sql.DataSource;

/*
 * Importing NotNull annotations.
 */
import org.jetbrains.annotations.NotNull;

/**
 * Has the responsibility of creating transaction tokens. Right now, it
 * follows the FactoryMethod pattern, but could be refactored as an abstract
 * factory as new technologies are supported.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class TransactionTokenFactory
    implements  Factory,
                Singleton
{
    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class TransactionTokenFactorySingletonContainer
    {
        /**
         * The actual singleton.
         */
        public static final TransactionTokenFactory SINGLETON =
            new TransactionTokenFactory();
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected TransactionTokenFactory() {};

    /**
     * Retrieves a <code>TransactionTokenFactory</code> instance.
     * @return such instance.
     */
    @NotNull
    public static TransactionTokenFactory getInstance()
    {
        return TransactionTokenFactorySingletonContainer.SINGLETON;
    }

    /**
     * Creates a DataSourceTransactionToken using given connection.
     * @param transactionStatus the transaction status.
     * @param dataSource the data source to use inside the same transaction.
     * @return the transaction token, or null if the connection is invalid.
     */
    @NotNull
    public DataSourceTransactionToken createTransactionToken(
        @NotNull final DefaultTransactionStatus transactionStatus,
        @NotNull final DataSource dataSource)
    {
        return new DataSourceTransactionToken(transactionStatus, dataSource);
    }
}
