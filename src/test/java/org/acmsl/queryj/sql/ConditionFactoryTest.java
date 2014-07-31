/*
                      Project tests

Copyright (C) 2003  Jose San Leandro Armend?riz
chous@acm-sl.org

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public
License as published by the Free Software Foundation; either
version 2 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

Thanks to ACM S.L. for distributing this library under the GPL license.
Contact info: pepe@acm-sl.com
Postal Address: c/Playa de Lagoa, 1
Urb. Valdecaba?as
Boadilla del monte
28660 Madrid
Spain

******************************************************************************
*
* Filename: $RCSfile$
*
* Author: Jose San Leandro Armend?riz
*
* Description: Executes all tests defined for package
*              org.acmsl.queryj.sql.
*
*/
package org.acmsl.queryj.sql;

/*
* Importing project classes.
*/
// JUnitDoclet begin import
import org.acmsl.queryj.sql.ConditionFactory;
// JUnitDoclet end import

/*
* Importing JUnit classes.
*/
import junit.framework.TestCase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
This file is part of  JUnitDoclet, a project to generate basic
test cases  from source code and  helping to keep them in sync
during refactoring.

Copyright (C) 2002-2005  ObjectFab GmbH  (http://www.objectfab.de/)

This library is  free software; you can redistribute it and/or
modify  it under the  terms of  the  GNU Lesser General Public
License as published  by the  Free Software Foundation; either
version 2.1  of the  License, or  (at your option)  any  later
version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or  FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You  should  have  received a  copy of the  GNU Lesser General
Public License along with this  library; if not, write  to the
Free  Software  Foundation, Inc.,  59 Temple Place,  Suite 330,
Boston, MA  02111-1307  USA
*/


/**
* Tests ConditionFactoryTest class.
* @see org.acmsl.queryj.ConditionFactory
*/
public class ConditionFactoryTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  @Nullable
  org.acmsl.queryj.sql.ConditionFactory conditionfactory = null;
  // JUnitDoclet end class
  
  /**
  * Creates a ConditionFactoryTest with given name.
  * @param name such name.
  */
  public ConditionFactoryTest(String name)
  {
    // JUnitDoclet begin method ConditionFactoryTest
    super(name);
    // JUnitDoclet end method ConditionFactoryTest
  }
  
  /**
  * Creates an instance of the tested class.
  * @return such instance.
  
  */
  @NotNull
  public org.acmsl.queryj.sql.ConditionFactory createInstance()
  throws Exception
  {
    // JUnitDoclet begin method testcase.createInstance
    return org.acmsl.queryj.sql.ConditionFactory.getInstance();
    // JUnitDoclet end method testcase.createInstance
  }
  
  /**
  * Performs any required steps before each test.
  * @throws Exception if an unexpected situation occurs.
  */
  protected void setUp()
  throws Exception
  {
    // JUnitDoclet begin method testcase.setUp
    super.setUp();
    conditionfactory = createInstance();
    // JUnitDoclet end method testcase.setUp
  }
  
  /**
  * Performs any required steps after each test.
  * @throws Exception if an unexpected situation occurs.
  */
  protected void tearDown()
  throws Exception
  {
    // JUnitDoclet begin method testcase.tearDown
    conditionfactory = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  /**
  * Tests ConditionFactoryTestgetInstance()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.queryj.sql.ConditionFactory#getInstance()
  */
  public void testGetInstance()
  throws Exception
  {
    // JUnitDoclet begin method getInstance
    // JUnitDoclet end method getInstance
  }
  
  /**
  * Tests ConditionFactoryTestcreateCondition()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.queryj.sql.ConditionFactory#createCondition(org.acmsl.queryj.sql.Field, org.acmsl.queryj.sql.ConditionOperator, org.acmsl.queryj.sql.Field)
  */
  public void testCreateCondition()
  throws Exception
  {
    // JUnitDoclet begin method createCondition
    // JUnitDoclet end method createCondition
  }
  
  /**
  * Tests ConditionFactoryTestcreateVariableCondition()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.queryj.sql.ConditionFactory#createVariableCondition(org.acmsl.queryj.sql.Field, org.acmsl.queryj.sql.ConditionOperator)
  */
  public void testCreateVariableCondition()
  throws Exception
  {
    // JUnitDoclet begin method createVariableCondition
    // JUnitDoclet end method createVariableCondition
  }
  
  /**
  * Tests ConditionFactoryTestwrap()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.queryj.sql.ConditionFactory#wrap(org.acmsl.queryj.sql.Condition, java.lang.String, java.lang.String)
  */
  public void testWrap()
  throws Exception
  {
    // JUnitDoclet begin method wrap
    // JUnitDoclet end method wrap
  }
  
  
  
  /**
  * JUnitDoclet moves marker to this method, if there is not match
  * for them in the regenerated code and if the marker is not empty.
  * This way, no test gets lost when regenerating after renaming.
  * Method testVault is supposed to be empty.
  * @throws Exception if an unexpected situation occurs.
  */
  public void testVault()
  throws Exception
  {
    // JUnitDoclet begin method testcase.testVault
    // JUnitDoclet end method testcase.testVault
  }
  
  public static void main(String[] args)
  {
    // JUnitDoclet begin method testcase.main
    junit.textui.TestRunner.run(ConditionFactoryTest.class);
    // JUnitDoclet end method testcase.main
  }
}
