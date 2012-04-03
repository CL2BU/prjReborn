package prjVpX.reborn.game.managers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.cache.SquareState;
import prjVpX.reborn.database.DatabaseClient;


public class RoomModelManager {
    public final int OPEN = 0;
    public final int CLOSED = 1;
	
	public int Id;
    public String ModelName;
    public int DoorX;
    public int DoorY;
    public int DoorZ;
    public int DoorDir;
    public String Items;
    public String HeightMap;
    public String Map;
    public String SerializeMap = "";
    public String SerializeRelativeMap = "";
    public int MapSizeX;
    public int MapSizeY;
    public int MapSize;
    public List<String> Lines;

    public int[][] SqState;
    public double[][] SqFloorHeight;
    public SquareState[][] Squares;
    public static HashMap<String, RoomModelManager> ModelsByName = new HashMap<String, RoomModelManager>();
	public static List<RoomModelManager> Models = new ArrayList<RoomModelManager>();
	
	public RoomModelManager(
			String Name,
		    int DoorX,
		    int DoorY,
		    int DoorZ,
		    int DoorDir,
		    String Items,
		    String HeightMap
	)
	{
		this.DoorDir = DoorDir;
		this.DoorX = (DoorX - 1);
		this.DoorY = (DoorY);
		this.DoorZ = DoorZ;
		this.Items = Items;
		this.HeightMap = HeightMap;
		this.Map = this.HeightMap;
		this.ModelName = Name;
        String[] tmpHeightmap = this.Map.split(""+(char)13);

        this.MapSizeX = tmpHeightmap[0].length();
        this.MapSizeY = tmpHeightmap.length;
        this.Lines = new ArrayList<String>();
        Lines = new ArrayList(Arrays.asList(this.Map.split(""+(char)13)));

        this.SqState = new int[MapSizeX][MapSizeY];
        this.SqFloorHeight = new double[MapSizeX][MapSizeY];
        this.Squares = new SquareState[MapSizeX][MapSizeY];
        

        for (int y = 0; y < MapSizeY; y++)
        {
            if (y > 0)
            {
                tmpHeightmap[y] = tmpHeightmap[y].substring(1);
            }

            for (int x = 0; x < MapSizeX; x++)
            {
                String Square = tmpHeightmap[y].substring(x,x + 1).trim().toLowerCase();

                if (Square.equals("x"))
                {
                	Squares[x][y] = SquareState.UNWALKABLE;
                    SqState[x][y] = CLOSED;
                }
                else if(isNumeric(Square))
                {
                	Squares[x][y] = SquareState.WALKABLE;
                    SqState[x][y] = OPEN;
                    SqFloorHeight[x][y] = Double.parseDouble(Square);
                    MapSize++;
                }

                if (this.DoorX == x && this.DoorY == y)
                {
                	Squares[x][y] = SquareState.WALKABLE;
                    SerializeRelativeMap += (int)DoorZ + "";
                }
                else
                {
                    if(Square.isEmpty() || Square == null)
                    {
                        continue;
                    }
                    SerializeRelativeMap += Square;
                }
            }
            SerializeRelativeMap += (char)13;
        }

        for(String MapLine: this.Map.split("\r\n"))
        {
            if(MapLine.isEmpty() || MapLine == null)
            {
                continue;
            }
            SerializeMap += MapLine + (char)13;
        }
	}
	public static void AddPage(RoomModelManager Page)
	{
			ModelsByName.put(Page.ModelName, Page);
			Models.add(Page);
	}
    
    private static boolean isNumeric(String cadena){
        try
        {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
	public static void RoomSerialize(
		    String Name,
		    int DoorX,
		    int DoorY,
		    int DoorZ,
		    int DoorDir,
		    String Items,
		    String HeightMap
		    
	)
	{
		RoomModelManager Page = new RoomModelManager(Name, DoorX, DoorY, DoorZ, DoorDir, Items, HeightMap);
		AddPage(Page);
	}
	public static ResultSet GetCacheMyRooms() throws Exception
	{
		DatabaseClient ClientDB = new DatabaseClient(prjvpx.Database);
		return ClientDB.Query("SELECT * FROM rooms_models");
	}
	public static void GenerateAll() throws Exception
	{
		ResultSet Set = GetCacheMyRooms();
		while(Set.next())
		{
			RoomSerialize(Set.getString("model_name"), Set.getInt("door_x"), Set.getInt("door_y"), Set.getInt("door_z"), Set.getInt("door_dir"), Set.getString("items"), Set.getString("heightmap"));
		}
		Logger.log(RoomModelManager.class, "Cached " + RoomModelManager.Models.size() + " Room models");
	}
}
