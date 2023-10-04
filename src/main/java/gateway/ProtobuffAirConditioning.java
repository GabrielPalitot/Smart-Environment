package gateway;

import com.google.protobuf.CodedOutputStream;
import com.house.objects.AirConditioning;
import java.io.IOException;
import java.net.*;
import com.house.objects.AirConditioningInfo;
import com.house.objects.AirConditioning;

public class ProtobuffAirConditioning {
    public enum StatusAirConditioning {
        TURNED_ON,
        TURNED_OFF,
        MALFUNCTION,
        STAND_BY
    }
    private boolean connected;
    private StatusAirConditioning status;
    private int temperature;

    public ProtobuffAirConditioning(boolean connected, StatusAirConditioning status, int temperature) {
        this.connected = connected;
        this.status = status;
        this.temperature = temperature;
    }
    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public StatusAirConditioning getStatus() {
        return status;
    }

    public void setStatus(StatusAirConditioning status) {
        this.status = status;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public static void main(String[] args) throws IOException {
        int portTCP = 10000;
        boolean connected = false;
        ProtobuffAirConditioning air = new ProtobuffAirConditioning(true, StatusAirConditioning.STAND_BY, 20);

        // Instancing the message
        AirConditioningInfo airCond = AirConditioningInfo.newBuilder()
                .setName("AirConditioning123")
                .setIp("127.0.0.1")
                .setPort("10000")
                .build();
        while (!connected)
            try {
                // open the connection
                System.out.println("funfou");
                Socket socketAir = new Socket("localhost", portTCP);

                // Send Identification
                CodedOutputStream outAir = CodedOutputStream.newInstance(socketAir.getOutputStream());
                outAir.writeInt32NoTag(airCond.getSerializedSize());
                airCond.writeTo(outAir);
                outAir.flush();


                connected = true;
                socketAir.close();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Server is Offline, please wait for it to come online");

                MulticastSocket AirMultiSock = new MulticastSocket(portTCP);
                InetAddress group = InetAddress.getByName("228.0.0.8");
                AirMultiSock.joinGroup(group);

                byte[] bufAir = new byte[100];
                DatagramPacket inputReceiveMsg = new DatagramPacket(bufAir, bufAir.length);

                // waiting a message of Gateway (server)
                while(true) {
                    AirMultiSock.receive(inputReceiveMsg);
                    String textReceive = new String(inputReceiveMsg.getData(), 0, inputReceiveMsg.getLength());
                    System.out.println(textReceive);
                    if (textReceive.equalsIgnoreCase("Indentification")) {
                        break;
                    }
                }
            }
    }
}


