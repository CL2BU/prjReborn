package prjVpX.reborn.message.events.rooms;

import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.HabboManager;
import prjVpX.reborn.game.managers.RoomManager;
import prjVpX.reborn.game.managers.helpers.Room;
import prjVpX.reborn.game.pathfinder.Coord;
import prjVpX.reborn.game.pathfinder.Pathfinder;
import prjVpX.reborn.game.pathfinder.Rotation;
import prjVpX.reborn.message.ClientMessage;
import prjVpX.reborn.message.IClientMessage;

public class SignMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	
	public void handle(Session session, ClientMessage cMessage) {
		int Sign = cMessage.readInt();
	    session.Habbo.UpdateState("sign " + Sign);
	    session.Habbo.UpdateState("");
	}
}
