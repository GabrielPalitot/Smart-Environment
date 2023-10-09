package utilities;

import gateway.ProtobuffAirConditioning;
import gateway.ProtobuffLamp;
import com.house.objects.*;
import gateway.ProtobuffWindows;


public class ModificationClasses {
    /**
     * method for updating the status of the instantiated lamp object in the
     * Smart Object class Lamp
     * @param lampOb The Lamp Object to Be Modified
     * @param msgCond The message sent via protobuff that has the modification
     */
    public static void attLamp(ProtobuffLamp lampOb,Lamp msgCond ){
        lampOb.setStatus(msgCond.getStatus());
    }

    /**
     * method for updating the status of the instantiated lamp object in the
     *  Smart Object class Window
     * @param windowOb The Window Object to Be Modified
     * @param msgCond The message sent via protobuff that has the modification
     */
    public static void attWindow(ProtobuffWindows windowOb, Windows msgCond ){
        windowOb.setStatus(msgCond.getStatus());
    }

    /**
     * method for updating the status and temperature of the instantiated Air-Conditioning object in the
     * @param airOb The Air-Conditioning Object to Be Modified
     * @param msgCond The message sent via protobuff that has the modification
     */
    public static void attAirConditioning(ProtobuffAirConditioning airOb, AirConditioning msgCond ){
        airOb.setStatus(msgCond.getStatus());
        airOb.setTemperature(msgCond.getSettingTemperature());
    }

}
