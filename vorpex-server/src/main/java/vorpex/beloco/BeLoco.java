/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="BeLoco.java" company="VorpeX">
 * Copyright (c) 2011-2012 All Right Reserved, http://vorpex.biz/
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
 * @email d.gunn@vorpex.biz
 * @date 21-12-2012
 * @summary
 ******************************************************************************/
package vorpex.beloco;

import vorpex.beloco.game.SessionManager;
import vorpex.beloco.message.MessageHandler;

/**
 * Classed used for implementing and handling all data relevant to 'BeLoco'
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */
public class BeLoco {

	/**
	 * Used to hold information about all sessions in the server
	 */
	private SessionManager sessionManager = new SessionManager();

	/**
	 * Used to hold information about messages in the game
	 */
	private MessageHandler messageHandler = new MessageHandler();
	
	/**
	 * @return the sessionManager
	 */
	public final SessionManager getSessionManager() {
		return sessionManager;
	}

	/**
	 * @param sessionmanager the sessionManager to set
	 */
	public final void setSessionManager(final SessionManager sessionmanager) {
		this.sessionManager = sessionmanager;
	}

	/**
	 * @return the messageHandler
	 */
	public final MessageHandler getMessageHandler() {
		return messageHandler;
	}

	/**
	 * @param messagehandler the messageHandler to set
	 */
	public final void setMessageHandler(final MessageHandler messagehandler) {
		this.messageHandler = messagehandler;
	}
}
