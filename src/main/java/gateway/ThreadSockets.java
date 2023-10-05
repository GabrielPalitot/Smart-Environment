package gateway;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.Info;
import com.house.objects.User;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;


public class ThreadSockets extends Thread {
        private Socket socket;

        public ThreadSockets(Socket s){
            this.socket = s;
        }



    public void run(){
        //Print name of the Thread
        System.out.println(Thread.currentThread().getId());

        HashMapUnique map = HashMapUnique.getInstance();

        try{
            CodedInputStream inServer = CodedInputStream.newInstance(socket.getInputStream());
            int size = inServer.readRawVarint32();
            int oldLimit = inServer.pushLimit(size);
            Info inf = Info.parseFrom(inServer);
            inServer.popLimit(oldLimit);

            // add online services
            map.addInMap(inf.getName(),(int) Thread.currentThread().getId());

            System.out.println(inf.toString());

            // USER SPACE
            if (inf.getName().equals("user")){
                String initialMessage = "Selecione o Servico Desejado:\n" +
                                        "1.Lampadas"+ (map.getFromMap("Lamp")!=null?" Online\n":" Offline\n")
                                        +"2.ArCondicionado" +(map.getFromMap("AirConditioning")!=null?" Online\n":" Offline\n")
                                        +"3.Janela" +(map.getFromMap("window")!=null?" Online\n":" Offline\n")
                                        +"4.Sensor"+ (map.getFromMap("sensor")!=null?" Online\n":" Offline\n");

                User msgCond = User.newBuilder()
                        .setComando(initialMessage)
                        .build();

                byte[] bytes = msgCond.toByteArray();
                CodedOutputStream outServer = CodedOutputStream.newInstance(socket.getOutputStream());
                outServer.writeByteArrayNoTag(bytes);
                outServer.flush();


            }

            socket.close();
        } catch (IOException ioe){
            System.out.println("Erro " + ioe.toString());
        }
    }
}
