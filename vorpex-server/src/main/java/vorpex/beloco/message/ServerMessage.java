/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="ServerMessage.java" company="VorpeX">
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
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * Class used for the constructing of messages.
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */
public class ServerMessage {

	/**
	 * The message id of the server message.
	 */
	private short iMessageID;
	
	/**
	 * The body of the server message
	 */
	private ChannelBuffer cBuffer;

	/**
	 * Constructor for the Server Message
	 * @param id The id of the Message being wrote
	 */
	public ServerMessage(final short id) {

		this.iMessageID = id;
		this.cBuffer = ChannelBuffers.dynamicBuffer(1024);
	}

	/**
	 * Writes a byte to the cBuffer
	 * @param b The byte to be wrote to the cBuffer
	 */
	public final void writeByte(final int b) {
		this.cBuffer.writeByte(b);
	}

	/**
	 * Writes a char to the cBuffer
	 * @param b The char to be wrote to the cBuffer
	 */
	public final void writeChar(final char b) {

		this.cBuffer.writeChar(b);
	}

	/**
	 * Writes bytes to the cBuffer
	 * @param bytes The Bytes to be wrote to the cBuffer
	 */
	public final void writeBytes(final byte[] bytes) {

		if (bytes.length == 0 || bytes == null) { return; }

		this.cBuffer.writeBytes(bytes);
	}

	/**
	 * Writes a string to the cBuffer
	 * @param str The String to be wrote to the cBuffer
	 */
	public final void writeString(final String str) {

		if (str == null || str.length() == 0) {
			this.cBuffer.writeShort(0);
			return;
		}

		this.cBuffer.writeShort(str.length());
		this.cBuffer.writeBytes(str.getBytes());
	}

	/**
	 * Used to rite an integer to the cBuffer
	 * @param i The Integer to be wrote
	 */
	public final void writeInt(final int i) {

		this.cBuffer.writeInt(i);
	}

	/**
	 * Used to write a short to the cBuffer
	 * @param i The Short to be wrote
	 */
	public final void writeShort(final short i) {

		this.cBuffer.writeShort(i);
	}

	/**
	 * Used to write a double to the cBuffer
	 * @param i The Double to be wrote
	 */
	public final void writeDouble(final double i) {

		this.cBuffer.writeDouble(i);
	}

	/**
	 * Used to write a boolean to the cBuffer
	 * @param b The Boolean to be wrote
	 */
	public final void writeBoolean(final boolean b) {

		this.cBuffer.writeByte(b ? 1 : 0);
	}

	/**
	 * Compiles the entire message into a writable Buffer message
	 * @return the entire message in a writable Buffer message
	 */
	public final ChannelBuffer getMessageData() {

		// Init the Buffer to hold the message
		ChannelBuffer data = ChannelBuffers.buffer(this.cBuffer.writerIndex() + 6);

		// Write the length
		data.writeInt(this.cBuffer.writerIndex() + 2);

		// Write Msg ID
		data.writeShort(this.iMessageID);

		// Write Msg Body
		data.writeBytes(this.cBuffer.array(), 0, this.cBuffer.writerIndex());
		
		// Return entire msg.
		return data;
	}

	/**
	 * @return the iMessageID
	 */
	public final short getMessageID() {
		return iMessageID;
	}

	/**
	 * Returns a string value of the current buffer
	 * @return The string value of the current buffer
	 */
	public final String getBodyString() {
		return new String(this.cBuffer.toString(Charset.defaultCharset()));
	}

	/**
	 * Returns the current length of the buffer.
	 * @return the current length of the buffer.
	 */
	public final int getCurrentLength() {
		return this.cBuffer.readableBytes();
	}
}
