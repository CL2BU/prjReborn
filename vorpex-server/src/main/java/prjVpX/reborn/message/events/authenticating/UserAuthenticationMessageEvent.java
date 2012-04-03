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
package prjVpX.reborn.message.events.authenticating;


import java.sql.ResultSet;
import java.sql.SQLException;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.HabboManager;
import prjVpX.reborn.game.managers.MessengerFriendsManager;
import prjVpX.reborn.message.ClientMessage;
import prjVpX.reborn.message.IClientMessage;
import prjVpX.reborn.message.ServerMessage;


/**
 * Incoming Event
 * @author Dominic Gunn (d.gunn@prjvpx.biz)
 */
public class UserAuthenticationMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	public void handle(Session session, ClientMessage cMessage) throws SQLException, ClassNotFoundException, Exception {
			DatabaseClient Client = new DatabaseClient(prjvpx.Database);
			
			ResultSet UserRow = Client.SecureQuery("SELECT * FROM users_characters WHERE IP = ?", session.getIP());
			if(UserRow.first())
			{
				HabboManager.SetDetailsForChannel(session.getChannel(),
						Integer.parseInt(UserRow.getString("id")), UserRow.getString("username"),
						UserRow.getString("motto"), UserRow.getString("figure"),
						UserRow.getString("gender"), UserRow.getString("credits"), session, UserRow.getString("beencreated"));
				session.setHabbo(HabboManager.HabbosByChannel.get(session.getChannel()));

				Client.SecureExec("UPDATE users_characters SET connected = 1 WHERE id = ?", session.Habbo.Id + "");
				ServerMessage InitSystem = new ServerMessage(ServerEvents.InitSystem);
				session.sendMessage(InitSystem);
				
				ServerMessage InitCredits = new ServerMessage(prjVpX.reborn.cache.ServerEvents.SendCredits);
				InitCredits.writeString(HabboManager.HabbosByChannel.get(session.getChannel()).Credits + ".0");
				session.sendMessage(InitCredits);
				
				ServerMessage SendUser = new ServerMessage(prjVpX.reborn.cache.ServerEvents.MyInformation);
		        SendUser.writeInt(HabboManager.HabbosByChannel.get(session.getChannel()).Id);
		        SendUser.writeString(HabboManager.HabbosByChannel.get(session.getChannel()).Username);
		        SendUser.writeString(HabboManager.HabbosByChannel.get(session.getChannel()).Look);
				SendUser.writeString(HabboManager.HabbosByChannel.get(session.getChannel()).Gender.toLowerCase());
		        SendUser.writeString(HabboManager.HabbosByChannel.get(session.getChannel()).Motto);
		        SendUser.writeString("habbowall");
		        SendUser.writeBoolean(true);
		        SendUser.writeInt(MessengerFriendsManager.getFriendsForID(HabboManager.HabbosByChannel.get(session.getChannel()).Id).size()); // Friends
		        SendUser.writeInt(3);
		        SendUser.writeInt(3);
		        SendUser.writeBoolean(true);
		        session.sendMessage(SendUser);
		        
		        ServerMessage MoreData = new ServerMessage(ServerEvents.MoreData);
		        MoreData.writeBoolean(true);
		        MoreData.writeBoolean(false);
		        session.sendMessage(MoreData);
		        
		        ServerMessage UnData = new ServerMessage(ServerEvents.UnData);
		        UnData.writeBoolean(false);
		        session.sendMessage(UnData);
		        
		        ServerMessage FuseRights = new ServerMessage(ServerEvents.FuseRights);
		        FuseRights.writeInt(2);
		        FuseRights.writeInt(7);
		        session.sendMessage(FuseRights);
		        
		        ServerMessage Favs = new ServerMessage(ServerEvents.SetFavs);
		        Favs.writeInt(30);
		        Favs.writeInt(0);
		        session.sendMessage(Favs);
		        
		        ServerMessage Home = new ServerMessage(ServerEvents.SetHome);
		        Home.writeInt(18);
		        Home.writeInt(18);
		        session.sendMessage(Home);
		        
				ServerMessage InitActivityPoints = new ServerMessage(prjVpX.reborn.cache.ServerEvents.Pixels);
				InitActivityPoints.writeInt(2);
				InitActivityPoints.writeInt(0);
				InitActivityPoints.writeInt(1000);
				InitActivityPoints.writeInt(1);
				InitActivityPoints.writeInt(1000);
				session.sendMessage(InitActivityPoints);

		        ServerMessage Snow = new ServerMessage(ServerEvents.Snow);
		        Snow.writeBoolean(true);
		        session.sendMessage(Snow);
		        
		        ServerMessage Club = new ServerMessage(prjVpX.reborn.cache.ServerEvents.ClubData);
		        Club.writeString("club_habbo");
		        Club.writeInt(55); // Days
		        Club.writeInt(0);
		        Club.writeInt(0);
		        Club.writeInt(1);
		        Club.writeBoolean(false);
		        Club.writeBoolean(false);
		        Club.writeInt(0);
		        Club.writeInt(0);
		        Club.writeInt(0);
		        session.sendMessage(Club);
				
		        ServerMessage Friends = new ServerMessage(prjVpX.reborn.cache.ServerEvents.Friends);
	            Friends.writeInt(1100);
	            Friends.writeInt(300);
	            Friends.writeInt(800);
	            Friends.writeInt(1100);
	            Friends.writeInt(0); // categories

	            Friends.writeInt(MessengerFriendsManager.getFriendsForID(HabboManager.HabbosByChannel.get(session.getChannel()).Id).size()); // amount friends
	            for(MessengerFriendsManager Friend : MessengerFriendsManager.getFriendsForID(HabboManager.HabbosByChannel.get(session.getChannel()).Id))
	            {
	        		HabboManager Habbo = HabboManager.getHabboByID(Friend.IdWho);
	    			if(Habbo != null)
	    			{
	    	            Friends.writeInt(Habbo.Id); // id
			            Friends.writeString(Habbo.Username); // username
			            Friends.writeInt((HabboManager.getIfIsOnline(Habbo.Id)) ? Habbo.cSession.getSessionID() : Habbo.Id); // If Online SessionID if not UserID
			            Friends.writeBoolean(HabboManager.getIfIsOnline(Habbo.Id)); // online
			            Friends.writeBoolean(false); // inroom
			            Friends.writeString(Habbo.Look); // look
			            Friends.writeInt(0); // category id
			            Friends.writeString(Habbo.Motto);
			            Friends.writeString(Habbo.BeenCreated);
			            Friends.writeInt(0);
	    			}
	            }

		        session.sendMessage(Friends);
				
				ServerMessage InitNotif = new ServerMessage(ServerEvents.Alert);
				InitNotif.writeString("Welcome at Habbo.net.nz! " + HabboManager.HabbosByChannel.get(session.getChannel()).Username + "\nWe appriciate your look at our hotel!");
				InitNotif.writeBoolean(false);
				InitNotif.writeBoolean(false);
				session.sendMessage(InitNotif);
			}
	}
}
