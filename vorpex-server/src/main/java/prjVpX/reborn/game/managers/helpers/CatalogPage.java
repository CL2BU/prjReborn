package prjVpX.reborn.game.managers.helpers;

public class CatalogPage {

	public String PageType;
	public int PageId;
	public int PageIcon;
	public int PageColor;
	public String PageName;
	public int PageIsSub;
	public String PageExtra;
	public String PageHead;
	public String PageTeaser;
	public String PageText;
	public String PageOtherText;
	public String PageTextDetails;
	public String PageTextTeaser;
	public CatalogPage(String sType, int sPageId, int sPageIcon, int sPageColor, String sPageName,
			int sPageIsSub, String sPageExtra, String sPageHead, String sPageTeaser,
			String sPageText, String sPageOtherText, String sPageTextDetails, String sPageTextTeaser)
	{
		this.PageType = sType;
		this.PageId = sPageId;
		this.PageIcon = sPageIcon;
		this.PageColor = sPageColor;
		this.PageName = sPageName;
		this.PageIsSub = sPageIsSub;
		this.PageExtra = sPageExtra;
		this.PageHead = sPageHead;
		this.PageTeaser = sPageTeaser;
		this.PageText = sPageText;
		this.PageOtherText = sPageOtherText;
		this.PageTextDetails = sPageTextDetails;
		this.PageTextTeaser = sPageTextTeaser;
	}
}
