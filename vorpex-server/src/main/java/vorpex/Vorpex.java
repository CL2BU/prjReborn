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
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import vorpex.beloco.BeLoco;
import vorpex.logger.Logger;
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

	/**
	 * Used for anything related to 'BeLoco'. Including Session Management
	 */
	private static BeLoco beLoco = new BeLoco();

	/**
	 * Main method for the .jar.
	 * @param args Any parameters passed
	 */
    public static void main(final String[] args) {

    	// Show the pre-start up message.
    	preStartupMessage();

    	// Initial start-up declaration.
        Logger.log(Vorpex.class, "Attempting to setup the beloco server");

        // Start Hibernate
        setupHibernate();

        // Start Netty.
        setupNetty();

    	// Start-up complete
        Logger.log(Vorpex.class, "BeLoco Server successfully running");
    }

    /**
     * Used to set-up everything related to Netty.
     */
    private static void setupNetty() {

    	Logger.log(Vorpex.class, "Starting Channel Factory");
    	ChannelFactory tFactory = new NioServerSocketChannelFactory(Executors
                .newCachedThreadPool(), Executors.newCachedThreadPool(),
                Runtime.getRuntime().availableProcessors() * 2 + 1
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
    private static void setupHibernate() {
    	Logger.log(Vorpex.class, "Starting Hibernate Configuration");
        setSessionFactory(new org.hibernate.cfg.Configuration().configure().buildSessionFactory());
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

	/**
	 * @return the beLoco
	 */
	public static BeLoco getBeLoco() {
		return beLoco;
	}

	/**
	 * @param beloco the beLoco to set
	 */
	public static void setBeLoco(final BeLoco beloco) {
		Vorpex.beLoco = beloco;
	}
}
