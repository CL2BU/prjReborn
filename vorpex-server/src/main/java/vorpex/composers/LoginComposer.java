package vorpex.composers;

import vorpex.net.PacketHandler;
import vorpex.net.ReceivingFactory;
import vorpex.net.ServerMessage;

public class LoginComposer extends PacketHandler {
    @Override
    public void ParseIn(ReceivingFactory Main)
    {
    	try
    	{
	    	ServerMessage Message = new ServerMessage();
	    	Message.InitHeader(2, Message);
	    	Message.Output.writeBoolean(true);
	    	Message.Output.writeInt(1);
	    	Main.ClosePacket(Main.Socket, Message);
    	}
    	catch(Exception ex)
    	{ }
    }
}
