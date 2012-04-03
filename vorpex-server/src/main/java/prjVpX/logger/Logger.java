/*******************************************************************************
 * <copyright file="Logger.java" company="VorpeX">
 *  Copyright (c) 2011-2012 All Right Reserved, http://prjvpx.biz/
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
 *  @email d.gunn@prjvpx.biz
 *  @date 19-12-2012
 *  @summary
 ******************************************************************************/
package prjVpX.logger;

/**
 * Classed used for logging data to the console window.
 * @author Dominic
 *
 */
public class Logger {

	/**
	 * Declaration of log4j Logger.
	 */
	private static org.apache.log4j.Logger logger = null;

	/**
	 * Function used to log data to the console window.
	 * @param tClass the name of the class the log call is from.
	 * @param sLog The data to be logged to console window.
	 */
	public static void log(final Class<?> tClass, final String sLog) {

		// Initialise the logger with the class being passed.
		logger = org.apache.log4j.Logger.getLogger(tClass.getSimpleName());

		// Log a general messsage
		logger.info(sLog);
	}

	/**
	 * Function used to log debug data to the console window.
	 * @param sClassName the name of the class the log call is from.
	 * @param sLog The data to be logged to console window.
	 */
	public static void debug(final String sClassName, final String sLog) {
	
		// Initialise the logger with the class being passed.
		logger = org.apache.log4j.Logger.getLogger(sClassName);
	
		// Log a general messsage
		logger.debug(sLog);
	}

	/**
	 * Function used to log a fatal error to the console window.
	 * @param sClassName the name of the class the log call is from.
	 * @param sLog The data to be logged to console window.
	 */
	public static void fatal(final Class<?> tClass, final String sLog) {

		// Initialise the logger with the class being passed.
		logger = org.apache.log4j.Logger.getLogger(tClass.getSimpleName());

		// Log a general messsage
		logger.fatal(sLog);
	}

	/**
	 * Function used to log an error to the console window.
	 * @param sClassName the name of the class the log call is from.
	 * @param sLog The data to be logged to console window.
	 */
	public static void error(final String sClassName, final String sLog) {

		// Initialise the logger with the class being passed.
		logger = org.apache.log4j.Logger.getLogger(sClassName);

		// Log a general messsage
		logger.error(sLog);
	}
}
