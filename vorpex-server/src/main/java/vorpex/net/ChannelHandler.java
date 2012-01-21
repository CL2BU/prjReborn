/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="ChannelHandler.java" company="VorpeX">
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
package vorpex.net;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import vorpex.Vorpex;

/**
 * Class used to handle incoming connections.
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */
public class ChannelHandler extends SimpleChannelHandler {

	@Override
	public final void channelConnected(final ChannelHandlerContext ctx, final ChannelStateEvent e) throws Exception {
		// Check we're not being BOMBARDED
        if (e instanceof ChannelStateEvent) {
        	Vorpex.getBeLoco().getSessionManager().addConnection(e.getChannel());
        }
    }

    @Override
	public final void exceptionCaught(final ChannelHandlerContext ctx, final ExceptionEvent e) {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
