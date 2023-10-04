package gateway;
import java.io.*;
import java.net.*;
public class SimpleProtobufTCP_UDPServer{

    // Realizes the discovery of smart Equipments in the network.
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
    public static void main(String[] args) throws IOException {

        int portTCP = 10000;

        // Send Multicast Message
        smartDiscovery(portTCP);


        // TCP Connections
        Thread threadTCP = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(portTCP);
                System.out.println("The port " + portTCP + " was open for TCP Connections");

                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client " + socket.getInetAddress().getHostAddress() + " connected via TCP.");

                    // Init a new thread to resolve TCP connections
                    ThreadSockets thread = new ThreadSockets(socket);
                    thread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        // UDP Connections
        Thread threadUDP = new Thread(() ->{
            DatagramSocket UdpSocket = new DatagramSocket(20000);
            System.out.println("Servidor is Online");
            byte[] bufS = new byte[100];
            while (true) {
                DatagramPacket inputReceive = new DatagramPacket(bufS, bufS.length);
                UdpSocket.receive(inputReceive);
                String textReceive = new String(inputReceive.getData());
                System.out.println(textReceive);
            }
        });


    }
}

