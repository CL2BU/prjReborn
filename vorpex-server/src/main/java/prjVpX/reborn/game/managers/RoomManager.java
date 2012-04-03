package prjVpX.reborn.game.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.managers.helpers.Room;
import prjVpX.reborn.message.ServerMessage;

public class RoomManager {
	public static List<Room> RoomList = new ArrayList<Room>();
	public static HashMap<Integer, Room> RoomByIdList = new HashMap<Integer, Room>();
	
	public RoomManager() throws SQLException, ClassNotFoundException, Exception
	{
		GenerateAll();
	}
	
	public static ResultSet getSQL() throws ClassNotFoundException, SQLException, Exception
	{
		DatabaseClient DB = new DatabaseClient(prjvpx.Database);
		return DB.Query("SELECT * FROM rooms");
	}
	
	public static void AddRoomToHashMapOrList(Room sRoom, Integer sId)
	{
		if(RoomList.contains(sRoom))
			return;
		RoomList.add(sRoom);
		if(RoomByIdList.containsValue(sRoom))
			return;
		RoomByIdList.put(sId, sRoom);
	}
	
	public static void CreateRoom(Integer sId, Integer sOwner, String sName, String sDesc, String sModel)
	{
		Room NewRoomForMap = new Room(sId, sOwner, sName, sDesc, sModel);
		AddRoomToHashMapOrList(NewRoomForMap, sId);
	}
	
	public static void GenerateAll() throws SQLException, ClassNotFoundException, Exception
	{
		ResultSet Set = getSQL();
		while(Set.next())
		{
			CreateRoom(Set.getInt("id"), Set.getInt("ownerid"), Set.getString("name"), Set.getString("description"), Set.getString("model"));
		}
		Logger.log(RoomManager.class, "Cached " + RoomManager.RoomList.size() + " Room's into the RoomManager");
	}
	
	public static List<Room> getRoomsForID(Integer ID)
	{
		List<Room> R = new ArrayList<Room>();
		for(Room tRoom : RoomList)
		{
			if(tRoom.Owner == ID)
			{
				R.add(tRoom);
			}
		}
		return R;
	}
	
	public static Room getRoomByID(Integer ID)
	{
		for(Room fRoom : RoomList)
		{
			if(fRoom.Id == ID)
			{
				return fRoom;
			}
		}
		return null;
	}
	
	public static void SendMessageToAllInRoom(Integer RoomId, ServerMessage sMessage)
	{
		if(getRoomByID(RoomId) != null)
		{
			for(HabboManager User : getRoomByID(RoomId).UsersInRoom)
			{
				User.sendBytes(sMessage);
			}
		}
	}
	
	public static void SendMessageToAllInRoomInsteadOfMe(Integer myId, Integer RoomId, ServerMessage sMessage)
	{
		if(getRoomByID(RoomId) != null)
		{
			for(HabboManager User : getRoomByID(RoomId).UsersInRoom)
			{
				if(User.Id == myId)
					return;
				User.sendBytes(sMessage);
			}
		}
	}
}
