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
 * Filename: NestedConditionOperator.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents condition operators involving nested queries
 *              SQL statements.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing Jetbrains annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents condition operators involving nested queries SQL statements.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class NestedConditionOperator
    extends  ConditionOperator
{
    /**
     * The nested query.
     */
    private SelectQuery m__Query;

    /**
     * Creates a nested operator using given information.
     * @param symbol the symbol.
     * @param query the operator query.
     */
    public NestedConditionOperator(@NotNull final String symbol, @NotNull final SelectQuery query)
    {
        super(symbol);
        immutableSetQuery(query);
    }

    /**
     * Specifies the operator query.
     * @param query the query.
     */
    protected final void immutableSetQuery(@NotNull final SelectQuery query)
    {
        m__Query = query;
    }

    /**
     * Specifies the operator query.
     * @param query the query.
     */
    protected void setQuery(@NotNull final SelectQuery query)
    {
        immutableSetQuery(query);
    }

    /**
     * Retrieves the operator query.
     * @return such query.
     */
    @NotNull
    public SelectQuery getQuery()
    {
        return m__Query;
    }

    /**
     * Checks if given object is logically equal to this one.
     * @param candidate the object to check.
     * @return <code>true</code> if both objects are logically equal.
     */
    @Override
    public boolean equals(@Nullable final Object candidate)
    {
        return equals(candidate, getQuery());
    }

    /**
     * Checks if given object is logically equal to this one.
     * @param candidate the object to check.
     * @param query the query.
     * @return <code>true</code> if both objects are logically equal.
     */
    public boolean equals(@Nullable final Object candidate, @NotNull final SelectQuery query)
    {
        boolean result = super.equals(candidate);

        if  (!result) 
        {
            result = !(candidate instanceof NestedConditionOperator);
        }

        if  (!result)
        {
            @NotNull final NestedConditionOperator t_Candidate =
                (NestedConditionOperator) candidate;

            result = (t_Candidate.getQuery() == query);

            if  (!result)
            {
                result = (t_Candidate.getQuery() != null);

                if  (result) 
                {
                    result = (t_Candidate.getQuery().equals(query));
                }
                else
                {
                    result = (query == null);
                }
            }
        }

        return result;
    }

    /**
     * Retrieves the hash code.
     * @return such information.
     */
    @Override
    public int hashCode()
    {
        return hashCode(getQuery());
    }

    /**
     * Retrieves the hash code.
     * @param query the select query.
     * @return such information.
     */
    protected int hashCode(@NotNull final SelectQuery query)
    {
        return (NestedConditionOperator.class + query.toString()).hashCode();
    }
}

