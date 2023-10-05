package gateway;

import com.google.protobuf.CodedInputStream;
import com.house.objects.Info;

import java.io.*;
import java.net.*;
public class ThreadSockets extends Thread {
        private Socket socket;

        public ThreadSockets(Socket s){
            this.socket = s;
        }

    public void run(){
        //Print name of the Thread
        System.out.println(Thread.currentThread().getName());
        try{
            CodedInputStream inServer = CodedInputStream.newInstance(socket.getInputStream());
            int size = inServer.readRawVarint32();
            int oldLimit = inServer.pushLimit(size);
            Info inf = Info.parseFrom(inServer);
            inServer.popLimit(oldLimit);
            System.out.println(inf.toString());


            socket.close();
        } catch (IOException ioe){
            System.out.println("Erro " + ioe.toString());
        }
    }
}
