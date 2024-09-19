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
 * Filename: VariableCondition.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents conditions in SQL statements.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing NotNull annotations.
 */
import org.jetbrains.annotations.NotNull;

/*
 * Importing JDK classes.
 */
import java.util.Collection;

/**
 * Represents conditions in SQL statements.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class VariableCondition
    extends  AtomicCondition
{
    /**
     * Creates a variable condition using given information.
     * @param field the left-side field.
     * @param operator the operator.
     */
    public VariableCondition(
        @NotNull final Field field, @NotNull final ConditionOperator operator)
    {
        super(field, operator, "?");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public Collection<VariableCondition> getVariableConditions()
    {
        @NotNull final Collection<VariableCondition> result = super.getVariableConditions();

        result.add(this);

        return result;
    }
}
