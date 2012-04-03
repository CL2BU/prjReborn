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
import java.util.Iterator;
import java.util.Map;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;
import prjVpX.reborn.cache.ServerEvents;
import prjVpX.reborn.database.DatabaseClient;
import prjVpX.reborn.game.Session;
import prjVpX.reborn.game.managers.CatalogManager;
import prjVpX.reborn.game.managers.helpers.CatalogPage;
import prjVpX.reborn.message.ClientMessage;
import prjVpX.reborn.message.IClientMessage;
import prjVpX.reborn.message.ServerMessage;


/**
 * Incoming Event
 * @author Dominic Gunn (d.gunn@prjvpx.biz)
 */
public class CatalogLoadPageMessageEvent implements IClientMessage {

	/* (non-Javadoc)
	 * @see prjvpx.beloco.message.IClientMessage#handle(prjvpx.beloco.game.Session, prjvpx.beloco.message.ClientMessage)
	 */
	
	public void handle(Session session, ClientMessage cMessage) {
		ServerMessage CataPage = new ServerMessage(ServerEvents.SendCatalogPage);
        CataPage.writeBoolean(true);
        CataPage.writeInt(0);
        CataPage.writeInt(0);
        CataPage.writeInt(-1);
        CataPage.writeBoolean(false);
        CataPage.writeBoolean(false);
        CataPage.writeInt(CatalogManager.CatalogPages.size());
        for(CatalogPage Page : CatalogManager.CatalogPages)
        {
            CataPage.writeBoolean(true);
            CataPage.writeInt(Page.PageColor);
            CataPage.writeInt(Page.PageIcon);
            CataPage.writeInt(Page.PageId);
            CataPage.writeString(Page.PageName);
            CataPage.writeInt(prjVpX.reborn.game.managers.CatalogManager.GetPagesForId(Page.PageId).size());
            for(CatalogPage sPage : CatalogManager.GetPagesForId(Page.PageId))
            {
                CataPage.writeBoolean(true);
                CataPage.writeInt(sPage.PageColor);
                CataPage.writeInt(sPage.PageIcon);
                CataPage.writeInt(sPage.PageId);
                CataPage.writeString(sPage.PageName);
                CataPage.writeInt(0);
            }
        }
        CataPage.writeBoolean(false); // new items shit!
        session.sendMessage(CataPage);
	}

}
