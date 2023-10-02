package gateway;
import java.io.*;
import java.net.*;
import com.google.protobuf.CodedInputStream;
import gateway.PessoaPB.Pessoa;


public class SimpleProtobufTCP_UDPServer extends Thread {

	private DatagramSocket socket;
	private ServerSocket listenSocket;
	private static byte[] buf = new byte[256];


	public SimpleProtobufTCP_UDPServer() throws IOException {
		socket = new DatagramSocket(4445);
		listenSocket = new ServerSocket(6789);
	}
		public static void main(String argv[]) {

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
				//Socket connectionSocket = listenSocket.accept();
				//CodedInputStream in = CodedInputStream.newInstance(connectionSocket.getInputStream());
				//int size = in.readInt32();
				//Pessoa p = Pessoa.parseFrom(in);
				//System.out.println(p.toString());
				//System.out.println(size);

				//ThreadSockets thread = new ThreadSockets(connectionSocket);

				//thread.start();
				// Checar o uso de parseDelimitedFrom()
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}