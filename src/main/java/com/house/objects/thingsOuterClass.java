// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: livingroom.proto

package com.house.objects;

public final class thingsOuterClass {
  private thingsOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_intelligentHouse_Info_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_intelligentHouse_Info_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_intelligentHouse_Lamp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_intelligentHouse_Lamp_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_intelligentHouse_AirConditioning_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_intelligentHouse_AirConditioning_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_intelligentHouse_Windows_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_intelligentHouse_Windows_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_intelligentHouse_User_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_intelligentHouse_User_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_intelligentHouse_TempSensor_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_intelligentHouse_TempSensor_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020livingroom.proto\022\020intelligentHouse\".\n\004" +
      "Info\022\014\n\004name\030\001 \001(\t\022\n\n\002ip\030\002 \001(\t\022\014\n\004port\030\003" +
      " \001(\t\"\213\001\n\004Lamp\022\014\n\004name\030\001 \001(\t\022\014\n\004turn\030\002 \001(" +
      "\010\022-\n\006status\030\003 \001(\0162\035.intelligentHouse.Lam" +
      "p.Status\"8\n\006Status\022\r\n\tTURNED_ON\020\000\022\016\n\nTUR" +
      "NED_OFF\020\001\022\017\n\013MALFUNCTION\020\003\"\276\001\n\017AirCondit" +
      "ioning\022\014\n\004name\030\001 \001(\t\022\014\n\004turn\030\002 \001(\010\0228\n\006st" +
      "atus\030\003 \001(\0162(.intelligentHouse.AirConditi" +
      "oning.Status\022\033\n\023setting_temperature\030\004 \001(" +
      "\005\"8\n\006Status\022\r\n\tTURNED_ON\020\000\022\016\n\nTURNED_OFF" +
      "\020\001\022\017\n\013MALFUNCTION\020\003\"\212\001\n\007Windows\022\014\n\004name\030" +
      "\001 \001(\t\022\014\n\004turn\030\002 \001(\010\0220\n\006status\030\003 \001(\0162 .in" +
      "telligentHouse.Windows.Status\"1\n\006Status\022" +
      "\n\n\006OPENED\020\000\022\n\n\006CLOSED\020\001\022\017\n\013MALFUNCTION\020\003" +
      "\"\027\n\004User\022\017\n\007command\030\001 \001(\t\"!\n\nTempSensor\022" +
      "\023\n\013temperature\030\001 \001(\005B\'\n\021com.house.object" +
      "sB\020thingsOuterClassP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_intelligentHouse_Info_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_intelligentHouse_Info_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_intelligentHouse_Info_descriptor,
        new java.lang.String[] { "Name", "Ip", "Port", });
    internal_static_intelligentHouse_Lamp_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_intelligentHouse_Lamp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_intelligentHouse_Lamp_descriptor,
        new java.lang.String[] { "Name", "Turn", "Status", });
    internal_static_intelligentHouse_AirConditioning_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_intelligentHouse_AirConditioning_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_intelligentHouse_AirConditioning_descriptor,
        new java.lang.String[] { "Name", "Turn", "Status", "SettingTemperature", });
    internal_static_intelligentHouse_Windows_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_intelligentHouse_Windows_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_intelligentHouse_Windows_descriptor,
        new java.lang.String[] { "Name", "Turn", "Status", });
    internal_static_intelligentHouse_User_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_intelligentHouse_User_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_intelligentHouse_User_descriptor,
        new java.lang.String[] { "Command", });
    internal_static_intelligentHouse_TempSensor_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_intelligentHouse_TempSensor_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_intelligentHouse_TempSensor_descriptor,
        new java.lang.String[] { "Temperature", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
