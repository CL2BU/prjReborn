package prjVpX.reborn.game.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.managers.helpers.*;

public class UserItemManager {
	public static List<UserItem> UserItems = new ArrayList<UserItem>();
	public static HashMap<Integer, UserItem> UserItemById = new HashMap<Integer, UserItem>();
	
	public static void AddItem(UserItem Item)
	{
		if(UserItems.contains(Item))
			return;
		UserItems.add(Item);
		UserItemById.put(Item.iId, Item);
	}
	
	public static void SerializeUserItem(Integer iId, Integer iRoomId, Integer iFurniId, String iExtraData, Integer iX, Integer iY, Integer iW, Integer iRot,
			String iMoodInfo, String iWiredInfo, String iWiredItems)
	{
		UserItem Item = new UserItem(iId, iRoomId, iFurniId, iExtraData, iX, iY, iW, iRot,
			iMoodInfo, iWiredInfo, iWiredItems);
		AddItem(Item);
	}
	
	public static ResultSet getSQLUserItems() throws ClassNotFoundException, SQLException, Exception
	{
		DatabaseClient DB = new DatabaseClient(prjvpx.Database);
		return DB.Query("SELECT * FROM rooms_items");
	}
	
	public static void GenerateAll() throws ClassNotFoundException, SQLException, Exception
	{
		ResultSet Set = getSQLUserItems();
		while(Set.next())
		{
			SerializeUserItem(Set.getInt("id"), Set.getInt("room_id"), Set.getInt("furni_id"), Set.getString("extradata"), Set.getInt("x"), Set.getInt("y"),
					Set.getInt("w"), Set.getInt("rot"), Set.getString("moodlight"), Set.getString("wired_string"), Set.getString("wired_items"));
		}
		Logger.log(UserItemManager.class, "Cached " + UserItemManager.UserItems.size() + " User items");
	}
	
	public static List<Furniture> getItemsForHabboId(Integer Id)
	{
		List<Furniture> I = new ArrayList<Furniture>();
		for(UserItem UserI : UserItemManager.UserItems)
		{
			Room R = RoomManager.RoomByIdList.get(UserI.iRoomId);
			if(R.Owner == Id)
				I.add(FurnitureManager.FurniById.get(UserI.iFurniId));
		}
		return I;
	}
}
