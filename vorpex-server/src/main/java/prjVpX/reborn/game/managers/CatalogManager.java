package prjVpX.reborn.game.managers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.managers.helpers.CatalogItem;
import prjVpX.reborn.game.managers.helpers.CatalogPage;


public class CatalogManager {
	public static List<CatalogPage> CatalogPages = new ArrayList<CatalogPage>();
	public static List<CatalogPage> CatalogSubs = new ArrayList<CatalogPage>();
	public static List<CatalogItem> CatalogItems = new ArrayList<CatalogItem>();
	public static HashMap<Integer, CatalogPage> CatalogPagesById = new HashMap<Integer, CatalogPage>();
	public static void AddItem(CatalogItem Item)
	{
			CatalogItems.add(Item);
	}
	public static void AddPage(CatalogPage Page)
	{
		if(Page.PageIsSub > -1)
			CatalogSubs.add(Page);
		else
			CatalogPages.add(Page);
		CatalogPagesById.put(Page.PageId, Page);
	}
	public static void ItemSerialize(Integer iId, Integer iPageId, String iName, Integer iCostCredits, Integer iCostPixels, Integer iCostQuest,
			Integer iAmount, String iFurniId, Integer iIsClub)
	{
		CatalogItem Item = new CatalogItem(iId, iPageId, iName, iCostCredits, iCostPixels, iCostQuest,
				iAmount, iFurniId, iIsClub);
		AddItem(Item);
	}
	public static void PageSerialize(String sType, int sPageId, int sPageIcon, int sPageColor, String sPageName,
			int sPageIsSub, String sPageExtra, String sPageHead, String sPageTeaser,
			String sPageText, String sPageOtherText, String sPageTextDetails, String sPageTextTeaser)
	{
		CatalogPage Page = new CatalogPage(sType, sPageId, sPageIcon, sPageColor, sPageName,
				sPageIsSub, sPageExtra, sPageHead, sPageTeaser,
				sPageText, sPageOtherText,  sPageTextDetails, sPageTextTeaser);
		AddPage(Page);
	}
	public static ResultSet getSQLItems() throws Exception
	{
		DatabaseClient ClientDB = new DatabaseClient(prjvpx.Database);
		return ClientDB.Query("SELECT * FROM catalog_items");
	}
	public static ResultSet getSQLPages() throws Exception
	{
		DatabaseClient ClientDB = new DatabaseClient(prjvpx.Database);
		return ClientDB.Query("SELECT * FROM catalog_pages");
	}
	public static void GenerateAllItems() throws Exception
	{
		ResultSet Set = getSQLItems();
		while(Set.next())
		{
			ItemSerialize(Set.getInt("id"),
					Set.getInt("page_id"),
					Set.getString("catalog_name"),
					Set.getInt("cost_credits"),
					Set.getInt("cost_pixels"),
					Set.getInt("cost_snow"),
					Set.getInt("amount"),
					Set.getString("item_ids"),
					Set.getInt("vip"));
		}
		Logger.log(CatalogManager.class, "Cached " + CatalogManager.CatalogItems.size() + " Catalog Items");
	}
	public static void GenerateAllPages() throws Exception
	{
		ResultSet Set = getSQLPages();
		while(Set.next())
		{
			PageSerialize(Set.getString("page_layout"),
					Set.getInt("id"),
					Set.getInt("icon_image"),
					Set.getInt("icon_color"),
					Set.getString("caption"),
					Set.getInt("parent_id"),
					Set.getString("page_layout"),
					Set.getString("page_headline"),
					Set.getString("page_teaser"),
					Set.getString("page_text1"),
					Set.getString("page_text2"),
					Set.getString("page_text_details"),
					Set.getString("page_text_teaser"));
		}
		Logger.log(CatalogManager.class, "Cached " + CatalogManager.CatalogPages.size() + " Catalog Pages and " + CatalogManager.CatalogSubs.size() + " Subpages");
	}
    public static List<CatalogPage> GetPagesForId(int Id)
    {
        List<CatalogPage> L = new ArrayList<CatalogPage>();
        for (CatalogPage Data : CatalogManager.CatalogSubs)
        {
            if (Data.PageIsSub == Id)
                L.add(Data);
        }
        return L;
    }
	public static List<CatalogItem> GetItemsForPageId(int pageId) {
		List<CatalogItem> I = new ArrayList<CatalogItem>();
		for(CatalogItem Item : CatalogManager.CatalogItems)
		{
			if(Item.iPageId == pageId)
				I.add(Item);
		}
		return I;
	}
}
