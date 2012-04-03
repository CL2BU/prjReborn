package prjVpX.reborn.game.managers.helpers;

import java.util.ArrayList;
import java.util.List;

public class CatalogItem {
	public Integer iId;
	public Integer iPageId;
	public String iName;
	public Integer iCostCredits;
	public Integer iCostPixels;
	public Integer iCostQuest;
	public Integer iAmount;
	public String iFurniId;
	public Integer iIsClub;
	public Integer iExtraAmounts;
	public Integer iExtraInfo;
	public List<Integer> ItemIds = new ArrayList<Integer>();
	
	public CatalogItem(Integer iId, Integer iPageId, String iName, Integer iCostCredits, Integer iCostPixels, Integer iCostQuest,
			Integer iAmount, String iFurniId, Integer iIsClub)
	{
		this.iId = iId;
		this.iPageId = iPageId;
		this.iName = iName;
		this.iCostCredits = iCostCredits;
		this.iCostPixels = iCostPixels;
		this.iCostQuest = iCostQuest;
		this.iAmount = iAmount;
		this.iFurniId = iFurniId;
		this.iIsClub = iIsClub;
		String[] ItemIdsIn = iFurniId.split(";");
		if(ItemIdsIn.length > 0)
		{
			for(String ItemId : ItemIdsIn)
			{
				if(this.ItemIds.contains(Integer.parseInt(ItemId)) || ItemId.equals(""))
					return;
				this.ItemIds.add(Integer.parseInt(ItemId));
			}
		}
		else
		{
			if(iFurniId.equals(""))
				return;
			this.ItemIds.add(Integer.parseInt(iFurniId));
		}
	}
}
