package utilities;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.*;

import java.io.IOException;

public class    ProtoUtils {
    public static Info receiveMessageProtoInfo(CodedInputStream in) throws IOException {
        int size = in.readRawVarint32();
        int oldLimit = in.pushLimit(size);
        Info inf = Info.parseFrom(in);
        in.popLimit(oldLimit);
        return inf;
    }
    public static void sendMessageProtoInfo(CodedOutputStream out, Info cond) throws IOException {
        out.writeMessageNoTag(cond);
        out.flush();
    }

    public static User receiveMessageProtoUser(CodedInputStream in) throws IOException{
        int length = in.readRawVarint32();
        byte[] bytes = in.readRawBytes(length);
        User msgReceived = User.parseFrom(bytes);

        return msgReceived;
    }

    public static void sendMessageProtoUser(CodedOutputStream out, User cond) throws IOException{
        byte[] bytes = cond.toByteArray();
        out.writeByteArrayNoTag(bytes);
        out.flush();
    }

    public static Lamp receiveMessageProtoLamp(CodedInputStream in) throws IOException{
        int size = in.readRawVarint32();
        int oldLimit = in.pushLimit(size);
        Lamp lamps = Lamp.parseFrom(in);
        in.popLimit(oldLimit);
        return lamps;
    }

    public static void sendMessageProtoLamp(CodedOutputStream out, Lamp cond) throws IOException{
        out.writeMessageNoTag(cond);
        out.flush();
    }

    public static Windows receiveMessageProtoWindow(CodedInputStream in) throws IOException{
        int size = in.readRawVarint32();
        int oldLimit = in.pushLimit(size);
        Windows windows = Windows.parseFrom(in);
        in.popLimit(oldLimit);
        return windows;
    }

    public static void sendMessageProtoWindow(CodedOutputStream out, Windows cond) throws IOException{
        out.writeMessageNoTag(cond);
        out.flush();
    }

    public static AirConditioning receiveMessageProtoAirConditioning(CodedInputStream in) throws IOException{
        int size = in.readRawVarint32();
        int oldLimit = in.pushLimit(size);
        AirConditioning AirCond = AirConditioning.parseFrom(in);
        in.popLimit(oldLimit);
        return AirCond;
    }

    public static void sendMessageProtoAirConditioning(CodedOutputStream out, AirConditioning cond) throws IOException{
        out.writeMessageNoTag(cond);
        out.flush();
    }

}
