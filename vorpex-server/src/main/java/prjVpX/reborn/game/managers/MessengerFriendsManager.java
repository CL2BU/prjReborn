package prjVpX.reborn.game.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jboss.netty.channel.Channel;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.database.DatabaseClient;


public class MessengerFriendsManager {
	
	public int IdWho;
	public int IdFrom;
	public static List<MessengerFriendsManager> Habbos = new ArrayList<MessengerFriendsManager>();
	
	public MessengerFriendsManager(int sIdWho, int sIdFrom)
	{
		this.IdWho = sIdWho;
		this.IdFrom = sIdFrom;
	}
	public static void AddHabbo(MessengerFriendsManager Habbo)
	{
		if(Habbos.contains(Habbo))
			return;
		Habbos.add(Habbo);
	}
	public static void SerializeHabbo(int sIdWho, int sIdFrom)
	{
		MessengerFriendsManager Habbo = new MessengerFriendsManager(sIdWho, sIdFrom);
		AddHabbo(Habbo);
	}
	public static void getAllFriendsFromSQL()
	{
		try {
			DatabaseClient ClientDB = new DatabaseClient(prjvpx.Database);
			ResultSet Result = ClientDB.Query("SELECT * FROM messenger_friends");
			while(Result.next())
			{
				SerializeHabbo(Result.getInt("friend_id"), Result.getInt("user_id"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Logger.log(MessengerFriendsManager.class, "Cached " + Habbos.size() + " Messenger Friends");
	}
	public static List<MessengerFriendsManager> getFriendsForID(Integer ID)
	{
        List<MessengerFriendsManager> L = new ArrayList<MessengerFriendsManager>();
        for (MessengerFriendsManager Data : Habbos)
        {
            if (Data.IdFrom == ID)
                L.add(Data);
        }
        return L;
	}
}