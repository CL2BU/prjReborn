/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="ClientMessage.java" company="VorpeX">
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
package vorpex.beloco.message;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * Class used for the reading of messages.
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */
public class ClientMessage {

	/**
	 * The ID of the message
	 */
	private short iMessageID;

	/**
	 * The Buffer containing the rest of the message
	 */
	private ChannelBuffer cBuffer;

	/**
	 * Constructor for the Message.
	 * @param buffer Containing all information about the message
	 */
	public ClientMessage(final ChannelBuffer buffer) {

		// Read the message id
		this.iMessageID = buffer.readShort();

		// Set the rest of the message
		this.cBuffer = (buffer == null || buffer.readableBytes() == 0) ? ChannelBuffers.EMPTY_BUFFER : buffer;
	}

	/**
	 * Reads an int from the client message
	 * @return The int frmo the reading
	 */
	public int readInt() {
		return this.cBuffer.readInt();
	}

	/**
	 * Reads a short from the Client message
	 * @return The short from the message
	 */
	public final int readShort() {
		return this.cBuffer.readShort();
	}

	/**
	 * Reads a string from the client message
	 * @return The string from the message
	 */
	public final String readString() {
		return new String(this.cBuffer.readBytes(this.readShort()).toString(Charset.defaultCharset()));
	}

	/**
	 * Reads a boolean from the client message
	 * @return The boolean read from the message
	 */
	public final boolean readBoolean() {

		if (this.cBuffer.readableBytes() > 0 && this.cBuffer.readByte() == 1) {
			return true;
		}

		return false;
	}

	/**
	 * @return the iMessageID
	 */
	public final short getMessageID() {
		return iMessageID;
	}

	/**
	 * @param messageID the iMessageID to set
	 */
	public final void setMessageID(final short messageID) {
		this.iMessageID = messageID;
	}

	/**
	 * Returns a string value of the remaining buffer
	 * @return The string value of the remaining buffer
	 */
	public final String getBodyString() {
		return new String(this.cBuffer.toString(Charset.defaultCharset()));
	}

	/**
	 * Returns the current length of the remaining readable bytes.
	 * @return the current length of the remaining readable bytes.
	 */
	public final int getCurrentLength() {
		return this.cBuffer.readableBytes();
	}
}
