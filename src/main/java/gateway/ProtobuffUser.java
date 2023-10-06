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
import java.util.Scanner;

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
                Scanner scanner = new Scanner(System.in);

                Socket socketUsr = new Socket("localhost", portTCP);

                // Send Identification
                CodedOutputStream outUsr = CodedOutputStream.newInstance(socketUsr.getOutputStream());
                sendMessageProtoInfo(outUsr,usrCond);
                boolean exit = true;


                //Receive services online
                CodedInputStream inUsr = CodedInputStream.newInstance(socketUsr.getInputStream());
                User msgReceived = receiveMessageProtoUser(inUsr);
                String Recebimento = msgReceived.getCommand();
                System.out.println(Recebimento);

                String inputUser = scanner.nextLine();
                User msgCond = User.newBuilder()
                            .setCommand(inputUser)
                            .build();

                sendMessageProtoUser(outUsr,msgCond);

                User msgReceivedFromServer = receiveMessageProtoUser(inUsr);
                Recebimento = msgReceivedFromServer.getCommand();
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
