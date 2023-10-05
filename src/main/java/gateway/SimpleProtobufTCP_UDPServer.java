package gateway;
import java.io.*;
import java.net.*;
public class SimpleProtobufTCP_UDPServer{

    // Realizes the discovery of smart Equipments in the network.
    public static void smartDiscovery(int portMultiCast){
        try {
            MulticastSocket socketMulti = new MulticastSocket(portMultiCast);
            InetAddress group = InetAddress.getByName("228.0.0.8"); // Mesmo endereço multicast
            String msgMulti = "Indentification";
            DatagramPacket packet = new DatagramPacket(msgMulti.getBytes(), msgMulti.length(), group, portMultiCast);
            socketMulti.send(packet);
            socketMulti.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        int portMultiCast = 15000;
        int portTCP = 10000;
        int portUDP = 20000;
        int portUserTCP = 11000;

        // Send Multicast Message
        smartDiscovery(portMultiCast);

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

