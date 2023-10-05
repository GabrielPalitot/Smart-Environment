package gateway;

import java.io.IOException;
import java.net.Socket;

import com.google.protobuf.CodedInputStream;
import com.house.objects.Info;

public class ThreadSocketsUser extends Thread {
    private Socket socket;

    public ThreadSocketsUser(Socket s){
        this.socket = s;
    }

    public void run(){
        //Print name of the Thread
        System.out.println(Thread.currentThread().getName());
        try{
            CodedInputStream inServer = CodedInputStream.newInstance(connectionSocket.getInputStream());
            int size = in.readInt32();
            Pessoa p = Pessoa.parseFrom(in);
            System.out.println(p.toString());
            socket.close();
        } catch (IOException ioe){
            System.out.println("Erro " + ioe.toString());
        }
    }
}
