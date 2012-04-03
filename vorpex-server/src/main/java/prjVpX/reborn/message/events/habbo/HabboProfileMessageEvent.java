/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="InitMessageEvent.java" company="VorpeX">
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
package prjVpX.reborn.message.events.habbo;


import java.sql.ResultSet;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.HabboManager;
import prjVpX.reborn.message.ClientMessage;
import prjVpX.reborn.message.IClientMessage;
import prjVpX.reborn.message.ServerMessage;


/**
 * Incoming Event
 * @author Dominic Gunn (d.gunn@prjvpx.biz)
 */
public class HabboProfileMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	
	public void handle(Session session, ClientMessage cMessage) {
		Integer ProfileUserID = cMessage.readInt();
		ServerMessage HabboProfile = new ServerMessage(ServerEvents.WatchProfile);
		HabboProfile.writeInt(0); //If it's me
		HabboProfile.writeString(HabboManager.getHabboByID(ProfileUserID).Username);
		HabboProfile.writeString(HabboManager.getHabboByID(ProfileUserID).Look);
		HabboProfile.writeString(HabboManager.getHabboByID(ProfileUserID).Motto);
		HabboProfile.writeString(HabboManager.getHabboByID(ProfileUserID).BeenCreated); //Created.
		HabboProfile.writeInt(555);
		HabboProfile.writeInt(1); //how many friends
		HabboProfile.writeString("");
		HabboProfile.writeBoolean(true); //online
		HabboProfile.writeInt(0); //unknown
		HabboProfile.writeInt(prjvpx.GetTimestamp());
		session.sendMessage(HabboProfile);
	}
}
