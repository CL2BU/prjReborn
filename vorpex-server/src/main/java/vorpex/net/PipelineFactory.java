/*******************************************************************************
 * <copyright file="Vorpex.java" company="VorpeX">
 *  Copyright (c) 2011-2012 All Right Reserved, http://vorpex.biz/
 *
 *  This source is subject to the "Don't Be A Dick" License.
 *  Please see the License.txt file for more information.
 *  You may not use this file except in compliance with the License.
 *
 *  THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 *  KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 *  PARTICULAR PURPOSE.
 *
 *  @author Dominic Gunn
 *  @email d.gunn@vorpex.biz
 *  @date 19-12-2012
 *  @summary
 ******************************************************************************/
package vorpex.net;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;

import vorpex.net.codex.Decoder;
import vorpex.net.codex.Encoder;

/**
 * Class used for the initialisation of ChannelPipeLines.
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */
public class PipelineFactory implements ChannelPipelineFactory {

	/**
	 * Function used to get the various pipes for
	 * Netty (handler, encoder, decoder, etc.).
	 * @throws Exception Error creating pipelines.
	 * @return The created pipeline for Netty
	 */
	@Override
	public final ChannelPipeline getPipeline() throws Exception {

		// Channel Pipeline to create handlers.
		ChannelPipeline cPipeline = Channels.pipeline();

		// Create the Handlers.
		cPipeline.addLast("handler", new ChannelHandler());
		cPipeline.addLast("decoder", new Decoder());
		cPipeline.addLast("encoder", new Encoder());
		cPipeline.addLast("pipelineExecutor", new ExecutionHandler(
				new OrderedMemoryAwareThreadPoolExecutor(
                200, 1048576, 1073741824, 100, TimeUnit.MILLISECONDS, Executors
                .defaultThreadFactory())));
		
		return cPipeline;
	}
}
