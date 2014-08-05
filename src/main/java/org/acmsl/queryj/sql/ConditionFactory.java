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
 * Filename: ConditionFactory.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Has the responsibility of knowing how to create conditions.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing some ACM-SL Commons classes.
 */
import org.acmsl.commons.patterns.Factory;
import org.acmsl.commons.patterns.Singleton;

/*
 * Importing Jetbrains annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Importing some JDK classes.
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Has the responsibility of knowing how to create conditions.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class ConditionFactory
    implements  Factory,
                Singleton
{
    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class ConditionFactorySingletonContainer
    {
        /**
         * The actual singleton.
         */
        public static final ConditionFactory SINGLETON =
            new ConditionFactory();
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected ConditionFactory() {};

    /**
     * Retrieves a <code>ConditionFactory</code> instance.
     * @return such instance.
     */
    @NotNull
    public static ConditionFactory getInstance()
    {
        return ConditionFactorySingletonContainer.SINGLETON;
    }

    /**
     * Creates a condition.
     * Note: Removed precondition rightSideField != null due
     * to <code>isNull</code>.
     * @param leftSideField the left-side field.
     * @param operator the operator.
     * @param rightSideField the right-side field.
     * @return such type of instance.
     */
    @NotNull
    public Condition createCondition(
        @NotNull final Field leftSideField,
        @NotNull final ConditionOperator operator,
        @NotNull final Field rightSideField)
    {
        return
            new AtomicCondition(
                leftSideField, operator, rightSideField);
    }

    /**
     * Creates a condition
     * @param leftSideField the left-side field.
     * @param operator the operator.
     * @param value the fixed value.
     * @return such type of instance.
     */
    @NotNull
    public Condition createCondition(
        @NotNull final Field leftSideField,
        @NotNull final ConditionOperator operator,
        final int value)
    {
        return new AtomicCondition(leftSideField, operator, value);
    }

    /**
     * Creates a condition
     * @param leftSideField the left-side field.
     * @param operator the operator.
     * @param value the fixed value.
     * @return such type of instance.
     */
    @NotNull
    public Condition createCondition(
        @NotNull final Field leftSideField,
        @NotNull final ConditionOperator operator,
        final long value)
    {
        return new AtomicCondition(leftSideField, operator, value);
    }

    /**
     * Creates a condition
     * @param leftSideField the left-side field.
     * @param operator the operator.
     * @param value the fixed value.
     * @return such type of instance.
     */
    @NotNull
    public Condition createCondition(
        @NotNull final Field leftSideField,
        @NotNull final ConditionOperator operator,
        final double value)
    {
        return new AtomicCondition(leftSideField, operator, value);
    }

    /**
     * Creates a condition
     * @param leftSideField the left-side field.
     * @param operator the operator.
     * @param value the fixed value.
     * @return such type of instance.
     */
    @NotNull
    public Condition createCondition(
        @NotNull final Field leftSideField,
        @NotNull final ConditionOperator operator,
        @NotNull final BigDecimal value)
    {
        return new AtomicCondition(leftSideField, operator, value);
    }

    /**
     * Creates a condition
     * @param leftSideField the left-side field.
     * @param operator the operator.
     * @param value the fixed value.
     * @return such type of instance.
     */
    @NotNull
    public Condition createCondition(
        @NotNull final Field leftSideField,
        @NotNull final ConditionOperator operator,
        @NotNull final String value)
    {
        return new AtomicCondition(leftSideField, operator, value);
    }

    /**
     * Creates a condition
     * @param leftSideField the left-side field.
     * @param operator the operator.
     * @param value the fixed value.
     * @return such type of instance.
     */
    @NotNull
    public Condition createCondition(
        @NotNull final Field leftSideField,
        @NotNull final ConditionOperator operator,
        @NotNull final Calendar value)
    {
        return new AtomicCondition(leftSideField, operator, value);
    }

    /**
     * Creates a condition
     * @param leftSideField the left-side field.
     * @param operator the operator.
     * @param value the fixed value.
     * @return such type of instance.
     */
    @NotNull
    public Condition createCondition(
        @NotNull final Field leftSideField,
        @NotNull final ConditionOperator operator,
        @NotNull final Date value)
    {
        return new AtomicCondition(leftSideField, operator, value);
    }

    /**
     * Creates a variable condition.
     * @param field the field.
     * @param operator the operator.
     * @return such type of instance.
     */
    @NotNull
    public VariableCondition createVariableCondition(
        @NotNull final Field field,
        @NotNull final ConditionOperator operator)
    {
        return new VariableCondition(field, operator);
    }

    /**
     * Creates a wrapper condition.
     * @param condition the condition to wrap.
     * @param prefix the prefix.
     * @param suffix the suffix.
     * @return the wrapped condition.
     */
    @NotNull
    public Condition wrap(
        @NotNull final Condition condition, @NotNull final String prefix, @NotNull final String suffix)
    {
        return new _ConditionWrapper(condition, prefix, suffix);
    }

    /**
     * Creates a wrapper condition.
     * @param condition the condition to wrap.
     * @param prefix the prefix.
     * @param suffix the suffix.
     * @return the wrapped condition.
     */
    @NotNull
    public Condition wrap(
        @NotNull final AtomicCondition condition,
        @NotNull final String prefix,
        @NotNull final String suffix)
    {
        return new _AtomicConditionWrapper(condition, prefix, suffix);
    }

    /**
     * Creates a wrapper condition.
     * @param condition the condition to wrap.
     * @param prefix the prefix.
     * @param suffix the suffix.
     * @return the wrapped condition.
     */
    @NotNull
    public VariableCondition wrap(
        @NotNull final VariableCondition condition,
        @NotNull final String prefix,
        @NotNull final String suffix)
    {
        return new _VariableConditionWrapper(condition, prefix, suffix);
    }

    /**
     * Envelopes a condition surrounding it with a prefix and suffix.
     * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro</a>
     */
    protected static class _ConditionWrapper
        extends  Condition
    {
        /**
         * The condition to wrap.
         */
        private Condition m__Condition;

        /**
         * The prefix.
         */
        private String m__strPrefix;

        /**
         * The suffix.
         */
        private String m__strSuffix;

        /**
         * Creates a condition wrapper with given information.
         * @param condition the condition to wrap.
         * @param prefix the prefix.
         * @param suffix the suffix.
         */
        public _ConditionWrapper(
            @NotNull final Condition condition,
            @NotNull final String prefix,
            @NotNull final String suffix)
        {
            super();

            immutableSetCondition(condition);
            immutableSetPrefix(prefix);
            immutableSetSuffix(suffix);
        }
        /**
         * Specifies the condition to wrap.
         * @param condition such condition.
         */
        private void immutableSetCondition(@NotNull final Condition condition)
        {
            m__Condition = condition;
        }

        /**
         * Specifies the condition to wrap.
         * @param condition such condition.
         */
        protected void setCondition(@NotNull final Condition condition)
        {
            immutableSetCondition(condition);
        }

        /**
         * Retrieves the wrapped condition.
         * @return such condition.
         */
        @NotNull
        public Condition getCondition()
        {
            return m__Condition;
        }

        /**
         * Specifies the prefix.
         * @param prefix such prefix.
         */
        private void immutableSetPrefix(@NotNull final String prefix)
        {
            m__strPrefix = prefix;
        }

        /**
         * Specifies the prefix.
         * @param prefix such prefix.
         */
        protected void setPrefix(@NotNull final String prefix)
        {
            immutableSetPrefix(prefix);
        }

        /**
         * Retrieves the prefix.
         * @return such prefix.
         */
        @NotNull
        public String getPrefix()
        {
            return m__strPrefix;
        }

        /**
         * Specifies the suffix.
         * @param suffix such suffix.
         */
        private void immutableSetSuffix(@NotNull final String suffix)
        {
            m__strSuffix = suffix;
        }

        /**
         * Specifies the suffix.
         * @param suffix such suffix.
         */
        @SuppressWarnings("unused")
        protected void setSuffix(@NotNull final String suffix)
        {
            immutableSetSuffix(suffix);
        }

        /**
         * Retrieves the suffix.
         * @return such suffix.
         */
        public String getSuffix()
        {
            return m__strSuffix;
        }

        // Delegate methods //


        /**
         * Retrieves the variable conditions.
         * @return such collection.
         */
        @Override
        @NotNull
        public Collection<VariableCondition> getVariableConditions()
        {
            return
                getVariableConditions(
                    super.getVariableConditions(),
                    getCondition());
        }

        /**
         * Retrieves the variable conditions.
         * @return such collection.
         */
        @NotNull
        protected Collection<VariableCondition> getVariableConditions(
            @Nullable final Collection<VariableCondition> parentVariableConditions,
            @Nullable final Condition condition)
        {
            @Nullable Collection<VariableCondition> result = parentVariableConditions;

            if  (condition != null) 
            {
                @NotNull final Collection<VariableCondition> t_cVariableConditions =
                    condition.getVariableConditions();

                if  (t_cVariableConditions.size() > 0)
                {
                    if  (result == null)
                    {
                        result = new ArrayList<VariableCondition>();
                    }

                    result.addAll(t_cVariableConditions);
                }
            }

            if  (result == null)
            {
                result = new ArrayList<VariableCondition>();
            }

            return result;
        }

        /**
         * Outputs a brief text version of the condition.
         * @return such text.
         */
        @NotNull
        public String toSimplifiedString()
        {
            return
                toSimplifiedString(
                    getPrefix(),
                    getCondition(),
                    getSuffix());
        }

        /**
         * Outputs a brief text version of the condition.
         * @param prefix the prefix.
         * @param condition the condition.
         * @param suffix the suffix.
         * @return such text.
         */
        @NotNull
        protected String toSimplifiedString(
            @NotNull final String prefix,
            @Nullable final Condition condition,
            @NotNull final String suffix)
        {
            @NotNull String result = "";

            if  (condition != null)
            {
                result =
                      prefix
                    + condition.toSimplifiedString()
                    + suffix;
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
            return toString(getPrefix(), getCondition(), getSuffix());
        }

        /**
         * Outputs a text version of the condition.
         * @param prefix the prefix.
         * @param condition the condition.
         * @param suffix the suffix.
         * @return such text.
         */
        @NotNull
        public String toString(
            @NotNull final String prefix,
            @NotNull final Condition condition,
            @NotNull final String suffix)
        {
            return prefix + condition + suffix;
        }
    }

    /**
     * Envelopes a condition surrounding it with appropiate prefix and suffix.
     * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro</a>
     * @version $Revision$
     */
    protected static class _AtomicConditionWrapper
        extends  AtomicCondition
    {
        /**
         * The condition to wrap.
         */
        private AtomicCondition m__Condition;

        /**
         * The prefix.
         */
        private String m__strPrefix;

        /**
         * The suffix.
         */
        private String m__strSuffix;

        /**
         * Creates a condition wrapper with given information.
         * @param condition the condition to wrap.
         * @param prefix the prefix.
         * @param suffix the suffix.
         */
        public _AtomicConditionWrapper(
            @NotNull final AtomicCondition condition,
            @NotNull final String prefix,
            @NotNull final String suffix)
        {
            super(
                condition.getLeftSideField(),
                condition.getOperator(),
                condition.getRightSideField());

            immutableSetCondition(condition);
            immutableSetPrefix(prefix);
            immutableSetSuffix(suffix);
        }

        /**
         * Specifies the condition to wrap.
         * @param condition such condition.
         */
        private void immutableSetCondition(@NotNull final AtomicCondition condition)
        {
            m__Condition = condition;
        }

        /**
         * Specifies the condition to wrap.
         * @param condition such condition.
         */
        protected void setCondition(@NotNull final AtomicCondition condition)
        {
            immutableSetCondition(condition);
        }

        /**
         * Retrieves the wrapped condition.
         * @return such condition.
         */
        @NotNull
        public AtomicCondition getCondition()
        {
            return m__Condition;
        }

        /**
         * Specifies the prefix.
         * @param prefix such prefix.
         */
        private void immutableSetPrefix(@NotNull final String prefix)
        {
            m__strPrefix = prefix;
        }

        /**
         * Specifies the prefix.
         * @param prefix such prefix.
         */
        protected void setPrefix(@NotNull final String prefix)
        {
            immutableSetPrefix(prefix);
        }

        /**
         * Retrieves the prefix.
         * @return such prefix.
         */
        @NotNull
        public String getPrefix()
        {
            return m__strPrefix;
        }

        /**
         * Specifies the suffix.
         * @param suffix such suffix.
         */
        private void immutableSetSuffix(@NotNull final String suffix)
        {
            m__strSuffix = suffix;
        }

        /**
         * Specifies the suffix.
         * @param suffix such suffix.
         */
        @SuppressWarnings("unused")
        protected void setSuffix(@NotNull final String suffix)
        {
            immutableSetSuffix(suffix);
        }

        /**
         * Retrieves the suffix.
         * @return such suffix.
         */
        @NotNull
        public String getSuffix()
        {
            return m__strSuffix;
        }

        // Delegated methods //

        /**
         * Retrieves the left-side field.
         * @return such reference.
         */
        @Nullable
        public Field getLeftSideField()
        {
            return getLeftSideField(getCondition());
        }

        /**
         * Retrieves the left-side field.
         * @param condition the condition.
         * @return such reference.
         */
        @Nullable
        protected Field getLeftSideField(@Nullable final AtomicCondition condition)
        {
            @Nullable Field result = null;

            if  (condition != null) 
            {
                result = condition.getLeftSideField();
            }
            
            return result;
        }

        /**
         * Retrieves the condition operator.
         * @return such reference.
         */
        @Override
        @Nullable
        public ConditionOperator getOperator()
        {
            return getOperator(getCondition());
        }

        /**
         * Retrieves the condition operator.
         * @param condition the condition.
         * @return such reference.
         */
        @Nullable
        protected ConditionOperator getOperator(@Nullable final AtomicCondition condition)
        {
            @Nullable ConditionOperator result = null;

            if  (condition != null) 
            {
                result = condition.getOperator();
            }
            
            return result;
        }

        /**
         * Retrieves the right-side field.
         * @return such reference.
         */
        @Override
        @Nullable
        public Field getRightSideField()
        {
            return getRightSideField(getCondition());
        }

        /**
         * Retrieves the right-side field.
         * @param condition the condition.
         * @return such reference.
         */
        @Nullable
        protected Field getRightSideField(@Nullable final AtomicCondition condition)
        {
            @Nullable final Field result;

            if  (condition == null)
            {
                result = null;
            }
            else
            {
                result = condition.getRightSideField();
            }
            
            return result;
        }

        /**
         * Retrieves the right-side value.
         * @return such reference.
         */
        @Override
        @Nullable
        public String getRightSideValue()
        {
            return getRightSideValue(getCondition());
        }

        /**
         * Retrieves the right-side value.
         * @param condition the condition.
         * @return such reference.
         */
        @Nullable
        protected String getRightSideValue(@Nullable final AtomicCondition condition)
        {
            @Nullable String result = null;

            if  (condition != null) 
            {
                result = condition.getRightSideValue();
            }
            
            return result;
        }

        /**
         * Retrieves the variable conditions.
         * @return such collection.
         */
        @Override
        @NotNull
        public Collection<VariableCondition> getVariableConditions()
        {
            return
                getVariableConditions(
                    super.getVariableConditions(),
                    getCondition());
        }

        /**
         * Retrieves the variable conditions.
         * @param parentVariableConditions the parent's variable conditions.
         * @param condition the condition.
         * @return such collection.
         */
        @NotNull
        public Collection<VariableCondition> getVariableConditions(
            @Nullable final Collection<VariableCondition> parentVariableConditions,
            @Nullable final Condition condition)
        {
            @Nullable Collection<VariableCondition> result = parentVariableConditions;

            if  (condition != null) 
            {
                final Collection<VariableCondition> t_cVariableConditions =
                    condition.getVariableConditions();

                if  (t_cVariableConditions.size() > 0)
                {
                    if  (result == null)
                    {
                        result = new ArrayList<VariableCondition>();
                    }

                    result.addAll(t_cVariableConditions);
                }
            }

            if  (result == null)
            {
                result = new ArrayList<VariableCondition>();
            }

            return result;
        }

        /**
         * Outputs a brief text version of the condition.
         * @return such text.
         */
        @Override
        @NotNull
        public String toSimplifiedString()
        {
            return
                toSimplifiedString(getPrefix(), getCondition(), getSuffix());
        }

        /**
         * Outputs a brief text version of the condition.
         * @param prefix the prefix.
         * @param condition the condition.
         * @param suffix the suffix.
         * @return such text.
         */
        @NotNull
        protected String toSimplifiedString(
            @NotNull final String prefix,
            @Nullable final Condition condition,
            @NotNull final String suffix)
        {
            @NotNull String result = "";

            if  (condition != null)
            {
                result =
                      prefix
                    + condition.toSimplifiedString()
                    + suffix;
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
            return toString(getPrefix(), getCondition(), getSuffix());
        }

        /**
         * Outputs a text version of the condition.
         * @param prefix the prefix.
         * @param condition the condition.
         * @param suffix the suffix.
         * @return such text.
         */
        @NotNull
        protected String toString(
            @NotNull final String prefix,
            @NotNull final Condition condition,
            @NotNull final String suffix)
        {
            return prefix + condition + suffix;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(@Nullable final Object object)
        {
            return super.equals(object);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode()
        {
            return super.hashCode();
        }
    }

    /**
     * Envelopes a variable condition surrounding it with appropiate
     * prefix and suffix.
     * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro</a>
     * @version $Revision$
     */
    protected static class _VariableConditionWrapper
        extends  VariableCondition
    {
        /**
         * The condition to wrap.
         */
        private VariableCondition m__Condition;

        /**
         * The prefix.
         */
        private String m__strPrefix;

        /**
         * The suffix.
         */
        private String m__strSuffix;

        /**
         * Creates a condition wrapper with given information.
         * @param condition the condition to wrap.
         * @param prefix the prefix.
         * @param suffix the suffix.
         */
        public _VariableConditionWrapper(
            @NotNull final VariableCondition condition,
            @NotNull final String prefix,
            @NotNull final String suffix)
        {
            super(
                condition.getLeftSideField(),
                condition.getOperator());

            immutableSetCondition(condition);
            immutableSetPrefix(prefix);
            immutableSetSuffix(suffix);
        }

        /**
         * Specifies the condition to wrap.
         * @param condition such condition.
         */
        private void immutableSetCondition(@NotNull final VariableCondition condition)
        {
            m__Condition = condition;
        }

        /**
         * Specifies the condition to wrap.
         * @param condition such condition.
         */
        protected void setCondition(@NotNull final VariableCondition condition)
        {
            immutableSetCondition(condition);
        }

        /**
         * Retrieves the wrapped condition.
         * @return such condition.
         */
        @NotNull
        public VariableCondition getCondition()
        {
            return m__Condition;
        }

        /**
         * Specifies the prefix.
         * @param prefix such prefix.
         */
        private void immutableSetPrefix(@NotNull final String prefix)
        {
            m__strPrefix = prefix;
        }

        /**
         * Specifies the prefix.
         * @param prefix such prefix.
         */
        protected void setPrefix(@NotNull final String prefix)
        {
            immutableSetPrefix(prefix);
        }

        /**
         * Retrieves the prefix.
         * @return such prefix.
         */
        @NotNull
        public String getPrefix()
        {
            return m__strPrefix;
        }

        /**
         * Specifies the suffix.
         * @param suffix such suffix.
         */
        private void immutableSetSuffix(@NotNull final String suffix)
        {
            m__strSuffix = suffix;
        }

        /**
         * Specifies the suffix.
         * @param suffix such suffix.
         */
        @SuppressWarnings("unused")
        protected void setSuffix(@NotNull final String suffix)
        {
            immutableSetSuffix(suffix);
        }

        /**
         * Retrieves the suffix.
         * @return such suffix.
         */
        @NotNull
        public String getSuffix()
        {
            return m__strSuffix;
        }


        // Delegated methods //

        /**
         * Retrieves the left-side field.
         * @return such reference.
         */
        @Override
        @Nullable
        public Field getLeftSideField()
        {
            return getLeftSideField(getCondition());
        }

        /**
         * Retrieves the left-side field.
         * @param condition the condition.
         * @return such reference.
         */
        @Nullable
        protected Field getLeftSideField(@Nullable final VariableCondition condition)
        {
            @Nullable final Field result;

            if  (condition == null)
            {
                result = null;
            }
            else
            {
                result = condition.getLeftSideField();
            }
            
            return result;
        }

        /**
         * Retrieves the condition operator.
         * @return such reference.
         */
        @Nullable
        public ConditionOperator getOperator()
        {
            return getOperator(getCondition());
        }

        /**
         * Retrieves the condition operator.
         * @param condition the condition.
         * @return such reference.
         */
        @Nullable
        protected ConditionOperator getOperator(
            @Nullable final VariableCondition condition)
        {
            @Nullable ConditionOperator result = null;

            if  (condition != null) 
            {
                result = condition.getOperator();
            }
            
            return result;
        }

        /**
         * Retrieves the right-side field.
         * @return such reference.
         */
        @Nullable
        public Field getRightSideField()
        {
            return getRightSideField(getCondition());
        }

        /**
         * Retrieves the right-side field.
         * @param condition the condition.
         * @return such reference.
         */
        @Nullable
        protected Field getRightSideField(@Nullable final VariableCondition condition)
        {
            @Nullable Field result = null;

            if  (condition != null) 
            {
                result = condition.getRightSideField();
            }
            
            return result;
        }

        /**
         * Retrieves the right-side value.
         * @return such reference.
         */
        @Nullable
        public String getRightSideValue()
        {
            return getRightSideValue(getCondition());
        }

        /**
         * Retrieves the right-side value.
         * @param condition the condition.
         * @return such reference.
         */
        @Nullable
        protected String getRightSideValue(@Nullable final VariableCondition condition)
        {
            @Nullable String result = null;

            if  (condition != null) 
            {
                result = condition.getRightSideValue();
            }
            
            return result;
        }

        /**
         * Outputs a brief text version of the condition.
         * @return such text.
         */
        @NotNull
        public String toSimplifiedString()
        {
            return
                toSimplifiedString(
                    getPrefix(),
                    getCondition(),
                    getSuffix());
        }

        /**
         * Outputs a brief text version of the condition.
         * @param prefix the prefix.
         * @param condition the condition.
         * @param suffix the suffix.
         * @return such text.
         */
        @NotNull
        protected String toSimplifiedString(
            @NotNull final String prefix,
            @Nullable final VariableCondition condition,
            @NotNull final String suffix)
        {
            @NotNull String result = "";

            if  (condition != null)
            {
                result =
                      prefix
                    + condition.toSimplifiedString()
                    + suffix;
            }

            return result;
        }

        /**
         * Outputs a text version of the condition.
         * @return such text.
         */
        @NotNull
        public String toString()
        {
            return toString(getPrefix(), getCondition(), getSuffix());
        }

        /**
         * Outputs a text version of the condition.
         * @param prefix the prefix.
         * @param condition the condition.
         * @param suffix the suffix.
         * @return such text.
         */
        @NotNull
        protected String toString(
            @NotNull final String prefix,
            @NotNull final Condition condition,
            @NotNull final String suffix)
        {
            return prefix + condition + suffix;
        }
    }
}
