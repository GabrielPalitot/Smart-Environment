package utilities;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.house.objects.*;

import java.io.IOException;

public class ProtoUtils {
    /**
     * Message of receive via protobuff for Info class available in .proto
     * @param in Handler of Input Stream
     * @return Returns the message from the protobuff's Info class that can be transformed to String to
     *          see its contents
     * @throws IOException
     */
    public static Info receiveMessageProtoInfo(CodedInputStream in) throws IOException {
        int size = in.readRawVarint32();
        int oldLimit = in.pushLimit(size);
        Info inf = Info.parseFrom(in);
        in.popLimit(oldLimit);
        return inf;
    }

    /**
     * Prepare to send typical Info Class message via protobuff
     * @param out Handler of Output Stream
     * @param cond Info Class Formatted Message
     * @throws IOException
     */
    public static void sendMessageProtoInfo(CodedOutputStream out, Info cond) throws IOException {
        out.writeMessageNoTag(cond);
        out.flush();
    }

    /**
     * Message of receive via protobuff for User class available in .proto
     * @param in Handler of Input Stream
     * @return Returns the message from the protobuff's User class that can be transformed to String to
     *                see its contents
     * @throws IOException
     */
    public static User receiveMessageProtoUser(CodedInputStream in) throws IOException{
        int length = in.readRawVarint32();
        byte[] bytes = in.readRawBytes(length);
        User msgReceived = User.parseFrom(bytes);

        return msgReceived;
    }

    /**
     * Prepare to send typical User Class message via protobuff
     * @param out Handler of Output Stream
     * @param cond User Class Formatted Message
     * @throws IOException
     */
    public static void sendMessageProtoUser(CodedOutputStream out, User cond) throws IOException{
        byte[] bytes = cond.toByteArray();
        out.writeByteArrayNoTag(bytes);
        out.flush();
    }

    /**
     *  Message of receive via protobuff for Lamp class available in .proto
     * @param in Handler of Input Stream
     * @return Returns the message from the protobuff's Lamp class that can be transformed to String to
     *                   see its contents
     * @throws IOException
     */
    public static Lamp receiveMessageProtoLamp(CodedInputStream in) throws IOException{
        int size = in.readRawVarint32();
        int oldLimit = in.pushLimit(size);
        Lamp lamps = Lamp.parseFrom(in);
        in.popLimit(oldLimit);
        return lamps;
    }

    /**
     * Prepare to send typical Lamp Class message via protobuff
     * @param out Handler of Output Stream
     * @param cond Lamp Class Formatted Message
     * @throws IOException
     */
    public static void sendMessageProtoLamp(CodedOutputStream out, Lamp cond) throws IOException{
        out.writeMessageNoTag(cond);
        out.flush();
    }

    /**
     * Message of receive via protobuff for Window class available in .proto
     * @param in Handler of Input Stream
     * @return Returns the message from the protobuff's Window class that can be transformed to String to
     *                         see its contents
     * @throws IOException
     */
    public static Windows receiveMessageProtoWindow(CodedInputStream in) throws IOException{
        int size = in.readRawVarint32();
        int oldLimit = in.pushLimit(size);
        Windows windows = Windows.parseFrom(in);
        in.popLimit(oldLimit);
        return windows;
    }

    /**
     * Prepare to send typical Window Class message via protobuff
     * @param out Handler of Output Stream
     * @param cond Window Class Formatted Message
     * @throws IOException
     */
    public static void sendMessageProtoWindow(CodedOutputStream out, Windows cond) throws IOException{
        out.writeMessageNoTag(cond);
        out.flush();
    }

    /**
     * Message of receive via protobuff for AirConditioning class available in .proto
     * @param in Handler of Input Stream
     * @return Returns the message from the protobuff's AirConditioning class that can be transformed to String to
     *                               see its contents
     * @throws IOException
     */
    public static AirConditioning receiveMessageProtoAirConditioning(CodedInputStream in) throws IOException{
        int size = in.readRawVarint32();
        int oldLimit = in.pushLimit(size);
        AirConditioning AirCond = AirConditioning.parseFrom(in);
        in.popLimit(oldLimit);
        return AirCond;
    }

    /**
     * Prepare to send typical AirConditioning Class message via protobuff
     * @param out Handler of Output Stream
     * @param cond AirConditioning Class Formatted Message
     * @throws IOException
     */
    public static void sendMessageProtoAirConditioning(CodedOutputStream out, AirConditioning cond) throws IOException{
        out.writeMessageNoTag(cond);
        out.flush();
    }

}
