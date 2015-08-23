import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.debugger.ConsoleDebugger;
import org.jivesoftware.smack.java7.Java7SmackInitializer;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import javax.security.sasl.Sasl;
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
                    .setPort(Integer.parseInt(prop.getProperty("portNumber")))
                    .build();

            AbstractXMPPConnection conn1 = new XMPPTCPConnection(config);
            conn1.connect();
            conn1.login("admin", "admin");

            ChatManager chatmanager = ChatManager.getInstanceFor(conn1);
            Chat newChat = chatmanager.createChat("shanxs@death3", new ChatMessageListener()
            {
                @Override
                public void processMessage(Chat chat, Message message)
                {
                    System.out.println("Received message: " + message);
                }
            });

            newChat.sendMessage("Howdy!");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
