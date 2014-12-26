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
 * Filename: UpdateQueryTest.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Indicates JUnit how to test UpdateQuery classes.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.queryj.sql.Field;
import org.acmsl.queryj.sql.IntField;
import org.acmsl.queryj.sql.QueryFactory;
import org.acmsl.queryj.sql.UpdateQuery;
import org.acmsl.queryj.sql.StringField;
import org.acmsl.queryj.sql.Table;
import org.acmsl.queryj.sql.TableAlias;

/*
 * Importing JUnit classes.
 */
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

/*
 * Importing Jetbrains annotations.
 */
import org.jetbrains.annotations.NotNull;

/**
 * Indicates JUnit how to test UpdateQuery classes.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro</a>
 */
//@RunWith(JUnit4.class)
public class UpdateQueryTest
{
    /**
     * The expected query #1.
     */
    protected static final String EXPECTED_QUERY_1 =
        "UPDATE USERS SET NAME = 'myself', AGE = 30 WHERE USERID = 1";

    /**
     * The expected query #2.
     */
    protected static final String EXPECTED_QUERY_2 =
        "UPDATE USERS SET NAME = ?, AGE = 30 WHERE USERID = 1";

    /**
     * The expected query #3.
     */
    protected static final String EXPECTED_QUERY_3 =
        "UPDATE USERS SET NAME = ?, AGE = ? WHERE USERID = 1";

    /**
     * The expected query #4.
     */
    protected static final String EXPECTED_QUERY_4 =
        "UPDATE USERS SET NAME = ?, AGE = ? WHERE USERID = ?";

    /**
     * The expected query #5.
     */
    protected static final String EXPECTED_QUERY_5 =
        "UPDATE USERS SET AGE = ? WHERE (USERS.NAME = ?) AND (USERS.AGE > 20)";

    /**
     * The USERS table.
     */
    protected static final UsersTable USERS = new UsersTable() {};

    /**
     * Tests the toString() method
     * @see org.acmsl.queryj.sql.UpdateQuery#toString()
     */
    //@Test
    public void testToString1()
    {
        @NotNull final QueryFactory t_QueryFactory = QueryFactory.getInstance();

        Assert.assertNotNull(t_QueryFactory);

        @NotNull final UpdateQuery t_Query = t_QueryFactory.createUpdateQuery();

        t_Query.update(USERS);
        t_Query.set(USERS.NAME, "myself");
        t_Query.set(USERS.AGE, 30);

        t_Query.where(USERS.USERID.equals(1));

        Assert.assertNotNull(t_Query);

        @NotNull final String t_strQuery = t_Query.toString();

        Assert.assertNotNull(t_strQuery);

        Assert.assertEquals(EXPECTED_QUERY_1, t_strQuery);
    }

    /**
     * Tests the toString() method
     * @see org.acmsl.queryj.sql.UpdateQuery#toString()
     */
    public void testToString2()
    {
        @NotNull final QueryFactory t_QueryFactory = QueryFactory.getInstance();

        Assert.assertNotNull(t_QueryFactory);

        @NotNull final UpdateQuery t_Query = t_QueryFactory.createUpdateQuery();

        t_Query.update(USERS);
        t_Query.set(USERS.NAME);
        t_Query.set(USERS.AGE, 30);

        t_Query.where(USERS.USERID.equals(1));

        Assert.assertNotNull(t_Query);

        @NotNull final String t_strQuery = t_Query.toString();

        Assert.assertNotNull(t_strQuery);

        Assert.assertEquals(EXPECTED_QUERY_2, t_strQuery);
    }

    /**
     * Tests the toString() method
     * @see org.acmsl.queryj.sql.UpdateQuery#toString()
     */
    public void testToString3()
    {
        @NotNull final QueryFactory t_QueryFactory = QueryFactory.getInstance();

        Assert.assertNotNull(t_QueryFactory);

        @NotNull final UpdateQuery t_Query = t_QueryFactory.createUpdateQuery();

        t_Query.update(USERS);
        t_Query.set(USERS.NAME);
        t_Query.set(USERS.AGE);

        t_Query.where(USERS.USERID.equals(1));

        Assert.assertNotNull(t_Query);

        @NotNull final String t_strQuery = t_Query.toString();

        Assert.assertNotNull(t_strQuery);

        Assert.assertEquals(EXPECTED_QUERY_3, t_strQuery);
    }

    /**
     * Tests the toString() method
     * @see org.acmsl.queryj.sql.UpdateQuery#toString()
     */
    public void testToString4()
    {
        @NotNull final QueryFactory t_QueryFactory = QueryFactory.getInstance();

        Assert.assertNotNull(t_QueryFactory);

        @NotNull UpdateQuery t_Query = t_QueryFactory.createUpdateQuery();

        t_Query.update(USERS);
        t_Query.set(USERS.NAME);
        t_Query.set(USERS.AGE);

        t_Query.where(USERS.USERID.equals());

        Assert.assertNotNull(t_Query);

        @NotNull final String t_strQuery = t_Query.toString();

        Assert.assertNotNull(t_strQuery);

        Assert.assertEquals(EXPECTED_QUERY_4, t_strQuery);
    }

    /**
     * Tests the toString() method
     * @see org.acmsl.queryj.sql.UpdateQuery#toString()
     */
    public void testToString5()
    {
        @NotNull final QueryFactory t_QueryFactory = QueryFactory.getInstance();

        Assert.assertNotNull(t_QueryFactory);

        @NotNull final UpdateQuery t_Query = t_QueryFactory.createUpdateQuery();

        t_Query.update(USERS);
        t_Query.set(USERS.AGE);

        t_Query.where(USERS.NAME.equals().and(USERS.AGE.greaterThan(20)));

        Assert.assertNotNull(t_Query);

        @NotNull final String t_strQuery = t_Query.toString();

        Assert.assertNotNull(t_strQuery);

        Assert.assertEquals(EXPECTED_QUERY_5, t_strQuery);
    }

    /**
     * Test-only table.
     * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro</a>
     * @version $Revision$
     */
    public static class UsersTable
        extends  Table
    {
        /**
         * The USERS table USERID field.
         */
        @NotNull
        public IntField USERID =
            new IntField("USERID", this) {};

        /**
         * The USERS table NAME field.
         */
        @NotNull
        public StringField NAME =
            new StringField("NAME", this) {};

        /**
         * The USERS table AGE field.
         */
        @NotNull
        public IntField AGE =
            new IntField("AGE", this) {};

        /**
         * All fields.
         */
        @NotNull
        public Field[] ALL =
            new Field[] {USERID, NAME, AGE};

        /**
         * The table name.
         */
        public static final String TABLE_NAME = "USERS";

        /**
         * The table alias.
         */
        @NotNull
        public TableAlias ALIAS =
            new TableAlias("usrs", this) {};

        /**
         * Creates a USERS table.
         */
        protected UsersTable()
        {
            super(TABLE_NAME);
        }

        /**
         * Retrieves <code>all</code> fields. It's equivalent to a
         * star in a query.
         * @return such fields.
         */
        @NotNull
        public Field[] getAll()
        {
            return ALL;
        }
    }
}

