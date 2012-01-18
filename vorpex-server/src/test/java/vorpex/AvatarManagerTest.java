/*******************************************************************************
 * <copyright file="AvatarManagerTest.java" company="VorpeX">
 *  Copyright (c) 2011-2012 All Right Reserved, http://vorpex.biz/
 *  
 *  This source is subject to the "Don't Be A Dick" License.
 *  Please see the License.txt file for more information.
 *  You may not use this file except in compliance with the License.
 *  
 *  THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
 *  KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 *  PARTICULAR PURPOSE.
 *  
 *  @author Dominic Gunn
 *  @email d.gunn@vorpex.biz
 *  @date 18-12-2012
 *  @summary
 ******************************************************************************/
package vorpex;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;

import vorpex.spring.model.Avatar;

public class AvatarManagerTest {

private static Logger logger = Logger.getLogger(AvatarManagerTest.class);
    
    @Test
    public void testInsertAvatar() throws Exception {
        
        logger.debug("Create Spring contex (will update this next time)");
        
        // Object creation
        Avatar Avatar1 = new Avatar(1, "Bar");
        logger.debug("person1 = " + Avatar1 + ", [" + Avatar1.getAvatarName() + "]");
        Avatar Avatar2 = new Avatar(2, "Foo");
        logger.debug("person2 = " + Avatar2 + ", [" + Avatar2.getAvatarName() + "]");
        
        List<Avatar> persons = new ArrayList<Avatar>();
        persons.add(Avatar1);
        persons.add(Avatar2);
        logger.debug("persons = " + persons);
    }
}
