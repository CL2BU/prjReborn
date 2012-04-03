/*******************************************************************************
 * <copyright file="prjvpx.java" company="VorpeX">
 *  Copyright (c) 2011-2012 All Right Reserved, http://prjvpx.biz/
 *
 *  This source is subject to the "Don't Be A Dick" License.
 *  Please see the License.txt file for more information.
 *  You may not use this file except in compliance with the License.
 *
 *  THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 *  KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 *  PARTICULAR PURPOSE.
 *
 *  @author Dominic Gunn
 *  @email d.gunn@prjvpx.biz
 *  @date 19-12-2012
 *  @summary
 ******************************************************************************/
package prjVpX;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.hibernate.SessionFactory;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import prjVpX.logger.Logger;
import prjVpX.net.PipelineFactory;
import prjVpX.reborn.Reborn;
import prjVpX.reborn.game.BannerGenerator;
import prjVpX.reborn.game.Crypto;
import prjVpX.reborn.game.MusConnection;
import prjVpX.reborn.game.managers.CatalogManager;
import prjVpX.reborn.game.managers.FurnitureManager;
import prjVpX.reborn.game.managers.HabboManager;
import prjVpX.reborn.game.managers.MessengerFriendsManager;
import prjVpX.reborn.game.managers.MyRoomsManager;
import prjVpX.reborn.game.managers.RoomManager;
import prjVpX.reborn.game.managers.RoomModelManager;
import prjVpX.reborn.game.managers.UserItemManager;


/**
 * Main class in the entire Emulation. 'Core' of the server.
 * @author Dominic (d.gunn@prjvpx.biz)
 */
public class prjvpx {

	/**
	 * Hibernate Session Factory.
	 */
	private static SessionFactory sessionFactory;
	public static String[] Database = new String[3];
    public static Map<String, BannerGenerator> BannersRC4 = new HashMap<String, BannerGenerator>(100);
	/**
	 * Used for anything related to 'Reborn'. Including Session Management
	 */
	private static Reborn iReborn = new Reborn();
	public static Crypto Crypto;
	public static MusConnection MUSConn;

	/**
	 * Main method for the .jar.
	 * @param args Any parameters passed
	 * @throws Exception 
	 */
    public static void main(final String[] args) throws Exception {

    	// Show the pre-start up message.
    	preStartupMessage();

    	// Initial start-up declaration.
        Logger.log(prjvpx.class, "Attempting to setup the Reborn server");

        // Start Hibernate
        setupHibernate();

        // Start Netty
        setupNetty();
        
        // Setup of Database variable's
        Database[0] = "testemu";
        Database[1] = "root";
        Database[2] = "hobbitex99";
        
        // Generate Cache
        CatalogManager.GenerateAllPages();
        CatalogManager.GenerateAllItems();
        MyRoomsManager.GenerateAll();
        RoomModelManager.GenerateAll();
        HabboManager.GenerateAll();
        MessengerFriendsManager.getAllFriendsFromSQL();
        RoomManager.GenerateAll();
        FurnitureManager.GenerateAll();
        UserItemManager.GenerateAll();
        
    	// Start-up complete
        Logger.log(prjvpx.class, "Reborn Server successfully running");
    }

    /**
     * Used to set-up everything related to Netty.
     */
    private static void setupNetty() {

    	Logger.log(prjvpx.class, "Starting Channel Factory");
    	ChannelFactory tFactory = new NioServerSocketChannelFactory(Executors
                .newCachedThreadPool(), Executors.newCachedThreadPool(),
                Runtime.getRuntime().availableProcessors() * 2 + 1
        );
 
    	Logger.log(prjvpx.class, "Starting Bootstrap set-up.");
    	ServerBootstrap tBootStrap = new ServerBootstrap(tFactory);
    	tBootStrap.setPipelineFactory(new PipelineFactory());

    	try {
    		Logger.log(prjvpx.class, "Starting Netty Socket-Binding");
    		tBootStrap.bind(new InetSocketAddress(30005));
    	} catch (ChannelException ce) {
            Logger.fatal(prjvpx.class, ce.getMessage());
            return;
        }
    }

    /**
     * Function used to start-up the hibernate session factory.
     */
    private static void setupHibernate() {
    	Logger.log(prjvpx.class, "Starting Hibernate Configuration");
        setSessionFactory(new org.hibernate.cfg.Configuration().configure().buildSessionFactory());
    }

    /**
     * Used for decoration of the console.
     */
    private static void preStartupMessage() {

    	System.out.println("=========================================");
    	System.out.println("==            Reborn SERVER            ==");
    	System.out.println("==          HTTP://VORPEX.BIZ          ==");
    	System.out.println("=========================================");
    }

	/**
	 * @return the sessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param factory the sessionFactory to set
	 */
	public static void setSessionFactory(final SessionFactory factory) {
		prjvpx.sessionFactory = factory;
	}

	/**
	 * @return the Reborn
	 */
	public static Reborn getReborn() {
		return iReborn;
	}

	/**
	 * @param Reborn the Reborn to set
	 */
	public static void setReborn(final Reborn Reborn) {
		prjvpx.iReborn = Reborn;
	}
	
	public static int GetTimestamp()
    {
        return (int)(System.currentTimeMillis() / 1000);
    }
}
