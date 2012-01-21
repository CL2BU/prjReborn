package vorpex.composers;

import vorpex.net.PacketHandler;
import vorpex.net.ReceivingFactory;
import vorpex.net.ServerMessage;

public class InitComposer extends PacketHandler {
    @Override
    public void ParseIn(ReceivingFactory Main)
    {
    	try
    	{
	    	ServerMessage Message = new ServerMessage();
	    	Message.InitHeader(1, Message);
	    	Message.Output.writeInt(1);
	    	Message.Output.writeBoolean(true);
	    	Main.ClosePacket(Main.Socket, Message);
    	}
    	catch(Exception ex)
    	{ }
    }
}
