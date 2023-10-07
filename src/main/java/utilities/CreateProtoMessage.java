package utilities;

import com.house.objects.AirConditioning;
import com.house.objects.Lamp;
import com.house.objects.User;
import com.house.objects.Windows;

public class CreateProtoMessage {
    public static User createUserMessage(String command){
        User ackSendLamp = User.newBuilder()
                .setCommand(command)
                .build();
        return ackSendLamp;
    }
    public static Lamp modifyLampMessage(Lamp.Status command){
        Lamp lampModified = Lamp.newBuilder()
                .setName("Lamp")
                .setTurn(true)
                .setStatus(command)
                .build();
        return lampModified;
    }

    public static Windows modifyWindowMessage(Windows.Status command){
        Windows windowModified = Windows.newBuilder()
                .setName("Window")
                .setTurn(true)
                .setStatus(command)
                .build();
        return windowModified;
    }


    public static AirConditioning modifyAirMessage(String command) {
        String[] parts = command.split(",", 2);
        String part1 = parts[0];
        String part2 = parts[1];
        int temperature = Integer.parseInt(part2);
        System.out.println(temperature);
        AirConditioning.Status status;

        if (part1.equals("TURNED_ON")) {
            status = AirConditioning.Status.TURNED_ON;
        }else {
            status = AirConditioning.Status.TURNED_OFF;
        }
            AirConditioning airModified = AirConditioning.newBuilder()
                    .setName("AirConditioning")
                    .setTurn(true)
                    .setStatus(status)
                    .setSettingTemperature(temperature)
                    .build();
        return airModified;
    }
}
