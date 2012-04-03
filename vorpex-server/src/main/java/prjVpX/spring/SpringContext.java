/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="SpringContext.java" company="VorpeX">
 * Copyright (c) 2011-2012 All Right Reserved, http://prjvpx.biz/
 * 
 * This source is subject to the "Don't Be A Dick" License.
 * Please see the License.txt file for more information.
 * You may not use this file except in compliance with the License.
 * 
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 * 
 * @author Dominic Gunn
 * @email d.gunn@prjvpx.biz
 * @date 21-12-2012
 * @summary
 ******************************************************************************/
package prjVpX.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {

	private static ApplicationContext context;
    private static SpringContext instance;
    private final static String CONFIG_FILE = "spring-config.xml";

    public static SpringContext getInstance() {
        if (null == instance) {
            instance = new SpringContext();
            context = new ClassPathXmlApplicationContext(
                    CONFIG_FILE);
        }
        return instance;
    }

    public Object getBean(String bean) {
        return context.getBean(bean);
    }
}
