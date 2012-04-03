package prjVpX.reborn.message.events.rooms;

import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.HabboManager;
import prjVpX.reborn.game.managers.RoomManager;
import prjVpX.reborn.game.managers.helpers.Room;
import prjVpX.reborn.game.pathfinder.Coord;
import prjVpX.reborn.game.pathfinder.Pathfinder;
import prjVpX.reborn.game.pathfinder.Rotation;
import prjVpX.reborn.message.ClientMessage;
import prjVpX.reborn.message.IClientMessage;
import prjVpX.reborn.message.ServerMessage;

public class TalkMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	
	public void handle(Session session, ClientMessage cMessage) {
		String ToSay = cMessage.readString();
		int SmileState = 0;
        
        if (ToSay.contains(":)") || ToSay.contains("=)") || ToSay.contains(":D") || ToSay.contains("=D"))
            SmileState = 1;

        if (ToSay.contains(":@") || ToSay.contains(">:(") || ToSay.contains(">:@"))
            SmileState = 2;

        if (ToSay.contains(":o") || ToSay.contains("D:"))
            SmileState = 3; 

        if (ToSay.contains(":(") || ToSay.contains(":'(") || ToSay.contains("=(") || ToSay.contains("='("))
            SmileState = 4;
		String Type = "talk";
		String Site = "";
	    int PacketId = ServerEvents.Shout;
	    if(Type == "talk")
	    	PacketId = ServerEvents.Talk;
	    else
	    	PacketId = ServerEvents.Shout;
	    
	    ServerMessage Talk = new ServerMessage(PacketId);
	    Talk.writeInt(session.getSessionID());
	    Talk.writeString(ToSay);
	    Talk.writeInt(SmileState);
	    Talk.writeInt(((!Site.equals("")) ? 1 : 0));
	    if(!Site.equals(""))
	    {
	    	Talk.writeString(Site.replace("http://", ""));
	    	Talk.writeString(Site);
	        Talk.writeBoolean(true);
	    }
	    Talk.writeInt(0);
	    RoomManager.SendMessageToAllInRoom(session.Habbo.CurrentRoom, Talk);
	}
}
