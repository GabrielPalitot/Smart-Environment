package gateway;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.*;

import java.io.IOException;
import java.net.*;
import com.house.objects.AirConditioning;

import static utilities.CreateProtoMessage.createUserMessage;
import static utilities.ModificationClasses.attAirConditioning;
import static utilities.MulticastUtils.smartReconnect;
import static utilities.ProtoUtils.*;
import static utilities.ProtoUtils.receiveMessageProtoAirConditioning;

public class ProtobuffAirConditioning {

    private boolean turn;
    private AirConditioning.Status status;
    private int temperature;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProtobuffAirConditioning() {
    }

    public AirConditioning.Status getStatus() {
        return status;
    }

    public void setStatus(AirConditioning.Status status) {
        this.status = status;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isTurn(){
        return this.turn;
    }
    public void setTurn(boolean turn){
        this.turn = turn;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int portTCP = 11000;
        int portMultiCast = 15000;
        String AirConditioningMulticast = "228.0.0.8";


        boolean connected = false;
        boolean falseEver = false;

        ProtobuffAirConditioning AirConditioningOb = new ProtobuffAirConditioning();
        AirConditioningOb.setName("AirConditioning");
        AirConditioningOb.setTurn(true);
        AirConditioningOb.setStatus(AirConditioning.Status.TURNED_ON);
        AirConditioningOb.setTemperature(20);

        Info AirConditioningCond = Info.newBuilder()
                .setName("AirConditioning")
                .setIp("127.0.0.1")
                .setPort("10000")
                .build();

        while (!connected){
            try {
                // open the connection
                Socket socketAirConditioning = new Socket("localhost", portTCP);

                // Declaring in and out services
                CodedInputStream inAirConditioning = CodedInputStream.newInstance(socketAirConditioning.getInputStream());
                CodedOutputStream outAirConditioning = CodedOutputStream.newInstance(socketAirConditioning.getOutputStream());

                // Send the Identification
                sendMessageProtoInfo(outAirConditioning,AirConditioningCond);

                // receiving ack
                User ackAirConditioning = receiveMessageProtoUser(inAirConditioning);
                System.out.println(ackAirConditioning.getCommand());

                // Information between AirConditioning and Gateway
                while(true) {

                    // Send Information Status
                    AirConditioning AirConditioningMsgCond = AirConditioning.newBuilder()
                            .setName(AirConditioningOb.getName())
                            .setTurn(AirConditioningOb.isTurn())
                            .setStatus(AirConditioningOb.getStatus())
                            .setSettingTemperature(AirConditioningOb.getTemperature())
                            .build();
                    System.out.println(AirConditioningOb.getStatus().toString());
                    sendMessageProtoAirConditioning(outAirConditioning,AirConditioningMsgCond);

                    User receiveFromGateway = receiveMessageProtoUser(inAirConditioning);
                    System.out.println(receiveFromGateway.getCommand());


                    if (receiveFromGateway.getCommand().equals("mod")){
                        // sending acknow
                        User acknowSend = createUserMessage("acknow");
                        sendMessageProtoUser(outAirConditioning,acknowSend);

                        // receiving new information
                        AirConditioning receiveAirConditioning = receiveMessageProtoAirConditioning(inAirConditioning);
                        System.out.println("NOVO STATUS " + receiveAirConditioning.getStatus().toString());
                        System.out.println("NOVA TEMPERATURA " + String.valueOf(receiveAirConditioning.getSettingTemperature()));
                        attAirConditioning(AirConditioningOb,receiveAirConditioning);
                    }
                    else if (receiveFromGateway.getCommand().equals("not")) {
                        //System.out.println("entrando aqui");
                        Thread.sleep(5000);
                        //System.out.println("passei do sleep");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                smartReconnect(portMultiCast,AirConditioningMulticast);
            }
        }
    }
}


