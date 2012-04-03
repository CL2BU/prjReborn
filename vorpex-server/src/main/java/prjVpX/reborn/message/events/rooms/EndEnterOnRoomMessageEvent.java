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
package prjVpX.reborn.message.events.rooms;


import java.sql.ResultSet;
import java.sql.SQLException;

import prjVpX.prjvpx;
import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.message.ClientMessage;
import prjVpX.reborn.message.IClientMessage;
import prjVpX.reborn.message.ServerMessage;
import prjVpX.reborn.game.managers.helpers.*;
import prjVpX.reborn.game.managers.*;


/**
 * Incoming Event
 * @author Dominic Gunn (d.gunn@prjvpx.biz)
 */
public class EndEnterOnRoomMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	
	public void handle(Session session, ClientMessage cMessage) {
		Room RoomToEnter = RoomManager.getRoomByID(session.Habbo.CurrentRoom);
		if(session.Habbo.CurrentRoom != 0)
		{
			Integer RoomId = session.Habbo.CurrentRoom;
			
		    ServerMessage UserData1 = new ServerMessage(ServerEvents.preUserData);
		    UserData1.writeInt(1);
		    UserData1.writeInt(session.Habbo.Id);
		    UserData1.writeString(session.Habbo.Username);
		    UserData1.writeInt(0);
		    session.sendMessage(UserData1);
		    
		    ServerMessage UserToLoad = new ServerMessage(ServerEvents.UserData);
		    UserToLoad.writeInt(RoomToEnter.UsersInRoom.size());
		    for(HabboManager CurrentUser : RoomToEnter.UsersInRoom)
		    {
		        UserToLoad.writeInt(CurrentUser.Id);
		        UserToLoad.writeString(CurrentUser.Username);
		        UserToLoad.writeString(CurrentUser.Motto);
		        UserToLoad.writeString(CurrentUser.Look);
		        UserToLoad.writeInt(CurrentUser.cSession.getSessionID()); // session id
		        UserToLoad.writeInt(CurrentUser.X);
		        UserToLoad.writeInt(CurrentUser.Y);
		        UserToLoad.writeString(CurrentUser.Z); // user z
		        UserToLoad.writeInt(CurrentUser.Rot);
		        UserToLoad.writeInt(1);
		        UserToLoad.writeString(CurrentUser.Gender.toLowerCase());
		        UserToLoad.writeInt(-1);
		        UserToLoad.writeInt(-1);
		        UserToLoad.writeInt(0);
		        UserToLoad.writeInt(525);
		    }
		    session.sendMessage(UserToLoad);
		    
		    ServerMessage UserToLoad1 = new ServerMessage(ServerEvents.UserData);
		    UserToLoad1.writeInt(1);
		    UserToLoad1.writeInt(session.Habbo.Id);
		    UserToLoad1.writeString(session.Habbo.Username);
		    UserToLoad1.writeString(session.Habbo.Motto);
		    UserToLoad1.writeString(session.Habbo.Look);
		    UserToLoad1.writeInt(session.Habbo.cSession.getSessionID()); // session id
		    UserToLoad1.writeInt(session.Habbo.X);
		    UserToLoad1.writeInt(session.Habbo.Y);
		    UserToLoad1.writeString(session.Habbo.Z); // user z
		    UserToLoad1.writeInt(session.Habbo.Rot);
		    UserToLoad1.writeInt(1);
		    UserToLoad1.writeString(session.Habbo.Gender.toLowerCase());
		    UserToLoad1.writeInt(-1);
		    UserToLoad1.writeInt(-1);
		    UserToLoad1.writeInt(0);
		    UserToLoad1.writeInt(525);
		    RoomManager.SendMessageToAllInRoomInsteadOfMe(session.Habbo.Id, RoomId, UserToLoad1);
		    
		    ServerMessage RoomPanel = new ServerMessage(ServerEvents.RoomPanel);
		    RoomPanel.writeBoolean(true);
		    RoomPanel.writeInt(RoomId);
		    RoomPanel.writeBoolean(true);
		    session.sendMessage(RoomPanel);
		
		    ServerMessage MoreData = new ServerMessage(ServerEvents.RoomRating);
		    MoreData.writeInt(500);
		    MoreData.writeBoolean(true);
		    session.sendMessage(MoreData);
		    
		    ServerMessage sRoomData = new ServerMessage(ServerEvents.RoomData);
		    sRoomData.writeBoolean(true);
		    sRoomData.writeInt(RoomId);
		    sRoomData.writeBoolean(false);
		    sRoomData.writeString(RoomToEnter.Name);
		    sRoomData.writeInt(RoomToEnter.Owner);
		    sRoomData.writeString("BetterWay");
		    sRoomData.writeInt(0);
		    sRoomData.writeInt(55);
		    sRoomData.writeInt(60);
		    sRoomData.writeString(RoomToEnter.Desc);
		    sRoomData.writeInt(0);
		    sRoomData.writeInt(2);
		    sRoomData.writeInt(50);
		    sRoomData.writeInt(3);
		    sRoomData.writeString("");
		    sRoomData.writeInt(0);
		    sRoomData.writeInt(0);
		    sRoomData.writeInt(0);
		    // Icons
		    sRoomData.writeInt(0);
		    sRoomData.writeInt(0);
		    sRoomData.writeInt(0);
		    // bools
		    sRoomData.writeBoolean(true);
		    sRoomData.writeBoolean(true);
		    sRoomData.writeBoolean(false);
		    sRoomData.writeBoolean(false);
		    sRoomData.writeBoolean(false);
		    session.sendMessage(sRoomData);
		}
	}
}
