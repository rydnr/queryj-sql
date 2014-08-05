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
 * Filename: ConditionOperator.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents conditions in SQL statements.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing some JetBrains annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents operators used inside conditions.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class ConditionOperator
{
    /**
     * The symbol.
     */
    private String m__strSymbol;

    /**
     * Creates a operator using given information.
     * @param symbol the operator symbol.
     */
    public ConditionOperator(@NotNull final String symbol)
    {
        immutableSetSymbol(symbol);
    }

    /**
     * Specifies the operator symbol.
     * @param symbol the symbol.
     */
    protected final void immutableSetSymbol(@NotNull final String symbol)
    {
        m__strSymbol = symbol;
    }

    /**
     * Specifies the operator symbol.
     * @param symbol the symbol.
     */
    @SuppressWarnings("unused")
    protected void setSymbol(@NotNull final String symbol)
    {
        immutableSetSymbol(symbol);
    }

    /**
     * Retrieves the operator symbol.
     * @return such symbol.
     */
    @NotNull
    public String getSymbol()
    {
        return m__strSymbol;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public String toString()
    {
        return getSymbol();
    }

    /**
     * Checks if given object is logically equal to this one.
     * @param candidate the object to check.
     * @return <code>true</code> if both objects are logically equal.
     */
    @Override
    public boolean equals(@Nullable final Object candidate)
    {
        boolean result = false;

        if (candidate instanceof ConditionOperator)
        {
            result = equals(candidate, getSymbol());
        }

        return result;
    }

    /**
     * Checks if given object is logically equal to this one.
     * @param candidate the object to check.
     * @param symbol the symbol.
     * @return <code>true</code> if both objects are logically equal.
     */
    protected boolean equals(@Nullable final Object candidate, @Nullable final String symbol)
    {
        boolean result = (candidate == null);

        if  (!result) 
        {
            result = !(candidate instanceof ConditionOperator);
        }

        if  (!result)
        {
            @NotNull final ConditionOperator t_Candidate = (ConditionOperator) candidate;

            if  (t_Candidate.getSymbol() != null)
            {
                result = (t_Candidate.getSymbol().equals(symbol));
            }
            else
            {
                result = (symbol == null);
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
        return hashCode(getSymbol());
    }

    /**
     * Retrieves the hash code.
     * @param symbol the symbol.
     * @return such information.
     */
    protected int hashCode(@NotNull final String symbol)
    {
        return (ConditionOperator.class + symbol).hashCode();
    }
}

