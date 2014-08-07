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
 * Filename: TableAlias.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents SQL fields.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing NotNull annotations.
 */
import org.jetbrains.annotations.NotNull;

/**
 * Represents SQL fields.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class TableAlias
{
    /**
     * The alias name.
     */
    private String m__strName;

    /**
     * The table this alias points to.
     */
    private Table m__Table;

    /**
     * Creates a table alias using given information.
     * @param name the field name.
     * @param table the table.
     */
    public TableAlias(@NotNull final String name, @NotNull final Table table)
    {
        immutableSetName(name);
        immutableSetTable(table);
    }

    /**
     * Specifies the alias name.
     * @param name the name.
     */
    protected final void immutableSetName(@NotNull final String name)
    {
        m__strName = name;
    }

    /**
     * Specifies the alias name.
     * @param name the name.
     */
    protected void setName(@NotNull final String name)
    {
        immutableSetName(name);
    }

    /**
     * Retrieves the alias name.
     * @return such reference.
     */
    @NotNull
    public String getName()
    {
        return m__strName;
    }

    /**
     * Specifies the table.
     * @param table the table.
     */
    protected final void immutableSetTable(@NotNull final Table table)
    {
        m__Table = table;
    }

    /**
     * Specifies the table.
     * @param table the table.
     */
    protected void setTable(@NotNull final Table table)
    {
        immutableSetTable(table);
    }

    /**
     * Retrieves the table.
     * @return such reference.
     */
    @NotNull
    public Table getTable()
    {
        return m__Table;
    }

    /**
     * Outputs a text version of the tabl ealias.
     * @return such text.
     */
    @Override
    @NotNull
    public String toString()
    {
        return getName();
    }
}
