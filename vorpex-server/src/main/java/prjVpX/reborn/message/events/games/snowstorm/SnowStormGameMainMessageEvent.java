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
package prjVpX.reborn.message.events.games.snowstorm;


import java.sql.ResultSet;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.message.ClientMessage;
import prjVpX.reborn.message.IClientMessage;
import prjVpX.reborn.message.ServerMessage;


/**
 * Incoming Event
 * @author Dominic Gunn (d.gunn@prjvpx.biz)
 */
public class SnowStormGameMainMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	
	public void handle(Session session, ClientMessage cMessage) {
        ServerMessage SS = new ServerMessage(ServerEvents.CurrentSnowStormPlays);
        SS.writeInt(0);
        SS.writeInt(0);
        SS.writeInt(1);
        SS.writeInt(15); // games left,TODO: static
        session.sendMessage(SS);

        ServerMessage SS2 = new ServerMessage(1071);
        SS2.writeInt(10806);
        SS2.writeString("GET_SNOWWAR_TOKENS");
        SS2.writeInt(1);
        session.sendMessage(SS2);
	}
}