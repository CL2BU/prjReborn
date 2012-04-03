package prjVpX.reborn.game.managers;

import prjVpX.reborn.game.managers.helpers.*;;

public class DanceManager {
	public static int GetDanceNum(Dance dance)
	{
	   	 if (dance == Dance.DANCE)
	            return 1;
	   	 
	   	if (dance == Dance.POGO_MOGO)
            return 2;
	   	
	   	if (dance == Dance.DUCK_FUNK)
            return 3;
	   	
	   	if (dance == Dance.THE_ROLLIE)
            return 4;
	   	
	   	if (dance == Dance.NONE)
            return 0;
	   	 
	   	return 0;
    }
	
	public static Dance GetDanceFromNum(int d)
	{
		if(d == 0)
			return Dance.DANCE;
		
	    if(d == 1)
		    return Dance.DANCE;
	    
	    if(d == 2)
			return Dance.POGO_MOGO;
		
	    if(d == 3)
		    return Dance.DUCK_FUNK;
	    
	    if(d == 4)
			return Dance.THE_ROLLIE;
	   	 
	   	return Dance.NONE;
    }
}
