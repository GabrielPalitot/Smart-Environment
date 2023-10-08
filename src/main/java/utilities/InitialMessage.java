package utilities;

import gateway.HashMapUnique;

public class InitialMessage {
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
