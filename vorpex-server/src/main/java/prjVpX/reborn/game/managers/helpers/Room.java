package prjVpX.reborn.game.managers.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;





import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.HabboManager;
import prjVpX.reborn.game.managers.RoomManager;
import prjVpX.reborn.game.managers.RoomModelManager;
import prjVpX.reborn.message.ServerMessage;

public class Room {
	public Integer Id;
	public Integer Owner;
	public String Name;
	public String Desc;
	public String Model;
	public boolean canWalkTrough = true;
	public List<HabboManager> UsersInRoom = new ArrayList<HabboManager>();
	
	public Room(Integer sId, Integer sOwner, String sName, String sDesc, String sModel)
	{
		this.Id = sId;
		this.Owner = sOwner;
		this.Name = sName;
		this.Desc = sDesc;
		this.Model = sModel;
	}
	
	public void AddUserToRoom(HabboManager User)
	{
		if(UsersInRoom.contains(User))
			return;
		UsersInRoom.add(User);
	}
	
	public RoomModelManager getRoomModel()
	{
		if(RoomModelManager.ModelsByName.containsKey(this.Model))
			return RoomModelManager.ModelsByName.get(this.Model);
		return null;
	}

    
	public void RemoveUserFromRoom(HabboManager User)
	{
		if(!UsersInRoom.contains(User))
			return;
		UsersInRoom.remove(User);
	}
	
    public boolean IsUserOnXY(int X, int Y)
    {
        for(HabboManager User : this.UsersInRoom)
        {
            if(User.X == X && User.Y == Y && !this.canWalkTrough)
                return true;
        }
        
        return false;
    }
	public void UserLeavesRoom(HabboManager User)
	{
		if(User != null)
		{
			ServerMessage GetOut = new ServerMessage(ServerEvents.GetOutOfRoom);
	        GetOut.writeString(User.cSession.getSessionID() + "");
	        RoomManager.SendMessageToAllInRoomInsteadOfMe(User.Id, this.Id, GetOut);
	        RemoveUserFromRoom(User);
		}
	}
}
