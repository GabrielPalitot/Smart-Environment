package utilities;

import gateway.ProtobuffAirConditioning;
import gateway.ProtobuffLamp;
import com.house.objects.*;
import gateway.ProtobuffWindows;

public class ModificationClasses {
    public static void attLamp(ProtobuffLamp lampOb,Lamp msgCond ){
        lampOb.setStatus(msgCond.getStatus());
    }
    public static void attWindow(ProtobuffWindows windowOb, Windows msgCond ){
        windowOb.setStatus(msgCond.getStatus());
    }
    public static void attAirConditioning(ProtobuffAirConditioning airOb, AirConditioning msgCond ){
        airOb.setStatus(msgCond.getStatus());
        //System.out.println(msgCond.getSettingTemperature());
        airOb.setTemperature(msgCond.getSettingTemperature());
    }

}
