package vorpex.net;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.buffer.ChannelBuffers;

public class ServerMessage {

	public ChannelBuffer Buffer;
	public ChannelBufferOutputStream Output;
	public boolean Ready;
	
	public ServerMessage()
	{
		Buffer = ChannelBuffers.buffer(4096);
		Output = new ChannelBufferOutputStream(Buffer);
	}
	
	public void InitHeader(int hHeader, ServerMessage Message)
	{
		try
		{
			Ready = false;
			Buffer.clear();
			Output.writeInt(0);
			Output.writeShort(hHeader);
		}
		catch(Exception e)
		{ }
	}
	
}
