/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="LoginMessageEvent.java" company="VorpeX">
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
package vorpex.beloco.message.events;

import vorpex.beloco.game.Session;
import vorpex.beloco.message.ClientMessage;
import vorpex.beloco.message.IClientMessage;

/**
 * Incoming Event
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */
public class LoginMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see vorpex.beloco.message.IClientMessage#handle(vorpex.beloco.game.Session, vorpex.beloco.message.ClientMessage)
	 */
	@Override
	public void handle(Session session, ClientMessage cMessage) {
		// TODO Auto-generated method stub

	}

}
