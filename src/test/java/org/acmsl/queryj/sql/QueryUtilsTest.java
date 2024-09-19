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
 * Filename: QueryUtilsTest.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Tests for QueryUtils.
 *
 */
package org.acmsl.queryj.sql;

/*
 * Importing NotNull annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Importing JUnit classes.
 */
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

/**
 * Represents standard SQL insert queries.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
@RunWith(JUnit4.class)
public class QueryUtilsTest
{
    /**
     * Tests whether shouldBeEscaped deals with integers properly.
     */
    @Test
    public void shouldBeEscaped_works_for_integers()
    {
        @NotNull final QueryUtils instance = QueryUtils.getInstance();

        Assert.assertNotNull(instance);

        Assert.assertFalse(instance.shouldBeEscaped(30));
    }

    /**
     * Tests whether shouldBeEscaped deals with arrays properly.
     */
    @Test
    public void shouldBeEscaped_works_for_arrays()
    {
        @NotNull final QueryUtils instance = QueryUtils.getInstance();

        Assert.assertNotNull(instance);

        Assert.assertFalse(instance.shouldBeEscaped(new Integer[] { 30 }));
    }
}
