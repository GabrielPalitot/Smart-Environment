package gateway;
import java.io.*;
import java.net.*;

import com.google.protobuf.CodedInputStream;
import com.house.objects.AirConditioningInfo;
import com.house.objects.AirConditioning;
public class ServerTest extends Thread{


    public ServerTest(){

    }
    public static void smartDiscovery(int portTCP){
        try {
            MulticastSocket socketMulti = new MulticastSocket(portTCP);
            InetAddress group = InetAddress.getByName("228.0.0.8"); // Mesmo endereço multicast
            String msgMulti = "Indentification";
            DatagramPacket packet = new DatagramPacket(msgMulti.getBytes(), msgMulti.length(), group, portTCP);
            socketMulti.send(packet);
            socketMulti.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void run()//overrride thread method
    {
        try {
            int portTCP = 10000;
            ServerSocket listenSocket;
            listenSocket = new ServerSocket(portTCP);
            smartDiscovery(portTCP);
            Socket connectionSocket = listenSocket.accept();
            System.out.println("Connection accepted");
            CodedInputStream in = CodedInputStream.newInstance(connectionSocket.getInputStream());
            int size = in.readInt32();
            AirConditioningInfo airInfo = AirConditioningInfo.parseFrom(in);
            System.out.println(airInfo.toString());
            System.out.println(size);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String argv[]) {

            ServerTest thread = new ServerTest();
            thread.start();


    }

}
