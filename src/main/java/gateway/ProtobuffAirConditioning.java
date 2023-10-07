package gateway;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.AirConditioning;
import java.io.IOException;
import java.net.*;
import com.house.objects.AirConditioning;
import com.house.objects.Info;

import static utilities.MulticastUtils.smartReconnect;

public class ProtobuffAirConditioning {

    private boolean turn;
    private AirConditioning.Status status;
    private int temperature;

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
        String hostMulticast = "228.0.0.8";
        boolean connected = false;

        ProtobuffAirConditioning air = new ProtobuffAirConditioning();
        air.setTurn(true);
        air.setStatus(AirConditioning.Status.TURNED_ON);
        air.setTemperature(20);

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
                smartReconnect(portMultiCast,hostMulticast);
            }
        }
    }
}


