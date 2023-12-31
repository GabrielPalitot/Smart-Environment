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
import static utilities.InitialMessage.initialMessageGateway;
import static utilities.MulticastUtils.*;
import static utilities.ProtoUtils.*;

public class ProtobuffGatewayTCP_UDP {

    public static void main(String[] args) throws IOException, InterruptedException {
        /**
         * Declaring the gateway macros, such as ports to use and data structures
         */
        int portTCPLamp = 10000;
        int portTCPAir = 11000;
        int portTCPWindow = 12000;
        int portUDP = 20000;
        int portMultiCast = 15000;

        // Host for use Multicast
        String hostMultiCast = "228.0.0.8";

        // Used DataStructures
        HashMapUnique map = HashMapUnique.getInstance();
        BlockingQueue<String> fifoLamp = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> fifoAir = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> fifoWindow = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> fifoSensor = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> fifoCommunLamp = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> fifoCommunWindow = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> fifoCommunAir = new ArrayBlockingQueue<>(1);


        // Sending Multicast message advising that the gateway is active.
        smartDiscovery(portMultiCast,hostMultiCast);

        /**
         * Declaring what each thread will do, separated into Lamp, Air, Window and Sensor (UDP).
         * It's always the same structure with its particularities depending on the
         * function of each smart device.
         */

        // TCP Connections Lamp
        Thread threadTCPLamp = new Thread(() -> {
            try {
                // Process of Creation of Socket.
                ServerSocket lampServerSocket = new ServerSocket(portTCPLamp);
                Socket smartSocketLamp = lampServerSocket.accept();

                System.out.println("The port " + portTCPLamp + " was open for TCP Connection");

                // Declaring the handlers of input and output.
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


                initialMessageGateway(map);

                while(true)
                {
                    Lamp lamp = receiveMessageProtoLamp(inServer);
                    //clear the fifo for update the lamp status
                    fifoLamp.clear();
                    fifoLamp.put(lamp.getStatus().toString());

                    // trying capture a mod information.
                    String modifiedLamp = fifoCommunLamp.poll();
                    if (modifiedLamp == null){
                        User modifyNot = createUserMessage("not");
                        sendMessageProtoUser(outServer,modifyNot);
                    }
                    else {
                        if (modifiedLamp.equals("TURNED_OFF")) {
                            User modifyYes = createUserMessage("mod");
                            sendMessageProtoUser(outServer,modifyYes);

                            User acknowReceiveLamp = receiveMessageProtoUser(inServer);

                            Lamp newLamp = modifyLampMessage(Lamp.Status.TURNED_OFF);
                            sendMessageProtoLamp(outServer, newLamp);
                        }
                        else if (modifiedLamp.equals("TURNED_ON")){
                            User modifyYes = createUserMessage("mod");
                            sendMessageProtoUser(outServer,modifyYes);

                            User acknowReceiveLamp = receiveMessageProtoUser(inServer);

                            Lamp newLamp = modifyLampMessage(Lamp.Status.TURNED_ON);
                            sendMessageProtoLamp(outServer, newLamp);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Lampada foi Desconectada");
                map.removeInMap("Lamp");
                initialMessageGateway(map);
            }
        });

        // TCP Connections AirConditioning
        Thread threadTCPAir = new Thread(() -> {
            try {
                // Process of Creation of Socket.
                ServerSocket airServerSocket = new ServerSocket(portTCPAir);
                Socket smartSocketAir = airServerSocket.accept();

                System.out.println("The port " + portTCPAir + " was open for TCP Connection");

                // Declaring the handlers of input and output.
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

                initialMessageGateway(map);

                while(true)
                {
                    AirConditioning airConditioning = receiveMessageProtoAirConditioning(inServer);
                    //clear the fifo for update the air status
                    fifoAir.clear();
                    fifoAir.put(airConditioning.getStatus().toString()+","+String.valueOf(airConditioning.getSettingTemperature()));

                    // trying capture a mod information.
                    String modifiedAir = fifoCommunAir.poll();
                    if (modifiedAir == null){
                        User modifyNot = createUserMessage("not");
                        sendMessageProtoUser(outServer,modifyNot);
                    }
                    else {
                        if (modifiedAir.split(",")[0].equals("TURNED_OFF")){
                            User modifyYes = createUserMessage("mod");
                            sendMessageProtoUser(outServer,modifyYes);

                            User acknowReceiveWindow = receiveMessageProtoUser(inServer);
                            String mod = "TURNED_OFF,"+modifiedAir.split(",")[1];
                            AirConditioning newAir = modifyAirMessage(mod);
                            sendMessageProtoAirConditioning(outServer,newAir);
                        }
                        else if (modifiedAir.split(",")[0].equals("TURNED_ON")){
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
                System.out.println("Ar condicionado foi Desconectado");
                map.removeInMap("AirConditioning");
                initialMessageGateway(map);
            }
        });

        // TCP Connections Window
        Thread threadTCPWindow = new Thread(() -> {
            try {
                // Process of Creation of Socket.// Process of Creation of Socket.
                ServerSocket windowServerSocket = new ServerSocket(portTCPWindow);
                Socket smartSocketLamp = windowServerSocket.accept();

                System.out.println("The port " + portTCPWindow + " was open for TCP Connection");

                // Declaring the handlers of input and output.
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

                initialMessageGateway(map);

                while(true)
                {
                    Windows window = receiveMessageProtoWindow(inServer);
                    //clear the fifo for update the lamp status
                    fifoWindow.clear();
                    fifoWindow.put(window.getStatus().toString());

                    // trying capture a mod information.
                    String modifiedWindow = fifoCommunWindow.poll();
                    if (modifiedWindow == null){
                        User modifyNot = createUserMessage("not");
                        sendMessageProtoUser(outServer,modifyNot);
                    }
                    else {
                        if (modifiedWindow.equals("CLOSED")) {
                            User modifyYes = createUserMessage("mod");
                            sendMessageProtoUser(outServer,modifyYes);

                            User acknowReceiveWindow = receiveMessageProtoUser(inServer);

                            Windows newWindow = modifyWindowMessage(Windows.Status.CLOSED);
                            sendMessageProtoWindow(outServer, newWindow);
                        }
                        else if (modifiedWindow.equals("OPENED")){
                            User modifyYes = createUserMessage("mod");
                            sendMessageProtoUser(outServer,modifyYes);

                            User acknowReceiveWindow = receiveMessageProtoUser(inServer);

                            Windows newWindow = modifyWindowMessage(Windows.Status.OPENED);
                            sendMessageProtoWindow(outServer, newWindow);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Janela foi Desconectada");
                map.removeInMap("Window");
                initialMessageGateway(map);
            }
        });



        // UDP connection with the sensor.
        Runnable sensorTask = () -> {
            try {

                // Enabling communication via UDP
                DatagramSocket socket = new DatagramSocket(portUDP);


                while (true) {
                    // buffer of data
                    byte[] receiveData = new byte[1024];

                    // declaring the packet
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                    // Receive the UDP packet
                    socket.receive(receivePacket);

                    // Extract the received message and its length
                    String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                    // Print the received message
                    if (message != null){
                        map.addInMap("sensor",(int) Thread.currentThread().getId());
                        fifoSensor.clear();
                        fifoSensor.put(message);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        /**
         * Starting the threads with the command thread.start()
         */
        // Create a thread and start it to run the UDP server code
        Thread sensorThread = new Thread(sensorTask);
        sensorThread.start();

        // Starting the threads of Gateway
        threadTCPWindow.start();
        threadTCPLamp.start();
        threadTCPAir.start();

        /**
         * Main loop, in which it will handle the requests that will be made by the user.
         * case 1 is for the Lamp.
         * case 2 is for the Air.
         * case 3 is for the Window.
         * case 4 is for the Sensor.
         * case 5 is for the exit.
         */
        boolean exit = false;
        while(!exit) {
            // declaring a scanner for read from keyboard
            Scanner scanner = new Scanner(System.in);
            initialMessageGateway(map);
            String readFromKeyboard = scanner.nextLine();

            switch (readFromKeyboard) {
                case "1":
                    // Looking the queue for some information about Lamp
                    String informationLamp = fifoLamp.peek();
                    if (informationLamp == null || map.getFromMap("Lamp") == null){
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
                            fifoCommunLamp.put("TURNED_OFF");

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
                            fifoCommunLamp.put("TURNED_ON");
                        }
                        break;
                    }
                case "2":
                    // Looking the queue for some information about Air
                    String informationAir = fifoAir.peek();
                    if (informationAir == null || map.getFromMap("AirConditioning") == null){
                        System.out.println("Ar-Condicionado Indisponível no Momento");
                        break;
                    }
                    else if (informationAir.split(",")[0].equals("TURNED_ON"))
                    {
                        System.out.println("Status do Ar-Condicionado: " + informationAir.split(",")[0] + " Temperatura: " +informationAir.split(",")[1]);
                        System.out.println("\nPressione:\n 1 - Desligar\n 2 -Alterar a temperatura\n 3 - Sair");
                        readFromKeyboard = "";
                        readFromKeyboard = scanner.nextLine();

                        if (readFromKeyboard.equals("1")){
                            System.out.println("Alterar pra OFF\n");
                            fifoCommunAir.put("TURNED_OFF,"+informationAir.split(",")[1]);
                        }else if(readFromKeyboard.equals("2"))
                        {
                            System.out.println("\nDigite a tempertura entre 16-28ºC :\n");
                            readFromKeyboard = "";
                            readFromKeyboard = scanner.nextLine();
                            fifoCommunAir.put(informationAir.split(",")[0]+","+readFromKeyboard);

                        }
                        break;
                    }
                    else if (informationAir.split(",")[0].equals("TURNED_OFF"))
                    {
                        System.out.println("Status do Ar-Condicionado: \n" + informationAir.split(",")[0]);
                        System.out.println("Deseja Ligar? Pressione 1, se deseja sair pressione 2");
                        readFromKeyboard = "";
                        readFromKeyboard = scanner.nextLine();

                        if (readFromKeyboard.equals("1")){
                            System.out.println("Alterar pra ON\n");
                            fifoCommunAir.put("TURNED_ON,"+informationAir.split(",")[1]);
                        }
                        break;
                    }

                case "3":
                    // Looking the queue for some information about Window
                    String informationWindow = fifoWindow.peek();
                    if (informationWindow == null || map.getFromMap("Window") == null){
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
                            fifoCommunWindow.put("CLOSED");
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
                            fifoCommunWindow.put("OPENED");
                        }
                        break;
                    }

                case "4":
                    // Information of sensor.
                    System.out.println("Temperatura da Sala: "+fifoSensor.peek()+"ºC\n");
                    break;
                case "5":
                    exit = true;


            }
        }
        /**
         * leaving the gateway. Finalizing the Process
         */
        // exit the process closing the threads.
        System.exit(0);
    }
}

