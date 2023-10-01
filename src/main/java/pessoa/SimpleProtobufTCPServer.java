package pessoa;
import java.io.*;
import java.net.*;

import com.google.protobuf.CodedInputStream;

import pessoa.PessoaPB.Pessoa;

class SimpleProtobufTCPServer {
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
				// Checar o uso de parseDelimitedFrom()
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}