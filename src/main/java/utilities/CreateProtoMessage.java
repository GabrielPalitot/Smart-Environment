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


    public static AirConditioning modifyAirMessage(AirConditioning.Status command){
        AirConditioning airModified = AirConditioning.newBuilder()
                .setName("AirConditioning")
                .setTurn(true)
                .setStatus(command)
                .build();
        return airModified;
    }
}
