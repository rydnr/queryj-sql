/*
                        QueryJ

    Copyright (C) 2002-2005  Jose San Leandro Armendariz
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
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Indicates JUnit how to test Condition classes.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.queryj.sql.Condition;
import org.acmsl.queryj.sql.Field;
import org.acmsl.queryj.sql.IntField;
import org.acmsl.queryj.sql.Table;
import org.acmsl.queryj.sql.TableAlias;

/*
 * Importing JUnit classes.
 */
import junit.framework.TestCase;
import org.jetbrains.annotations.NotNull;

/**
 * Indicates JUnit how to test Condition classes.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro</a>
 */
public class ConditionTest
    extends  TestCase
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
     * Constructs a test case with the given name.
     * @param name the test case name.
     */
    public ConditionTest(String name)
    {
        super(name);
    }

    /**
     * Tests the condition class.
     * @see org.acmsl.queryj.sql.Condition
     */
    public void test1Condition()
    {
        @NotNull Condition t_FirstCondition = USERS.USERID.greaterThan(10);

        assertTrue(t_FirstCondition != null);

        assertEquals("USERS.USERID > 10", t_FirstCondition.toString());
    }

    /**
     * Tests the condition class.
     * @see org.acmsl.queryj.sql.Condition
     */
    public void test2Condition()
    {
        @NotNull Condition t_SecondCondition = USERS.NAME.equals();

        assertEquals("USERS.NAME = ?", t_SecondCondition.toString());
    }

    /**
     * Tests the condition class.
     * @see org.acmsl.queryj.sql.Condition
     */
    public void test3Condition()
    {
        @NotNull Condition t_SecondCondition = USERS.NAME.equals();

        t_SecondCondition = USERS.NAME.equals();

        t_SecondCondition = t_SecondCondition.or(USERS.NAME.isNull());

        assertEquals(
            "(USERS.NAME = ?) OR (USERS.NAME is null)",
            t_SecondCondition.toString());
    }

    /**
     * Tests the condition class.
     * @see org.acmsl.queryj.sql.Condition
     */
    public void test4Condition()
    {
        @NotNull Condition t_FirstCondition = USERS.USERID.greaterThan(10);

        @NotNull Condition t_SecondCondition = USERS.NAME.equals();

        t_SecondCondition = t_SecondCondition.or(USERS.NAME.isNull());

        t_FirstCondition = t_FirstCondition.and(t_SecondCondition);

        assertEquals(
            "(USERS.USERID > 10) AND ((USERS.NAME = ?) OR (USERS.NAME is null))",
            t_FirstCondition.toString());
    }

    /**
     * Tests the condition class.
     * @see org.acmsl.queryj.sql.Condition
     */
    public void test5Condition()
    {
        @NotNull Condition t_FirstCondition = USERS.USERID.greaterThan(10);

        @NotNull Condition t_SecondCondition = USERS.NAME.equals();

        t_SecondCondition = t_SecondCondition.or(USERS.NAME.isNull());

        @NotNull Condition t_ThirdCondition = USERS.NAME.equals();

        t_FirstCondition = t_FirstCondition.and(t_SecondCondition);

        t_FirstCondition = t_FirstCondition.or(t_ThirdCondition);

        assertEquals(
                  "((USERS.USERID > 10)"
            +   " AND "
            +     "((USERS.NAME = ?)"
            +      " OR "
            +     "(USERS.NAME is null)))"
            + " OR "
            +  "(USERS.NAME = ?)",
            t_FirstCondition.toString());
    }

    /**
     * Executes the tests from command line.
     * @param args the command-line arguments. Not needed so far.
     */
    public static void main(String args[])
    {
        junit.textui.TestRunner.run(ConditionTest.class);
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
        public Field[] getAll()
        {
            return new Field[] {USERID, NAME};
        }
    }
}

