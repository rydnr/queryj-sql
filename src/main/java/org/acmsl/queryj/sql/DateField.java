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
 * Filename: DateField.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents date fields.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing Jetbrains annotations.
 */
import org.jetbrains.annotations.NotNull;

/*
 * Importing some JDK classes.
 */
import java.util.Date;

/**
 * Represents date fields.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class DateField
    extends  Field
{
    /**
     * Creates a Date field using given information.
     * @param name the field name.
     * @param table the table.
     */
    public DateField(@NotNull final String name, @NotNull final Table table)
    {
        super(name, table);
    }

    /**
     * Retrieves the condition to be able to filter for equality.
     * @param value the value.
     * @return such kind of condition.
     */
    @NotNull
    public Condition equals(@NotNull final Date value)
    {
        return
            equals(
                value,
                ConditionFactory.getInstance(),
                ConditionOperatorRepository.getInstance());
    }

    /**
     * Retrieves the condition to be able to filter for equality.
     * @param value the value.
     * @param conditionFactory the <code>ConditionFactory</code> instance.
     * @param conditionOperatorRepository the
     * <code>ConditionOperatorRepository</code> instance.
     * @return such kind of condition.
     */
    @NotNull
    protected Condition equals(
        @NotNull final Date value,
        @NotNull final ConditionFactory conditionFactory,
        @NotNull final ConditionOperatorRepository conditionOperatorRepository)
    {
        return
            conditionFactory.createCondition(
                this,
                conditionOperatorRepository.getEquals(),
                value);
    }

    /**
     * Retrieves the condition to be able to filter for non-equality.
     * @param value the value.
     * @return such kind of condition.
     */
    @SuppressWarnings("unused")
    @NotNull
    public Condition notEquals(@NotNull final Date value)
    {
        return
            notEquals(
                value,
                ConditionFactory.getInstance(),
                ConditionOperatorRepository.getInstance());
    }

    /**
     * Retrieves the condition to be able to filter for non-equality.
     * @param value the value.
     * @param conditionFactory the <code>ConditionFactory</code> instance.
     * @param conditionOperatorRepository the
     * <code>ConditionOperatorRepository</code> instance.
     * @return such kind of condition.
     */
    @NotNull
    protected Condition notEquals(
        @NotNull final Date value,
        @NotNull final ConditionFactory conditionFactory,
        @NotNull final ConditionOperatorRepository conditionOperatorRepository)
    {
        return
            conditionFactory.createCondition(
                this,
                conditionOperatorRepository.getNotEquals(),
                value);
    }
}
