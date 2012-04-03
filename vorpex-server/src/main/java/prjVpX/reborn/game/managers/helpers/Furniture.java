package prjVpX.reborn.game.managers.helpers;
public class Furniture {
	
	public Integer iId;
	public String iName;
	public String iItemName;
	public String iItemType;
	public String iFurniType;
	public Integer iSpriteId;
	public Integer iWidth;
	public Integer iLength;
	public Integer iHeight;
	public Boolean iCanStack;
	public Boolean iCanSit;
	public Boolean iCanWalk;
	public Boolean iCanGift;
	public Boolean iCanTrade;
	public Boolean iCanRecycle;
	public Boolean iCanSell;
	
	public Furniture(Integer iId, String iName, String iItemName, String iItemType,
			String iFurniType, Integer iSpriteId, Integer iWidth, Integer iLength, Integer iHeight, Integer iCanStack,
			Integer iCanSit, Integer iCanWalk, Integer iCanGift, Integer iCanTrade, Integer iCanRecycle, Integer iCanSell)
	{
		this.iId = iId;
		this.iName = iName;
		this.iItemName = iItemName;
		this.iItemType = iItemType;
		this.iFurniType = iFurniType;
		this.iSpriteId = iSpriteId;
		this.iWidth = iWidth;
		this.iLength = iLength;
		this.iHeight = iHeight;
		this.iCanStack = iCanStack == 1;
		this.iCanSit = iCanSit == 1;
		this.iCanWalk = iCanWalk == 1;
		this.iCanGift = iCanGift == 1;
		this.iCanTrade = iCanTrade == 1;
		this.iCanRecycle = iCanRecycle == 1;
		this.iCanSell = iCanSell == 1;
	}
}
