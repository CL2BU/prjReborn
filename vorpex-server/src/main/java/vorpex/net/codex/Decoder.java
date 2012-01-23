/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="Decoder.java" company="VorpeX">
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

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import vorpex.Vorpex;
import vorpex.beloco.message.ClientMessage;
import vorpex.logger.Logger;

/**
 * Class used for the handling, and decoding of incoming messages.
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */
public class Decoder extends FrameDecoder {

	@Override
	protected final Object decode(final ChannelHandlerContext ctx, final Channel channel,
			final ChannelBuffer buffer) throws Exception {

		// The first four bytes are the length...
		if (ctx.getChannel() == null || channel == null || buffer.readableBytes() < 4) {

			// decode() will be called again when more bytes are ready
			return null;
		}

		/*
		if(Emulator.getCommunication().getSessions().getSessionFromChannel(ctx.getChannel()).getCrypto() != null){
			buffer = Emulator.getCommunication().getSessions().getSessionFromChannel(ctx.getChannel()).getCrypto().decipher(buffer);
		}*/

		// mark an index just incase we got right length already
		buffer.markReaderIndex();

		// Grab the first byte, then reset to check for policy I guess
		boolean policyCheck = (buffer.readByte() == 60) ? true : false;
		
		// Now we'll check the first character :-)
		if (!policyCheck) {	// '@'
			buffer.resetReaderIndex();

		while (buffer.readableBytes() >= 6) {
				// Assume it's length first.
				int tMsgLen = buffer.readInt();

				// Make sure enough bytes are ready for the msg
				if (buffer.readableBytes() < tMsgLen) {

					// Reset to read len of bytes again
					buffer.resetReaderIndex();
					return null;
				}

				// Get the rest of the packet into a channel buffer obj
				ClientMessage cMessage = new ClientMessage(buffer.readBytes(tMsgLen));

				// Handle the Message
				Vorpex.getBeLoco().getSessionManager().getSession(channel).handleMessage(cMessage);
			}
		}
		else if (policyCheck) {	// '<'
			// Just get rid of any existing bytes, we know it's policy
			buffer.discardReadBytes();

			String policy = "<?xml version=\"1.0\"?>\r\n"
                + "<!DOCTYPE cross-domain-policy SYSTEM \"/xml/dtds/cross-domain-policy.dtd\">\r\n"
                + "<cross-domain-policy>\r\n"
                + "<allow-access-from domain=\"*\" to-ports=\"*\" />\r\n"
                + "</cross-domain-policy>\0";

			channel.write(policy);
		}
		return null;
	}

	@Override
	public final void exceptionCaught(final ChannelHandlerContext ctx, final ExceptionEvent e) {

		Logger.log(Decoder.class, e.getCause().getMessage());
		ctx.getChannel().disconnect();
	}
}
