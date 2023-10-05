package gateway;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.Info;
import com.house.objects.User;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class ProtobuffUser {
    public ProtobuffUser(){

    }

    public static String stringFixBreakline(String string){
        string.replaceAll("\\\\n", "\\n ");
        return string;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int portTCP = 10000;
        int portMultiCast = 15000;
        boolean connected = false;

        ProtobuffUser usr = new ProtobuffUser();

        Info usrCond = Info.newBuilder()
                .setName("user")
                .setIp("127.0.0.1")
                .setPort("10000")
                .build();


        while (!connected){
            try {
                // open the connection
                Socket socketUsr = new Socket("localhost", portTCP);

                // Send Identification
                CodedOutputStream outUsr = CodedOutputStream.newInstance(socketUsr.getOutputStream());
                outUsr.writeMessageNoTag(usrCond);
                outUsr.flush();

                    CodedInputStream inUsr = CodedInputStream.newInstance(socketUsr.getInputStream());
                    int length = inUsr.readRawVarint32();
                    byte[] bytes = inUsr.readRawBytes(length);

                    User msgReceived = User.parseFrom(bytes);
                    String Recebimento = msgReceived.getComando();

                    System.out.println(Recebimento);

                socketUsr.close();
                connected=true;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Server is Offline, please wait for it to come online");

                MulticastSocket usrMultiSock = new MulticastSocket(portMultiCast);
                InetAddress group = InetAddress.getByName("228.0.0.8");
                usrMultiSock.joinGroup(group);

                byte[] bufUsr = new byte[100];
                DatagramPacket inputReceiveMsg = new DatagramPacket(bufUsr, bufUsr.length);

                // waiting a message of Gateway (server)
                while(true) {
                    usrMultiSock.receive(inputReceiveMsg);
                    String textReceive = new String(inputReceiveMsg.getData(), 0, inputReceiveMsg.getLength());
                    System.out.println(textReceive);
                    if (textReceive.equalsIgnoreCase("Indentification")) {
                        Thread.sleep(5000);
                        break;
                    }
                }
            }

        }

    }
}
