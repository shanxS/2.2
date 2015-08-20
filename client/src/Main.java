import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.debugger.ConsoleDebugger;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.server.ExportException;

/**
 * Created by shanxS on 20/08/15.
 */


public class Main
{
    public static void main(String[] er)
    {
        try
        {
            System.setProperty("smack.debugEnabled", "true");

            AbstractXMPPConnection conn1 = new XMPPTCPConnection("admin", "admin", "death3");
            ConsoleDebugger debugger = new ConsoleDebugger(conn1, new PrintWriter(System.out), new BufferedReader(new InputStreamReader(System.in)));
            ConsoleDebugger.printInterpreted = true;

            conn1.connect();

            Presence presence = new Presence(Presence.Type.unavailable);
            presence.setStatus("Gone fishing");
            // Send the packet (assume we have an XMPPConnection instance called "con").
            conn1.sendStanza(presence);


            System.out.print(conn1.getHost());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
