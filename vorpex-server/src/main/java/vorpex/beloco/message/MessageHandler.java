/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="MessageHandler.java" company="VorpeX">
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
package vorpex.beloco.message;

import java.util.HashMap;

import vorpex.beloco.game.Session;
import vorpex.beloco.message.events.*;
import vorpex.logger.Logger;

/**
 * Class used for the handling of messages in the server
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */
public class MessageHandler {

	/**
	 * Used to store data about Messages in the server
	 */
    private HashMap<Integer, IClientMessage> messages = new HashMap<Integer, IClientMessage>();

    /**
     * Used to initialise and fill all possible messages.
     */
    public MessageHandler() {

    	this.messages.put(1, new InitMessageEvent());
    	this.messages.put(2, new LoginMessageEvent());
    }

    /**
     * Used for Handling of a message
     * @param session The Session the message came from
     * @param cMessage The Message being invoked
     */
	public final void invoke(final Session session, final ClientMessage cMessage) {

		// Used to Remove Overhead
		IClientMessage iMessage = null;

		// Check if it's null
		if((iMessage = this.messages.get(cMessage.getMessageID())) == null) {
			Logger.log(MessageHandler.class, "Unkwown Message (id: " + cMessage.getMessageID()+ ", client session: " + session.getSessionID() + ")");
			return;
		}

		// Handle the Message
		iMessage.handle(session, cMessage);
	}
}
