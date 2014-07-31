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
 * Filename: Table.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents relational tables.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing ACM-SL classes.
 */
import org.acmsl.queryj.sql.Field;
import org.acmsl.queryj.sql.TableAlias;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Importing some JDK classes.
 */
import java.lang.ref.WeakReference;

/**
 * Represents relational tables.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public abstract class Table
{
    /**
     * The table name.
     */
    private String m__strName;

    /**
     * The alias name.
     */
    private String m__strTableAliasName;

    /**
     * The table alias reference.
     */
    private WeakReference m__TableAliasReference;

    /**
     * Builds a table using given information.
     * @param name the name.
     * @param alias the alias.
     * @precondition name != null
     */
    protected Table(final String name, final String alias)
    {
        immutableSetName(name);
        immutableSetTableAliasName(alias);
    }

    /**
     * Builds a table using given information.
     * @param name the name.
     * @precondition name != null
     */
    protected Table(final String name)
    {
        this(name, null);
    }

    /**
     * Retrieves <code>all</code> fields. It's equivalent to a star in
     * a query.
     * @return such fields.
     */
    @NotNull
    public abstract Field[] getAll();

    /**
     * Specifies the table name.
     * @param name the name.
     */
    private void immutableSetName(final String name)
    {
        m__strName = name;
    }

    /**
     * Specifies the table name.
     * @param name the name.
     */
    protected void setName(final String name)
    {
        immutableSetName(name);
    }

    /**
     * Retrieves the table name.
     * @return such reference.
     */
    public String getName()
    {
        return m__strName;
    }

    /**
     * Specifies the table alias.
     * @param alias the alias.
     */
    private void immutableSetTableAliasName(final String alias)
    {
        m__strTableAliasName = alias;
    }

    /**
     * Specifies the table alias name.
     * @param alias the alias.
     */
    protected void setTableAliasName(final String alias)
    {
        immutableSetTableAliasName(alias);
    }

    /**
     * Retrieves the table alias name.
     * @return such reference.
     */
    protected String getTableAliasName()
    {
        return m__strTableAliasName;
    }

    /**
     * Specifies the table alias reference.
     * @param reference the reference.
     */
    protected void setTableAliasReference(final WeakReference reference)
    {
        m__TableAliasReference = reference;
    }

    /**
     * Retrieves the table alias reference.
     * @return such reference.
     */
    protected WeakReference getTableAliasReference()
    {
        return m__TableAliasReference;
    }

    /**
     * Retrieves the table alias.
     * @return such instance.
     */
    @Nullable
    public TableAlias getTableAlias()
    {
        @Nullable TableAlias result = null;

        WeakReference t_TableAliasReference =
            getTableAliasReference();

        String t_strTableAliasName = getTableAliasName();

        if  (t_TableAliasReference != null)
        {
            result = (TableAlias) t_TableAliasReference.get();
        }

        if  (result == null)
        {
            if  (t_strTableAliasName != null)
            {
                result = new TableAlias(t_strTableAliasName, this);
                setTableAliasReference(new WeakReference(result));
            }
        }

        return result;
    }

    /**
     * Outputs a text version of the table.
     * @return such text.
     */
    public String toString()
    {
        @NotNull StringBuffer result = new StringBuffer();

        @Nullable TableAlias t_TableAlias = getTableAlias();

        if  (t_TableAlias != null) 
        {
            result.append(t_TableAlias);
        }
        else 
        {
            result.append(getName());
        }

        return result.toString();
    }
}
