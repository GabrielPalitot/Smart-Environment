package gateway;
import java.io.*;
import java.net.*;

import static utilities.MulticastUtils.*;

public class SimpleProtobufTCP_UDPServer{

    public static void main(String[] args) throws IOException {
        int portTCP = 10000;
        int portUDP = 20000;
        int portMultiCast = 15000;
        String hostMultiCast = "228.0.0.8";

        // Send Multicast Message
        smartDiscovery(portMultiCast,hostMultiCast);

        // TCP Connections
        Thread threadTCPSmart = new Thread(() -> {
            try {
                ServerSocket smartServerSocket = new ServerSocket(portTCP);
                System.out.println("The port " + portTCP + " was open for TCP Connections");

                while (true) {
                    Socket smartSocket = smartServerSocket.accept();
                    System.out.println("Client " + smartSocket.getInetAddress().getHostAddress() + " connected via TCP.");

                    // Init a new thread to resolve TCP connections
                    ThreadSockets thread = new ThreadSockets(smartSocket);
                    thread.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        threadTCPSmart.start();

        /*
        // ProtobuffUser TCP Connection
        Thread threadTCPUser = new Thread(() -> {
            try {
                ServerSocket userServerSocket = new ServerSocket(portUserTCP);

                while (true) {
                    Socket socket = userServerSocket.accept();
                    System.out.println("Client " + socket.getInetAddress().getHostAddress() + " connected via TCP.");

                    // Init a new thread to resolve TCP connections
                    ThreadSocketsUser thread = new ThreadSocketsUser(socket);
                    thread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        // UDP Connections
        Thread threadUDP = new Thread(() ->{
            DatagramSocket UdpSocket = null;
            try {
                UdpSocket = new DatagramSocket(portUDP);
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Servidor is Online");
            byte[] bufS = new byte[100];
            while (true) {
                DatagramPacket inputReceive = new DatagramPacket(bufS, bufS.length);
                try {
                    UdpSocket.receive(inputReceive);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String textReceive = new String(inputReceive.getData());
                System.out.println(textReceive);
            }
        });

            */
    }
}

