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

import static utilities.MulticastUtils.*;
import static utilities.ProtoUtils.*;

public class ProtobuffUser {
    public ProtobuffUser(){

    }



    public static void main(String[] args) throws IOException, InterruptedException {
        int portTCP = 10000;
        int portMultiCast = 15000;
        String hostMultiCast = "228.0.0.8";

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
                sendMessageProtoInfo(outUsr,usrCond);

                //Receive services online
                CodedInputStream inUsr = CodedInputStream.newInstance(socketUsr.getInputStream());
                User msgReceived = receiveMessageProtoUser(inUsr);
                String Recebimento = msgReceived.getComando();

                System.out.println(Recebimento);

                //End of Connection
                socketUsr.close();
                connected=true;
            } catch (IOException e) {
                e.printStackTrace();
                smartReconnect(portMultiCast,hostMultiCast);
            }

        }

    }
}
