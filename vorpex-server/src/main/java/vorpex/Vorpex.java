/*******************************************************************************
 * <copyright file="Vorpex.java" company="VorpeX">
 *  Copyright (c) 2011-2012 All Right Reserved, http://vorpex.biz/
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
 *  @email d.gunn@vorpex.biz
 *  @date 19-12-2012
 *  @summary
 ******************************************************************************/
package vorpex;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import vorpex.composers.InitComposer;
import vorpex.composers.LoginComposer;
import vorpex.logger.Logger;
import vorpex.net.PacketHandler;
import vorpex.net.PipelineFactory;

/**
 * Main class in the entire Emulation. 'Core' of the server.
 * @author Dominic (d.gunn@vorpex.biz)
 */
public class Vorpex {

	/**
	 * Hibernate Session Factory.
	 */
	private static SessionFactory sessionFactory;
	public static PacketHandler[] Handler;
	/**
	 * Main method for the .jar
	 * @param args Any parameters passed
	 */
    public static void main(final String[] args) {

    	// Show the pre-start up message.
    	preStartupMessage();

    	// Initial start-up declaration.
        Logger.log(Vorpex.class, "Attempting to setup the beloco server");

        // Start Netty.
        setupNetty();

        // Start Hibernate
        setupHibernate("localhost", "root", "vorpexdb", "hobbitex99");

        // Intialize Requests
        RequestsInit();
        
    	// Start-up complete
        Logger.log(Vorpex.class, "BeLoco Server successfully running");
    }

    /**
     * Requests set-up
     */
    private static void RequestsInit()
    {
    	try
    	{
    		Handler = new PacketHandler[10000];
    		RegisterRequest(1, new InitComposer());
    		RegisterRequest(2, new LoginComposer());
    	}
    	catch(Exception ex)
    	{ }
    }
    /**
     * Request Registrar
     */
    private static void RegisterRequest(int hHeader, PacketHandler hHndl)
    {
    	Handler[hHeader] = hHndl;
    }
    /**
     * Used to set-up everything related to Netty.
     */
    private static void setupNetty() {

    	Logger.log(Vorpex.class, "Starting Channel Factory");
    	ChannelFactory tFactory = new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()
        );
 
    	Logger.log(Vorpex.class, "Starting Bootstrap set-up.");
    	ServerBootstrap tBootStrap = new ServerBootstrap(tFactory);
    	tBootStrap.setPipelineFactory(new PipelineFactory());

    	try {
    		Logger.log(Vorpex.class, "Starting Netty Socket-Binding");
    		tBootStrap.bind(new InetSocketAddress(30000));
    	} catch (ChannelException ce) {
            Logger.fatal(Vorpex.class, ce.getMessage());
            getSessionFactory().close();
            return;
        }
    }

    /**
     * Function used to start-up the hibernate session factory.
     */
    private static void setupHibernate(String DATABASE_HOST, String User, String Database, String Password) {
		Configuration cConfig = new Configuration();
		
		/**
		 * This will be able for Hibernate 3.6.0 and 4.0.1
		 */
		/**
		 * Setting the main class
		 */
		cConfig.configure("hibernate.cfg.xml");
        /**
         * Starting Session Factory with the Properties up here
         */
        /**
         * Creating the ServiceRegistry
         */
        /**
         * Set the Factory for able to use
         */
        setSessionFactory(cConfig.configure().buildSessionFactory());
    }

    /**
     * Used for decoration of the console.
     */
    private static void preStartupMessage() {

    	System.out.println("=========================================");
    	System.out.println("==           BELOCO SERVER             ==");
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
		Vorpex.sessionFactory = factory;
	}
}
