/*******************************************************************************
 * /*******************************************************************************
 * <copyright file="SessionManager.java" company="VorpeX">
 * Copyright (c) 2011-2012 All Right Reserved, http://vorpex.biz/
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
 * @email d.gunn@vorpex.biz
 * @date 21-12-2012
 * @summary
 ******************************************************************************/
package vorpex.beloco.game;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;

import vorpex.logger.Logger;

/**
 * Class used for the handling all data to do with servers sessions
 * @author Dominic Gunn (d.gunn@vorpex.biz)
 */
public class SessionManager implements Runnable {

	/**
	 * Map of all the sessions connected to the server
	 */
    private Map<Channel, Session> sessions;

    /**
     * Integer containing the amount of connected clients
     */
    private int clientCount = 0;

    /**
     * Used in an attempt to drop any dead connections that exist.
     */
    private Thread clientMonitor;

    /**
     * Constructor for the Session Manager.
     */
    public SessionManager() {

    	this.sessions = new HashMap<Channel, Session>();

    	// Ensure the thread is set-up
    	clientMonitor = new Thread(this, "Session Monitor");
    	clientMonitor.setPriority(Thread.MIN_PRIORITY);

    	// Start the monitoring
    	this.startMonitor();
    }

    /**
     * Used to start the Monitoring of clients (so we can drop dead ones).
     */
    public final void startMonitor() {

    	this.clientMonitor.start();
    }

    /**
     * Used to stop (interrupt) the monitoring of clients (server-shutdown).
     */
    public final void stopMonitor() {

    	this.clientMonitor.interrupt();
    }

    /**
     * Adds a connection to the map of connected users.
     * @param channel The channel of the connecting user.
     */
    public final void addConnection(final Channel channel) {

    	// Set details of the latet client
    	Session session = new Session(channel, ++clientCount);

    	// Store the session
    	this.getSessions().put(channel, session);

    	// Log details of the connection
    	Logger.log(SessionManager.class, "Accepted Session (id: " + session.getSessionID() + ", ip: " + session.getIP() + ")");
    }

    /**
     * Used to get the session of a single user via their channel.
     * @param channel The channel we're searching for.
     * @return The session of the user
     */
    public final Session getSession(final Channel channel) {

    	// Attempt to save overhead
    	Session session;

    	// Grab the session and check against null
    	if((session = this.sessions.get(channel)) != null) {
    		return session;
    	}

    	return null;
    }

	/**
	 * @return the sessions
	 */
	public final Map<Channel, Session> getSessions() {
		return sessions;
	}

	/**
	 * @param mSessions Map of sessions to set
	 */
	public final void setSessions(final Map<Channel, Session> mSessions) {
		this.sessions = mSessions;
	}

	@Override
	public final void run() {

		// Run an infinite loop, with wait between each attempt
		while (true) {

			// TODO: Drop sessions
			try {
				Thread.sleep(60 * 1000);
			}
			catch (InterruptedException ex) {

				Logger.fatal(SessionManager.class, "Error Dropping ead connections");
				break;
			}
		}
	}
}
