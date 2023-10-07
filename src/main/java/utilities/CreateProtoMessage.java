package utilities;

import com.house.objects.Lamp;
import com.house.objects.User;

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
}
