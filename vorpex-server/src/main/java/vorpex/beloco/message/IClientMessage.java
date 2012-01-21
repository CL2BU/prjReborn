/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="IClientMessage.java" company="VorpeX">
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

import vorpex.beloco.game.Session;

/**
 * Interface to be implemented by Client Messages
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */
public interface IClientMessage {

	/**
	 * To be implemented in Incoming messages
	 * @param session The Session the Message came from
	 * @param cMessage The Message that's being received
	 */
	void handle(Session session, ClientMessage cMessage);
}
