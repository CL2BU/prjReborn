/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="Encoder.java" company="VorpeX">
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
package vorpex.net.codex;

import java.nio.channels.Channels;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import vorpex.beloco.message.ServerMessage;

/**
 * Class used for the writing messages back to the client
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */

public class Encoder extends SimpleChannelHandler {

	@Override
	public void writeRequested(final ChannelHandlerContext ctx, final MessageEvent e) {

		// Check we're sending a valid Message and nothing went wrong
        if (e.getMessage() instanceof ServerMessage) {
            ServerMessage message = (ServerMessage) e.getMessage();

            Channels.write(ctx, e.getFuture(), message.getBytes());
        }
    }
}
