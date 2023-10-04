package gateway;
import java.io.*;
import java.net.*;
import com.google.protobuf.CodedInputStream;
import gateway.PessoaPB.Pessoa;

import javax.xml.crypto.Data;

/*
public class SimpleProtobufTCP_UDPServer extends Thread {

	private DatagramSocket socket;
	private ServerSocket listenSocket;
	private static byte[] buf = new byte[256];


	public SimpleProtobufTCP_UDPServer() throws IOException {
		socket = new DatagramSocket(4445);
		listenSocket = new ServerSocket(6789);
	}
		public static void main(String[] args) {

		try {
			SimpleProtobufTCP_UDPServer server = new SimpleProtobufTCP_UDPServer();

			while (true) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				server.socket.receive(packet);
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				String received = new String(packet.getData(), 0, packet.getLength());
				System.out.println(received);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}*/


public class SimpleProtobufTCP_UDPServer{
    public static void main(String[] args) throws IOException {
        DatagramSocket UdpSocket = new DatagramSocket(20000);
        System.out.println("Servidor is Online");
        byte[] bufS = new byte[100];
        while (true) {
            DatagramPacket inputReceive = new DatagramPacket(bufS, bufS.length);
            UdpSocket.receive(inputReceive);
            String textReceive = new String(inputReceive.getData());
            System.out.println(textReceive);
        }
    }
}

