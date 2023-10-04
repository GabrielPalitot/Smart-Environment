package gateway;

import com.house.objects.Windows;
import java.io.IOException;
import java.net.*;


public class ProtobuffWindows {
    public enum StatusWindows {
        OPEN,
        CLOSED,
        MALFUNCTION,
    }
    private boolean connected;
    private StatusWindows status;

    public ProtobuffWindows(boolean connected, StatusWindows status) {
        this.connected = connected;
    }
    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public StatusWindows getStatus() {
        return status;
    }

    public void setStatus(StatusWindows status) {
        this.status = status;
    }

    public static void main(String[] args) {
        ProtobuffWindows air = new ProtobuffWindows(true, ProtobuffWindows.StatusWindows.CLOSED);

    }
}


