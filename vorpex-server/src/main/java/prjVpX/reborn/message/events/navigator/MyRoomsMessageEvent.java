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
package prjVpX.reborn.message.events.navigator;


import java.sql.ResultSet;
import java.sql.SQLException;

import prjVpX.prjvpx;
import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.MyRoomsManager;
import prjVpX.reborn.message.ClientMessage;
import prjVpX.reborn.message.IClientMessage;
import prjVpX.reborn.message.ServerMessage;


/**
 * Incoming Event
 * @author Dominic Gunn (d.gunn@prjvpx.biz)
 */
public class MyRoomsMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	
	public void handle(Session session, ClientMessage cMessage) {
	    ServerMessage MyRooms = new ServerMessage(ServerEvents.LookRooms);
	    MyRooms.writeInt(5);
	    MyRooms.writeString("");
	    MyRooms.writeInt(MyRoomsManager.Myrooms.size());
	
	    for(MyRoomsManager Data : MyRoomsManager.Myrooms)
	    {
	    	DatabaseClient Client;
	    	try {
				Client = new DatabaseClient(prjvpx.Database);
				ResultSet DataSet = Client.SecureQuery("SELECT * FROM users_characters WHERE id = ?", Data.Owner);
		        if(DataSet.next())
		        {
					MyRooms.writeInt(Data.Id);
			        MyRooms.writeBoolean(false);
			        MyRooms.writeString(Data.Name);
			        MyRooms.writeInt(1);
			        MyRooms.writeString(DataSet.getString("username"));
			        MyRooms.writeInt(0);
			        MyRooms.writeInt(Data.UsersNow);
			        MyRooms.writeInt(Data.UsersNow);
			        MyRooms.writeString(Data.Description);
			        MyRooms.writeInt(0);
			        MyRooms.writeInt((Data.Category == 3) ? 0 : 2);
			        MyRooms.writeInt(Data.Score);
			        MyRooms.writeInt(Data.Category);
			        MyRooms.writeString("");
			        MyRooms.writeInt(0);
			        MyRooms.writeInt(0);
			        MyRooms.writeInt(1);
			        MyRooms.writeString("Tagging");
			        MyRooms.writeInt(0);
			        MyRooms.writeInt(0);
			        MyRooms.writeInt(0);
			
			        // booleans
			        MyRooms.writeBoolean(true);
			        MyRooms.writeBoolean(true);
		        }
	    	}
	    	catch(Exception ex)
	    	{}
	    }
	    MyRooms.writeBoolean(false);
	    session.sendMessage(MyRooms);
	}
}
