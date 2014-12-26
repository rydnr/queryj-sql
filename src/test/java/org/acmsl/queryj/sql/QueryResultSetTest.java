/*
                        QueryJ SQL

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
    Contact info: chous@acm-sl.org
    Postal Address: c/Playa de Lagoa, 1
                    Urb. Valdecabanas
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: QueryResultSetTest.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Indicates JUnit how to test QueryResultSet classes.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing JetBrains annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* 
 * Importing JDK classes.
 */
import java.sql.ResultSet;

/*
 * Importing JUnit classes.
 */
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

/**
 * Tests QueryResultSetTest class.
 * @see org.acmsl.queryj.sql.QueryResultSet
 */
//@RunWith(JUnit4.class)
public class QueryResultSetTest
{
    /**
     * Tests QueryResultSetTest accessor methods.
     * @throws Exception if an unexpected situation occurs.
     */
    public void testSetGetFetchDirection()
        throws Exception
    {
        @NotNull final int[] t_aTests = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};
    
        @Nullable final Query query = null;
        @Nullable final ResultSet resultSet = null;

        @NotNull final QueryResultSet instance = new QueryResultSet(query, resultSet);

        for (int t_iTestIndex = 0; t_iTestIndex < t_aTests.length; t_iTestIndex++)
        {
            instance.setFetchDirection(t_aTests[t_iTestIndex]);
            Assert.assertEquals(
                t_aTests[t_iTestIndex],
                instance.getFetchDirection());
        }
    }
  
    /**
     * Tests QueryResultSetTest accessor methods.
     * @throws Exception if an unexpected situation occurs.
     */
    public void testSetGetFetchSize()
        throws Exception
    {
        @NotNull int[] t_aTests = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};
    
        @Nullable final Query query = null;
        @Nullable final ResultSet resultSet = null;

        @NotNull final QueryResultSet instance = new QueryResultSet(query, resultSet);

        for (int t_iTestIndex = 0; t_iTestIndex < t_aTests.length; t_iTestIndex++)
        {
            instance.setFetchSize(t_aTests[t_iTestIndex]);
            Assert.assertEquals(
                t_aTests[t_iTestIndex],
                instance.getFetchSize());
        }
    } 
}
