package gateway;
import java.io.*;
import java.net.*;


import com.google.protobuf.CodedInputStream;

import gateway.PessoaPB.Pessoa;

public class SimpleProtobufTCP_UDPServer {
	public static void main(String argv[]) {
		ServerSocket listenSocket;
		try {
			listenSocket = new ServerSocket(6789);
			while (true) {
				Socket connectionSocket = listenSocket.accept();
				CodedInputStream in = CodedInputStream.newInstance(connectionSocket.getInputStream());
				int size = in.readInt32();
				Pessoa p = Pessoa.parseFrom(in);
				System.out.println(p.toString());
				System.out.println(size);

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