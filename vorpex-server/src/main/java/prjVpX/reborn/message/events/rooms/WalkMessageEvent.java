package prjVpX.reborn.message.events.rooms;

import java.util.List;

import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.HabboManager;
import prjVpX.reborn.game.managers.RoomManager;
import prjVpX.reborn.game.managers.helpers.Room;
import prjVpX.reborn.game.pathfinder.Coord;
import prjVpX.reborn.game.pathfinder.Pathfinder;
import prjVpX.reborn.game.pathfinder.Rotation;
import prjVpX.reborn.message.ClientMessage;
import prjVpX.reborn.message.IClientMessage;

public class WalkMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	
	public void handle(Session session, ClientMessage cMessage) {

        	int GoalX = cMessage.readInt();
        	int GoalY = cMessage.readInt();
        	HabboManager RoomUser = session.Habbo;
        	Room R = RoomManager.getRoomByID(RoomUser.CurrentRoom);
	        RoomUser.GoalX = GoalX;
	        RoomUser.GoalY = GoalY;
	        RoomUser.IsWalking = true;
	        RoomUser.IsSit = false;
	        Pathfinder Finder = new Pathfinder(R, RoomUser);
	        WalkMessageComposer.Compose(Finder, session, RoomUser);
	}
}
