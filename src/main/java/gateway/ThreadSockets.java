package gateway;

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

            socket.close();
        } catch (IOException ioe){
            System.out.println("Erro " + ioe.toString());
        }
    }
}
