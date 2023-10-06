package gateway;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.Info;
import com.house.objects.Lamp;
import com.house.objects.User;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;

import utilities.ProtoUtils;

import static utilities.ProtoUtils.*;


public class ThreadSockets extends Thread {
        private Socket socket;

        public ThreadSockets(Socket s){
            this.socket = s;
        }



    public void run(){
        HashMapUnique map = HashMapUnique.getInstance();
        LinkedBlockingQueueUnique queue = LinkedBlockingQueueUnique.getInstance();
        try{
            CodedInputStream inServer = CodedInputStream.newInstance(socket.getInputStream());
            Info inf = receiveMessageProtoInfo(inServer);

            // add online services
            map.addInMap(inf.getName(),(int) Thread.currentThread().getId());

            System.out.println(inf.toString());

            // USER SPACE Thread
            if (inf.getName().equals("user")){
                    String initialMessage = "Selecione o Servico Desejado:\n" +
                            "1.Lampadas" + (map.getFromMap("Lamp") != null ? "-Online\n" : "-Offline\n")
                            + "2.ArCondicionado" + (map.getFromMap("AirConditioning") != null ? "-Online\n" : "-Offline\n")
                            + "3.Janela" + (map.getFromMap("window") != null ? "-Online\n" : "-Offline\n")
                            + "4.Sensor" + (map.getFromMap("sensor") != null ? "-Online\n" : "-Offline\n");

                    User msgCond = User.newBuilder()
                            .setCommand(initialMessage)
                            .build();

                    //Send Onlines for user
                    CodedOutputStream outServer = CodedOutputStream.newInstance(socket.getOutputStream());
                    sendMessageProtoUser(outServer, msgCond);

                    // waiting
                    User msgReceivedFromUser = receiveMessageProtoUser(inServer);
                    switch (msgReceivedFromUser.getCommand()){
                        case "1":
                            System.out.println("Apertou 1");
                            queue.addInQueue("1");
                            System.out.println(queue.watchFromQueue());

                            Thread.sleep(10000);
                            System.out.println(queue.watchFromQueue());
                            String msgFromLamp = queue.getFromQueue();
                            msgCond = User.newBuilder()
                                    .setCommand(msgFromLamp)
                                    .build();
                            sendMessageProtoUser(outServer,msgCond);
                            break;
                        default:
                            System.out.println("errou");
                            break;
                    };
            }

            if (inf.getName().equals("Lamp")){

                    String command = queue.getFromQueue();
                    CodedOutputStream outServer = CodedOutputStream.newInstance(socket.getOutputStream());

                    User msgLampCond = User.newBuilder()
                                    .setCommand(command)
                                            .build();
                    sendMessageProtoUser(outServer,msgLampCond);
                    Lamp receivedLamp=receiveMessageProtoLamp(inServer);
                    queue.addInQueue(receivedLamp.getStatus().toString());

            }




            socket.close();

        } catch (Exception ioe){
            System.out.println("Erro " + ioe.toString());
        }
    }
}
