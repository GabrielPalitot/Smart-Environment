package gateway;

import com.house.objects.Lamp;
import java.io.IOException;
import java.net.*;


public class ProtobuffLamp {
    public enum StatusLamp {
        TURNED_ON,
        TURNED_OFF,
        MALFUNCTION,
    }
    private boolean connected;
    private StatusLamp status;

    public ProtobuffLamp(boolean connected, StatusLamp status) {
        this.connected = connected;
        this.status = status;
    }
    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public StatusLamp getStatus() {
        return status;
    }

    public void setStatus(StatusLamp status) {
        this.status = status;
    }

    public static void main(String[] args) {
        ProtobuffLamp air = new ProtobuffLamp(true, ProtobuffLamp.StatusLamp.TURNED_OFF);

    }
}


