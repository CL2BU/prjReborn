package prjVpX.reborn.game.managers.helpers;

public class UserItem {
	public Integer iId;
	public Integer iRoomId;
	public Integer iFurniId;
	public String iExtraData;
	public Integer iX;
	public Integer iY;
	public Integer iW;
	public Integer iRot;
	public String iMoodInfo;
	public String iWiredInfo;
	public String iWiredItems;
	public UserItem(Integer iId, Integer iRoomId, Integer iFurniId, String iExtraData, Integer iX, Integer iY, Integer iW, Integer iRot,
			String iMoodInfo, String iWiredInfo, String iWiredItems)
	{
		this.iId = iId;
		this.iRoomId = iRoomId;
		this.iFurniId = iFurniId;
		this.iExtraData = iExtraData;
		this.iX = iX;
		this.iY = iY;
		this.iW = iW;
		this.iRot = iRot;
		this.iMoodInfo = iMoodInfo;
		this.iWiredInfo = iWiredInfo;
		this.iWiredItems = iWiredItems;
	}
}
