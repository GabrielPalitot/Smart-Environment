package pessoa;

import java.io.*;
import java.net.*;

import com.google.protobuf.CodedOutputStream;

import pessoa.PessoaPB.Pessoa;

class SimpleProtobufTCPClient {
	public static void main(String argv[]) {
		Socket clientSocket;

	    Pessoa person = Pessoa.newBuilder()
	    .setId(1)
	    .setName("Paulo")
	    .setEmail("pauloalr@ufc.br")
		.setFone("85999888777")
		.build();
		
		try {
			clientSocket = new Socket("localhost", 6789);
			
			CodedOutputStream out = CodedOutputStream.newInstance(clientSocket.getOutputStream());
			out.writeInt32NoTag(person.getSerializedSize());
			person.writeTo(out);

			out.flush();
			clientSocket.close();
			
			//Checar o uso de writeDelimitedTo()
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}