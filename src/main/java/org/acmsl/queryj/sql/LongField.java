//;-*- mode: java -*-
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
    Contact info: jose.sanleandro@acm-sl.com

 ******************************************************************************
 *
 * Filename: LongField.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents long fields.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing Jetbrains annotations
 */
import org.jetbrains.annotations.NotNull;

/**
 * Represents long fields.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class LongField
    extends  Field
{
    /**
     * Creates a long field using given information.
     * @param name the field name.
     * @param table the table.
     */
    public LongField(@NotNull final String name, @NotNull final Table table)
    {
        super(name, table);
    }

    /**
     * Retrieves the condition to be able to filter for equality.
     * @param value the value.
     * @return such kind of condition.
     */
    @NotNull
    public Condition equals(final long value)
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
        final long value,
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
    public Condition notEquals(final long value)
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
        final long value,
        @NotNull final ConditionFactory conditionFactory,
        @NotNull final ConditionOperatorRepository conditionOperatorRepository)
    {
        return
            conditionFactory.createCondition(
                this,
                conditionOperatorRepository.getNotEquals(),
                value);
    }

    /**
     * Retrieves the condition to be able to filter for lower values.
     * @param value the value.
     * @return such kind of condition.
     */
    @SuppressWarnings("unused")
    @NotNull
    public Condition greaterThan(final long value)
    {
        return
            greaterThan(
                value,
                ConditionFactory.getInstance(),
                ConditionOperatorRepository.getInstance());
    }

    /**
     * Retrieves the condition to be able to filter for lower values.
     * @param value the value.
     * @param conditionFactory the <code>ConditionFactory</code> instance.
     * @param conditionOperatorRepository the
     * <code>ConditionOperatorRepository</code> instance.
     * @return such kind of condition.
     */
    @NotNull
    protected Condition greaterThan(
        final long value,
        @NotNull final ConditionFactory conditionFactory,
        @NotNull final ConditionOperatorRepository conditionOperatorRepository)
    {
        return
            conditionFactory.createCondition(
                this,
                conditionOperatorRepository.getGreaterThan(),
                value);
    }

    /**
     * Retrieves the condition to be able to filter for higher values.
     * @param value the value.
     * @return such kind of condition.
     */
    @SuppressWarnings("unused")
    @NotNull
    public Condition lessThan(final long value)
    {
        return
            lessThan(
                value,
                ConditionFactory.getInstance(),
                ConditionOperatorRepository.getInstance());
    }

    /**
     * Retrieves the condition to be able to filter for higher values.
     * @param value the value.
     * @param conditionFactory the <code>ConditionFactory</code> instance.
     * @param conditionOperatorRepository the
     * <code>ConditionOperatorRepository</code> instance.
     * @return such kind of condition.
     */
    @NotNull
    protected Condition lessThan(
        final long value,
        @NotNull final ConditionFactory conditionFactory,
        @NotNull final ConditionOperatorRepository conditionOperatorRepository)
    {
        return
            conditionFactory.createCondition(
                this,
                conditionOperatorRepository.getLessThan(),
                value);
    }
}
