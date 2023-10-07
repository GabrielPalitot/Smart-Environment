package gateway;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.*;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static utilities.CreateProtoMessage.*;
import static utilities.MulticastUtils.*;
import static utilities.ProtoUtils.*;

public class SimpleProtobufTCP_UDPServer{

    public static void main(String[] args) throws IOException, InterruptedException {
        int portTCPLamp = 10000;
        int portTCPAir = 11000;
        int portTCPWindow = 12000;
        int portUDP = 20000;
        int portMultiCast = 15000;

        HashMapUnique map = HashMapUnique.getInstance();
        HashMapUnique2 map2 = HashMapUnique2.getInstance();
        BlockingQueue<String> fifoLamp = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> fifoAir = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> fifoWindow = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> fifoUser = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> fifoCommun = new ArrayBlockingQueue<>(1);



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

                // Receiving the information message from Lamp
                Info inf = receiveMessageProtoInfo(inServer);
                System.out.println(inf.toString());


                // add in hashmap online services
                map.addInMap(inf.getName(),(int) Thread.currentThread().getId());

                // sending acknowledgment
                User acknowLamp = createUserMessage("acknow");
                sendMessageProtoUser(outServer,acknowLamp);



                while(true)
                {
                    //System.out.println("Reiniciou");
                    Lamp lamp = receiveMessageProtoLamp(inServer);
                    //clear the fifo for update the lamp status
                    fifoLamp.clear();
                    fifoLamp.put(lamp.getStatus().toString());

                    String modifiedLamp = fifoCommun.poll();
                    //System.out.println("MODIFICADA LAMPADA: "+modifiedLamp);
                    if (modifiedLamp == null){
                        User modifyNot = createUserMessage("not");
                        sendMessageProtoUser(outServer,modifyNot);
                        //System.out.println("VOU MANDAR O NOT");
                    }
                    else {
                        if (modifiedLamp.equals("TURNED_OFF")) {
                            //System.out.println("entrei no OFF");
                            User modifyYes = createUserMessage("mod");
                            sendMessageProtoUser(outServer,modifyYes);

                            User acknowReceiveLamp = receiveMessageProtoUser(inServer);

                            Lamp newLamp = modifyLampMessage(Lamp.Status.TURNED_OFF);
                            sendMessageProtoLamp(outServer, newLamp);
                        }
                        else if (modifiedLamp.equals("TURNED_ON")){
                            //System.out.println("Entrei no ON");
                            User modifyYes = createUserMessage("mod");
                            sendMessageProtoUser(outServer,modifyYes);

                            User acknowReceiveLamp = receiveMessageProtoUser(inServer);

                            Lamp newLamp = modifyLampMessage(Lamp.Status.TURNED_ON);
                            sendMessageProtoLamp(outServer, newLamp);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        Thread threadTCPAir = new Thread(() -> {
            try {
                ServerSocket airServerSocket = new ServerSocket(portTCPAir);
                Socket smartSocketAir = airServerSocket.accept();

                System.out.println("The port " + portTCPAir + " was open for TCP Connection");

                CodedInputStream inServer = CodedInputStream.newInstance(smartSocketAir.getInputStream());
                CodedOutputStream outServer = CodedOutputStream.newInstance(smartSocketAir.getOutputStream());

                // Receiving the information message from Lamp
                Info inf = receiveMessageProtoInfo(inServer);
                System.out.println(inf.toString());


                // add in hashmap online services
                map.addInMap(inf.getName(),(int) Thread.currentThread().getId());

                // sending acknowledgment
                User acknowWindow = createUserMessage("acknow");
                sendMessageProtoUser(outServer,acknowWindow);



                while(true)
                {
                    //System.out.println("Reiniciou");
                    AirConditioning airConditioning = receiveMessageProtoAirConditioning(inServer);
                    //clear the fifo for update the lamp status
                    fifoAir.clear();
                    fifoAir.put(airConditioning.getStatus().toString()+","+String.valueOf(airConditioning.getSettingTemperature()));

                    String modifiedAir = fifoCommun.poll();
                    //System.out.println("MODIFICADA LAMPADA: "+modifiedLamp);
                    if (modifiedAir == null){
                        User modifyNot = createUserMessage("not");
                        sendMessageProtoUser(outServer,modifyNot);
                        //System.out.println("VOU MANDAR O NOT");
                    }
                    else {
                        if (modifiedAir.split(",")[0].equals("TURNED_OFF")){
                            //System.out.println("entrei no OFF");
                            User modifyYes = createUserMessage("mod");
                            sendMessageProtoUser(outServer,modifyYes);

                            User acknowReceiveWindow = receiveMessageProtoUser(inServer);
                            String mod = "TURNED_OFF,"+modifiedAir.split(",")[1];
                            AirConditioning newAir = modifyAirMessage(mod);
                            sendMessageProtoAirConditioning(outServer,newAir);
                        }
                        else if (modifiedAir.split(",")[0].equals("TURNED_ON")){
                            //System.out.println("Entrei no ON");
                            User modifyYes = createUserMessage("mod");
                            sendMessageProtoUser(outServer,modifyYes);

                            User acknowReceiveWindow = receiveMessageProtoUser(inServer);
                            String mod = "TURNED_ON,"+modifiedAir.split(",")[1];;

                            AirConditioning newAir = modifyAirMessage(mod);
                            sendMessageProtoAirConditioning(outServer, newAir);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        Thread threadTCPWindow = new Thread(() -> {
            try {
                ServerSocket windowServerSocket = new ServerSocket(portTCPWindow);
                Socket smartSocketLamp = windowServerSocket.accept();

                System.out.println("The port " + portTCPLamp + " was open for TCP Connection");

                CodedInputStream inServer = CodedInputStream.newInstance(smartSocketLamp.getInputStream());
                CodedOutputStream outServer = CodedOutputStream.newInstance(smartSocketLamp.getOutputStream());

                // Receiving the information message from Lamp
                Info inf = receiveMessageProtoInfo(inServer);
                System.out.println(inf.toString());


                // add in hashmap online services
                map.addInMap(inf.getName(),(int) Thread.currentThread().getId());

                // sending acknowledgment
                User acknowWindow = createUserMessage("acknow");
                sendMessageProtoUser(outServer,acknowWindow);



                while(true)
                {
                    //System.out.println("Reiniciou");
                    Windows window = receiveMessageProtoWindow(inServer);
                    //clear the fifo for update the lamp status
                    fifoWindow.clear();
                    fifoWindow.put(window.getStatus().toString());

                    String modifiedWindow = fifoCommun.poll();
                    //System.out.println("MODIFICADA LAMPADA: "+modifiedLamp);
                    if (modifiedWindow == null){
                        User modifyNot = createUserMessage("not");
                        sendMessageProtoUser(outServer,modifyNot);
                        //System.out.println("VOU MANDAR O NOT");
                    }
                    else {
                        if (modifiedWindow.equals("CLOSED")) {
                            //System.out.println("entrei no OFF");
                            User modifyYes = createUserMessage("mod");
                            sendMessageProtoUser(outServer,modifyYes);

                            User acknowReceiveWindow = receiveMessageProtoUser(inServer);

                            Windows newWindow = modifyWindowMessage(Windows.Status.CLOSED);
                            sendMessageProtoWindow(outServer, newWindow);
                        }
                        else if (modifiedWindow.equals("OPENED")){
                            //System.out.println("Entrei no ON");
                            User modifyYes = createUserMessage("mod");
                            sendMessageProtoUser(outServer,modifyYes);

                            User acknowReceiveWindow = receiveMessageProtoUser(inServer);

                            Windows newWindow = modifyWindowMessage(Windows.Status.OPENED);
                            sendMessageProtoWindow(outServer, newWindow);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        threadTCPWindow.start();
        threadTCPLamp.start();
        threadTCPAir.start();

        boolean exit = false;
        while(!exit) {
            String initialMessage = "Selecione o Servico Desejado:\n" +
                    "1.Lampadas" + (map.getFromMap("Lamp") != null ? "-Online\n" : "-Offline\n")
                    + "2.ArCondicionado" + (map.getFromMap("AirConditioning") != null ? "-Online\n" : "-Offline\n")
                    + "3.Janela" + (map.getFromMap("Window") != null ? "-Online\n" : "-Offline\n")
                    + "4.Sensor" + (map.getFromMap("sensor") != null ? "-Online\n" : "-Offline\n")
                    + "5.Sair da Aplicação\nSua Resposta: ";

            Scanner scanner = new Scanner(System.in);
            System.out.println(initialMessage);
            String readFromKeyboard = scanner.nextLine();


            switch (readFromKeyboard) {
                case "1":
                    String informationLamp = fifoLamp.poll();
                    if (informationLamp == null){
                        System.out.println("Lampada Indisponível no Momento");
                        break;
                    }
                    else if (informationLamp.equals("TURNED_ON"))
                    {
                        System.out.println("Status da Lâmpada: \n" + informationLamp);
                        System.out.println("Deseja Desligar? Pressione 1, se deseja sair pressione 2");
                        readFromKeyboard = "";
                        readFromKeyboard = scanner.nextLine();

                        if (readFromKeyboard.equals("1")){
                            System.out.println("Alterar pra OFF\n");
                            fifoCommun.put("TURNED_OFF");
                        }
                        break;
                    }
                    else if (informationLamp.equals("TURNED_OFF"))
                    {
                        System.out.println("Status da Lâmpada: \n" + informationLamp);
                        System.out.println("Deseja Ligar? Pressione 1, se deseja sair pressione 2");
                        readFromKeyboard = "";
                        readFromKeyboard = scanner.nextLine();
                        //System.out.println("entrou aqui");

                        if (readFromKeyboard.equals("1")){
                            System.out.println("Alterar pra ON\n");
                            fifoCommun.put("TURNED_ON");
                        }
                        break;
                    }
                case "2":
                    String informationAir = fifoAir.poll();
                    if (informationAir == null){
                        System.out.println("Ar-Condicionado Indisponível no Momento");
                        break;
                    }
                    else if (informationAir.split(",")[0].equals("TURNED_ON"))
                    {
                        System.out.println("Status do Ar-Condicionado: " + informationAir.split(",")[0] + " Temperatura: " +informationAir.split(",")[1]);
                        System.out.println("\nPressione:\n 1 - Desigar\n 2 -Alterar a temperatura\n 3 - Sair");
                        readFromKeyboard = "";
                        readFromKeyboard = scanner.nextLine();

                        if (readFromKeyboard.equals("1")){
                            System.out.println("Alterar pra OFF\n");
                            fifoCommun.put("TURNED_OFF,"+informationAir.split(",")[1]);
                        }else if(readFromKeyboard.equals("2"))
                        {
                            System.out.println("\nDigite a tempertura entre 16-28ºC :\n");
                            readFromKeyboard = "";
                            readFromKeyboard = scanner.nextLine();
                            fifoCommun.put(informationAir.split(",")[0]+","+readFromKeyboard);

                        }
                        break;
                    }
                    else if (informationAir.split(",")[0].equals("TURNED_OFF"))
                    {
                        System.out.println("Status do Ar-Condicionado: \n" + informationAir);
                        System.out.println("Deseja Ligar? Pressione 1, se deseja sair pressione 2");
                        readFromKeyboard = "";
                        readFromKeyboard = scanner.nextLine();
                        //System.out.println("entrou aqui");

                        if (readFromKeyboard.equals("1")){
                            System.out.println("Alterar pra ON\n");
                            fifoCommun.put("TURNED_ON,"+informationAir.split(",")[1]);
                        }
                        break;
                    }

                case "3":
                    String informationWindow = fifoWindow.poll();
                    if (informationWindow == null){
                        System.out.println("Janela Indisponível no Momento");
                        break;
                    }
                    else if (informationWindow.equals("OPENED"))
                    {
                        System.out.println("Status da Janela: \n" + informationWindow);
                        System.out.println("Deseja Fechar? Pressione 1, se deseja sair pressione 2");
                        readFromKeyboard = "";
                        readFromKeyboard = scanner.nextLine();

                        if (readFromKeyboard.equals("1")){
                            System.out.println("Alterar pra CLOSED\n");
                            fifoCommun.put("CLOSED");
                        }
                        break;
                    }
                    else if (informationWindow.equals("CLOSED"))
                    {
                        System.out.println("Status da Janela: \n" + informationWindow);
                        System.out.println("Deseja Abrir? Pressione 1, se deseja sair pressione 2");
                        readFromKeyboard = "";
                        readFromKeyboard = scanner.nextLine();
                        //System.out.println("entrou aqui");

                        if (readFromKeyboard.equals("1")){
                            System.out.println("Alterar pra OPENED\n");
                            fifoCommun.put("OPENED");
                        }
                        break;
                    }




                    /*
                case "2":
                    System.out.println("Status do Ar-Condicionado: " + map2.getFromMap("AirConditioning"));
                    System.out.println("Deseja " + (map2.getFromMap("AirConditioning").equals("TURNED_ON") ? "Desligar?\n Pressione 1 senão 2\n" : "Ligar? Pressione 1 senão 2\n"));
                    System.out.println("Deseja " + (map2.getFromMap("AirConditioning").equals("TURNED_ON") ? "Desligar?\n Pressione 1 senão 2\n" : "Ligar? Pressione 1 senão 2\n"));
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                /*

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
    }
}

