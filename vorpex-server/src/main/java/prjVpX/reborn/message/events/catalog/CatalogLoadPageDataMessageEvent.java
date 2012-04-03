/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="InitMessageEvent.java" company="VorpeX">
 * Copyright (c) 2011-2012 All Right Reserved, http://prjvpx.biz/
 * 
 * This source is subject to the "Don't Be A Dick" License.
 * Please see the License.txt file for more information.
 * You may not use this file except in compliance with the License.
 * 
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 * 
 * @author Dominic Gunn
 * @email d.gunn@prjvpx.biz
 * @date 21-12-2012
 * @summary
 ******************************************************************************/
package prjVpX.reborn.message.events.catalog;


import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import prjVpX.reborn.game.managers.helpers.*;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.CatalogManager;
import prjVpX.reborn.game.managers.FurnitureManager;
import prjVpX.reborn.game.managers.helpers.CatalogPage;
import prjVpX.reborn.message.ClientMessage;
import prjVpX.reborn.message.IClientMessage;
import prjVpX.reborn.message.ServerMessage;


/**
 * Incoming Event
 * @author Dominic Gunn (d.gunn@prjvpx.biz)
 */
public class CatalogLoadPageDataMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	public void handle(Session session, ClientMessage cMessage) {

		Integer I = cMessage.readInt();
		CatalogPage PageData = CatalogManager.CatalogPagesById.get(I);
        ServerMessage Catalog = new ServerMessage(ServerEvents.SendCatalogPageData);
        Catalog.writeInt(PageData.PageId);
        if ("frontpage".equals(PageData.PageType)) {
            Catalog.writeString("frontpage3");
            Catalog.writeInt(3);
            Catalog.writeString("catalog_frontpage_headline");
            Catalog.writeString("topstory_xmas11_03");
            Catalog.writeString("");
            Catalog.writeInt(11);
            Catalog.writeString("Habbo.net.nz!");
            Catalog.writeString("Hey Habbo, you can see that?");
            Catalog.writeString("Wow! Nacha marica");
            Catalog.writeString("How to get Credits?");
            Catalog.writeString("You can get Habbo Credits via Prepaid Cards, Home Phone, Credit Card, Mobile, completing offers and more!\n" + (char) 10 + "" + (char) 10 + " To redeem your Habbo Credits, enter your voucher code below.");
            Catalog.writeString("Redeem your Voucher:");
            Catalog.writeString("Rares");
            Catalog.writeString("#FEFEFE");
            Catalog.writeString("#FEFEFE");
            Catalog.writeString("No Information!");
            Catalog.writeString("magic.credits");
        } else if ("petpage".equals(PageData.PageType)) {
        	Catalog.writeString("pets");
            Catalog.writeInt(2);
            Catalog.writeString(PageData.PageHead);
            Catalog.writeString(PageData.PageTeaser);
            Catalog.writeInt(4);
            Catalog.writeString(PageData.PageText);
            Catalog.writeString("Choose a name:");
            if (PageData.PageName.contains("Pollit"))
            {
                Catalog.writeString("");
                Catalog.writeString("");
            }
            else
            {
                if (PageData.PageName.contains("Tortug") || PageData.PageName.contains("Arañ") || PageData.PageName.contains("Rana") || PageData.PageName.contains("Drag") || PageData.PageName.contains("Mono"))
                {
                	Catalog.writeString("Choose a name:");
                	Catalog.writeString("Select a race:");
                }
                else
                {
                	Catalog.writeString("Take a color:");
                	Catalog.writeString("Select a race:");
                }
            }
        } else if("hs_saddles".equals(PageData.PageType))
        {
        	//Catalog.writeString("hs_saddles");
        	Catalog.writeString("petcustomization");
            Catalog.writeInt(2);
            Catalog.writeString(PageData.PageHead);
            Catalog.writeString(PageData.PageTeaser);
            Catalog.writeInt(3);
            Catalog.writeString(PageData.PageText);
            Catalog.writeString("");
            Catalog.writeString("");
        	//[0][10]hs_saddles[0]petcustomization[0][0][0][2][0]catalog_saddles_header1_es
        }else if ("guild_frontpage".equals(PageData.PageType)) {
            Catalog.writeString("guild_frontpage");
            Catalog.writeInt(2);
            Catalog.writeString(PageData.PageHead);
            Catalog.writeString(PageData.PageTeaser);
            Catalog.writeInt(3);
            Catalog.writeString("BetterWay Rulez.");
            Catalog.writeString("*YEAH*");
            Catalog.writeString("Nobody beat's this.");
        } else if("spacepage".equals(PageData.PageType))
        {
            Catalog.writeString("spaces_new");
            Catalog.writeInt(1);
            Catalog.writeString(PageData.PageHead);
            Catalog.writeInt(1);
            Catalog.writeString(PageData.PageText);
        } else if("musicshop".equals(PageData.PageType))
        {
            Catalog.writeString("soundmachine");
            Catalog.writeInt(2);
            Catalog.writeString(PageData.PageHead);
            Catalog.writeString(PageData.PageTeaser);
            Catalog.writeInt(2);
            Catalog.writeString(PageData.PageText);
            Catalog.writeString(PageData.PageTextDetails);
        } else if("club_buy".equals(PageData.PageType))
        {
            Catalog.writeString("vip_buy");
            Catalog.writeInt(2);
            Catalog.writeString("ctlg_buy_vip_header");
            Catalog.writeString("ctlg_buy_vip_picture");
            Catalog.writeInt(0);                
        } else if("instructions".equals(PageData.PageType))
        {
        	Catalog.writeString("recycler_info");
            Catalog.writeInt(2);
            Catalog.writeString(PageData.PageHead);
            Catalog.writeString(PageData.PageTeaser);
            Catalog.writeInt(3);
            Catalog.writeString(PageData.PageText);
            String TextDetails = PageData.PageTextDetails.replace("{separe}", ";:;");
            String[] Details = TextDetails.split(";:;");
            Catalog.writeString(Details[0].replace("|", '\n' + ""));
            Catalog.writeString(Details[1].replace("|", '\n' + "") + '\n' + '\n' + PageData.PageOtherText);
            Catalog.writeInt(0);                
        } else if("marketplace_offers".equals(PageData.PageType))
        {
        	Catalog.writeString("marketplace");
            Catalog.writeInt(1);
            Catalog.writeString(PageData.PageHead);
            Catalog.writeInt(0);                
            Catalog.writeInt(0);                
        } else if("marketplace_mine".equals(PageData.PageType))
        {
        	Catalog.writeString("marketplace_own_items");
        	Catalog.writeInt(1);
            Catalog.writeString(PageData.PageHead);
            Catalog.writeInt(0);                
            Catalog.writeInt(0);              
        } else {
            Catalog.writeString("default_3x3");
            Catalog.writeInt(3);
            Catalog.writeString(PageData.PageHead);
            Catalog.writeString(PageData.PageTeaser);
            Catalog.writeString(PageData.PageOtherText);
            Catalog.writeInt(3);
            Catalog.writeString(PageData.PageText);
            Catalog.writeString(PageData.PageTextDetails);
            Catalog.writeString(PageData.PageTextTeaser);
        }
        List<CatalogItem> Items = CatalogManager.GetItemsForPageId(PageData.PageId);
        if (PageData.PageId != 2) {
            Catalog.writeInt(Items.size()); // Count Items
            for(CatalogItem ItemData : Items)
            {
                Catalog.writeInt(ItemData.iId); // ItemId
                Catalog.writeString(ItemData.iName); // Name
                Catalog.writeInt(ItemData.iCostCredits); // Cost Credits
                Catalog.writeInt(ItemData.iCostPixels); // Activity points
                Catalog.writeInt(0); // quest shit
                Catalog.writeBoolean(false); // Can gift?
                Catalog.writeInt(ItemData.ItemIds.size()); // count of items (for deals)
                for(Integer ID : ItemData.ItemIds)
                {
                	Integer ItemId = ID;
                    if(!FurnitureManager.FurniById.containsKey(ItemId))
                    	continue;
                    Furniture furniData = FurnitureManager.FurniById.get(ItemId);
                    Catalog.writeString(furniData.iItemType); // type: s, i, h, etc
                    Catalog.writeInt(furniData.iSpriteId); // spriteid
                    if (ItemData.iName.contains("wallpaper"))
                        Catalog.writeString(ItemData.iName.split("_")[2]); // shit for wallpapers
                    else if(ItemData.iName.contains("floor"))
                        Catalog.writeString(ItemData.iName.split("_")[2]); // shit for wallpapers
                    else if(ItemData.iName.contains("landscape"))
                        Catalog.writeString(ItemData.iName.split("_")[2]); // shit for wallpapers
                    else
                        Catalog.writeString(ItemData.iExtraInfo + ""); // shit for music and other shit
                    int Amount = ItemData.iAmount;
                    Catalog.writeInt(Amount); // amount of items
                    Catalog.writeInt(-1); // separe
                }
                Catalog.writeInt(ItemData.iIsClub); // is club/vip/etc shit
            }
        } else {
            Catalog.writeInt(0);
        }
        Catalog.writeInt(-1); // Final Shit
        session.sendMessage(Catalog);
	}
	
}
