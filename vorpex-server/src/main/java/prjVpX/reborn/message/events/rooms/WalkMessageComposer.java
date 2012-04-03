package prjVpX.reborn.message.events.rooms;

import java.util.List;

import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.HabboManager;
import prjVpX.reborn.game.pathfinder.Coord;
import prjVpX.reborn.game.pathfinder.Pathfinder;
import prjVpX.reborn.game.pathfinder.Rotation;

public class WalkMessageComposer {

	public static Runnable Compose(Pathfinder Finder, Session session, HabboManager RoomUser)
	{
		List<Coord> ListOfCoords = Finder.MakeFinder();
		if(!Finder.MakeFinder().iterator().hasNext() && RoomUser.IsWalking)
	    {
			if(RoomUser.GoalX != Finder.GoX || RoomUser.GoalY != Finder.GoY)
			{
		        return null;
			}
			if(!RoomUser.IsSit)
			{
				RoomUser.UpdateState("");
			}
	        RoomUser.IsWalking = false;
	        return null;
	    }
		else
		{
			for(Coord Next2 : ListOfCoords)
			{
				if((Next2 != null) ? true : false)
				{
					RoomUser.CurrentPos = Next2;
				    int NextX = Next2.X;
				    int NextY = Next2.Y;
				        
				    String UserZ;
				    int w = 0;
				    UserZ =  + w + "";
				    if (!UserZ.contains("."))
				         UserZ += ".0";
				    RoomUser.Rot = Rotation.Calculate(RoomUser.X, RoomUser.Y, NextX, NextY);
				    RoomUser.UpdateState("mv "+ NextX +","+ NextY +","+ UserZ);
				    RoomUser.X = NextX;
				    RoomUser.Y = NextY;
				    RoomUser.Z = UserZ;
					try {
						Thread.sleep(510);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					break;
				}
			}
			RoomUser.UpdateState("");
		}
		return null;
	}
}
