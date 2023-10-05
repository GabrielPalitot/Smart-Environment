package gateway;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.AirConditioning;
import java.io.IOException;
import java.net.*;
import com.house.objects.AirConditioning;
import com.house.objects.Info;

public class ProtobuffAirConditioning {
    public enum StatusAirConditioning {
        TURNED_ON,
        TURNED_OFF,
        MALFUNCTION,
        STAND_BY
    }

    private StatusAirConditioning status;
    private int temperature;

    public ProtobuffAirConditioning() {
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

    public static void main(String[] args) throws IOException, InterruptedException {
        int portTCP = 10000;
        int portMultiCast = 15000;
        boolean connected = false;

        ProtobuffAirConditioning air = new ProtobuffAirConditioning();

        // Instancing the message
        Info airCond = Info.newBuilder()
                .setName("AirConditioning")
                .setIp("127.0.0.1")
                .setPort("10000")
                .build();

        while (!connected){
            try {
                // open the connection
                Socket socketAir = new Socket("localhost", portTCP);

                // Send Identification
                CodedOutputStream outAir = CodedOutputStream.newInstance(socketAir.getOutputStream());
                outAir.writeMessageNoTag(airCond);
                outAir.flush();

                connected = true;
                socketAir.close();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Server is Offline, please wait for it to come online");

                MulticastSocket airMultiSock = new MulticastSocket(portMultiCast);
                InetAddress group = InetAddress.getByName("228.0.0.8");
                airMultiSock.joinGroup(group);

                byte[] bufAir = new byte[100];
                DatagramPacket inputReceiveMsg = new DatagramPacket(bufAir, bufAir.length);

                // waiting a message of Gateway (server)
                while(true) {
                    airMultiSock.receive(inputReceiveMsg);
                    String textReceive = new String(inputReceiveMsg.getData(), 0, inputReceiveMsg.getLength());
                    System.out.println(textReceive);
                    if (textReceive.equalsIgnoreCase("Indentification")) {
                        Thread.sleep(5000);
                        break;
                    }
                }
            }
        }
    }
}


