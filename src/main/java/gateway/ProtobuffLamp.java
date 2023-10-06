package gateway;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.Info;
import com.house.objects.Lamp;
import com.house.objects.User;

import java.io.IOException;
import java.net.*;

import static utilities.MulticastUtils.*;
import static utilities.ProtoUtils.*;


public class ProtobuffLamp {


    public enum Status {
        TURNED_ON,
        TURNED_OFF,
        MALFUNCTION,
    }
    private boolean turn;
    private Status status;

    public ProtobuffLamp() {
    }
    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int portTCP = 10000;
        int portMultiCast = 15000;
        String lampMulticast = "228.0.0.8";

        boolean connected = false;

        ProtobuffLamp lampOb = new ProtobuffLamp();
        lampOb.setTurn(true);
        lampOb.setStatus(Status.TURNED_ON);

        Info lampCond = Info.newBuilder()
                .setName("Lamp")
                .setIp("127.0.0.1")
                .setPort("10000")
                .build();

        while (!connected){
            try {
                // open the connection
                Socket socketLamp = new Socket("localhost", portTCP);

                // Send Identification
                CodedOutputStream outLamp = CodedOutputStream.newInstance(socketLamp.getOutputStream());
                outLamp.writeMessageNoTag(lampCond);
                outLamp.flush();

                CodedInputStream inLamp = CodedInputStream.newInstance(socketLamp.getInputStream());
                User msgReceived = receiveMessageProtoUser(inLamp);
                if (msgReceived.getCommand().equals("1")){
                    Lamp msgCond = Lamp.newBuilder()
                            .setTurn(lampOb.isTurn())
                            .setStatus(Lamp.Status.TURNED_ON)
                            .build();

                    sendMessageProtoLamp(outLamp,msgCond);
                }

                connected = true;
                socketLamp.close();
            } catch (IOException e) {
                e.printStackTrace();
                smartReconnect(portMultiCast,lampMulticast);
            }

        }
    }
}

/*
try {
                    Lamp lampcondcond = Lamp.newBuilder()
                                .setTurn(true)
                                .setStatus(Lamp.Status.TURNED_ON)
                                .build();

                    System.out.println("Estou esperando uma mensagem do servidor");
                    sendMessageProtoLamp(outLamp,lampcondcond);
                }catch (IOException e){
                        e.printStackTrace();
                        smartReconnect(portMultiCast,lampMulticast);
                }




 */


