package prjVpX.reborn.game.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.managers.helpers.Furniture;

public class FurnitureManager {
	public static List<Furniture> Furnitures = new ArrayList<Furniture>();
	public static HashMap<Integer, Furniture> FurniById = new HashMap<Integer, Furniture>();
	
	public static void AddFurni(Furniture Furni)
	{
		if(!Furnitures.contains(Furni))
			Furnitures.add(Furni);
		FurniById.put(Furni.iId, Furni);
		return;
	}
	
	public static void SerializeFurni(Integer iId, String iName, String iItemName, String iItemType,
			String iFurniType, Integer iSpriteId, Integer iWidth, Integer iLength, Integer iHeight, Integer iCanStack,
			Integer iCanSit, Integer iCanWalk, Integer iCanGift, Integer iCanTrade, Integer iCanRecycle, Integer iCanSell)
	{
		Furniture FurniItem = new Furniture(iId, iName, iItemName, iItemType,
			iFurniType, iSpriteId, iWidth, iLength, iHeight, iCanStack,
			iCanSit, iCanWalk, iCanGift, iCanTrade, iCanRecycle, iCanSell);
		AddFurni(FurniItem);
	}
	
	public static ResultSet getSQLFurni() throws ClassNotFoundException, SQLException, Exception
	{
		DatabaseClient DB = new DatabaseClient(prjvpx.Database);
		return DB.Query("SELECT * FROM furniture");
	}
	
	public static void GenerateAll() throws ClassNotFoundException, SQLException, Exception
	{
		ResultSet Set = getSQLFurni();
		while(Set.next())
		{
			SerializeFurni(Set.getInt("id"),
					Set.getString("public_name"),
					Set.getString("item_name"),
					Set.getString("type"),
					Set.getString("interaction_type"),
					Set.getInt("sprite_id"),
					Set.getInt("width"),
					Set.getInt("length"),
					Set.getInt("stack_height"),
					Set.getInt("can_stack"),
					Set.getInt("can_sit"),
					Set.getInt("is_walkable"),
					Set.getInt("allow_gift"),
					Set.getInt("allow_trade"),
					Set.getInt("allow_recycle"),
					Set.getInt("allow_marketplace_sell"));
		}
		Logger.log(FurnitureManager.class, "Cached " + FurnitureManager.Furnitures.size() + " Furni Items");
	}
}
