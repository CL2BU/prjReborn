/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="Session.java" company="VorpeX">
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

package vorpex.beloco.game;

import org.jboss.netty.channel.Channel;

import vorpex.Vorpex;
import vorpex.beloco.message.ClientMessage;
import vorpex.beloco.message.ServerMessage;

/**
 * Class used for the handling of individual sessions.
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */
public class Session {

	/**
	 * ID Of the Session
	 */
	private int iSessionID;

	/**
	 * Channel of the session
	 */
	private Channel cChannel;
	
	/**
	 * Constructor for the Session class
	 * @param channel The channel relevant to the session.
	 * @param id The ID of the session
	 */
	public Session(final Channel channel, final int id) {

		this.cChannel = channel;
		this.iSessionID = id;
	}

	/**
	 * Function used to handle messages being passed to the session
	 * @param cMessage A single message to be handled.
	 */
	public void handleMessage(final ClientMessage cMessage) {

		Vorpex.getBeLoco().getMessageHandler().invoke(this, cMessage);
	}

	/**
	 * Function used to send a message to the connected client.
	 * @param sMessage the message being sent
	 */
	public final void sendMessage(final ServerMessage sMessage) {

		this.cChannel.write(sMessage);
	}

	/**
	 * @return the cChannel
	 */
	public final Channel getChannel() {
		return cChannel;
	}

	/**
	 * @param channel the cChannel to set
	 */
	public final void setChannel(final Channel channel) {
		this.cChannel = channel;
	}

	/**
	 * @return the iSessionID
	 */
	public final int getSessionID() {
		return iSessionID;
	}

	/**
	 * @param sessionID the iSessionID to set
	 */
	public final void setSessionID(final int sessionID) {
		this.iSessionID = sessionID;
	}

	/**
	 * @return The IP of the connected user.
	 */
    public final String getIP() {
        return this.getChannel().getRemoteAddress().toString().split(":")[0].substring(1);
    }
}
