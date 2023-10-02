package gateway;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SensorUDP extends Thread {
    private static DatagramSocket socket;
    private static InetAddress address;

    private static byte[] buf ;

    public SensorUDP() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public static void main(String argv[]) throws SocketException, UnknownHostException {

        SensorUDP sensor = new SensorUDP();
        while(true)
        {

            String msg = "27.0\0";
            buf = msg.getBytes();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            //scheduler.schedule(() -> {
                DatagramPacket packet = new DatagramPacket(buf, buf.length, sensor.address, 4445);
               try {
                    sensor.socket.send(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
              //  System.out.println("Task executed!");
            //}, 2, TimeUnit.SECONDS); // Delay of 2 seconds

            //packet = new DatagramPacket(buf, buf.length);
            //socket.receive(packet);
            //String received = new String(packet.getData(), 0, packet.getLength());
        }
    }

    public void close() {
        socket.close();
    }
}
