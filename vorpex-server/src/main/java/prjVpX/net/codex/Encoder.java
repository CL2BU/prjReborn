/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="Encoder.java" company="VorpeX">
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
package prjVpX.net.codex;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import prjVpX.reborn.message.ServerMessage;


/**
 * Class used for the writing messages back to the client
 * @author Dominic Gunn (d.gunn@prjvpx.biz)
 */

public class Encoder extends SimpleChannelHandler {

	@Override
	public void writeRequested(final ChannelHandlerContext ctx, final MessageEvent e) {

		// Need to do an XML Policy check.
		if (!(e.getMessage() instanceof ServerMessage) && (e.getMessage() instanceof String)) {
			
			//We can just send a string
			org.jboss.netty.channel.Channels.write(
					ctx, e.getFuture(), ChannelBuffers.copiedBuffer((String)e.getMessage(), Charset.forName("UTF-8")));
			return;
		}

		// Check we're sending a valid Message and nothing went wrong
        if (e.getMessage() instanceof ServerMessage) {
            ServerMessage message = (ServerMessage) e.getMessage();

            org.jboss.netty.channel.Channels.write(ctx, e.getFuture(), message.getMessageData());
        }
    }
}
