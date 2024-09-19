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
 * Filename: Condition.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents conditions.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing some ACM-SL classes.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Importing some JDK classes.
 */
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents conditions.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public abstract class Condition
{
    /**
     * The inner condition.
     */
    private Condition m__InnerCondition;

    /**
     * The possible list of variable conditions.
     */
    private Collection<VariableCondition> m__cVariableConditions;

    /**
     * Creates a condition.
     */
    public Condition() {}

    /**
     * Specifies a new inner condition.
     * @param condition the new condition.
     */
   protected void setInnerCondition(@NotNull final Condition condition)
    {
        m__InnerCondition = condition;
    }

    /**
     * Retrieves the inner condition.
     * @return such instance.
     */
    @Nullable
    protected Condition getInnerCondition()
    {
        return m__InnerCondition;
    }

    /**
     * Specifies the variable conditions.
     * @param collection the variable conditions.
     */
    protected void setVariableConditionCollection(final Collection<VariableCondition> collection)
    {
        m__cVariableConditions = collection;
    }

    /**
     * Retrieves the variable conditions.
     * @return such collection.
     */
    @Nullable
    protected final Collection<VariableCondition> immutableGetVariableConditions()
    {
        return m__cVariableConditions;
    }

    /**
     * Retrieves the variable condition collection.
     * @return such collection.
     */
    @Nullable
    protected Collection<VariableCondition> getVariableConditionCollection()
    {
        return immutableGetVariableConditions();
    }

    /**
     * Adds new variable conditions.
     * @param conditions the new conditions.
     */
    protected void addVariableConditions(
        @Nullable final Collection<VariableCondition> conditions)
    {
        if  (   (conditions != null)
             && (conditions.size() > 0))
        {
            Collection<VariableCondition> t_cVariableConditions =
                getVariableConditionCollection();

            if  (t_cVariableConditions == null)
            {
                t_cVariableConditions = new ArrayList<VariableCondition>();
                setVariableConditionCollection(t_cVariableConditions);
            }

            t_cVariableConditions.addAll(conditions);
        }
    }

    /**
     * Retrieves the variable conditions.
     * @return such collection.
     */
    @NotNull
    public Collection<VariableCondition> getVariableConditions()
    {
        return getVariableConditions(immutableGetVariableConditions());
    }

    /**
     * Retrieves the variable conditions.
     * @param explicitConditions the explicit/non-wrapped conditions.
     * @return such collection.
     */
    @NotNull
    protected Collection<VariableCondition> getVariableConditions(
        @Nullable final Collection<VariableCondition> explicitConditions)
    {
        @NotNull final Collection<VariableCondition> result = new ArrayList<VariableCondition>();

        final Condition t_InnerCondition = getInnerCondition();

        if  (t_InnerCondition != null)
        {
            @NotNull final Collection<VariableCondition> t_cInnerVariableConditions =
                t_InnerCondition.getVariableConditions();

            if  (t_cInnerVariableConditions.size() > 0)
            {
                result.addAll(t_cInnerVariableConditions);
            }
        }

        if  (explicitConditions != null)
        {
            result.addAll(explicitConditions);
        }

        return result;
    }

    /**
     * Requests an operation with given condition.
     * @param condition the condition to evaluate.
     * @param operator the operator.
     * @return the resulting condition.
     */
    @NotNull
    public Condition operate(
        @NotNull final Condition condition, @NotNull final String operator)
    {
        return
            operate(
                condition,
                operator,
                getInnerCondition(),
                ConditionFactory.getInstance());
    }

    /**
     * Requests an operation with given condition.
     * @param condition the condition to evaluate.
     * @param operator the operator.
     * @param innerCondition the inner condition.
     * @param conditionFactory the {@link ConditionFactory} instance.
     * @return the resulting condition.
     */
    @NotNull
    protected Condition operate(
        @NotNull final Condition condition,
        @NotNull final String operator,
        @Nullable final Condition innerCondition,
        @NotNull final ConditionFactory conditionFactory)
    {
        @Nullable Condition t_InnerCondition = innerCondition;

        if  (t_InnerCondition != null)
        {
            addVariableConditions(innerCondition.getVariableConditions());
        }
        else 
        {
            t_InnerCondition = this; // Subclasses override toString()
        }                            //            |
                                     //            v
        @NotNull final String t_strPrefix =
            "(" + t_InnerCondition.toString() + ") " + operator + " (";
        @NotNull final String t_strSuffix = ")";

        setInnerCondition(
            conditionFactory.wrap(condition, t_strPrefix, t_strSuffix));

        return this;
    }

    /**
     * Requests AND evaluation with given condition.
     * @param condition the condition to evaluate.
     * @return the resulting condition.
     */
    @NotNull
    public Condition and(@NotNull final Condition condition)
    {
        return operate(condition, "AND");
    }

    /**
     * Requests OR evaluation with given condition.
     * @param condition the condition to evaluate.
     * @return the resulting condition.
     */
    @NotNull
    public Condition or(@NotNull final Condition condition)
    {
        return operate(condition, "OR");
    }

    /**
     * Retrieves the complete or simplified text associated with
     * given field.
     * @param field the field to serialize.
     * @param simplify if fields should appear
     * without explicit table information.
     * @return such text.
     */
    @NotNull
    protected String toString(@Nullable final Field field, final boolean simplify)
    {
        String result = "null";

        if  (field != null)
        {
            if  (simplify)
            {
                result = field.toSimplifiedString();
            }
            else 
            {
                result = field.toString();
            }
        }
        
        return result;
    }

    /**
     * Outputs a brief text version of the condition.
     * @return the condition.
     */
    @NotNull
    public String toSimplifiedString()
    {
        return toSimplifiedString(getInnerCondition());
    }

    /**
     * Outputs a brief text version of the condition.
     * @param innerCondition the inner condition.
     * @return the condition.
     */
    @NotNull
    public String toSimplifiedString(@Nullable final Condition innerCondition)
    {
        String result = "";

        if  (innerCondition != null)
        {
            result = innerCondition.toSimplifiedString();
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public String toString()
    {
        return toString(getInnerCondition());
    }

    /**
     * Outputs a text version of the condition.
     * @param innerCondition the inner condition.
     * @return the condition.
     */
    @NotNull
    protected String toString(@Nullable final Condition innerCondition)
    {
        return innerCondition != null ? innerCondition.toString() : "";
    }
}
