package gateway;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.Info;

import com.house.objects.Windows;
import com.house.objects.User;
import java.io.IOException;
import java.net.*;

import static utilities.MulticastUtils.smartReconnect;

import static utilities.CreateProtoMessage.*;
import static utilities.MulticastUtils.*;
import static utilities.ProtoUtils.*;
import static utilities.ModificationClasses.*;

public class ProtobuffWindows {

    private String name;
    private boolean turn;

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Windows.Status status;

    public ProtobuffWindows() {
    }

    public boolean isTurned() {
        return turn;
    }

    public void setTurned(boolean turn) {
        this.turn = turn;
    }

    public Windows.Status getStatus() {
        return status;
    }

    public void setStatus(Windows.Status status) {
        this.status = status;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int portTCP = 12000;
        int portMultiCast = 15000;
        String windowsMulticast = "228.0.0.8";

        boolean connected = false;
        boolean falseEver = false;

        ProtobuffWindows windowOb = new ProtobuffWindows();
        windowOb.setName("Window");
        windowOb.setTurn(true);
        windowOb.setStatus(Windows.Status.OPENED);

        Info windowCond = Info.newBuilder()
                .setName("Window")
                .setIp("127.0.0.1")
                .setPort("12000")
                .build();

        while (!connected){
            try {
                // open the connection
                Socket socketWindow = new Socket("localhost", portTCP);

                // Declaring in and out services
                CodedInputStream inWindow = CodedInputStream.newInstance(socketWindow.getInputStream());
                CodedOutputStream outWindow = CodedOutputStream.newInstance(socketWindow.getOutputStream());

                // Send the Identification
                sendMessageProtoInfo(outWindow,windowCond);

                // receiving ack
                User ackWindow = receiveMessageProtoUser(inWindow);
                System.out.println(ackWindow.getCommand());

                // Information between Window and Gateway
                while(true) {
                    //System.out.println("aqui");
                    // Send Information Status
                    Windows WindowMsgCond = Windows.newBuilder()
                            .setName(windowOb.getName())
                            .setTurn(windowOb.isTurn())
                            .setStatus(windowOb.getStatus())
                            .build();
                    System.out.println(windowOb.getStatus().toString());
                    sendMessageProtoWindow(outWindow,WindowMsgCond);

                    User receiveFromGateway = receiveMessageProtoUser(inWindow);
                    System.out.println(receiveFromGateway.getCommand());


                    if (receiveFromGateway.getCommand().equals("mod")){
                        // sending acknow
                        User acknowSend = createUserMessage("acknow");
                        sendMessageProtoUser(outWindow,acknowSend);

                        // receiving new information
                        Windows receiveWindow = receiveMessageProtoWindow(inWindow);
                        System.out.println("NOVO STATUS " + receiveWindow.getStatus().toString());
                        attWindow(windowOb,receiveWindow);
                    }
                    else if (receiveFromGateway.getCommand().equals("not")) {
                        //System.out.println("entrando aqui");
                        Thread.sleep(5000);
                        //System.out.println("passei do sleep");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                smartReconnect(portMultiCast,windowsMulticast);
            }
        }
    }
}


