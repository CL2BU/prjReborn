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
import java.util.*;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.FurnitureManager;
import prjVpX.reborn.game.managers.HabboManager;
import prjVpX.reborn.game.managers.UserItemManager;
import prjVpX.reborn.message.ClientMessage;
import prjVpX.reborn.message.IClientMessage;
import prjVpX.reborn.message.ServerMessage;
import prjVpX.reborn.game.managers.helpers.*;


/**
 * Incoming Event
 * @author Dominic Gunn (d.gunn@prjvpx.biz)
 */
public class HabboInventaryMessagEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	
	public void handle(Session session, ClientMessage cMessage) {
	
	    List<Furniture> AllItems = UserItemManager.getItemsForHabboId(session.Habbo.Id);
	    List<Furniture> WallItems = new ArrayList<Furniture>();
	    List<Furniture> FloorItems = new ArrayList<Furniture>();
	    for(Furniture furniData : AllItems)
	    {
	        if(furniData.iItemType.equals("s"))
	            FloorItems.add(furniData);
	        else
	        {
	            WallItems.add(furniData);
	        }
	    }
	    
	    ServerMessage FloorInventory = new ServerMessage(ServerEvents.SendInventory);
	    FloorInventory.writeString("S");
	    FloorInventory.writeInt(1);
	    FloorInventory.writeInt(1);
	    FloorInventory.writeInt(FloorItems.size());
	    for(Furniture furniData : FloorItems)
	    {
	        FloorInventory.writeInt(furniData.iId);
	        FloorInventory.writeString(furniData.iItemType.toUpperCase());
	        FloorInventory.writeInt(furniData.iId);
	        FloorInventory.writeInt(furniData.iSpriteId);
	        FloorInventory.writeInt(1);
	        FloorInventory.writeString("");
	        FloorInventory.writeInt(0);
	        FloorInventory.writeBoolean(furniData.iCanRecycle);
	        FloorInventory.writeBoolean(furniData.iCanTrade);
	        FloorInventory.writeBoolean(furniData.iCanStack);
	        FloorInventory.writeBoolean(furniData.iCanSell);
	        FloorInventory.writeInt(-1);
	        FloorInventory.writeString("");
	        FloorInventory.writeInt(0);
	    }
	    session.sendMessage(FloorInventory);
	    
	    ServerMessage WallInventory = new ServerMessage(ServerEvents.SendInventory);
	    WallInventory.writeString("I");
	    WallInventory.writeInt(1);
	    WallInventory.writeInt(1);
	    WallInventory.writeInt(WallItems.size());
	    for(Furniture Item : WallItems)
	    {
	        WallInventory.writeInt(Item.iId);
	        WallInventory.writeString(Item.iItemType.toUpperCase());
	        WallInventory.writeInt(Item.iId);
	        WallInventory.writeInt(Item.iSpriteId);
	        if(Item.iName.contains("a2"))
	            WallInventory.writeInt(3);
	        else if(Item.iName.contains("wall"))
	            WallInventory.writeInt(2);
	        else if(Item.iName.contains("land"))
	            WallInventory.writeInt(4);
	        else
	            WallInventory.writeInt(1);
	        WallInventory.writeInt(0);
	        WallInventory.writeString("");
	        WallInventory.writeBoolean(Item.iCanRecycle);
	        WallInventory.writeBoolean(Item.iCanTrade);
	        WallInventory.writeBoolean(Item.iCanStack);
	        WallInventory.writeBoolean(Item.iCanSell);
	        WallInventory.writeInt(-1);
	    }	
	    session.sendMessage(WallInventory);
	}
}
