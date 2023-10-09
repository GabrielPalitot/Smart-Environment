package utilities;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastUtils {
    /**
     * Method that send a package named "Identification" via UDP for all devices in multicast net.
     * warning that the gateway is active.
     * @param portMultiCast the port of multicast
     * @param host the IP of multcast
     */
    public static void smartDiscovery(int portMultiCast, String host){
        try {
            MulticastSocket socketMulti = new MulticastSocket(portMultiCast);
            InetAddress group = InetAddress.getByName(host); // Same adress Multicast
            String msgMulti = "Indentification";
            DatagramPacket packet = new DatagramPacket(msgMulti.getBytes(), msgMulti.length(), group, portMultiCast);
            socketMulti.send(packet);
            socketMulti.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method that waiting for a server message called "Identification" for try to connect with server and
     * send a message with the name, port and IP of the object
     * @param portMulticast the port of multicast
     * @param host the IP of multicast
     * @throws IOException exception
     * @throws InterruptedException exception
     */

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
