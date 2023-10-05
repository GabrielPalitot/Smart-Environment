package gateway;

import com.google.protobuf.CodedOutputStream;
import com.house.objects.Info;
import com.house.objects.Windows;
import java.io.IOException;
import java.net.*;

import static utilities.MulticastUtils.smartReconnect;


public class ProtobuffWindows {
    public enum StatusWindows {
        OPEN,
        CLOSED,
        MALFUNCTION,
    }
    private boolean connected;
    private StatusWindows status;

    public ProtobuffWindows() {
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

    public static void main(String[] args) throws IOException, InterruptedException {
        int portTCP = 10000;
        int portMultiCast = 15000;
        String windowsMulticast = "228.0.0.8";

        boolean connected = false;

        ProtobuffWindows window = new ProtobuffWindows();

        Info windowCond = Info.newBuilder()
                .setName("window")
                .setIp("127.0.0.1")
                .setPort("10000")
                .build();

        while (!connected){
            try {
                // open the connection
                Socket socketWindow = new Socket("localhost", portTCP);

                // Send Identification
                CodedOutputStream outWindow = CodedOutputStream.newInstance(socketWindow.getOutputStream());
                outWindow.writeMessageNoTag(windowCond);
                outWindow.flush();

                connected = true;
                socketWindow.close();

            } catch (IOException e) {
                e.printStackTrace();
                smartReconnect(portMultiCast,windowsMulticast);
            }
        }
    }
}


