// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: livingroom.proto

package com.house.objects;

public interface LampOrBuilder extends
    // @@protoc_insertion_point(interface_extends:intelligentHouse.Lamp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>.intelligentHouse.Lamp.Status status = 2;</code>
   * @return The enum numeric value on the wire for status.
   */
  int getStatusValue();
  /**
   * <code>.intelligentHouse.Lamp.Status status = 2;</code>
   * @return The status.
   */
  com.house.objects.Lamp.Status getStatus();
}
