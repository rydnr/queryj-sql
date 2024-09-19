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
 * Importing NotNull classes.
 */
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
    private WeakReference<TableAlias> m__TableAliasReference;

    /**
     * Builds a table using given information.
     * @param name the name.
     * @param alias the alias.
     */
    protected Table(@NotNull final String name, @Nullable final String alias)
    {
        immutableSetName(name);
        if (alias != null)
        {
            immutableSetTableAliasName(alias);
        }
    }

    /**
     * Builds a table using given information.
     * @param name the name.
     */
    protected Table(@NotNull final String name)
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
    private void immutableSetName(@NotNull final String name)
    {
        m__strName = name;
    }

    /**
     * Specifies the table name.
     * @param name the name.
     */
    protected void setName(@NotNull final String name)
    {
        immutableSetName(name);
    }

    /**
     * Retrieves the table name.
     * @return such reference.
     */
    @NotNull
    public String getName()
    {
        return m__strName;
    }

    /**
     * Specifies the table alias.
     * @param alias the alias.
     */
    private void immutableSetTableAliasName(@NotNull final String alias)
    {
        m__strTableAliasName = alias;
    }

    /**
     * Specifies the table alias name.
     * @param alias the alias.
     */
    @SuppressWarnings("unused")
    protected void setTableAliasName(@NotNull final String alias)
    {
        immutableSetTableAliasName(alias);
    }

    /**
     * Retrieves the table alias name.
     * @return such reference.
     */
    @Nullable
    protected String getTableAliasName()
    {
        return m__strTableAliasName;
    }

    /**
     * Specifies the table alias reference.
     * @param reference the reference.
     */
    protected void setTableAliasReference(@NotNull final WeakReference<TableAlias> reference)
    {
        m__TableAliasReference = reference;
    }

    /**
     * Retrieves the table alias reference.
     * @return such reference.
     */
    @Nullable
    protected WeakReference<TableAlias> getTableAliasReference()
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

        @Nullable final WeakReference<TableAlias> t_TableAliasReference =
            getTableAliasReference();

        @Nullable final String t_strTableAliasName = getTableAliasName();

        if  (t_TableAliasReference != null)
        {
            result = t_TableAliasReference.get();
        }

        if  (result == null)
        {
            if  (t_strTableAliasName != null)
            {
                result = new TableAlias(t_strTableAliasName, this);
                setTableAliasReference(new WeakReference<TableAlias>(result));
            }
        }

        return result;
    }

    /**
     * Outputs a text version of the table.
     * @return such text.
     */
    @Override
    @NotNull
    public String toString()
    {
        @NotNull final StringBuilder result = new StringBuilder();

        @Nullable final TableAlias t_TableAlias = getTableAlias();

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
