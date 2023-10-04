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


    public static void main(String[] args) throws UnknownHostException {
        ProtobuffAirConditioning air = new ProtobuffAirConditioning(true, StatusAirConditioning.STAND_BY, 20);

        // Server is offline
        boolean connectedServer = false;

        // Instancing the message
        AirConditioningInfo airCond = AirConditioningInfo.newBuilder()
                .setName("AirConditioning123")
                .setIp("127.0.0.1")
                .setPort("10000")
                .build();

        //while (!connectedServer){
            try{
                // open the connection
                Socket socketAir = new Socket("localhost", 10000);
                CodedOutputStream outAir = CodedOutputStream.newInstance(socketAir.getOutputStream());
                outAir.writeInt32NoTag(airCond.getSerializedSize());
                airCond.writeTo(outAir);

                outAir.flush();
                socketAir.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        //}


    }
}


