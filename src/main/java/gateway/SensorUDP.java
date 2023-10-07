package gateway;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
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
        }
    }
    public void close() {
        socket.close();
    }
}
*/

public class SensorUDP{
    public static void main(String[] args) {
        int portUDP = 20000;
        while (true) {
            try {
                DatagramSocket socket = new DatagramSocket();
                Thread.sleep(5000);

                // the message
                byte[] buf = new byte[100];
                String message = "27.0\0";
                buf = message.getBytes();

                // the ip and port
                InetAddress ip = InetAddress.getByName("127.0.0.1");


                // Preparation to send
                DatagramPacket OutputSend = new DatagramPacket(buf,buf.length,ip,portUDP);
                socket.send(OutputSend);
            }catch (IOException e){
                System.out.println("Server Offline, The connection will try be reestablished in 5 seconds ");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


