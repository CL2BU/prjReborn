/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="MessageHandler.java" company="VorpeX">
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
package prjVpX.reborn.message;

import java.sql.SQLException;
import java.util.HashMap;

import prjVpX.logger.Logger;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.message.events.authenticating.*;
import prjVpX.reborn.message.events.catalog.CatalogLoadPageDataMessageEvent;
import prjVpX.reborn.message.events.catalog.CatalogLoadPageMessageEvent;
import prjVpX.reborn.message.events.games.snowstorm.SnowStormGameMainMessageEvent;
import prjVpX.reborn.message.events.habbo.HabboBadgeInventaryMessageEvent;
import prjVpX.reborn.message.events.habbo.HabboInventaryMessagEvent;
import prjVpX.reborn.message.events.habbo.HabboProfileMessageEvent;
import prjVpX.reborn.message.events.navigator.FeaturedRoomMessageEvent;
import prjVpX.reborn.message.events.navigator.MyRoomsMessageEvent;
import prjVpX.reborn.message.events.rooms.ActionMessageEvent;
import prjVpX.reborn.message.events.rooms.ChangeLookAndGenderMessageEvent;
import prjVpX.reborn.message.events.rooms.ChangeMottoMessageEvent;
import prjVpX.reborn.message.events.rooms.DanceMessageEvent;
import prjVpX.reborn.message.events.rooms.EndEnterOnRoomMessageEvent;
import prjVpX.reborn.message.events.rooms.EnterOnRoomMessageEvent;
import prjVpX.reborn.message.events.rooms.SerializeHeightMapMessageEvent;
import prjVpX.reborn.message.events.rooms.ShoutMessageEvent;
import prjVpX.reborn.message.events.rooms.SignMessageEvent;
import prjVpX.reborn.message.events.rooms.SitMessageEvent;
import prjVpX.reborn.message.events.rooms.TalkMessageEvent;
import prjVpX.reborn.message.events.rooms.WalkMessageEvent;


/**
 * Class used for the handling of messages in the server
 * @author Dominic Gunn (d.gunn@prjvpx.biz)
 */
public class MessageHandler {

	/**
	 * Used to store data about Messages in the server
	 */
	private HashMap<Short, IClientMessage> messages = new HashMap<Short, IClientMessage>();
    /**
     * Used to initialise and fill all possible messages.
     */
    public MessageHandler() {
    	//Init System
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.ReadRelease, new UserAuthenticationMessageEvent());
    	
    	//Habbo
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.LookProfile, new HabboProfileMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.LoadBadgesInventary, new HabboBadgeInventaryMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.LoadInventary, new HabboInventaryMessagEvent());
    	
    	//Rooms
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.LookPublics, new FeaturedRoomMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.LookOnMyRooms, new MyRoomsMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.EnterOnRoom, new EnterOnRoomMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.EndEnterRoom, new EndEnterOnRoomMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.SerializeHeightmap, new SerializeHeightMapMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.Walk, new WalkMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.Talk, new TalkMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.Shout, new ShoutMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.Sign, new SignMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.ChangeLooks, new ChangeLookAndGenderMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.ChangeMotto, new ChangeMottoMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.Dance, new DanceMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.SitOnRoom, new SitMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.Wave, new ActionMessageEvent());
    	
    	//Snowstorm
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.LoadSnowStormState, new SnowStormGameMainMessageEvent());
    	
    	//Catalog
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.LoadCatalog, new CatalogLoadPageMessageEvent());
    	this.messages.put((short)prjVpX.reborn.cache.ClientEvents.LoadCatalogPage, new CatalogLoadPageDataMessageEvent());
    }

    /**
     * Used for Handling of a message
     * @param session The Session the message came from
     * @param cMessage The Message being invoked
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
	public final void invoke(final Session session, final ClientMessage cMessage) throws SQLException, ClassNotFoundException, Exception {

		// Used to Remove Overhead
		IClientMessage iMessage = this.messages.get(cMessage.getMessageID());

		// Check if it's null
		if(!this.messages.containsKey(cMessage.getMessageID())) {
			Logger.log(MessageHandler.class, "Unkwown Message (id: " + cMessage.getMessageID()+ ", client session: " + session.getSessionID() + ")");
			Logger.log(MessageHandler.class, "Incoming: " + cMessage.getBodyString());
			return;
		}
		Logger.log(MessageHandler.class, "Kwown Message (id: " + cMessage.getMessageID()+ ", client session: " + session.getSessionID() + ")");
		Logger.log(MessageHandler.class, "Incoming: " + cMessage.getBodyString());
		// Handle the Message
		iMessage.handle(session, cMessage);
	}
}
