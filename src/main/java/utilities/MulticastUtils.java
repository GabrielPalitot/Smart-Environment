package utilities;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastUtils {
    public static void smartDiscovery(int portMultiCast, String host){
        try {
            MulticastSocket socketMulti = new MulticastSocket(portMultiCast);
            InetAddress group = InetAddress.getByName(host); // Mesmo endereço multicast
            String msgMulti = "Indentification";
            DatagramPacket packet = new DatagramPacket(msgMulti.getBytes(), msgMulti.length(), group, portMultiCast);
            socketMulti.send(packet);
            socketMulti.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    // 228.0.0.8
    public static void smartReconnect(int portMulticast, String host) throws IOException, InterruptedException {
        System.out.println("Server is Offline, please wait for it to come online");

        MulticastSocket usrMultiSock = new MulticastSocket(portMulticast);
        InetAddress group = InetAddress.getByName(host);
        usrMultiSock.joinGroup(group);

        byte[] bufUsr = new byte[100];
        DatagramPacket inputReceiveMsg = new DatagramPacket(bufUsr, bufUsr.length);

        // waiting a message of Gateway (server)
        while(true) {
            usrMultiSock.receive(inputReceiveMsg);
            String textReceive = new String(inputReceiveMsg.getData(), 0, inputReceiveMsg.getLength());
            System.out.println(textReceive);
            if (textReceive.equalsIgnoreCase("Indentification")) {
                usrMultiSock.leaveGroup(group);
                Thread.sleep(5000);
                break;
            }
        }
    }

}
