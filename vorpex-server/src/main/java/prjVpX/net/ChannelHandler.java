/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="ChannelHandler.java" company="VorpeX">
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
package prjVpX.net;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.SessionManager;
import prjVpX.reborn.game.managers.HabboManager;
import prjVpX.reborn.game.managers.RoomManager;
import prjVpX.reborn.game.managers.helpers.Room;


/**
 * Class used to handle incoming connections.
 * @author Dominic Gunn (d.gunn@prjvpx.biz)
 */
public class ChannelHandler extends SimpleChannelHandler {

	public final void channelConnected(final ChannelHandlerContext ctx, final ChannelStateEvent e) throws Exception {
		// Check we're not being BOMBARDED
        if (e instanceof ChannelStateEvent) {
        	prjvpx.getReborn().getSessionManager().addConnection(e.getChannel());
        	prjVpX.reborn.game.managers.HabboManager.SerializeHabbo(e.getChannel(), 0, "", "", "", "", "", null);
        }
    }
	
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e)
	{
		try
		{
	        if (e instanceof ChannelStateEvent) {
	        	HabboManager Habbo = HabboManager.HabbosByChannel.get(e.getChannel());
	        	if(Habbo.isOnRoom)
	        	{
	        		Room R = RoomManager.getRoomByID(Habbo.CurrentRoom);
	        		R.UserLeavesRoom(Habbo);
	        	}
				DatabaseClient Client = new DatabaseClient(prjvpx.Database);
				Client.SecureExec("UPDATE users_characters SET connected = 0 WHERE id = ?", Habbo.Id + "");
				Logger.log(SessionManager.class, "Disconnected Session (id: " + prjvpx.getReborn().getSessionManager().getSession(e.getChannel()).getSessionID() + ", ip: " + prjvpx.getReborn().getSessionManager().getSession(e.getChannel()).getIP() + ")");
			}
		}
		catch(Exception ex) { }
	}

	public final void exceptionCaught(final ChannelHandlerContext ctx, final ExceptionEvent e) {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
