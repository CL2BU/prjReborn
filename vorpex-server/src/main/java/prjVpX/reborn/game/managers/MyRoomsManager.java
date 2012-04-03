package prjVpX.reborn.game.managers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.database.DatabaseClient;


public class MyRoomsManager {
	
	public int Id;
    public String Name;
    public String Description;
    public String Type;
    public String Owner;
    public String Password;
    public int State;
    public int Category;
    public int UsersNow;
    public int UsersMax;
    public String ModelName;
    public String CCTs;
    public int Score;
    public List<String> Tags;
    public boolean AllowPets;
    public boolean AllowPetsEating;
    public boolean AllowWalkthrough;
    public boolean AllowRightsOverride;
    public boolean Hidewall;
    public String Wallpaper;
    public String Floor;
    public String Landscape;
	public static List<MyRoomsManager> Myrooms = new ArrayList<MyRoomsManager>();
	
	public MyRoomsManager(int Id,
			String Name,
			String Description,
		    String Type,
		    String Owner,
		    String Password,
		    int State,
		    int Category,
		    int UsersNow,
		    int UsersMax,
		    String ModelName,
		    String CCTs,
		    int Score,
		    List<String> Tags,
		    boolean AllowPets,
		    boolean AllowPetsEating,
		    boolean AllowWalkthrough,
		    boolean AllowRightsOverride,
		    boolean Hidewall,
		    String Wallpaper,
		    String Floor,
		    String Landscape
	)
	{
		this.Name = Name;
		this.Description = Description;
		this.Type = Type;
		this.Owner = Owner;
		this.Password = Password;
		this.State = State;
		this.Category = Category;
		this.UsersNow = UsersNow;
		this.UsersMax = UsersMax;
		this.ModelName = ModelName;
		this.CCTs = CCTs;
		this.Score = Score;
		this.Tags = Tags;
		this.AllowPets = AllowPets;
		this.AllowPetsEating = AllowPetsEating;
		this.AllowWalkthrough = AllowWalkthrough;
		this.AllowRightsOverride = AllowRightsOverride;
		this.Hidewall = Hidewall;
		this.Wallpaper = Wallpaper;
		this.Floor = Floor;
		this.Landscape = Landscape;
	}
	public static void AddPage(MyRoomsManager Page)
	{
			Myrooms.add(Page);
	}
	public static void RoomSerialize(int Id,
		    String Name,
		    String Description,
		    String Type,
		    String Owner,
		    String Password,
		    int State,
		    int Category,
		    int UsersNow,
		    int UsersMax,
		    String ModelName,
		    String CCTs,
		    int Score,
		    List<String> Tags,
		    boolean AllowPets,
		    boolean AllowPetsEating,
		    boolean AllowWalkthrough,
		    boolean AllowRightsOverride,
		    boolean Hidewall,
		    String Wallpaper,
		    String Floor,
		    String Landscape
	)
	{
		MyRoomsManager Page = new MyRoomsManager(Id, Name, Description, Type, Owner, Password, State, Category,
				UsersNow, UsersMax, ModelName, CCTs, Score, Tags, AllowPets,
				AllowPetsEating, AllowWalkthrough, AllowRightsOverride, Hidewall, Wallpaper, Floor, Landscape);
		AddPage(Page);
	}
	public static ResultSet GetCacheMyRooms() throws Exception
	{
		DatabaseClient ClientDB = new DatabaseClient(prjvpx.Database);
		return ClientDB.Query("SELECT * FROM rooms WHERE ownerid = '1'");
	}
	public static void GenerateAll() throws Exception
	{
		ResultSet Set = GetCacheMyRooms();
		List<String> Taggers = new ArrayList<String>();
		while(Set.next())
		{
			RoomSerialize(Set.getInt("id"), Set.getString("name"),
					Set.getString("description"), Set.getString("roomtype"),
					Set.getString("ownerid"), Set.getString("password"),
					Set.getInt("state"), Set.getInt("category"),
					Set.getInt("usersnow"), Set.getInt("maxusers"),
					Set.getString("model"), Set.getString("CCTs"),
					Set.getInt("score"), Taggers,
					Set.getBoolean("pets"), Set.getBoolean("petseat"),
					Set.getBoolean("walkonusers"), Set.getBoolean("rights"),
					Set.getBoolean("hidewalls"), Set.getString("wall"),
					Set.getString("floor"), Set.getString("landscape"));
		}
		Logger.log(MyRoomsManager.class, "Cached " + MyRoomsManager.Myrooms.size() + " User Rooms");
	}
}
