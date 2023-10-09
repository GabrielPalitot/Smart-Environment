package gateway;

import java.io.IOException;
import java.net.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SensorUDP{
    public static void main(String[] args) {
        /**
         * Declaring the ports to use
         */
        int portUDP = 20000;
        while (true) {
            try {
                DatagramSocket socket = new DatagramSocket();
                Thread.sleep(5000);

                // the message
                byte[] buf = new byte[100];

                //random number generator for the sensor
                Random rand = new Random();
                double randomNumber = ((rand.nextDouble()-0.5)*0.5)+20;

                String message = String.format("%.2f",randomNumber);
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


