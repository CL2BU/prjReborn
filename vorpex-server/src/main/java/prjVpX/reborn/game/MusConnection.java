package prjVpX.reborn.game;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.jboss.netty.channel.Channel;

import prjVpX.prjvpx;
import prjVpX.logger.Logger;

/**
 *
 * @author M
 */
public class MusConnection extends Thread
{
    private ServerSocket Mus;
    public static Session session;

    public MusConnection(int MusPort, Session sSession)
    {
        try
        {
            Mus = new ServerSocket(MusPort);
            session = sSession;
        } catch (IOException ex) {

        }
    }
    public static void SetSession(Session mSession)
    {
    	MusConnection.session = mSession;
    	Logger.log(MusConnection.class, "Putted new session: " + mSession.getSessionID());
    }
    
    public String MsgId;
    public void run()
    {
        DataInputStream in;
        PrintWriter out;
        byte[] msg = new byte[23];

        int len;

        while (!Mus.isClosed())
        {
            try
            {
                Socket Client = null;
                try
                {
                    Client = Mus.accept();

                    try
                    {
                        in = new DataInputStream(Client.getInputStream());
                        out = new PrintWriter(Client.getOutputStream(), true);
                    }
                    catch (Exception ex)
                    {
                        continue;
                    }

                    in.readFully(msg);

                    MsgId = new String(msg);
                    Logger.log(MusConnection.class, "Ticket incoming: " + MsgId);
                }
                catch (Exception ex) // exception in .close
                {

                }
            }
            catch(Exception e)
            {}
        }
    }
}