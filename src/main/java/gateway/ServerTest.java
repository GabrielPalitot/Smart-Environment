package gateway;
import java.io.*;
import java.net.*;

import com.google.protobuf.CodedInputStream;
import com.house.objects.AirConditioningInfo;
import com.house.objects.AirConditioning;
public class ServerTest {
    public static void main(String argv[]) {
        ServerSocket listenSocket;
        try {
            listenSocket = new ServerSocket(10000);
            while (true) {
                Socket connectionSocket = listenSocket.accept();
                CodedInputStream in = CodedInputStream.newInstance(connectionSocket.getInputStream());
                int size = in.readInt32();
                AirConditioningInfo airInfo = AirConditioningInfo.parseFrom(in);
                System.out.println(airInfo.toString());
                System.out.println(size);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
