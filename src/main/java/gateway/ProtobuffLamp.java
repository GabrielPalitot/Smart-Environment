package gateway;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.Info;
import com.house.objects.Lamp;
import com.house.objects.User;

import java.io.IOException;
import java.net.*;

import static utilities.CreateProtoMessage.*;
import static utilities.MulticastUtils.*;
import static utilities.ProtoUtils.*;
import static utilities.ModificationClasses.*;


public class ProtobuffLamp {

    private String name;
    private boolean turn;
    private Lamp.Status status;

    public ProtobuffLamp() {
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public boolean isTurn() {
        return turn;
    }
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Lamp.Status getStatus() {
        return status;
    }

    public void setStatus(Lamp.Status status) {
        this.status = status;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int portTCP = 10000;
        int portMultiCast = 15000;
        String lampMulticast = "228.0.0.8";

        boolean connected = false;
        boolean falseEver = false;

        ProtobuffLamp lampOb = new ProtobuffLamp();
        lampOb.setName("Lamp");
        lampOb.setTurn(true);
        lampOb.setStatus(Lamp.Status.TURNED_ON);

        Info lampCond = Info.newBuilder()
                .setName("Lamp")
                .setIp("127.0.0.1")
                .setPort("10000")
                .build();

        while (!connected){
            try {
                // open the connection
                Socket socketLamp = new Socket("localhost", portTCP);

                // Declaring in and out services
                CodedInputStream inLamp = CodedInputStream.newInstance(socketLamp.getInputStream());
                CodedOutputStream outLamp = CodedOutputStream.newInstance(socketLamp.getOutputStream());

                // Send the Identification
                sendMessageProtoInfo(outLamp,lampCond);

                // receiving ack
                User ackLamp = receiveMessageProtoUser(inLamp);
                System.out.println(ackLamp.getCommand());

                // Information between Lamp and Gateway
                while(true) {
                    //System.out.println("aqui");
                    // Send Information Status
                    Lamp lampMsgCond = Lamp.newBuilder()
                            .setName(lampOb.getName())
                            .setTurn(lampOb.isTurn())
                            .setStatus(lampOb.getStatus())
                            .build();
                    System.out.println(lampOb.getStatus().toString());
                    sendMessageProtoLamp(outLamp,lampMsgCond);

                    User receiveFromGateway = receiveMessageProtoUser(inLamp);
                    System.out.println(receiveFromGateway.getCommand());


                    if (receiveFromGateway.getCommand().equals("mod")){
                        // sending acknow
                        User acknowSend = createUserMessage("acknow");
                        sendMessageProtoUser(outLamp,acknowSend);

                        // receiving new information
                        Lamp receiveLamp = receiveMessageProtoLamp(inLamp);
                        System.out.println("NOVO STATUS " + receiveLamp.getStatus().toString());
                        attLamp(lampOb,receiveLamp);
                    }
                    else if (receiveFromGateway.getCommand().equals("not")) {
                        //System.out.println("entrando aqui");
                        Thread.sleep(5000);
                        //System.out.println("passei do sleep");
                    }
                }

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


