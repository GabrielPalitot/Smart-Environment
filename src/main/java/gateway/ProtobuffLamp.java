package gateway;

import com.google.protobuf.CodedOutputStream;
import com.house.objects.Info;
import com.house.objects.Lamp;
import java.io.IOException;
import java.net.*;

import static utilities.MulticastUtils.smartReconnect;


public class ProtobuffLamp {


    public enum StatusLamp {
        TURNED_ON,
        TURNED_OFF,
        MALFUNCTION,
    }
    private boolean connected;
    private StatusLamp status;

    public ProtobuffLamp() {
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

    public static void main(String[] args) throws IOException, InterruptedException {
        int portTCP = 10000;
        int portMultiCast = 15000;
        String lampMulticast = "228.0.0.8";

        boolean connected = false;

        ProtobuffLamp Lamp = new ProtobuffLamp();

        Info lampCond = Info.newBuilder()
                .setName("Lamp")
                .setIp("127.0.0.1")
                .setPort("10000")
                .build();

        while (!connected){
            try {
                // open the connection
                Socket socketLamp = new Socket("localhost", portTCP);

                // Send Identification
                CodedOutputStream outLamp = CodedOutputStream.newInstance(socketLamp.getOutputStream());
                outLamp.writeMessageNoTag(lampCond);
                outLamp.flush();

                connected = true;
                socketLamp.close();

                //keep the process alive
                while(true)
                {

                }


            } catch (IOException e) {
                e.printStackTrace();
                smartReconnect(portMultiCast,lampMulticast);
            }

        }
    }
}


