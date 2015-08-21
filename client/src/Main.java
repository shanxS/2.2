import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.debugger.ConsoleDebugger;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.*;
import java.util.Properties;

/**
 * Created by shanxS on 20/08/15.
 */


public class Main
{
    // [TODO] shanxS
    // why the fuck is it so difficlult to
    // add properties fiel to idea
    private final static String propFileName = "/Users/shanxS/workshop/play5_smack/2.2/client/res/config.properties";

    public static void main(String[] er)
    {
        try
        {
            Properties prop = new Properties();
            InputStream input = new FileInputStream(propFileName);
            prop.load(input);

            // samck debug
            System.setProperty("smack.debugEnabled", "true");
            ConsoleDebugger.printInterpreted = true;

            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword(prop.getProperty("adminName"), prop.getProperty("adminPassword"))
                    .setServiceName(prop.getProperty("serviceName"))
                    .setPort(5222)
                    .build();

            AbstractXMPPConnection conn1 = new XMPPTCPConnection(config);
            conn1.connect();

            Presence presence = new Presence(Presence.Type.unavailable);
            presence.setStatus("Gone fishing");
            conn1.sendStanza(presence);

            System.out.print(conn1.getHost());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
