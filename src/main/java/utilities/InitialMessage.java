package utilities;

import gateway.HashMapUnique;

public class InitialMessage {
    /**
     * Displays the initial message to the user and checks which services are online
     * @param map A HashMap of the HashMapUnique class, which is a singleton that will be used to
     *      check if the service is online. The service is online if it has been added to HashMap
     *
     */
    public static void initialMessageGateway(HashMapUnique map){
        String initialMessage = "Selecione o Servico Desejado:\n" +
                "1.Lampadas" + (map.getFromMap("Lamp") != null ? "-Online\n" : "-Offline\n")
                + "2.ArCondicionado" + (map.getFromMap("AirConditioning") != null ? "-Online\n" : "-Offline\n")
                + "3.Janela" + (map.getFromMap("Window") != null ? "-Online\n" : "-Offline\n")
                + "4.Sensor" + (map.getFromMap("sensor") != null ? "-Online\n" : "-Offline\n")
                + "5.Sair da Aplicação\nSua Resposta: ";
        System.out.println(initialMessage);
    }
}
