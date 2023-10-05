package gateway;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.Info;

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
            if (inf.getName().equals("user")){
                /*CodedOutputStream outServer = CodedOutputStream.newInstance(socket.getOutputStream());
                outWindow.writeMessageNoTag(windowCond);
                outWindow.flush();*/
                System.out.println(map.getFromMap("Lamp"));
                while(true){
                    if (map.getFromMap("Lamp") != null){
                        int value = map.getFromMap("Lamp");
                        System.out.println("Há uma lampada");
                    }
                }

            }

            socket.close();
        } catch (IOException ioe){
            System.out.println("Erro " + ioe.toString());
        }
    }
}
