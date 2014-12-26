/*
                        QueryJ-SQL

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
 * Filename: VariableConditionTest.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Indicates JUnit how to test Condition classes.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing JDK classes.
 */
import java.util.Arrays;
import java.util.Collection;

/*
 * Importing JUnit classes.
 */
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

/*
 * Importing JetBrains annotations.
 */
import org.jetbrains.annotations.NotNull;

/**
 * Indicates JUnit how to test VariableCondition classes.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro</a>
 */
@RunWith(JUnit4.class)
public class VariableConditionTest
{
    /**
     * The expected condition.
     */
    protected static final String EXPECTED_CONDITION =
        "(USERS.USERID > 10) AND ((USERS.NAME = ?) OR (USERS.NAME is null)) OR (USERS.NAME = ?)";

    /**
     * The USERS table.
     */
    protected static final UsersTable USERS = new UsersTable() {};

    /**
     * Tests the condition class.
     * @see org.acmsl.queryj.sql.Condition
     */
    @Test
    @SuppressWarnings("unused")
    public void test1Condition()
    {
        @NotNull final Condition t_Condition = USERS.USERID.greaterThan();

        final Collection<VariableCondition> t_cVariableConditions = t_Condition.getVariableConditions();

        Assert.assertTrue(true);

        Assert.assertTrue(t_cVariableConditions.size() == 1);
    }

    /**
     * Tests the condition class.
     * @see org.acmsl.queryj.sql.Condition
     */
    //@Test
    @SuppressWarnings("unused")
    public void test2Condition()
    {
        @NotNull final Condition t_Condition =
            USERS.USERID.greaterThan().and(USERS.USERID.equals());

        @NotNull final Collection<VariableCondition> t_cVariableConditions = t_Condition.getVariableConditions();

        Assert.assertTrue(t_cVariableConditions.size() == 2);
    }

    /**
     * Tests the condition class.
     * @see org.acmsl.queryj.sql.Condition
     */
    //@Test
    @SuppressWarnings("unused")
    public void test3Condition()
    {
        @NotNull final Condition t_Condition =
            USERS.USERID.greaterThan(1).and(USERS.USERID.equals()).and(USERS.USERID.equals(1));

        @NotNull final Collection<VariableCondition> t_cVariableConditions = t_Condition.getVariableConditions();

        Assert.assertTrue(t_cVariableConditions.size() == 1);
    }

    /**
     * Tests the condition class.
     * @see org.acmsl.queryj.sql.Condition
     */
    //@Test
    @SuppressWarnings("unused")
    public void test3Condition2()
    {
        @NotNull final Condition t_Condition =
            USERS.USERID.greaterThan(1).and(USERS.USERID.equals()).and(USERS.USERID.equals());

        @NotNull final Collection<VariableCondition> t_cVariableConditions = t_Condition.getVariableConditions();

        Assert.assertTrue(t_cVariableConditions.size() == 2);
    }

    /**
     * Tests the condition class.
     * @see org.acmsl.queryj.sql.Condition
     */
    //@Test
    @SuppressWarnings("unused")
    public void test3Condition3()
    {
        @NotNull final Condition t_Condition =
            USERS.USERID.greaterThan().and(USERS.USERID.equals()).and(USERS.USERID.equals());

        @NotNull final Collection<VariableCondition> t_cVariableConditions = t_Condition.getVariableConditions();

        Assert.assertTrue(t_cVariableConditions.size() == 3);
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
        public Field NAME =
            new Field("NAME", this) {};

        /**
         * All fields.
         */
        @NotNull
        public Field[] ALL =
            new Field[] {USERID, NAME};

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
        @Override
        public Field[] getAll()
        {
            return ALL;
        }

        @Override
        public String toString()
        {
            return "UsersTable{" +
                   "ALIAS=" + ALIAS +
                   ", USERID=" + USERID +
                   ", NAME=" + NAME +
                   ", ALL=" + Arrays.toString(ALL) +
                   '}';
        }
    }
}

