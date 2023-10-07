package gateway;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.Info;
import com.house.objects.Lamp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import static utilities.MulticastUtils.*;
import static utilities.ProtoUtils.receiveMessageProtoInfo;
import static utilities.ProtoUtils.receiveMessageProtoLamp;

public class SimpleProtobufTCP_UDPServer{

    public static void main(String[] args) throws IOException, InterruptedException {
        int portTCPLamp = 10000;
        int portTCPAir = 11000;
        int portTCPWindow = 12000;
        int portUDP = 20000;
        int portMultiCast = 15000;

        HashMapUnique map = HashMapUnique.getInstance();
        HashMapUnique2 map2 = HashMapUnique2.getInstance();

        String hostMultiCast = "228.0.0.8";

        // Send Multicast Message
        smartDiscovery(portMultiCast,hostMultiCast);

        // TCP Connections
        Thread threadTCPLamp = new Thread(() -> {
            try {
                ServerSocket lampServerSocket = new ServerSocket(portTCPLamp);
                Socket smartSocketLamp = lampServerSocket.accept();

                System.out.println("The port " + portTCPLamp + " was open for TCP Connection");

                CodedInputStream inServer = CodedInputStream.newInstance(smartSocketLamp.getInputStream());
                CodedOutputStream outServer = CodedOutputStream.newInstance(smartSocketLamp.getOutputStream());

                Info inf = receiveMessageProtoInfo(inServer);
                System.out.println(inf.toString());
                // add in HashMap the online services
                map.addInMap(inf.getName(),(int) Thread.currentThread().getId());

                while(true)
                {
                    Lamp lamp = receiveMessageProtoLamp(inServer);
                    map2.addInMap(lamp.getName(),lamp.getStatus().toString());


                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread threadTCPAirConditioning = new Thread(() ->{
           try{
               ServerSocket airServerSocket = new ServerSocket(portTCPAir);
               Socket smartSocketAir = airServerSocket.accept();
               System.out.println("The port " + portTCPAir + " was open for TCP Connection");

               CodedInputStream inServer = CodedInputStream.newInstance(smartSocketAir.getInputStream());
               CodedOutputStream outServer = CodedOutputStream.newInstance(smartSocketAir.getOutputStream());

               Info inf = receiveMessageProtoInfo(inServer);
               System.out.println(inf.toString());
               // add in HashMap the online services
               map.addInMap(inf.getName(),(int) Thread.currentThread().getId());

               System.out.println("Client " +smartSocketAir.getInetAddress().getHostAddress() + " connected via TCP.");
               System.out.println("Hello");
           }catch (Exception e){
               e.printStackTrace();
           }
        });

        Thread threadTCPWindow = new Thread(() ->{
            try{
                ServerSocket windowServerSocket = new ServerSocket(portTCPWindow);
                Socket smartSocketWindow = windowServerSocket.accept();
                System.out.println("The port " + portTCPWindow + " was open for TCP Connection");

                CodedInputStream inServer = CodedInputStream.newInstance(smartSocketWindow.getInputStream());
                CodedOutputStream outServer = CodedOutputStream.newInstance(smartSocketWindow.getOutputStream());

                Info inf = receiveMessageProtoInfo(inServer);
                System.out.println(inf.toString());
                // add in HashMap the online services
                map.addInMap(inf.getName(),(int) Thread.currentThread().getId());

            }catch (Exception e){
                e.printStackTrace();
            }
        });
        threadTCPLamp.start();
        threadTCPWindow.start();
        threadTCPAirConditioning.start();


        boolean exit = false;
        while(!exit) {
            String initialMessage = "Selecione o Servico Desejado:\n" +
                    "1.Lampadas" + (map.getFromMap("Lamp") != null ? "-Online\n" : "-Offline\n")
                    + "2.ArCondicionado" + (map.getFromMap("AirConditioning") != null ? "-Online\n" : "-Offline\n")
                    + "3.Janela" + (map.getFromMap("window") != null ? "-Online\n" : "-Offline\n")
                    + "4.Sensor" + (map.getFromMap("sensor") != null ? "-Online\n" : "-Offline\n")
                    + "5.Sair da Aplicação";

            Scanner scanner = new Scanner(System.in);
            System.out.println(initialMessage);
            String readFromKeyboard = scanner.nextLine();


            switch (readFromKeyboard) {
                case "1":
                    System.out.println("Status da Lâmpada: " + map2.getFromMap("Lamp"));
                    System.out.println("Deseja " + (map2.getFromMap("Lamp").equals("TURNED_ON") ? "Desligar? Pressione 1 senão 2\n" : "Ligar? Pressione 1 senão 2\n"));
                    readFromKeyboard = scanner.nextLine();
                    if (readFromKeyboard.equalsIgnoreCase("1")) {
                        //todo modification

                    }
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;


            }
        }


        /*
        // UDP Connections
        Thread threadUDP = new Thread(() ->{
            DatagramSocket UdpSocket = null;
            try {
                UdpSocket = new DatagramSocket(portUDP);
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Servidor is Online");
            byte[] bufS = new byte[100];
            while (true) {
                DatagramPacket inputReceive = new DatagramPacket(bufS, bufS.length);
                try {
                    UdpSocket.receive(inputReceive);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String textReceive = new String(inputReceive.getData());
                System.out.println(textReceive);
            }
        });

            */
    }
}

