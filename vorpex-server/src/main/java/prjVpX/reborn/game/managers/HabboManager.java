package prjVpX.reborn.game.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jboss.netty.channel.Channel;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.helpers.Dance;
import prjVpX.reborn.game.pathfinder.Coord;
import prjVpX.reborn.message.ServerMessage;


public class HabboManager {
	
	public int Id;
	public String Username;
	public String Motto;
	public String Look;
	public String Gender;
	public String Credits;
	public Channel sChannel;
	public int X;
	public int Y;
	public String Z;
	public int Rot;
	public int CurrentRoom;
	public String State;
	public int GoalX;
	public int GoalY;
	public Boolean IsWalking = false;
	public Boolean IsSit = false;
	public Session cSession;
	public Boolean isOnRoom = false;
	public Coord CurrentPos = null;
	public String BeenCreated;
	public Dance CurrentDance;
	public static List<HabboManager> Habbos = new ArrayList<HabboManager>();
	public static HashMap<Channel, HabboManager> HabbosByChannel = new HashMap<Channel, HabboManager>();
	
	public HabboManager(int sId, String sUsername, String sMotto, String sLook, String sGender, String sCredits, Channel sChannel, Session sSession)
	{
		this.Id = sId;
		this.Username = sUsername;
		this.Motto = sMotto;
		this.Look = sLook;
		this.Gender = sGender;
		this.Credits = sCredits;
		this.X = 0;
		this.Y = 0;
		this.Z = "0";
		this.Rot = 2;
		this.sChannel = sChannel;
		this.cSession = sSession;
	}
	public void UpdateState(String State)
	{
        this.State = "State";
        if(!this.Z.contains("."))
        {
        	this.Z += ".0";
        }
        ServerMessage UpdateState = new ServerMessage(ServerEvents.UpdateState);
        UpdateState.writeInt(1); // don't know
        UpdateState.writeInt(this.cSession.getSessionID());
        UpdateState.writeInt(this.X);
        UpdateState.writeInt(this.Y);
        UpdateState.writeString(this.Z);
        UpdateState.writeInt(this.Rot);
        UpdateState.writeInt(this.Rot);
        UpdateState.writeString("/flatctrl 4/" + State +"//");
        RoomManager.SendMessageToAllInRoom(this.CurrentRoom, UpdateState);
	}
	public static void AddHabbo(HabboManager Habbo, Channel sChannel)
	{
		if(Habbos.contains(Habbo))
			return;
		Habbos.add(Habbo);
		if(HabbosByChannel.containsKey(sChannel))
			return;
		HabbosByChannel.put(sChannel, Habbo);
	}
	public static void SerializeHabbo(Channel sChannel, int sId, String sUsername, String sMotto, String sLook, String sGender, String sCredits, Session sSession)
	{
		HabboManager Habbo = new HabboManager(sId, sUsername, sMotto, sLook, sGender, sCredits, sChannel, sSession);
		AddHabbo(Habbo, sChannel);
	}
	public static void SetDetailsForChannel(Channel sChannel, int sId, String sUsername, String sMotto, String sLook, String sGender, String sCredits, Session sSession, String sBeenCreated)
	{
		if(HabbosByChannel.containsKey(sChannel))
		{
			HabbosByChannel.get(sChannel).Id = sId;
			HabbosByChannel.get(sChannel).Username = sUsername;
			HabbosByChannel.get(sChannel).Motto = sMotto;
			HabbosByChannel.get(sChannel).Look = sLook;
			HabbosByChannel.get(sChannel).Gender = sGender;
			HabbosByChannel.get(sChannel).Credits = sCredits;
			HabbosByChannel.get(sChannel).sChannel = sChannel;
			HabbosByChannel.get(sChannel).cSession = sSession;
			HabbosByChannel.get(sChannel).BeenCreated = sBeenCreated;
		}
	}
	public static HabboManager getHabboByID(Integer ID)
	{
		for(HabboManager Habbo : Habbos)
		{
			if(Habbo.Id == ID)
			{
				return Habbo;
			}
		}
		return null;
	}
	public void sendBytes(ServerMessage Message)
	{
		this.cSession.sendMessage(Message);
	}
	public static void GenerateAll() throws ClassNotFoundException, SQLException, Exception
	{
		DatabaseClient Client = new DatabaseClient(prjvpx.Database);
		ResultSet UserRow = Client.Query("SELECT * FROM users_characters");
		while(UserRow.next())
		{
			SerializeHabbo(null, 
					Integer.parseInt(UserRow.getString("id")), UserRow.getString("username"),
					UserRow.getString("motto"), UserRow.getString("figure"),
					UserRow.getString("gender"), UserRow.getString("credits"), null);
		}
		Logger.log(HabboManager.class, "Cached " + HabboManager.Habbos.size() + " Habbo's into the HabboManager");
	}
	public static Boolean getIfIsOnline(Integer iId) throws Exception
	{
		DatabaseClient Client = new DatabaseClient(prjvpx.Database);
		ResultSet UserRow = Client.SecureQuery("SELECT * FROM users_characters WHERE id = ?", iId + "");
		if(UserRow.first())
		{
			if(UserRow.getInt("connected") == 1)
			{
				return true;
			}
			return false;
		}
		return false;
	}
}
