package gateway;

import com.house.objects.AirConditioning;
import java.io.IOException;
import java.net.*;


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


    public static void main(String[] args) {
        ProtobuffAirConditioning air = new ProtobuffAirConditioning(true, StatusAirConditioning.STAND_BY, 20);

    }
}


