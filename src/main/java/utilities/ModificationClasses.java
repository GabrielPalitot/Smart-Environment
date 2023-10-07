package utilities;

import gateway.ProtobuffAirConditioning;
import gateway.ProtobuffLamp;
import com.house.objects.*;
import gateway.ProtobuffWindows;

public class ModificationClasses {
    public static void attLamp(ProtobuffLamp lampOb,Lamp msgCond ){
        lampOb.setTurn(msgCond.getTurn());
        lampOb.setStatus(msgCond.getStatus());
    }
    public static void attWindow(ProtobuffWindows windowOb, Windows msgCond ){
        windowOb.setTurn(msgCond.getTurn());
        windowOb.setStatus(msgCond.getStatus());
    }
    public static void attAirConditioning(ProtobuffAirConditioning airOb, AirConditioning msgCond ){
        airOb.setTurn(msgCond.getTurn());
        airOb.setStatus(msgCond.getStatus());
    }

}
