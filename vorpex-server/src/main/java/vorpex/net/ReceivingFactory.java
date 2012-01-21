package vorpex.net;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import vorpex.Vorpex;
import vorpex.crypto.Crypto;
import vorpex.logger.Logger;

		public class ReceivingFactory extends SimpleChannelUpstreamHandler
		{
			public Channel Socket;
		    public DataInputStream in;
		    public Crypto Crypto;
			public ServerMessage ClientMessage = new ServerMessage();
			
			public ReceivingFactory()
			{
				
			}
			
			@Override
			public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			{
			    Socket = e.getChannel();
			    Logger.log(ReceivingFactory.class, "Incoming connection from "+e.getChannel().getRemoteAddress().toString().split(":")[0].replace("/", ""));
			}
			
			@Override
			public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			{
			    Logger.log(ReceivingFactory.class, "Unexpected exception from downstream. "+e.getCause());
			    Socket.disconnect();
			}
			
			@Override
			public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			{
			    try
			    {
			        ChannelBuffer bufferin = (ChannelBuffer) e.getMessage();

			        ArrayList<DataInputStream> Messages = new ArrayList<DataInputStream>();

			        /**
			         * 4+2=6
			         */
			        if (bufferin.readableBytes() < 4)
			        { 
			        	Logger.log(ReceivingFactory.class, "Disconnected socket " + e.getChannel().getRemoteAddress().toString().split(":")[0].replace("/", ""));
			            Socket.disconnect();
			            return;
			        }
		            if(bufferin.getByte(0) == 60)
		            {
		                if(Crypto == null || Crypto.RC4Decode == null)
		                {
			            	byte Bytes[] = "<?xml version=\"1.0\"?>\r\n<!DOCTYPE cross-domain-policy SYSTEM \"/xml/dtds/cross-domain-policy.dtd\">\r\n<cross-domain-policy>\r\n   <allow-access-from domain=\"*\" to-ports=\"1-65535\" />\r\n</cross-domain-policy>\0".getBytes();
			                ChannelBuffer buffer2 = ChannelBuffers.wrappedBuffer(Bytes);
			                ChannelFuture future = Socket.write(buffer2);
			                future.addListener(ChannelFutureListener.CLOSE);
			                return;
		                }
		            }
			        do
			        {
				        int readerindex = bufferin.readerIndex();
		                byte[] byt = new byte[4];
		                bufferin.readBytes(byt);
		                if(Crypto != null && Crypto.RC4Decode != null)
		                {
		                	Crypto.RC4Decode.parse(byt);
		                }

		                int size = ((byt[0] & 0xff) << 24) + ((byt[1] & 0xff) << 16) + ((byt[2] & 0xff) << 8) + (byt[3] & 0xff);
		                
		                if (size < 2 || size > 1024 * 5)
		                {
		                	Socket.disconnect();
		                    return;
		                }

		                if (bufferin.readableBytes() < size)
		                {
		                	bufferin.readerIndex(readerindex);
		                    break;
		                }

		                byt = new byte[size];
		                bufferin.readBytes(byt);
		                if(Crypto != null && Crypto.RC4Decode != null)
		                {
		                	Crypto.RC4Decode.parse(byt);
		                }
		                
				        Messages.add(new DataInputStream(new ByteArrayInputStream(byt)));
			        }
			        while(bufferin.readableBytes() >= 1);
				    for(DataInputStream i : Messages)
				    {
						int hHeader = i.readShort();
						try
					    {
							if(Vorpex.Handler[hHeader]!=null)
						    {
								in = i;
						    	Vorpex.Handler[hHeader].ParseIn(this);
						        Logger.log(ReceivingFactory.class, "Received " + hHeader);
						    }
						    else
						    {
						       	Logger.log(ReceivingFactory.class, "Received " + hHeader);
						    }
					    }
					    catch(Exception ex)
					    { }
						i.close();
				    }
			    }
			    catch(Exception ex)
			    { }
			}
			public void ClosePacket(Channel Client, ServerMessage Message)
			{
				try
				{
		            if(!Message.Ready)
		            {
		                Message.Buffer.setInt(0, Message.Buffer.writerIndex() - 4);
		                Message.Ready = true;
		            }
		            Client.write(Message.Buffer);
		        }
		        catch(Exception e)
		        { }
	        }
		}