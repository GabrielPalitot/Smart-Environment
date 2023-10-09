package utilities;

import com.house.objects.AirConditioning;
import com.house.objects.Lamp;
import com.house.objects.User;
import com.house.objects.Windows;

public class CreateProtoMessage {
    /**
     * Create a message in the format of the Protobuff, this message will be to send simple commands
     * @param command Command to be sent
     * @return Returns the message formed to be sent via protobuff
     */
    public static User createUserMessage(String command){
        User commandToSend = User.newBuilder()
                .setCommand(command)
                .build();
        return commandToSend;
    }

    /**
     * Method for informing the modification to be made to the Lamp smart equipment
     * @param command The status that should be changed
     * @return Returns the message formed to be sent via protobuff
     */
    public static Lamp modifyLampMessage(Lamp.Status command){
        Lamp lampModified = Lamp.newBuilder()
                .setName("Lamp")
                .setStatus(command)
                .build();
        return lampModified;
    }

    /**
     * Method for informing the modification to be made to the Window smart equipment
     * @param command The status that should be changed
     * @return Returns the message formed to be sent via protobuff
     */
    public static Windows modifyWindowMessage(Windows.Status command){
        Windows windowModified = Windows.newBuilder()
                .setName("Window")
                .setStatus(command)
                .build();
        return windowModified;
    }

    /**
     * Method for informing the modification to be made to the AirConditioning smart equipment
     * You can change the temperature and status, but if you don't want to change the temperature,
     * the value is repeated with what was already there.
     * @param command The status along with the temperature separated by comma
     * @return Returns the message formed to be sent via protobuff
     */
    public static AirConditioning modifyAirMessage(String command) {
        String[] parts = command.split(",", 2);
        String part1 = parts[0];
        String part2 = parts[1];
        int temperature = Integer.parseInt(part2);
        AirConditioning.Status status;

        if (part1.equals("TURNED_ON")) {
            status = AirConditioning.Status.TURNED_ON;
        }else {
            status = AirConditioning.Status.TURNED_OFF;
        }
            AirConditioning airModified = AirConditioning.newBuilder()
                    .setName("AirConditioning")
                    .setStatus(status)
                    .setSettingTemperature(temperature)
                    .build();
        return airModified;
    }
}
