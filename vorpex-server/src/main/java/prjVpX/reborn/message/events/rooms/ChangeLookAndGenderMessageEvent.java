package prjVpX.reborn.message.events.rooms;

import java.sql.SQLException;

import prjVpX.prjvpx;
import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.database.DatabaseClient;
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

public class ChangeLookAndGenderMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	
	public void handle(Session session, ClientMessage cMessage) throws ClassNotFoundException, SQLException, Exception {
	    String Gender = cMessage.readString();
	    String Look = cMessage.readString();
	    HabboManager CurrentUser = session.Habbo;
	    DatabaseClient Client = new DatabaseClient(prjvpx.Database);
		Client.Update("UPDATE users_characters SET figure = '" + Look + "' WHERE id = " + CurrentUser.Id);
	    Client.Update("UPDATE users_characters SET gender = '" + Gender + "' WHERE id = " + CurrentUser.Id);
	    CurrentUser.Look = Look;
	    CurrentUser.Gender = Gender;        
	    
	    ServerMessage UpdateInfo = new ServerMessage(ServerEvents.UpdateInfo);
	    UpdateInfo.writeInt(-1);
	    UpdateInfo.writeString(Look);
	    UpdateInfo.writeString(Gender.toLowerCase());
	    UpdateInfo.writeString(CurrentUser.Motto);
	    UpdateInfo.writeInt(525); // achv points
	    session.sendMessage(UpdateInfo);
	    
	    ServerMessage UpdateGInfo = new ServerMessage(ServerEvents.UpdateInfo);
	    UpdateGInfo.writeInt(CurrentUser.cSession.getSessionID());
	    UpdateGInfo.writeString(Look);
	    UpdateGInfo.writeString(Gender.toLowerCase());
	    UpdateGInfo.writeString(CurrentUser.Motto);
	    UpdateGInfo.writeInt(525); // achv points
	    RoomManager.SendMessageToAllInRoom(CurrentUser.CurrentRoom, UpdateGInfo);
	}
}
